import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.net.URL;
import java.io.*;
/**
 * This is Account that stores the values from the Profile class as well as an icon.
 *
 * @author William Wang
 * @version 2.1
 */
public class Account extends Profile
{
    // instance variables
    private ImageIcon icon;
    private int width;

    /**
     * Constructor for objects of class Account
     */
    public Account()
    {
        // initialise instance variables
        super();
        // use the default image
        this.icon = autoIcon();
        width = 150;
        testWidth();
    }

    public Account(String website, String username, String password)
    {
        // initialise instance variables according to specified data
        super(website, username, password);
        this.icon = autoIcon();
        width = 150;
        testWidth();
    }

    public ImageIcon autoIcon()
    {
        // split website value to get website name
        String[] arr = this.getWebsite().split("[.]");
        // set location of image to website name
        for(int i = 0; i < arr.length; i++)
        {
            String location = "Icons/" + arr[i] + ".png";
            // if image is valid, else use default icon
            icon = new ImageIcon(location);
            if(icon.getIconWidth() < 0 || icon.getIconHeight() < 0)
            {
                // if no icon exists, use the default image
            }
            else
            {
                // if icon exists, scale and return the icon
                icon = scaleImage(icon);
                return icon;
            }
        }
        // no personalized image exists, scale default icon and return
        icon = scaleImage(new ImageIcon("Icons/account.png"));
        return icon;
    }


    public ImageIcon scaleImage(ImageIcon icon)
    {
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon ic = new ImageIcon(newimg);  // transform it back
        return ic;
    }

    public void editAccount(String website, String username, String password)
    {
        super.editAccount(website, username, password);
        // change image;
        this.icon = autoIcon();
        // test for change in width from editing
        testWidth();
    }

    public void setIcon(ImageIcon icon)
    {
        // sets the icon
        this.icon = icon;
    }

    public ImageIcon getIcon()
    {
        // returns the icon
        return this.icon;
    }

    // test for whether the width of the panel has changed
    public void testWidth()
    {
        JLabel[] array = new JLabel[3];
        int w = 0;
        for(int i = 0; i < array.length; i++)
        {
            // display in order webite, username and password
            array[i] = new JLabel(HEADERS[i] + this.getAcc(i));
            w =(int)array[i].getPreferredSize().getWidth();
            // greater than previous width
            if(w > width)
            {
                // change width to greatest value
                this.width = w;
            }
        }
    }

    public int getWidth()
    {
        // return the width
        return width;
    }
}
