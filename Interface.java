import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.net.URL;
import java.io.*;
import javax.swing.border.*;
import java.util.concurrent.TimeUnit;

/**
 * Write a description of class Interface here.
 *
 * @author William Wang
 * @version (a version number or a date)
 */
public class Interface implements ActionListener
{
    // constants
    final String[] HEADERS = {"Website: ","Username: ","Password: "};
    final String FRAME_NAME = "Passnote: Your Very Own Offline Password Manager";
    final Color BACKGROUND_COLOR = Color.cyan;
    final Color OPTION_BACKGROUND = Color.green;
    final Color ACCOUNT_BACKGROUND = Color.yellow;
    final String HELP_INFO = "Help";

    // instance variables - replace the example below with your own
    JFrame frame;

    User user;

    JPanel accounts;
    JPanel account;

    JFrame settings;
    JButton settingsButton;

    JPanel option;
    JLabel optionLabel;
    JButton addAccount;
    JButton add;
    JButton editAccount;
    JButton editMenuButton;
    JButton refreshButton;
    JButton changeName;
    JButton changePassword;
    JButton undoButton;
    JButton quitProgram;
    JButton sortButton;
    JButton sortByWebsiteButton;
    JButton sortByUsernameButton;
    JButton sortByPasswordButton;
    JButton helpButton;

    JTextField websiteTextField, usernameTextField, passwordTextField;
    JButton save;
    JFrame addAccountFrame;

    JFrame editAccountFrame;
    JFrame editFrame;

    boolean editResult;
    JOptionPane pane;

    JPanel editPanel;
    JButton editButton;
    JPanel editOptionPanel;
    JButton backButton;
    JButton undo;
    JButton lock;

    JButton saveFile;
    JButton readFile;

    JButton btnSave, btnOpen;
    String fileName = "Files\\sample.txt";
    String defaultPath = System.getProperty("user.dir") + File.separator + fileName;
    String lastPath = null;

    /**
     * Constructor for objects of class Display
     */
    public Interface()
    {
        user = new User();
        displayJFrame();
    }

    /**
     * Constructor for objects of class Display
     */
    public Interface(User user)
    {
        this.user = user;
        displayJFrame();
    }

    /**
     * Constructor for objects of class Display
     */
    public Interface(String name)
    {
        user = new User(name);
        displayJFrame();
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void addOptionPanel()
    {
        // get option panel
        option = new JPanel();
        option.setLayout(new BorderLayout());

        JPanel right = new JPanel(new FlowLayout());
        JPanel left = new JPanel(new FlowLayout());

        settingsButton = new JButton("Settings");
        left.add(settingsButton);
        settingsButton.addActionListener(this);

        // get add account button
        addAccount = new JButton("+");
        addAccount.addActionListener(this);

        editMenuButton = new JButton("Edit");
        editMenuButton.addActionListener(this);

        sortButton = new JButton("Sort");
        sortButton.addActionListener(this);

        optionLabel = new JLabel();
        // add option label to option panel
        option.add(optionLabel, BorderLayout.CENTER);
        // set option panel background color
        option.setBackground(OPTION_BACKGROUND);
        refreshOptionLabel();

        // get refresh button
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this);

        quitProgram = new JButton("Quit");
        quitProgram.addActionListener(this);

        right.add(quitProgram);
        right.add(refreshButton);
        right.add(editMenuButton);
        right.add(addAccount);
        right.add(sortButton);

        option.add(left, BorderLayout.WEST);
        option.add(right, BorderLayout.EAST);

        left.setBackground(OPTION_BACKGROUND);
        right.setBackground(OPTION_BACKGROUND);

        frame.add(option, BorderLayout.NORTH);
    }

    public void recordAccounts(String path)
    {
        String absolutePath = path;
        String print = "";
        // write the content in file
        try
        (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(absolutePath)))
        {
            for(int i = 0; i < user.getNumberOfAccounts(); i++)
            {
                print = user.getAccount(i).toPrint() + "\n\n";
                System.out.println(print);
                bufferedWriter.write(user.getAccount(i).toPrint());
                bufferedWriter.write("\n\n");
            }
            bufferedWriter.close();
        }
        catch(IOException e)
        {
            // exception handling
            System.out.println("Input Output Error");
        }
    }

    public void openAccounts(String path)
    {
        String absolutePath = path;
        String input = "";
        // try read the content from file
        try
        (BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutePath)))
        {
            String line = bufferedReader.readLine();
            String website = "";
            String username = "";
            String password = "";
            while(line != null)
            {
                if(line.contains("Website: "))
                {
                    String[] l = line.split("Website: ");
                    website = l[1];
                    line = bufferedReader.readLine();
                    l = line.split("Username: ");
                    username = l[1];
                    line = bufferedReader.readLine();
                    l = line.split("Password: ");
                    password = l[1];
                    user.addAccount(website, username, password);
                }
                else
                {
                    line = bufferedReader.readLine();
                }
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            // exception handling
        }
        catch(IOException e)
        {
            // exception handling
        }
        refresh();
    }

    /**
     * Display the JFrame
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void displayJFrame()
    {
        // put your code here
        frame = new JFrame(FRAME_NAME);
        // optionPanel.addOptionPanel();
        addOptionPanel();
        // display user accounts
        displayUser(user);

        packMainFrame();
    }

    public void displayUser(User user)
    {
        accounts = new JPanel();
        displayAccounts();
    }

    public void refreshOptionLabel()
    {
        String n = user.getName();
        if(n.equals("unnamed user"))
        {
            n = n + "; Please click the change name item to input a name. :)";
        }
        else
        {

        }
        optionLabel.setText("Name: " + n + " | Number Of Accounts: " + user.getNumberOfAccounts());
    }

    public void refresh()
    {
        // get option label
        refreshOptionLabel();
        packMainFrame();
    }

    public void addAccount(String website, String username, String password)
    {
        user.addAccount(website, username, password);
    }

    public int getHeight(JPanel panel)
    {
        return(int) panel.getPreferredSize().getHeight();
    }

    public JPanel getAccountPanel(Account acc)
    {
        account = new JPanel();
        account.setLayout(new FlowLayout());
        JLabel image = new JLabel(acc.getIcon(), JLabel.CENTER);
        JLabel[] array = new JLabel[3];
        JPanel text = new JPanel();
        //text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));

        account.setPreferredSize(new Dimension(150, 150));

        image.setBackground(ACCOUNT_BACKGROUND);
        account.add(image);

        for(int i = 0; i < array.length; i++)
        {
            // display in order webite, username and password
            array[i] = new JLabel(HEADERS[i] + acc.getAcc(i));
            array[i].setHorizontalAlignment(JLabel.CENTER);
            account.add(array[i]);
        }
        account.setBackground(ACCOUNT_BACKGROUND);
        account.setPreferredSize(new Dimension(acc.getWidth(), 150));
        return account;
    }

    public JLabel[] getLabels(Account acc)
    {
        JLabel image = new JLabel(acc.getIcon(), JLabel.CENTER);
        JLabel[] array = new JLabel[3];
        for(int i = 0; i < array.length; i++)
        {
            // display in order webite, username and password
            array[i] = new JLabel(HEADERS[i] + acc.getAcc(i));
            int width =(int) array[i].getPreferredSize().getWidth();
            if(width > 150)
            {
                account.setPreferredSize(new Dimension(width + 2, 150));
                image.setPreferredSize(new Dimension(width, 75));
            }
            account.add(array[i]);
        }
        return array;
    }

    public JPanel displayAccounts()
    {
        accounts = new JPanel();
        accounts.setBackground(BACKGROUND_COLOR);
        for(int i = 0; i < user.getNumberOfAccounts(); i++)
        {
            accounts.add(getAccountPanel(user.getAccount(i)));
        }
        return accounts;
    }

    public void packMainFrame()
    {
        refreshOptionLabel();
        if(user.getNumberOfAccounts() == 0)
        {
            frame.add(noAccounts());
        }
        else
        {
            frame.add(displayAccounts(), BorderLayout.CENTER);
        }

        // set up the jframe, then display it
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(1000, 800));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel noAccounts()
    {
        JPanel empty = new JPanel();
        JLabel n = new JLabel("Welcome!");
        n.setPreferredSize(new Dimension(800, 100));
        n.setHorizontalAlignment(JLabel.CENTER);
        JLabel a = new JLabel("You do not currently have any accounts stored on this user.");
        JLabel b = new JLabel("Click on the Open As menu option and select the sample.txt to add some sample accounts.");
        JLabel c = new JLabel("Click on the + or Add Account button to add your own accounts.");
        n.setFont(new Font("Serif", Font.PLAIN, 40));
        a.setFont(new Font("Serif", Font.PLAIN, 30));
        b.setFont(new Font("Serif", Font.PLAIN, 20));
        c.setFont(new Font("Serif", Font.PLAIN, 20));
        empty.add(n);
        empty.add(a);
        empty.add(b);
        empty.add(c);
        empty.setBackground(BACKGROUND_COLOR);
        return empty;
    }

    public void pack(JFrame jframe)
    {
        jframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        //...Get information from the action event...
        //...Display it in the text area...

        if(e.getSource() == addAccount || e.getSource() == add)
        {
            JTextField websiteField = new JTextField(10);
            JTextField usernameField = new JTextField(10);
            JTextField password = new JTextField(10);

            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Website:"));
            myPanel.add(websiteField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Username:"));
            myPanel.add(usernameField);
            myPanel.add(Box.createHorizontalStrut(15)); // a spacer
            myPanel.add(new JLabel("Password:"));
            myPanel.add(password);

            int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
            if(result == JOptionPane.OK_OPTION)
            {
                addAccount(websiteField.getText(), usernameField.getText(), password.getText());
                refresh();
            }
        }
        else if(e.getSource() == btnSave)
        {
            FileChooser f = new FileChooser();
            String path = f.getPath();
            if(path != "")
            {
                lastPath = path;
                recordAccounts(path);
                JOptionPane.showMessageDialog(frame, "Accounts Successfully Saved!");
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Unable to save. Please Try Again.");
            }
        }
        else if(e.getSource() == btnOpen)
        {
            FileChooser f = new FileChooser();
            String path = f.getPath();
            if(path != "")
            {
                lastPath = path;
                openAccounts(path);
                JOptionPane.showMessageDialog(frame, "Accounts Successfully Added!");
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Unable to open. Please Try Again.");
            }
        }
        else if(e.getSource() == sortButton || e.getSource() == sortByWebsiteButton)
        {
            // sort accounts by website (default sort option)
            user.sortByWebsite();
            refresh();
        }
        else if(e.getSource() == sortByUsernameButton)
        {
            user.sortByUsername();
            refresh();
        }
        else if(e.getSource() == sortByPasswordButton)
        {
            user.sortByPassword();
            refresh();
        }
        else if(e.getSource() == refreshButton)
        {
            // refresh
            refresh();
        }
        else if(e.getSource() == backButton)
        {
            // close edit frame
            editFrame.dispose();
        }
        else if(e.getSource() == lock)
        {
            // lock application with password
            if(user.getPassword().equals(""))
            {
                // no password set
                JOptionPane.showMessageDialog(frame, "Please add a password first before you lock.");
                String password = JOptionPane.showInputDialog(frame, "Set Password");
                user.changePassword(password);
                refresh();
            }
            else
            {
                settings.dispose();
                frame.setVisible(false);
                Login l = new Login();
            }
        }
        else if(e.getSource() == changePassword)
        {
            // if password is empty
            if(user.getPassword().equals(""))
            {
                JOptionPane.showMessageDialog(frame, "Please add a password first :))");
                String password = JOptionPane.showInputDialog(frame, "Set Password");
                user.changePassword(password);
                refresh();
            }
            // ask for password confirmation before changing
            else
            {
                JOptionPane.showMessageDialog(frame, "Please confirm your password first :))");
                settings.dispose();
                frame.setVisible(false);
                Login l = new Login();
                l.needPasswordChange();
            }
        }
        else if(e.getSource() == undo || e.getSource() == undoButton)
        {
            user.undo();
            refresh();
        }

        else if(e.getSource() == changeName)
        {
            String name = JOptionPane.showInputDialog(frame, "Enter Name");
            user.changeName(name);
            refresh();
        }
        else if(e.getSource() == editAccount || e.getSource() == editMenuButton)
        {
            addEditFrame();
        }
        else if(e.getSource() == helpButton)
        {
            //custom title, information icon
            JOptionPane.showMessageDialog(frame,
                HELP_INFO,
                "Helpful Information",
                JOptionPane.INFORMATION_MESSAGE);
        }
        else if(e.getSource() == settingsButton)
        {
            // create settings frame
            settings = new JFrame("Settings");
            settings.setLayout(new FlowLayout());

            JLabel settingLabel = new JLabel("\u2699 Settings");
            settingLabel.setFont(new Font("Serif", Font.BOLD, 30));

            helpButton = new JButton("Help");
            
            helpButton.addActionListener(this);

            JPanel settingsPanel = new JPanel();
            settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

            settingsPanel.add(new JLabel(" "));
            settingsPanel.add(settingLabel);
            settingsPanel.add(helpButton);
            settingsPanel.add(new JLabel(" "));
            JLabel userMenu = new JLabel("User");

            settingsPanel.add(userMenu);
            settingsPanel.add(new JLabel(" "));
            changeName = new JButton("Change Name");
            changeName.addActionListener(this);
            settingsPanel.add(changeName);

            changePassword = new JButton("Change Password");
            changePassword.addActionListener(this);
            settingsPanel.add(changePassword);

            lock = new JButton("Lock");
            lock.addActionListener(this);
            settingsPanel.add(lock);

            settingsPanel.add(new JLabel(" "));
            JLabel menu = new JLabel("Files");
            settingsPanel.add(menu);
            settingsPanel.add(new JLabel(" "));

            btnSave = new JButton("Save As");
            btnSave.addActionListener(this);
            settingsPanel.add(btnSave);

            btnOpen = new JButton("Open File");
            btnOpen.addActionListener(this);
            settingsPanel.add(btnOpen);

            settingsPanel.add(new JLabel(" "));
            JLabel accounts = new JLabel("Accounts");
            settingsPanel.add(accounts);
            settingsPanel.add(new JLabel(" "));
            // get add account button
            add = new JButton("Add Account");
            add.addActionListener(this);
            settingsPanel.add(add);

            // get edit account button
            editAccount = new JButton("Edit Accounts");
            editAccount.addActionListener(this);
            settingsPanel.add(editAccount);

            undoButton = new JButton("Undo Button");
            undoButton.addActionListener(this);

            settingsPanel.add(undoButton);

            settingsPanel.add(new JLabel(" "));
            JLabel lb = new JLabel("Sorting");
            settingsPanel.add(lb);
            settingsPanel.add(new JLabel(" "));

            sortByWebsiteButton = new JButton("By Website");
            sortByWebsiteButton.addActionListener(this);
            sortByUsernameButton = new JButton("By Username");
            sortByUsernameButton.addActionListener(this);
            sortByPasswordButton = new JButton("By Password");
            sortByPasswordButton.addActionListener(this);

            settingsPanel.add(sortByWebsiteButton);
            settingsPanel.add(sortByUsernameButton);
            settingsPanel.add(sortByPasswordButton);
            settings.add(settingsPanel);

            settingsPanel.add(new JLabel(" "));

            int result = JOptionPane.showConfirmDialog(settings, settingsPanel, "Setting Options", JOptionPane.DEFAULT_OPTION);
            if(result == JOptionPane.OK_OPTION)
            {
                refresh();
            }
        }
        else if(e.getSource() == quitProgram)
        {
            quit();
        }
    }

    public JPanel editAccountPanel(Account acc)
    {
        JPanel button = new JPanel();
        JButton edit = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        edit.addActionListener(this);
        deleteButton.addActionListener(this);
        button.setLayout(new FlowLayout());
        // display image

        JLabel i = new JLabel();
        i.setIcon(acc.getIcon());
        button.add(i);

        // display the rest of the user info

        button.add(getLabels(acc)[0]);
        button.add(getLabels(acc)[1]);
        button.add(getLabels(acc)[2]);
        JPanel b = new JPanel();

        b.add(edit);
        b.add(deleteButton);
        button.add(b);
        deleteButton.addActionListener(new ActionListener()
            {
                @Override public void actionPerformed(ActionEvent e)
                {
                    if(e.getSource() == deleteButton)
                    {
                        user.removeAccount(user.getAccounts().indexOf(acc));
                        addEditFrame();
                    }
                }
            }
        );
        edit.addActionListener(new ActionListener()
            {
                @Override public void actionPerformed(ActionEvent e)
                {
                    if(e.getSource() == edit)
                    {
                        JTextField websiteField = new JTextField(10);
                        websiteField.setText(acc.getWebsite());
                        JTextField usernameField = new JTextField(10);
                        usernameField.setText(acc.getUsername());
                        JTextField passwordField = new JTextField(10);
                        passwordField.setText(acc.getPassword());
                        JPanel myPanel = new JPanel();
                        myPanel.add(new JLabel("Website:"));
                        myPanel.add(websiteField);
                        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                        myPanel.add(new JLabel("Username:"));
                        myPanel.add(usernameField);
                        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                        myPanel.add(new JLabel("Password:"));
                        myPanel.add(passwordField);
                        int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Values", JOptionPane.OK_CANCEL_OPTION);
                        if(result == JOptionPane.OK_OPTION)
                        {
                            acc.editAccount(websiteField.getText(), usernameField.getText(), passwordField.getText());
                            addEditFrame();
                        }
                    }
                }
            }
        );

        button.setPreferredSize(new Dimension(acc.getWidth(), 180));
        button.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        return button;
    }

    public void addEditFrame()
    {
        if(editFrame != null)
        {
            editFrame.dispose();
        }
        editFrame = new JFrame();
        editPanel = new JPanel(new FlowLayout());
        editOptionPanel = new JPanel();
        editPanel.setPreferredSize(new Dimension(800, 900));
        for(int i = 0; i < user.getNumberOfAccounts(); i++)
        {
            editPanel.add(editAccountPanel(user.getAccount(i)));
        }
        int result = 0;

        Object[] options = {"Ok", "Undo Delete"};
        int n = JOptionPane.showOptionDialog(editFrame, editPanel, "Account Editing", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(n == 1)
        {
            if(!user.undo())
            {
                JOptionPane.showMessageDialog(frame, "No account has been deleted or the previous account has already been restored.");
            }
            else
            {
                refresh();
            }
            addEditFrame();
        }

    }

    public void quit()
    {
        Object[] options = {"Yes, but I would like to save.", "Yes, without saving", "No"};
        int n = JOptionPane.showOptionDialog(frame, "Are You Sure?", "Quit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
        switch(n)
        {
            // Yes with Saving Password
            case 0:
            FileChooser f = new FileChooser();
            String path = f.getPath();
            if(path != "")
            {
                lastPath = path;
                recordAccounts(path);
                JOptionPane.showMessageDialog(frame, "Accounts Successfully Saved :)");
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Unable to save. Please Try Again. :(");
                quit();
            }
            break;
            // Yes, without Saving
            case 1:
            JOptionPane.showMessageDialog(frame, "Goodbye :))");
            System.exit(0);
            break;
            // No, go back
            case 2:
            break;
            default:
            break;
        }
    }

    private class UserSettings
    {
        JFrame frame;
        JTextField inputTextField;
        JLabel inputLabel;
        JPanel panel;
        JButton start;
        String in = "";
        String display = "";

        JFrame f;

        public void changePassword()
        {
            f = new JFrame();
            String password = JOptionPane.showInputDialog(f, "Enter Changed Password");
            user.changePassword(password);
        }
    }

    private class Login implements ActionListener
    {
        // instance variables - replace the example below with your own
        JFrame frame;
        JTextField nameTextField;
        JLabel nameLabel, img;
        JPanel panel;
        JButton start;
        Interface i;
        Color c = new Color(73, 63, 100);
        boolean change = false;

        /**
         * Constructor for objects of class Login
         */
        public Login()
        {
            // initialise instance variables
            frame = new JFrame("Login");
            frame.getContentPane().setLayout(new FlowLayout());
            panel = new JPanel(new GridLayout(3, 1));
            img = new JLabel();
            ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + File.separator + "Icons\\newUserLogin.png");

            Image image = icon.getImage(); // transform it
            Image newimg = image.getScaledInstance(700, 700, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            icon = new ImageIcon(newimg); // transform it back
            img.setIcon(icon);

            frame.add(img);
            frame.getContentPane().setBackground(c);

            nameTextField = new JTextField("");
            nameTextField.setFont(new Font("Serif", Font.PLAIN, 20));
            nameLabel = new JLabel("Enter Your Password Below:");
            nameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            start = new JButton("Start!");
            nameTextField.addActionListener(this);
            start.addActionListener(this);

            panel.add(nameLabel);
            panel.add(nameTextField);
            panel.add(start);

            start.setPreferredSize(new Dimension(200, 100));

            frame.add(panel);
            frame.setPreferredSize(new Dimension(1100, 800));
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        public void needPasswordChange()
        {
            // put your code here
            change = true;
        }

        public void actionPerformed(ActionEvent e)
        {
            //...Get information from the action event...
            //...Display it in the text area...

            if(e.getSource() == nameTextField || e.getSource() == start)
            {
                if(nameTextField.getText().equals(user.getPassword()))
                {
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Welcome Back " + user.getName() + " :))");

                    refresh();
                    if(change == true)
                    {
                        String password = JOptionPane.showInputDialog(frame, "Enter Changed Password");
                        user.changePassword(password);
                        change = false;
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Wrong Password. Please Try Again.");
                }

            }
        }

    }
}
