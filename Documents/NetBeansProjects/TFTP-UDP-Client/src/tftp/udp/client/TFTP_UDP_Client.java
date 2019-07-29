package tftp.udp.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import sun.misc.IOUtils;

/**
 *
 * @author ak581
 */
public class TFTP_UDP_Client {
    
    private static final String mode = "octet";
    private static final String SERVER_IP = "";
    private final int PORT_NUM = 1025;
    
    private static final byte OP_RRQ = 1;
    private static final byte OP_WRQ = 2;
    private static final byte OP_DATA = 3;
    private static final byte OP_ACK = 4;
    private static final byte OP_ERR = 5;
    
    private DatagramSocket socket = null;
    private DatagramPacket out;
    private DatagramPacket in;
    
    private InetAddress address = null;
    
    private byte[] requestSequence;
    private byte[] bufferSequence;
    
    private String filename;
    
    private TFTP_UDP_Client() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName(SERVER_IP);
    }
    
    private void createRequest(byte opcode, String filename) {
        this.filename = filename;
        int request_length = 2 + filename.length() + 1 + mode.length() + 1;
        requestSequence = new byte[request_length];
        requestSequence[0] = (byte) 0;
        requestSequence[1] = opcode;
        
        for(int i = 0; i < filename.length(); i++) {
            requestSequence[i + 2] = (byte) filename.charAt(i);
        }
        
        requestSequence[2 + filename.length()] = (byte) 0;
        
        for(int i = 0; i < mode.length(); i++) {
            requestSequence[i + 2 + filename.length() + 1] = (byte) mode.charAt(i);
        }
        
        requestSequence[2 + filename.length() + 1 + mode.length()] = (byte) 0;
    }
    
    private void sendRequest() throws IOException {
        out = new DatagramPacket(requestSequence, requestSequence.length, address, PORT_NUM);
        socket.send(out);
    }
    
    private void handleInput() throws IOException {
        ByteArrayOutputStream toFile = new ByteArrayOutputStream();
        int block = 1;
        do {
            System.out.println("TFTP Packet count: " + block);
            block++;
            DatagramPacket previous = in;
            socket.receive(in);
            if(in.getData()[1] == OP_ACK) {
                sendData();
            } else if(in.getData()[1] == OP_ERR) {
                errorReport();
            } else if(in.getData()[1] == OP_DATA) {
                if(!previous.equals(in)) {
                    DataOutputStream dataOutput = new DataOutputStream(toFile);
                    dataOutput.write(in.getData(), 4, in.getLength() - 4);
                    sendAck(in.getData()[2], in.getData()[3]);
                } else {
                    sendAck(in.getData()[2], in.getData()[3]);
                }
            }
        } while(in.getData().length >= 516);
        
        if(toFile.size() > 0) {
            OutputStream outputStream = new FileOutputStream(filename);
            toFile.writeTo(outputStream);
        }
    }
    
    private void sendAck(byte blockNum_l, byte blockNum_r) throws IOException {
        byte[] ack = new byte[] {0, blockNum_l, blockNum_r};
        DatagramPacket ack_packet = new DatagramPacket(ack, ack.length, address, in.getPort());
        socket.send(ack_packet);
    }
    
    private void sendData() throws FileNotFoundException, IOException {
        File file = new File("file.txt");
        byte[] fromFile = new byte[(int) file.length()];
        InputStream fileStream = new FileInputStream(file);
        fileStream.read(fromFile);
        //DatagramPacket toSend = new DatagramPacket();
    }
    
    private void errorReport() {
        String errorCode = new String(bufferSequence, 3, 1);
        String errorText = new String(bufferSequence, 4, in.getLength() - 4);
        System.err.println("Error: " + errorCode + "\n       " + errorText);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
