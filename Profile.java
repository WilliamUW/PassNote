
/**
 * Write a description of class Profile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Profile
{
    // instance variables - replace the example below with your own
    private String username;
    private String password;
    private String website;

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
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getUsername()
    {
        // return this username
        return this.username;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getPassword()
    {
        // return this username
        return this.password;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getWebsite()
    {
        // return this username
        return this.website;
    }

    public String toString()
    {
        return website + ", " + username + ", " + password;
    }

    public String toPrint()
    {
        String n = System.getProperty("line.separator");
        String print = "Website: " + website + n + "Username: " + username + n + "Password: " + password;
        return print;
    }

    public void display()
    {
        // return this username
        //Display display = new Display;
    }

    public void editAccount(String website, String username, String password)
    {
        this.website = website;
        this.username = username;
        this.password = password;
    }

    public String getAcc(int index)
    {
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
        return website;
    }

}
