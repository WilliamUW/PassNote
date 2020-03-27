import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.net.URL;
import java.io.*;
import javax.swing.border.*;
/**
 * User of the Password Manager application
 *
 * @author William Wang
 * @version (a version number or a date)
 */
public class User
{
    // instance variables for the User class
    private String name;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private Account lastRemoved;
    private int lastRemovedIndex;
    private Stack<Account> lastAccountRemoved = new Stack<Account>();
    private Stack<Integer> lastRemovedAccountIndex = new Stack<Integer>();
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
        this.name = "";
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
     * @return  this user's name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Add an account with the specified values
     *
     * @param  website  username    password
     */
    public void addAccount(String website, String username, String password)
    {
        // adds an account with the specified values
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
        String[][] sampleInfo = 
            {
                {"facebook.com","drive.google.com","instagram.com","gmail.com","outlook.com","twitter.com","dropbox.com","microsoft.com"},
                {"gmail","dropbox","microsoft","facebook.com","drive.google.com","instagram.com","gmail.com","dropbox"},
                {"drive.google","instagram","gmail","dropbox","microsoft","facebook.com","drive.google","instagram.com"}
            };
        for(int i = 0; i < sampleInfo[0].length; i++)
        {
            addAccount(sampleInfo[0][i],sampleInfo[1][i],sampleInfo[2][i]);
        }
    }

    public Account getAccount(int index)
    {
        return accounts.get(index);
    }

    public void removeAccount(int index)
    {
        // save then remove an account according to its index
        saveUndo(accounts.get(index));
        accounts.remove(index);
    }

    public void removeAccount(Account account)
    {
        // save then remove the specified account
        saveUndo(account);
        accounts.remove(account);
    }

    public ArrayList<Account> getAccounts()
    {
        // return arraylist of all accounts for this user
        return accounts;
    }

    public void saveUndo(Account account)
    {
        // save account and its index to stack
        lastAccountRemoved.push(account);
        lastRemovedAccountIndex.push(accounts.indexOf(account));
    }

    public boolean undo()
    {
        if(!lastAccountRemoved.isEmpty())
        {
            // last removed account exists 
            accounts.add(lastRemovedAccountIndex.pop(), lastAccountRemoved.pop());
            lastRemovedIndex = -1 ;
            lastRemoved = null;
            return true;
        }
        else
        {
            // no previous account deleted
            return false;
        }
    }

    public void sortByWebsite()
    {
        // convert ArrayList into List of accounts
        List<Account> accountsList = accounts;

        // sort with specified Comparator
        Collections.sort(accountsList, new Comparator<Account>()
            {
                public int compare(Account o1, Account o2)
                {
                    // compare between two accounts' website lexicographically
                    return o1.getWebsite().compareTo(o2.getWebsite());
                }
            });
        // reset ArrayList of user accounts 
        accounts = new ArrayList<Account>();
        // insert the alphabetically sorted accounts in order
        for(int i =0; i < accountsList.size(); i++)
            accounts.add(accountsList.get(i));
    }

    public void sortByUsername()
    {
        List<Account> accountsList = accounts;

        Collections.sort(accountsList, new Comparator<Account>(){

                public int compare(Account o1, Account o2)
                {
                    return o1.getUsername().compareTo(o2.getUsername());
                }
            });

        accounts = new ArrayList<Account>();
        for(int i =0; i < accountsList.size(); i++)
            accounts.add(accountsList.get(i));

    }

    public void sortByPassword()
    {
        List<Account> accountsList = accounts;

        Collections.sort(accountsList, new Comparator<Account>(){

                public int compare(Account o1, Account o2)
                {
                    return o1.getPassword().compareTo(o2.getPassword());
                }
            });

        accounts = new ArrayList<Account>();
        for(int i =0; i < accountsList.size(); i++)
            accounts.add(accountsList.get(i));

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
