import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.net.URL;
import java.io.*;
/**
 * Write a description of class Account here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Account extends Profile
{
    // instance variables - replace the example below with your own
    private ImageIcon icon;

    /**
     * Constructor for objects of class Account
     */
    public Account()
    {
        // initialise instance variables
        super();
        // use the default image
        icon = new ImageIcon("Icons/account.png");
        icon = scaleImage(icon);
    }

    public Account(String website, String username, String password)
    {
        super(website, username, password);
        this.icon = autoIcon();
    }
    
    public ImageIcon autoIcon()
    {
        // get website name
        String[] sp = this.getWebsite().split("[.]c");
        // set location of image to website name
        String location = "Icons/" + sp[0] + ".png";
        // if image is valid else use default icon
        icon = ifImageExists(new ImageIcon(location));
        // scale icon to gui size
        icon = scaleImage(icon);
        return icon;
    }
    
    public ImageIcon ifImageExists(ImageIcon icon)
    {
        if(icon.getIconWidth() < 0 || icon.getIconHeight() < 0)
        {
            // use the default image
            return new ImageIcon("Icons/account.png");
        }
        else
        {
            return icon;
        }
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
    }


    

    public void setIcon(ImageIcon icon)
    {
        this.icon = icon;
    }

    public ImageIcon getIcon()
    {
        return this.icon;
    }

    
}
