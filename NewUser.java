import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.net.URL;
import java.io.*;
import javax.swing.border.*;
/**
 * Write a description of class NewUser here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NewUser implements ActionListener
{
    // instance variables - replace the example below with your own
    private JFrame frame;
    private JTextField nameTextField;
    private JLabel welcome, nameLabel, img;
    private JPanel panel;
    private JButton start;
    private Interface i;
    private Color c = new Color(73, 63, 100);

    /**
     * Constructor for objects of class NewUser
     */
    public NewUser()
    {
        // initialise instance variables
        frame = new JFrame("Welcome");
        frame.getContentPane().setLayout(new FlowLayout());
        panel = new JPanel(new GridLayout(2,1));
        img = new JLabel();
        // if image is valid
        ImageIcon icon = new ImageIcon("Icons/newUserLogin.png");

        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(700, 700,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back
        img.setIcon(icon);

        frame.add(img);
        frame.getContentPane().setBackground(c);

        nameTextField = new JTextField("");
        nameTextField.setFont(new Font("Serif", Font.PLAIN, 20));
        nameTextField.addActionListener(this);

        welcome = new JLabel("Welcome!");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setFont(new Font("Serif", Font.PLAIN, 20));

        start = new JButton("Start!");
        start.setFont(new Font("Serif", Font.PLAIN, 40));
        start.addActionListener(this);

        panel.add(welcome);
        panel.add(start);

        start.setPreferredSize(new Dimension(200, 100));

        frame.add(panel);
        frame.setPreferredSize(new Dimension(1100, 800));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
        //...Get information from the action event...
        //...Display it in the text area...

        if(e.getSource() == start)
        {
            JFrame f = new JFrame();
            String name = "";
            boolean isDone = false;
            frame.setVisible(false);
            while (!isDone)
            {
                name=JOptionPane.showInputDialog(f,"Enter Name");  
                if(name == null)
                {
                    f.dispose();
                    frame.setVisible(true);
                    isDone = true;
                }
                else if(name.equals(""))
                {
                    name = "Unnamed User";
                    JOptionPane.showMessageDialog(frame,
                        "A name has not been entered. You can change your name in the Settings option.");
                    isDone = true;
                    JOptionPane.showMessageDialog(frame,
                        name + ", Welcome to Passnote!");
                    i = new Interface(name);
                }
                else if(name != null)
                {
                    isDone = true;
                    JOptionPane.showMessageDialog(frame,
                        name + ", Welcome to Passnote!");
                    i = new Interface(name);
                }
            }

        }
    }

}
