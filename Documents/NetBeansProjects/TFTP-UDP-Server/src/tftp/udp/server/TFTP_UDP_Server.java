package tftp.udp.server;

import java.net.DatagramSocket;

/**
 *
 * @author ak581
 */
public class TFTP_UDP_Server extends Thread {
    
    private static final String mode = "octet";
    private static final String SERVER_IP = "";
    private static final int PORT_NUM = 1025;
    
    private static final byte OP_RRQ = 1;
    private static final byte OP_WRQ = 2;
    private static final byte OP_DATA = 3;
    private static final byte OP_ACK = 4;
    private static final byte OP_ERR = 5;
    
    private DatagramSocket socket = null;
    
    public static void main(String[] args) {
        
    }
}
