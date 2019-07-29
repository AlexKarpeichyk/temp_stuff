package gui_pac;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ak581
 */
public class GUI_1 extends JFrame 
{
    JPanel panel = new JPanel();
    JPanel innerPanel = new JPanel();
    JButton button = new JButton("A long ass label");
    JButton button1 = new JButton("S");
    JLabel checkBoxLabel = new JLabel("Yes?");
    JCheckBox checkBox = new JCheckBox();
    //private JFrame frame = new JFrame("My First Window");
    
    public GUI_1()
    {
        super("Another Demo");
        createWindow();
    }
    
    private void createWindow() 
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());
        panel.add(button, BorderLayout.WEST);
        panel.add(button1, BorderLayout.EAST);
        panel.add(checkBoxLabel, BorderLayout.NORTH);
        panel.add(checkBox, BorderLayout.CENTER);
        for(int i = 0; i < 5; i++) {
            innerPanel.add(new JLabel("Label" + i));
        }
        panel.add(innerPanel, BorderLayout.PAGE_END);
        this.getContentPane().add(panel);
        panel.setBorder(new BevelBorder(BevelBorder.RAISED));
        this.pack();
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        GUI_1 gui = new GUI_1();
    }
}
