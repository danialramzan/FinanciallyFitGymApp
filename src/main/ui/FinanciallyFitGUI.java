package ui;

import java.awt.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.lang.*;
import java.awt.Color;
import java.io.FileNotFoundException;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Toolkit;

// Imports from console UI
import model.FinanciallyFitModel;
import model.MembersManager;
import persistence.JsonReader;
import persistence.JsonWriter;


/*
 * Represents the Gym Interface.
 */


public class FinanciallyFitGUI extends JFrame  {

    private static final String JSON_FILEPATH = "./data/membersManager.json";
    private FinanciallyFitModel financiallyFitModel = new FinanciallyFitModel();
    private MembersManager membersManager = new MembersManager();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JLabel label;
    private JTextField field;
    private JProgressBar progressBar;
    private ImageIcon imageIconSmall = new ImageIcon("data/logowide.png");
    private JPanel panel;
    private JPanel panel2;
    private Dimension notificationScreenSize = new Dimension(
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.25) ,
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.25));



    public FinanciallyFitGUI() throws InterruptedException, FileNotFoundException {
        super("FinanciallyFit");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jsonWriter = new JsonWriter(JSON_FILEPATH);
        jsonReader = new JsonReader(JSON_FILEPATH);

//        // Splash screen
//        showSplashScreen(3600);
//        Thread.sleep(3600);

        // Go fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Set border for the content pane
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));

        // Panel with BoxLayout
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#262630"));

        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(Color.decode("#262630"));

        // Spacing
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel2.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add Items
        JLabel logo = new JLabel(imageIconSmall);
        logo.setBackground(Color.decode("#262630"));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel logo2 = new JLabel(imageIconSmall);
        logo2.setBackground(Color.decode("#262630"));
        logo2.setForeground(Color.WHITE);
        logo2.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(logo);
        panel2.add(logo2);

        // Spacing
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel2.add(Box.createRigidArea(new Dimension(0, 40)));

        // GANG GANG'





        // BUTTON ADDER STARTER CODE


//        JButton btn = new JButton("SOMETHING");
//        btn3.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        System.exit(0);
//                    }
//                });
//        add(btn3);
//        btn.setBackground(Color.decode("#262630"));
//         btn.setForeground(Color.WHITE);

        initializeMenus();
        displayAppropriateMenu();


        // GANG GANG

        // Set the panel as the content pane
        setContentPane(panel);

        // Ensure visibility after setting up components
        setVisible(true);
    }

    private void displayAppropriateMenu() {
        if (membersManager.getSize() == 0) {
            setContentPane(panel);
            revalidate();
            repaint();
            } else {
            setContentPane(panel2);
            revalidate();
            repaint();
            }
    }

    private void initializeMenus() {
        addRegisterMemberButton(panel);
        addSaveButton(panel);
        addLoadButton(panel);
        addExitButton(panel);
        addRegisterMemberButton(panel2);
        addDeregisterMemberButton(panel2);
        addLogMemberAttendanceButton(panel2);
        addCalculateMonthlyBillButton(panel2);
        addCheckAttendanceOfMembersForDayButton(panel2);
        addSaveButton(panel2);
        addLoadButton(panel2);
        addExitButton(panel2);
    }

//    private void displayMenu2() {
//
//    }

    //        System.out.println("__________________________");
//        System.out.println("~FinanciallyFit Terminal~");
//        System.out.println("__________________________");
//        System.out.println("1. (r)egister member");
//        System.out.println("2. (d)eregister member");
//        System.out.println("3. (l)og member attendance");
//        System.out.println("4. (c)alculate monthly bill");
//        System.out.println("5. (v)iew members");
//        System.out.println("6. (a)ttendance of members for day");
//        System.out.println("7. (sa)ve");
//        System.out.println("8. (lo)ad");
//        System.out.println("9. (e)xit");


    private void addRegisterMemberButton(Container container) {
        JButton button = new JButton("Register Member");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(650, 60));
        button.setMaximumSize(new Dimension(650, 60));
        button.setPreferredSize(new Dimension(650, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 30));
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
    }

    public void addDeregisterMemberButton(Container container) {
        JButton button = new JButton("Deregister Member");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(650, 60));
        button.setMaximumSize(new Dimension(650, 60));
        button.setPreferredSize(new Dimension(650, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 30));
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
    }

    public void addLogMemberAttendanceButton(Container container) {
        JButton button = new JButton("Log Member Attendance");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(650, 60));
        button.setMaximumSize(new Dimension(650, 60));
        button.setPreferredSize(new Dimension(650, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 30));
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
    }

    public void addCalculateMonthlyBillButton(Container container) {
        JButton button = new JButton("Calculate Monthly Bill");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(650, 60));
        button.setMaximumSize(new Dimension(650, 60));
        button.setPreferredSize(new Dimension(650, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 30));
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
    }

    public void addCheckAttendanceOfMembersForDayButton(Container container) {
        JButton button = new JButton("Check Attendance Of Members For Day");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(650, 60));
        button.setMaximumSize(new Dimension(650, 60));
        button.setPreferredSize(new Dimension(650, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 30));
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
    }

    public void addSaveButton(Container container) {
        JButton button = new JButton("Save");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(650, 60));
        button.setMaximumSize(new Dimension(650, 60));
        button.setPreferredSize(new Dimension(650, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 30));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String returnString;
                try {
                    jsonWriter.open();
                    jsonWriter.write(membersManager);
                    jsonWriter.close();
                    returnString = "Successfully saved instance to " + JSON_FILEPATH;
                } catch (FileNotFoundException fnfe) {
                    returnString = "Unable to write to file: " + JSON_FILEPATH;
                }
                JOptionPane.showMessageDialog(
                        null,
                        returnString,
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE,
                        UIManager.getIcon("OptionPane.informationIcon"));
            }
        });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
    }

    //    private void saveMembersManager() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(membersManager);
//            jsonWriter.close();
//            System.out.println("Successfully saved instance to " + JSON_FILEPATH);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_FILEPATH);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads MembersManager instance from file
//    private void loadMembersManager() {
//        try {
//            membersManager = jsonReader.read();
//            System.out.println("Successfully loaded instance from " + JSON_FILEPATH);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_FILEPATH);
//        }
//    }

    public void addLoadButton(Container container) {
        JButton button = new JButton("Load");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(650, 60));
        button.setMaximumSize(new Dimension(650, 60));
        button.setPreferredSize(new Dimension(650, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 30));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String returnString;
                try {
                    membersManager = jsonReader.read();
                    returnString = "Successfully loaded instance from " + JSON_FILEPATH;
                } catch (IOException ioe) {
                    returnString = "Unable to read from file: " + JSON_FILEPATH;
                }
                int option = JOptionPane.showOptionDialog(
                        null,
                        returnString,
                        "Information",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        UIManager.getIcon("OptionPane.informationIcon"),
                        new Object[]{"OK"},
                        "OK");

                if (option == 0) {
                    displayAppropriateMenu();
                }
            }
        });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
    }

    public void addExitButton(Container container) {
        JButton button = new JButton("Exit");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(650, 60));
        button.setMaximumSize(new Dimension(650, 60));
        button.setPreferredSize(new Dimension(650, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 30));
        button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            AudioInputStream aui =
                                    AudioSystem.getAudioInputStream(new File("data/byebye.wav"));
                            Clip clip = AudioSystem.getClip();
                            clip.open(aui);
                            clip.start();
                        } catch (UnsupportedAudioFileException uafe) {
                            System.out.println("1");
                        } catch (IOException io) {
                            System.out.println("2");
                        } catch (LineUnavailableException lue) {
                            System.out.println("3");
                        }
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.exit(0);
                    }
                });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
    }



//    public FinanciallyFitGUI() throws InterruptedException, FileNotFoundException {
//        super("FinanciallyFit");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        panel = new JPanel();
//
//        jsonWriter = new JsonWriter(JSON_FILEPATH);
//        jsonReader = new JsonReader(JSON_FILEPATH);
//
//        // Splash screen
//        showSplashScreen(3600);
//        Thread.sleep(3600);
//
//        // Go fullscreen
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
//        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//
//        //Add Logo
//        JLabel logo = new JLabel(imageIconSmall);
//        logo.setBackground(Color.decode("#262630"));
//        logo.setForeground(Color.WHITE);
//        panel.add(logo);
//
//        pack();
//
//
//
//
////        JButton btn1 = new JButton("Register Member");
////        btn1.addActionListener(new ActionListener() {
////            public void actionPerformed(ActionEvent e) {
////                System.exit(0);
////            }
////        });
////        add(btn1);
////        btn1.setBackground(Color.decode("#262630"));
////        btn1.setForeground(Color.WHITE);
//
//
//        // BUTTON ADDER STARTER CODE
//
//        //JButton btn3 = new JButton("SOMETHING");
//        //btn3.addActionListener(new ActionListener() {
//        //            public void actionPerformed(ActionEvent e) {
//        //                System.exit(0);
//        //            }
//        //        });
//        //add(btn3);
//        //btn.setBackground(Color.decode("#262630"));
//        // btn.setForeground(Color.WHITE);
//
////        JLabel logo = new JLabel(imageIconSmall);
////        add(logo);
////        logo.setBackground(Color.decode("#262630"));
////        logo.setForeground(Color.WHITE);
////
////
////
////
////
////
////        //btn.addActionListener(this);
////        label = new JLabel("flag");
////        field = new JTextField(5);
////        add(field);
////        add(label);
////        pack();
////        setLocationRelativeTo(null);
////        setResizable(false);
////
//        getContentPane().setBackground(Color.decode("#262630"));
////        label.setForeground(Color.WHITE);
////        label.setBackground(Color.decode("#262630"));
////
//        setVisible(true);
//    }








    // Display a splash screen for the specified duration in milliseconds
    private void showSplashScreen(int duration) {

        ImageIcon imageIcon = new ImageIcon("data/logo.png");
        JLabel imageLabel = new JLabel(imageIcon);
        JWindow splash = new JWindow();

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(false);
        progressBar.setForeground(Color.white);
        progressBar.setBackground(Color.decode("#262630"));
        progressBar.setSize(20,20);
        add(progressBar);

        splash.getContentPane().setLayout(new BorderLayout());
        splash.getContentPane().add(imageLabel, BorderLayout.CENTER);
        splash.getContentPane().add(progressBar, BorderLayout.SOUTH);
        splash.getContentPane().setBackground(Color.WHITE);
        splash.setSize(402, 417);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        simulateLoading();

        Timer timer = new Timer(duration, e -> splash.dispose());

        timer.setRepeats(false);
        timer.start();
    }

    // Simulate a loading process
    private void simulateLoading() {
        Random random = new Random();
        List<Integer> timerList = new ArrayList<>();
        int randomValue1 = random.nextInt(99) + 1;
        int randomValue2 = random.nextInt(99) + 1;
        timerList.add(randomValue1);
        timerList.add(randomValue2);
        Timer timer = new Timer(20, new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (progress < 100) {
                    if (timerList.contains(progress)) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    progress++;
                    progressBar.setValue(progress);
                } else {
                    ((Timer) e.getSource()).stop();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        timer.start();
    }
}









//public class FinanciallyFitGUI {
//
//    private static final String JSON_FILEPATH = "./data/membersManager.json";
//
//    private FinanciallyFitModel financiallyFitModel = new FinanciallyFitModel();
//    private MembersManager membersManager = new MembersManager();
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    // EFFECTS: Starts the User Interface
//    public FinanciallyFitGUI() throws FileNotFoundException {
//
//        jsonWriter = new JsonWriter(JSON_FILEPATH);
//        jsonReader = new JsonReader(JSON_FILEPATH);
//
//        while (true) {
//            Scanner scanner = new Scanner(System.in);
//
//            if (membersManager.getSize() == 0) {
//                displayMenu1();
//                System.out.print("Please enter your choice: ");
//                String choice = scanner.nextLine();
//                processInput1(choice, scanner);
//            } else {
//                displayMenu2();
//                System.out.print("Please enter your choice: ");
//                String choice = scanner.nextLine();
//                processInput2(choice, scanner);
//            }
//        }
//    }
//
//    // EFFECTS: processes inputs for the primary menu
//    private void processInput1(String choice, Scanner scanner) {
//        if (choice.equals("1") || choice.equalsIgnoreCase("r")) {
//            registerMember(scanner);
//
//        } else if (choice.equals("2") || choice.equalsIgnoreCase("sa")) {
//            saveMembersManager();
//
//        } else if (choice.equals("3") || choice.equalsIgnoreCase("lo")) {
//            loadMembersManager();
//
//        } else if (choice.equals("4") || choice.equalsIgnoreCase("e")) {
//            exit();
//
//        } else {
//            System.out.println("Invalid choice. Please try again.");
//        }
//    }
//
//    // EFFECTS: processes inputs for the secondary menu
//    private void processInput2(String choice, Scanner scanner) {
//
//        if (choice.equals("1") || choice.equalsIgnoreCase("r")) {
//            registerMember(scanner);
//
//        } else if (choice.equals("2") || choice.equalsIgnoreCase("d")) {
//            deregisterMember(scanner);
//
//        } else if (choice.equals("3") || choice.equalsIgnoreCase("l")) {
//            logMemberAttendance(scanner);
//
//        } else if (choice.equals("4") || choice.equalsIgnoreCase("c")) {
//            calculateMonthlyBillUI(scanner);
//
//        } else if (choice.equals("5") || choice.equalsIgnoreCase("v")) {
//            viewMembers();
//
//        } else if (choice.equals("6") || choice.equalsIgnoreCase("a")) {
//            attendanceChecker(scanner);
//
//        } else if (choice.equals("7") || choice.equalsIgnoreCase("sa")) {
//            saveMembersManager();
//
//        } else if (choice.equals("8") || choice.equalsIgnoreCase("lo")) {
//            loadMembersManager();
//
//        } else if (choice.equals("9") || choice.equalsIgnoreCase("e")) {
//            exit();
//
//        } else {
//            System.out.println("Invalid choice. Please try again.");
//        }
//    }
//
//    // EFFECTS: Displays the starting Menu
//    public void displayMenu1() {
//        System.out.println("__________________________");
//        System.out.println("~FinanciallyFit Terminal~");
//        System.out.println("__________________________");
//        System.out.println("1. (r)egister member");
//        System.out.println("2. (sa)ve");
//        System.out.println("3. (lo)ad");
//        System.out.println("4. (e)xit");
//    }
//
//    // EFFECTS: Displays the secondary menu (when MembersManager is not empty)
//    public void displayMenu2() {
//        System.out.println("__________________________");
//        System.out.println("~FinanciallyFit Terminal~");
//        System.out.println("__________________________");
//        System.out.println("1. (r)egister member");
//        System.out.println("2. (d)eregister member");
//        System.out.println("3. (l)og member attendance");
//        System.out.println("4. (c)alculate monthly bill");
//        System.out.println("5. (v)iew members");
//        System.out.println("6. (a)ttendance of members for day");
//        System.out.println("7. (sa)ve");
//        System.out.println("8. (lo)ad");
//        System.out.println("9. (e)xit");
//    }
//
//    // EFFECTS: Exits the Program
//    private void exit() {
//        System.out.println("Exiting the FinanciallyFit terminal. Goodbye!");
//        System.exit(0);
//    }
//
//    // EFFECTS: Uses the returnAttendanceDay method to print a list of people who attended on a certain day
//    private void attendanceChecker(Scanner scanner) {
//        System.out.print("Enter date to check attendance for (YYYY-MM-DD): ");
//        String date = scanner.nextLine();
//        if (membersManager.returnAttendanceDay(date).isEmpty()) {
//            System.out.println("Nobody attended that day :(");
//        } else {
//            System.out.println("List of people who attended that day: " + membersManager.returnAttendanceDay(date));
//        }
//    }
//
//    // EFFECTS: prints out a list of members along with their regDate, Total Hours, and Days Attended
//    private void viewMembers() {
//
//        if (membersManager.getMembers().isEmpty()) {
//            System.out.println("No members are registered!");
//        } else {
//            System.out.println("_______________________________________________________________________");
//            System.out.println("Member Name     Registration Date    Total Hours    Days Attended");
//            for (GymMember m : membersManager.getMembers()) {
//                System.out.println(m.getName() + "       " + m.getRegDate() + "                  "
//                        + m.getTotalHours() + "           " + m.getAttendanceCount() + "/"
//                        + m.getNumOfDaysLeftInMonth());
//
//            }
//        }
//    }
//
//
//
//    // EFFECTS: Uses the calculateMonthlyBillPublic method to print a monthly bill for the user.
//    private void calculateMonthlyBillUI(Scanner scanner) {
//        System.out.print("Enter member name: ");
//        String billMemberName = scanner.nextLine();
//        double result = financiallyFitModel.calculateMonthlyBillPublic(membersManager.getMembers(), billMemberName);
//        if (result != -1) {
//            System.out.println("Monthly Bill for " + billMemberName + ": $" + result);
//            System.out.println("Note that as you attend the gym more often your total amount due will go down");
//        } else {
//            System.out.println("Member not found.");
//        }
//    }
//
//
//
//    // REQUIRES: inputs must respect REQUIRES of logAttendance in GymMember
//    // MODIFIES: GymMember
//    // EFFECTS: logs the attendance of the Member
//    private void logMemberAttendance(Scanner scanner) {
//        System.out.print("Enter member name: ");
//        String memberName = scanner.nextLine();
//        System.out.println("Enter date to log attendance for member " + memberName + " (YYYY-mm-dd):");
//        String logDate = scanner.nextLine();
//
//        GymMember foundMember = financiallyFitModel.findGymMemberPublic(membersManager.getMembers(), memberName);
//
//        if (foundMember != null) {
//            System.out.print("Enter time spent at the gym (hours): ");
//            double hours = scanner.nextDouble();
//            foundMember.logAttendance(hours, logDate);
//            System.out.println(hours + " hours logged for " + memberName);
//        } else {
//            System.out.println("Member not found.");
//        }
//    }
//
//
//    // Registers a member by constructing a GymMember object and adding it to membersManager.
//    // REQUIRES: Inputs need to respect REQUIRES of GymMember in GymMember.
//    // MODIFIES: GymMember
//    // EFFECTS: creates a GymMember with a username, allowed missed days,
//    // base membership cost, and an attendance log, (amongst another things)
//    private void registerMember(Scanner scanner) {
//        System.out.print("Enter member name: ");
//        String name = scanner.nextLine();
//        System.out.print("Enter date of registration (YYYY-mm-dd): ");
//        String regDate = scanner.next();
//        System.out.print("Enter number of days allowed missed: ");
//        Integer allowedMiss = scanner.nextInt();
//
//
//        GymMember gymMember = new GymMember(name, regDate, allowedMiss);
//        membersManager.addMember(gymMember);
//        System.out.println(name + " has been registered.");
//    }
//
//
//    // MODIFIES: membersManager
//    // EFFECTS: removes a member from the membersManager
//    // object using the removeMember method.
//    private void deregisterMember(Scanner scanner) {
//        System.out.print("Enter member name: ");
//        String name = scanner.nextLine();
//        GymMember foundMember = financiallyFitModel.findGymMemberPublic(membersManager.getMembers(), name);
//
//        if (foundMember != null) {
//            membersManager.removeMember(foundMember);
//            System.out.println(name + " has been deregistered.");
//        } else {
//            System.out.println("Member not found.");
//        }
//
//    }
//
//    // EFFECTS: saves the current MembersManager instance to file
//    private void saveMembersManager() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(membersManager);
//            jsonWriter.close();
//            System.out.println("Successfully saved instance to " + JSON_FILEPATH);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_FILEPATH);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads MembersManager instance from file
//    private void loadMembersManager() {
//        try {
//            membersManager = jsonReader.read();
//            System.out.println("Successfully loaded instance from " + JSON_FILEPATH);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_FILEPATH);
//        }
//    }
//
//}