import java.util.*;
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
 * Write a description of class User here.
 *
 * @author William Wang
 * @version (a version number or a date)
 */
public class User
{
    // instance variables - replace the example below with your own
    private String name;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private Account lastRemoved;
    private int lastRemovedIndex;
    private String password;
    private JFrame frame;
    private JTextField inputTextField;
    private JLabel inputLabel;
    private JPanel panel;
    private JButton start;
    private String in = "";
    private String display = "";

    /**
     * Constructor for objects of class User
     */
    public User()
    {
        // initialise instance variables
        this.name = "unnamed user";
        this.password = "";
    }

    /**
     * Constructor for objects of class User
     */
    public User(String name)
    {
        // initialise instance variables
        this.name = name;
        this.password = "";
    }

    /**
     * Return this user's name
     *
     * @return    this user's name
     */
    public String getName()
    {
        // put your code here
        return this.name;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void addAccount(String website, String username, String password)
    {
        // put your code here
        Account account = new Account(website, username, password);
        accounts.add(account);
    }

    public void changePassword(String password)
    {
        this.password = password;
    }

    public void changeName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String accountToString(Account account)
    {
        String u = account.getUsername();
        String p = account.getPassword();
        String w = account.getWebsite();
        String display = "Website: " + w + " | Username: " + u + " | Password: " + p;
        return display;
    }

    public int getNumberOfAccounts()
    {
        return accounts.size();
    }

    public int getNum()
    {
        return accounts.size();
    }

    public void printToString()
    {
        System.out.println("Name: " + name);
        System.out.println("Number of Accounts: " + getNumberOfAccounts());
        for(int i = 0; i < accounts.size(); i++)
        {
            System.out.println(accountToString(accounts.get(i)));
        }
    }

    public void initializeTestUser()
    {
        String[] websites =  {"facebook.com","drive.google.com","instagram.com","gmail.com","dropbox","microsoft","facebook","drive.google","instagram","gmail","dropbox","microsoft"};
        for(int i = 0; i < websites.length; i++)
        {
            addAccount(websites[i],"username","password");
        }
    }

    public Account getAccount(int index)
    {
        return accounts.get(index);
    }

    public void removeAccount(int index)
    {
        saveUndo(accounts.get(index));
        accounts.remove(index);
    }

    public void removeAccount(Account account)
    {
        saveUndo(account);
        accounts.remove(account);
    }

    public void saveUndo(Account account)
    {
        lastRemoved = account;
        lastRemovedIndex = accounts.indexOf(account);
    }

    public ArrayList<Account> getAccounts()
    {
        return accounts;
    }

    public boolean undo()
    {
        if(lastRemoved != null)
        {
            accounts.add(lastRemovedIndex, lastRemoved);
            lastRemovedIndex = -1 ;
            lastRemoved = null;
            return true;
        }
        else
        {
            return false;
        }
    }



    public boolean isVisible()
    {
        if(frame.isVisible() == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
