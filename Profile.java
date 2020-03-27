
/**
 * This a profile that knows its website, username and password.
 *
 * @author William Wang
 * @version 2.0
 */
public class Profile
{
        final String[] HEADERS = {"Website: ","Username: ","Password: "};
    // instance variables
    private String website;
    private String username;
    private String password;
    
    /**
     * Constructor for objects of class Profile
     */
    public Profile()
    {
        // initialise instance variables
        username = "";
        password = "";
        website = "";
    }

    public Profile(String website, String username, String password)
    {
        // initialise instance variables
        this.username = username;
        this.password = password;
        this.website = website;
    }
    
    public String getWebsite()
    {
        // return this account's website
        return this.website;
    }
    
    public String getUsername()
    {
        // return this account's username
        return this.username;
    }

    public String getPassword()
    {
        // return this account's password
        return this.password;
    }

    public String toString()
    {
        // converts the account information into a string
        return website + ", " + username + ", " + password;
    }

    public String toPrint()
    {
        // converts the account information to a printable format
        String n = System.getProperty("line.separator");
        String print = "Website: " + website + n + "Username: " + username + n + "Password: " + password + n + n;
        return print;
    }

    public void editAccount(String website, String username, String password)
    {
        // edit the account information to the modified values
        this.website = website;
        this.username = username;
        this.password = password;
    }

    public String getAcc(int index)
    {
        // return the specified value of the account according to the index provided.
        switch(index)
        {
            case 0:
            return website;

            case 1:
            return username;

            case 2:
            return password;

            default:
            break;
        }
        // return the website value by default
        return website;
    }

}
