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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Toolkit;

// Imports from console UI
import model.FinanciallyFitModel;
import model.GymMember;
import model.MembersManager;
import persistence.JsonReader;
import persistence.JsonWriter;


/*
 * Represents the Gym Interface.
 */


public class FinanciallyFitGUI extends JFrame  {

    private final ImageIcon imageIconSmall = new ImageIcon("data/logowide.png");
    private final ImageIcon logo = new ImageIcon("data/logo.png");

    private static final String JSON_FILEPATH = "./data/membersManager.json";
    private FinanciallyFitModel financiallyFitModel = new FinanciallyFitModel();
    private MembersManager membersManager = new MembersManager();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JProgressBar progressBar;
    private JPanel panel;
    private JPanel panel2;

    public FinanciallyFitGUI() throws InterruptedException, FileNotFoundException {
        super("FinanciallyFit");
        setIconImage(logo.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jsonWriter = new JsonWriter(JSON_FILEPATH);
        jsonReader = new JsonReader(JSON_FILEPATH);

        // Splash screen
//        showSplashScreen(3600);
//        Thread.sleep(3600);

        // Go fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Set border for the content pane
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));

        // Initialize Panel with BoxLayout
        panel = new JPanel();
        initializeNewPanel(panel);
        panel2 = new JPanel();
        initializeNewPanel(panel2);

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
        setVisible(true);
    }

    private void addLabel(String text, Container container, Integer width, Integer height, Integer font) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setMinimumSize(new Dimension(width, height));
        label.setMaximumSize(new Dimension(width, height));
        label.setPreferredSize(new Dimension(width, height));
        label.setFont(new Font("Helvectica", Font.BOLD, font));
        label.setBackground(Color.decode("#262630"));
        label.setForeground(Color.WHITE);
        container.add(label);
    }

    private void addEmptyTextBox(Container container, JTextField textBox) {
        textBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        textBox.setMinimumSize(new Dimension(650, 60));
        textBox.setMaximumSize(new Dimension(650, 60));
        textBox.setPreferredSize(new Dimension(650, 60));
        textBox.setFont(new Font("Helvectica", Font.BOLD, 25));
        textBox.setBackground(Color.decode("#262630"));
        textBox.setForeground(Color.WHITE);
        container.add(textBox);
        container.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    private void initializeNewPanel(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.decode("#262630"));
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel logo = new JLabel(imageIconSmall);
        logo.setBackground(Color.decode("#262630"));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(logo);
        container.add(Box.createRigidArea(new Dimension(0, 30)));
    }

    private void displayAppropriateMenu() {
        if (membersManager.getSize() == 0) {
            setContentPane(panel);
        } else {
            setContentPane(panel2);
        }
        revalidate();
        repaint();
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
        addViewMembersButton(panel2);
        addCheckAttendanceOfMembersForDayButton(panel2);
        addSaveButton(panel2);
        addLoadButton(panel2);
        addExitButton(panel2);
    }

    private void buttonPlaySoundEffect() {
        try {
            AudioInputStream aui = AudioSystem.getAudioInputStream(new File("data/pluh.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(aui);
            clip.start();
        } catch (UnsupportedAudioFileException uafe) {
            System.out.println("UnsupportedException");
        } catch (IOException io) {
            System.out.println("IOException");
        } catch (LineUnavailableException lue) {
            System.out.println("LineUnavailableException");
        }
    }

    private void addRegisterMemberButton(Container container) {
        JButton button = new JButton("Register Member");
        setUpButton(button, 640, 55, 28);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPlaySoundEffect();
                initializeRegisterMembers();

            }
        });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private static void setUpButton(JButton button, Integer width, Integer height, Integer fontSize ) {
        button.setMinimumSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font("Helvectica", Font.BOLD, fontSize));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
    }

    private void initializeRegisterMembers() {
        JPanel regPanel = new JPanel();
        initializeNewPanel(regPanel);
        addLabel("Enter member name:", regPanel, 650, 60, 25);
        JTextField textBoxName = new JTextField();
        addEmptyTextBox(regPanel, textBoxName);
        addLabel("Enter date of registration (YYYY-mm-dd):", regPanel, 650, 60, 25);
        JTextField textBoxRegDate = new JTextField();
        addEmptyTextBox(regPanel, textBoxRegDate);
        addLabel("Enter number of days allowed missed:", regPanel, 650, 60, 25);
        JTextField textBoxAllowedMiss = new JTextField();
        addEmptyTextBox(regPanel, textBoxAllowedMiss);
        JButton button = new JButton("Okay");
        button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        registerMember(textBoxName, textBoxRegDate, textBoxAllowedMiss);
                    }
                });
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(100, 60));
        button.setMaximumSize(new Dimension(100, 60));
        button.setPreferredSize(new Dimension(100, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 15));
        button.setFocusPainted(false);
        regPanel.add(button);
        setContentPane(regPanel);
        revalidate();
        repaint();
    }

    private void initializeDeregisterMembers() {
        JPanel deregPanel = new JPanel();
        initializeNewPanel(deregPanel);
        addLabel("Enter member name:", deregPanel, 650, 60, 25);
        JTextField textBoxName = new JTextField();
        addEmptyTextBox(deregPanel, textBoxName);
        JButton button = new JButton("Okay");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deregisterMember(textBoxName);
            }
        });
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(100, 60));
        button.setMaximumSize(new Dimension(100, 60));
        button.setPreferredSize(new Dimension(100, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 15));

        button.setFocusPainted(false);
        deregPanel.add(button);
        setContentPane(deregPanel);
        revalidate();
        repaint();
    }

    private void initializeCheckAttendanceOnDay() {
        JPanel attendanceCheckerPanel = new JPanel();
        initializeNewPanel(attendanceCheckerPanel);
        addLabel("Enter date to check attendance for (YYYY-MM-DD): ",
                attendanceCheckerPanel, 650, 60, 25);
        JTextField textBoxName = new JTextField();
        addEmptyTextBox(attendanceCheckerPanel, textBoxName);
        JButton button = new JButton("Okay");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAttendance(textBoxName);
            }
        });
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(100, 60));
        button.setMaximumSize(new Dimension(100, 60));
        button.setPreferredSize(new Dimension(100, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 15));

        button.setFocusPainted(false);
        attendanceCheckerPanel.add(button);
        setContentPane(attendanceCheckerPanel);
        revalidate();
        repaint();
    }

    private void viewMembers() {

        List<String> gymMemberNameList = new ArrayList<>();
        for (GymMember m : membersManager.getMembers()) {
            gymMemberNameList.add(m.getName());
        }

        GymMember currentMember = membersManager.getMembers().get(0);
        JList<String> gymMembers = new JList<String>(gymMemberNameList.toArray(new String[0]));
        JScrollPane nameScrollPanel = new JScrollPane(gymMembers);
        JPanel detailsPanel = new JPanel();
        JPanel exitPanel = new JPanel();

        gymMembers.setFont(new Font("Helvectica", Font.BOLD, 25));
        gymMembers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gymMembers.setSelectedIndex(0);


        initializeNewPanel(detailsPanel);
        updateDetailsPanel(detailsPanel, currentMember, nameScrollPanel);



        gymMembers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Get the selected index
                    int selectedIndex = gymMembers.getSelectedIndex();
                    GymMember indexMember = membersManager.getMembers().get(selectedIndex);

                    // Update detailsPanel based on the selected member
                    detailsPanel.removeAll();
                    initializeNewPanel(detailsPanel);
                    updateDetailsPanel(detailsPanel, indexMember, nameScrollPanel);
                    revalidate();
                    repaint();
                    }
                }
            }
        );


        exitPanel.setLayout(new BoxLayout(exitPanel, BoxLayout.Y_AXIS));

// Center the button both horizontally and vertically


        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAppropriateMenu();
            }
        });
        setUpButton(backButton, 100, 60, 15);
        backButton.setFocusPainted(false);
        backButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        exitPanel.add(Box.createVerticalGlue());
        exitPanel.add(backButton);
        exitPanel.add(Box.createVerticalGlue());


        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, nameScrollPanel, detailsPanel);
        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPaneHorizontal, exitPanel);
        splitPaneHorizontal.setAlignmentX(Component.CENTER_ALIGNMENT);
        splitPaneVertical.setAlignmentX(Component.CENTER_ALIGNMENT);
        splitPaneHorizontal.setOneTouchExpandable(true);
        splitPaneHorizontal.setDividerLocation(300);
        splitPaneVertical.setDividerLocation((int) (0.85 * Toolkit.getDefaultToolkit().getScreenSize().getHeight()));

        setContentPane(splitPaneVertical);
        revalidate();
        repaint();
    }

    private void updateDetailsPanel(JPanel detailsPanel, GymMember currentMember, JScrollPane nameScrollPanel) {
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));


        addLabel(currentMember.getName(), detailsPanel, 650, 60, 45);



        addLabel("_______________________________________________________________________________________",
                detailsPanel, 650, 60, 5);
        addLabel("Registration Date: " + currentMember.getRegDate(), detailsPanel, 650, 60, 20);
        addLabel("Total Hours This Month: " + currentMember.getTotalHours(), detailsPanel, 650, 60, 20);
        addLabel("Number Of Days Attended: " + currentMember.getAttendanceCount(), detailsPanel, 650, 60, 20);
        addLabel("Number Of Days Left: " + currentMember.getNumOfDaysLeftInMonth(),
                detailsPanel, 650, 60, 20);

        nameScrollPanel.setBackground(Color.decode("#262630"));
        nameScrollPanel.setForeground(Color.WHITE);
    }



    private void registerMember(JTextField textBoxName, JTextField textBoxRegDate, JTextField textBoxAllowedMiss) {
            GymMember gymMember = new GymMember(textBoxName.getText(), textBoxRegDate.getText(),
                    Integer.valueOf(textBoxAllowedMiss.getText()));
            membersManager.addMember(gymMember);
            int option = JOptionPane.showOptionDialog(
                null, textBoxName.getText() + " has been registered.",
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

    private void deregisterMember(JTextField textBoxName) {
        String returnstring = "Member not found.";
        GymMember foundMember =
                financiallyFitModel.findGymMemberPublic(membersManager.getMembers(), textBoxName.getText());
        if (foundMember != null) {
            membersManager.removeMember(foundMember);
            returnstring = textBoxName.getText() + " has been deregistered.";
        }
        int option = JOptionPane.showOptionDialog(
                null, returnstring,
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

    private void checkAttendance(JTextField textBoxDate) {
        JPanel attendancePanel = new JPanel();
        initializeNewPanel(attendancePanel);
        attendancePanel.setLayout(new BoxLayout(attendancePanel, BoxLayout.Y_AXIS));

        List<String> attendanceList = membersManager.returnAttendanceDay(textBoxDate.getText());
        JList<String> attendanceJList = new JList<String>(attendanceList.toArray(new String[0]));
        attendanceJList.setFont(new Font("Helvectica", Font.BOLD, 25));
        JScrollPane attendanceScrollPanel = new JScrollPane(attendanceJList);
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new BoxLayout(exitPanel, BoxLayout.Y_AXIS));

        if (attendanceList.isEmpty()) {
            addLabel("Nobody attended that day.", attendancePanel, 640, 55, 28);
        } else {
            attendancePanel.add(attendanceScrollPanel);
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAppropriateMenu();
            }
        });
        setUpButton(backButton, 100, 60, 15);
        backButton.setFocusPainted(false);
        backButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        exitPanel.add(Box.createVerticalGlue());
        exitPanel.add(backButton);
        exitPanel.add(Box.createVerticalGlue());

        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, attendancePanel, exitPanel);
        attendancePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        splitPaneVertical.setAlignmentX(Component.CENTER_ALIGNMENT);
        splitPaneVertical.setDividerLocation((int) (0.85 * Toolkit.getDefaultToolkit().getScreenSize().getHeight()));

        setContentPane(splitPaneVertical);
        revalidate();
        repaint();


//
//        exitPanel.add(Box.createVerticalGlue());
//        exitPanel.add(backButton);
//        exitPanel.add(Box.createVerticalGlue());
//        if (membersManager.returnAttendanceDay(textBoxDate.getText()).isEmpty()) {
//            System.out.println("Nobody attended that day :(");
//        } else {
//            System.out.println("List of people who attended that day: " + membersManager.returnAttendanceDay(date));
//        }
    }


    public void addDeregisterMemberButton(Container container) {
        JButton button = new JButton("Deregister Member");
        setUpButton(button, 640, 55, 28);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPlaySoundEffect();
                initializeDeregisterMembers();
            }
        });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public void addLogMemberAttendanceButton(Container container) {
        JButton button = new JButton("Log Member Attendance");
        setUpButton(button, 640, 55, 28);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPlaySoundEffect();
                initializeLogMemberAttendance();
            }
        });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public void initializeLogMemberAttendance() {
        JPanel memberAttendancePanel = new JPanel();
        initializeNewPanel(memberAttendancePanel);

        addLabel("Enter member name:", memberAttendancePanel, 650, 60, 25);
        JTextField textBoxName = new JTextField();
        addEmptyTextBox(memberAttendancePanel, textBoxName);

        addLabel("Enter date to log attendance (YYYY-mm-dd): ", memberAttendancePanel, 650, 60, 25);
        JTextField textBoxLogDate = new JTextField();
        addEmptyTextBox(memberAttendancePanel, textBoxLogDate);

        addLabel("Enter time spent at the gym (hours): ", memberAttendancePanel, 650, 60, 25);
        JTextField textBoxHours = new JTextField();
        addEmptyTextBox(memberAttendancePanel, textBoxHours);

        JButton button = new JButton("Okay");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logMemberAttendance(textBoxName, textBoxLogDate, textBoxHours);
            }
        });
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(new Dimension(100, 60));
        button.setMaximumSize(new Dimension(100, 60));
        button.setPreferredSize(new Dimension(100, 60));
        button.setFont(new Font("Helvectica", Font.BOLD, 15));
        button.setFocusPainted(false);
        memberAttendancePanel.add(button);
        setContentPane(memberAttendancePanel);
        revalidate();
        repaint();
    }

    public void logMemberAttendance(JTextField textBoxName, JTextField textBoxLogDate, JTextField textBoxHours) {
        String returnstring = "Member not found.";
        GymMember foundMember =
                financiallyFitModel.findGymMemberPublic(membersManager.getMembers(), textBoxName.getText());
        if (foundMember != null) {
            foundMember.logAttendance(Double.parseDouble(textBoxHours.getText()), textBoxLogDate.getText());
            returnstring =
                    textBoxHours.getText() + " hours logged for " +
                            textBoxName.getText() + " on " + textBoxLogDate.getText();
        }
                int option = JOptionPane.showOptionDialog(
                null, returnstring,
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


    public void addCalculateMonthlyBillButton(Container container) {
        JButton button = new JButton("Calculate Monthly Bill");
        setUpButton(button, 640, 55, 28);
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public void addViewMembersButton(Container container) {
        JButton button = new JButton("View Members");
        setUpButton(button, 640, 55, 28);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPlaySoundEffect();
                viewMembers();
            }
        });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public void addCheckAttendanceOfMembersForDayButton(Container container) {
        JButton button = new JButton("Check Attendance Of Members For Day");
        setUpButton(button, 640, 55, 28);
        button.setFont(new Font("Helvectica", Font.BOLD, 30));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPlaySoundEffect();
                initializeCheckAttendanceOnDay();
            }
        });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public void addSaveButton(Container container) {
        JButton button = new JButton("Save");
        setUpButton(button, 640, 55, 28);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPlaySoundEffect();
                initializeSave();
            }
        });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void initializeSave() {
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
        setUpButton(button, 640, 55, 28);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPlaySoundEffect();
                initializeLoad();
            }
        });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void initializeLoad() {
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

    public void addExitButton(Container container) {
        JButton button = new JButton("Exit");
        setUpButton(button, 640, 55, 28);
        button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        initializeExit();
                    }
                });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private static void initializeExit() {
        try {
            AudioInputStream aui =
                    AudioSystem.getAudioInputStream(new File("data/byebye.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(aui);
            clip.start();
        } catch (UnsupportedAudioFileException uafe) {
            System.out.println("UnsupportedException");
        } catch (IOException io) {
            System.out.println("IOException");
        } catch (LineUnavailableException lue) {
            System.out.println("LineUnavailableException");
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        System.exit(0);
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

        ImageIcon imageIcon = logo;
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

//}