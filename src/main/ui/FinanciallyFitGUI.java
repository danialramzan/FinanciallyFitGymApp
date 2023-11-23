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


    // EFFECTS: Starts the Graphical User Interface
    public FinanciallyFitGUI() throws InterruptedException, FileNotFoundException {
        super("FinanciallyFit");
        setIconImage(logo.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jsonWriter = new JsonWriter(JSON_FILEPATH);
        jsonReader = new JsonReader(JSON_FILEPATH);

        // Splash screen
        showSplashScreen(3600);
        Thread.sleep(3600);


        // Go fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);


        // Initialize the two main screen panels.
        panel = new JPanel();
        initializeNewPanel(panel);
        panel2 = new JPanel();
        initializeNewPanel(panel2);


        initializeMenus();
        displayAppropriateMenu();


        setContentPane(panel);
        setVisible(true);
    }

    // MODIFIES: container
    // EFFECTS: Helper Function to add a text box with given font and size
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

    // MODIFIES: container
    // EFFECTS: Helper Function to initialize and add an empty text box
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

    // MODIFIES: container
    // EFFECTS: Helper Function to initialize a new panel.
    private void initializeNewPanel(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.decode("#262630"));
        container.add(Box.createRigidArea(new Dimension(0, 23)));
        JLabel logo = new JLabel(imageIconSmall);
        logo.setBackground(Color.decode("#262630"));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(logo);
        container.add(Box.createRigidArea(new Dimension(0, 30)));
    }

    // EFFECTS: Displays appropriate menu depending on if memberManager is empty or not
    private void displayAppropriateMenu() {
        if (membersManager.getSize() == 0) {
            setContentPane(panel);
        } else {
            setContentPane(panel2);
        }
        revalidate();
        repaint();
    }

    // MODIFIES: panel, panel2
    // EFFECTS: Helper Function to initialize both panels
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

    // EFFECTS: Plays chosen sound effect
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

    // MODIFIES: container
    // EFFECTS: Helper Function to initialize and add an empty text box
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

    // MODIFIES: button
    // EFFECTS: Helper Function to initialize a new button
    private static void setUpButton(JButton button, Integer width, Integer height, Integer fontSize) {
        button.setMinimumSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font("Helvectica", Font.BOLD, fontSize));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(Color.decode("#262630"));
        button.setForeground(Color.WHITE);
    }



    // MODIFIES: FinanciallyFitGUI
    // EFFECTS: Initializes Register members panel and displays it
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
        setUpButton(button, 100, 60, 15);
        button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        registerMember(textBoxName, textBoxRegDate, textBoxAllowedMiss);
                    }
                });
        regPanel.add(button);
        setContentPane(regPanel);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: Initializes Deregister members panel and displays it
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
        setUpButton(button, 100, 60, 15);
        deregPanel.add(button);
        setContentPane(deregPanel);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: initializes Attendance Checking panel and displays it
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
        setUpButton(button, 100, 60, 15);
        attendanceCheckerPanel.add(button);
        setContentPane(attendanceCheckerPanel);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: sets up the viewMembers action
    private void viewMembers() {
        GymMember currentMember = membersManager.getMembers().get(0);
        JList gymMembers = setUpGymMembersJList();

        JScrollPane nameScrollPanel = new JScrollPane(gymMembers);
        JPanel detailsPanel = new JPanel();


        initializeNewPanel(detailsPanel);
        updateDetailsPanel(detailsPanel, currentMember, nameScrollPanel);

        gymMembers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = gymMembers.getSelectedIndex();
                    GymMember indexMember = membersManager.getMembers().get(selectedIndex);

                    detailsPanel.removeAll();
                    initializeNewPanel(detailsPanel);
                    updateDetailsPanel(detailsPanel, indexMember, nameScrollPanel);
                    revalidate();
                    repaint();
                    }
                }
            }
        );


        finalSetUpPanel(new JButton("Back"), nameScrollPanel, detailsPanel);
    }

    // EFFECTS: sets up the gym Members JList from membersManager
    private JList setUpGymMembersJList() {
        List<String> gymMemberNameList = new ArrayList<>();
        for (GymMember m : membersManager.getMembers()) {
            gymMemberNameList.add(m.getName());
        }

        JList<String> gymMembers = new JList<String>(gymMemberNameList.toArray(new String[0]));
        gymMembers.setFont(new Font("Helvectica", Font.BOLD, 25));
        gymMembers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gymMembers.setSelectedIndex(0);
        return gymMembers;

    }


    // MODIFIES: backButton,
    // EFFECTS: utility code for viewMembers
    private void finalSetUpPanel(JButton backButton, JScrollPane nameScrollPanel, JPanel detailsPanel) {
        setUpButton(backButton, 100, 60, 15);
        backButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAppropriateMenu();
            }
        });

        JPanel exitPanel = initializeExitPanel(backButton);
        JSplitPane splitPaneVertical = initializeVerticalPane(nameScrollPanel, detailsPanel, exitPanel);

        setContentPane(splitPaneVertical);
        revalidate();
        repaint();
    }

    // EFFECTS: utility code for viewMembers
    private static JSplitPane initializeVerticalPane(JScrollPane nameScrollPanel,
                                                     JPanel detailsPanel, JPanel exitPanel) {
        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, nameScrollPanel, detailsPanel);
        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPaneHorizontal, exitPanel);
        splitPaneHorizontal.setAlignmentX(Component.CENTER_ALIGNMENT);
        splitPaneVertical.setAlignmentX(Component.CENTER_ALIGNMENT);
        splitPaneHorizontal.setOneTouchExpandable(true);
        splitPaneHorizontal.setDividerLocation(300);
        splitPaneVertical.setDividerLocation((int) (0.85 * Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
        return splitPaneVertical;
    }

    // MODIFIES: this
    // EFFECTS: displays the information on the viewMembers panel
    private void updateDetailsPanel(JPanel detailsPanel, GymMember currentMember, JScrollPane nameScrollPanel) {
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        addLabel(currentMember.getName(), detailsPanel, 650, 60, 45);
        addLabel("_______________________________________________________________________________________",
                detailsPanel, 650, 60, 5);
        addLabel("Registration Date: "
                + currentMember.getRegDate(), detailsPanel, 650, 60, 20);
        addLabel("Total Hours This Month: "
                + currentMember.getTotalHours(), detailsPanel, 650, 60, 20);
        addLabel("Number Of Days Attended: "
                + currentMember.getAttendanceCount(), detailsPanel, 650, 60, 20);
        addLabel("Number Of Days Left: "
                        + currentMember.getNumOfDaysLeftInMonth(), detailsPanel, 650, 60, 20);

        nameScrollPanel.setBackground(Color.decode("#262630"));
        nameScrollPanel.setForeground(Color.WHITE);
    }


    // MODIFIES: this
    // EFFECTS: sets up the registerMembers action
    private void registerMember(JTextField textBoxName, JTextField textBoxRegDate, JTextField textBoxAllowedMiss) {
        GymMember gymMember = new GymMember(textBoxName.getText(), textBoxRegDate.getText(),
                Integer.valueOf(textBoxAllowedMiss.getText()));
        membersManager.addMember(gymMember);
        int option = JOptionPane.showOptionDialog(null,
                textBoxName.getText() + " has been registered.",
                "Information",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                UIManager.getIcon("OptionPane.informationIcon"),
                new Object[]{"OK"},
                "OK");

        if (option == 0) {
            displayAppropriateMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the deregisterMembers action
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


    // MODIFIES: this
    // EFFECTS: sets up the checkAttendance action
    private void checkAttendance(JTextField textBoxDate) {
        JPanel attendancePanel = new JPanel();
        initializeNewPanel(attendancePanel);
        attendancePanel.setLayout(new BoxLayout(attendancePanel, BoxLayout.Y_AXIS));
        List<String> attendanceList = membersManager.returnAttendanceDay(textBoxDate.getText());
        JList<String> attendanceJList = new JList<String>(attendanceList.toArray(new String[0]));
        attendanceJList.setFont(new Font("Helvectica", Font.BOLD, 25));
        JScrollPane attendanceScrollPanel = new JScrollPane(attendanceJList);

        attendancePanelHandler(attendanceList, attendancePanel, attendanceScrollPanel);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAppropriateMenu();
            }
        });
        setUpButton(backButton, 100, 60, 15);
        backButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel exitPanel = initializeExitPanel(backButton);
        JSplitPane splitPaneVertical = getSplitPaneVertical(attendancePanel, exitPanel);

        setContentPane(splitPaneVertical);
        revalidate();
        repaint();
    }

    // MODIFIES: attendancePanel
    // EFFECTS: structures the attendancePanel appropriately
    private void attendancePanelHandler(List<String> attendanceList,
                                        JPanel attendancePanel, JScrollPane attendanceScrollPanel) {
        if (attendanceList.isEmpty()) {
            addLabel("Nobody attended that day.", attendancePanel, 640, 55, 28);
        } else {
            attendancePanel.add(attendanceScrollPanel);
        }
    }

    // EFFECTS: returns a verticalPanel for viewMembers.
    private static JSplitPane getSplitPaneVertical(JPanel attendancePanel, JPanel exitPanel) {
        JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, attendancePanel, exitPanel);
        attendancePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        splitPaneVertical.setAlignmentX(Component.CENTER_ALIGNMENT);
        splitPaneVertical.setDividerLocation((int) (0.85 * Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
        return splitPaneVertical;
    }


    // EFFECTS: initializes Exit Panel
    private static JPanel initializeExitPanel(JButton backButton) {
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new BoxLayout(exitPanel, BoxLayout.Y_AXIS));
        exitPanel.add(Box.createVerticalGlue());
        exitPanel.add(backButton);
        exitPanel.add(Box.createVerticalGlue());
        return exitPanel;
    }


    // MODIFIES: container
    // EFFECTS : adds the button to the panel
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

    // MODIFIES: container
    // EFFECTS : adds the button to the panel
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


    // MODIFIES: this
    // EFFECTS: initializes Log Member Attendance panel and displays it
    public void initializeLogMemberAttendance() {
        JPanel memberAttendancePanel = new JPanel();
        initializeNewPanel(memberAttendancePanel);

        addLabel("Enter member name:", memberAttendancePanel, 650, 60, 25);
        JTextField textBoxName = new JTextField();
        addEmptyTextBox(memberAttendancePanel, textBoxName);

        addLabel("Enter date to log attendance (YYYY-mm-dd): ",
                memberAttendancePanel, 650, 60, 25);
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
        setUpButton(button, 100, 60, 15);
        memberAttendancePanel.add(button);
        setContentPane(memberAttendancePanel);
        revalidate();
        repaint();
    }

    // Registers a member by constructing a GymMember object and adding it to membersManager.
    // REQUIRES: Inputs need to respect REQUIRES of GymMember in GymMember.
    // MODIFIES: GymMember
    // EFFECTS: sets up the logMemberAttendance action (creates a GymMember with a username, allowed missed days,
    // base membership cost, and an attendance log, (amongst another things))
    public void logMemberAttendance(JTextField textBoxName, JTextField textBoxLogDate, JTextField textBoxHours) {

        String returnstring = "Member not found.";

        GymMember foundMember =
                financiallyFitModel.findGymMemberPublic(membersManager.getMembers(),textBoxName.getText());

        if (foundMember != null) {
            foundMember.logAttendance(Double.parseDouble(textBoxHours.getText()), textBoxLogDate.getText());

            returnstring = textBoxHours.getText() + " hours logged for "
                    + textBoxName.getText() + " on " + textBoxLogDate.getText();
        }
        int option = JOptionPane.showOptionDialog(
                null, returnstring, "Information",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                UIManager.getIcon("OptionPane.informationIcon"),
                new Object[]{"OK"},
                "OK");

        if (option == 0) {
            displayAppropriateMenu();
        }
    }


    // MODIFIES: container
    // EFFECTS: adds the CalculateMonthlyBill button to the container
    public void addCalculateMonthlyBillButton(Container container) {
        JButton button = new JButton("Calculate Monthly Bill");
        setUpButton(button, 640, 55, 28);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPlaySoundEffect();
                initializeCalculateMonthlyBill();
            }
        });
        container.add(button);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    // MODIFIES: this
    // EFFECTS: initializes Calculate Monthly Bill Panel and displays it
    private void initializeCalculateMonthlyBill() {
        JPanel monthlyBillPanel = new JPanel();
        initializeNewPanel(monthlyBillPanel);
        addLabel("Enter member name: ", monthlyBillPanel, 650, 60, 25);
        JTextField textBoxName = new JTextField();
        addEmptyTextBox(monthlyBillPanel, textBoxName);
        JButton button = new JButton("Okay");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateMonthlyBill(textBoxName);
            }
        });
        setUpButton(button, 100, 60, 15);
        monthlyBillPanel.add(button);

        setContentPane(monthlyBillPanel);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: sets up the CalculateMonthlyBill action
    private void calculateMonthlyBill(JTextField textBoxName) {
        String returnstring = "Member not found.";

        double result = financiallyFitModel.calculateMonthlyBillPublic(
                membersManager.getMembers(), textBoxName.getText());
        if (result != -1) {
            returnstring = "Monthly Bill for " + textBoxName.getText()
                    + ": $" + result + "\nNote: attending the gym more often will reduce your total amount due ";
        }

        int option = JOptionPane.showOptionDialog(
                null, returnstring, "Information",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                UIManager.getIcon("OptionPane.informationIcon"),
                new Object[]{"OK"},
                "OK");

        if (option == 0) {
            displayAppropriateMenu();
        }

    }

    // MODIFIES: container
    // EFFECTS: adds the addViewMembers button to the container
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

    // MODIFIES: container
    // EFFECTS: adds the CheckAttendanceOfMembersForDay button to the container
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

    // MODIFIES: container
    // EFFECTS: adds the addSave button to the container
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

    // EFFECTS: saves the current MembersManager instance to file and displays the result.
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
                null, returnString,
                "Information",
                JOptionPane.INFORMATION_MESSAGE,
                UIManager.getIcon("OptionPane.informationIcon"));
    }

    // MODIFIES: container
    // EFFECTS: adds the addLoad button to the container
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

    // EFFECTS: loads the current MembersManager instance from file and displays the result.
    private void initializeLoad() {
        String returnString;
        try {
            membersManager = jsonReader.read();
            returnString = "Successfully loaded instance from " + JSON_FILEPATH;
        } catch (IOException ioe) {
            returnString = "Unable to read from file: " + JSON_FILEPATH;
        }
        int option = JOptionPane.showOptionDialog(
                null, returnString, "Information",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
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

    // MODIFIES: this
    // EFFECTS: exits the program
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


    // EFFECTS: Displays the splash screen
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

    // EFFECTS:  simulates a loading process
    private void simulateLoading() {
        List<Integer> timerList = initiateTimerList();

        Timer timer = new Timer(20, new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (progress < 100) {
                    if (timerList.contains(progress)) {
                        threadSleepTime(100);
                    }
                    progress++;
                    progressBar.setValue(progress);
                } else {
                    ((Timer) e.getSource()).stop();
                    threadSleepTime(500);
                }
            }
        });

        timer.start();
    }

    // EFFECTS: ##(utility : simulateLoading)##
    // makes the tread sleep for a given amount of time.
    private static void threadSleepTime(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }


    // EFFECTS: ##(utility : simulateLoading)##
    // initializes the list used to induce random pauses in the loading list.
    private static List<Integer> initiateTimerList() {
        Random random = new Random();
        List<Integer> timerList = new ArrayList<>();
        int randomValue1 = random.nextInt(99) + 1;
        int randomValue2 = random.nextInt(99) + 1;
        timerList.add(randomValue1);
        timerList.add(randomValue2);
        return timerList;
    }
}
