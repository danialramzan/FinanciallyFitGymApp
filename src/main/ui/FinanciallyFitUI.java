package ui;

import model.FinanciallyFitModel;
import model.GymMember;
import model.MembersManager;

import java.util.*;


/*
 * Represents the Gym Interface.
 */
public class FinanciallyFitUI  {
    private FinanciallyFitModel financiallyFitModel = new FinanciallyFitModel();
    MembersManager membersManager = new MembersManager();


    public FinanciallyFitUI() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            displayMenu();
            System.out.print("Please enter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1") || choice.equals("r")) {
                registerMember(scanner);

            } else if (choice.equals("2") || choice.equals("d")) {
                deregisterMember(scanner);

            } else if (choice.equals("3") || choice.equals("l")) {
                logMemberAttendance(scanner);

            } else if (choice.equals("4") || choice.equals("c")) {
                calculateMonthlyBillUI(scanner);

            } else if (choice.equals("5") || choice.equals("v")) {
                viewMembers();

            } else if (choice.equals("6") || choice.equals("a")) {
                attendanceChecker(scanner);

            } else if (choice.equals("7") || choice.equals("e")) {
                exit();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displayMenu() {
        System.out.println("__________________________");
        System.out.println("~FinanciallyFit Terminal~");
        System.out.println("__________________________");
        System.out.println("1. (r)egister member");
        System.out.println("2. (d)eregister member");
        System.out.println("3. (l)og member attendance");
        System.out.println("4. (c)alculate monthly bill");
        System.out.println("5. (v)iew members");
        System.out.println("6. (a)ttendace of members for day");
        System.out.println("7. (e)xit");
    }

    private void exit() {
        System.out.println("Exiting the FinanciallyFit terminal. Goodbye!");
        System.exit(0);
    }

    private void attendanceChecker(Scanner scanner) {

        System.out.print("Enter date to check attendance for (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        if (membersManager.returnAttendanceDay(date).isEmpty()) {
            System.out.println("Nobody attended that day :(");
        } else {
            System.out.println("List of people who attended that day: " + membersManager.returnAttendanceDay(date));
        }
    }

    private void viewMembers() {

        if (membersManager.getMembers().isEmpty()) {
            System.out.println("No members are registered!");
        } else {
            System.out.println("_______________________________________________________________________");
            System.out.println("Member Name     Registration Date    Total Hours    Days Attended");
            for (GymMember m : membersManager.getMembers()) {
                System.out.println(m.getName() + "       " + m.getRegDate() + "                  "
                        + m.getTotalHours() + "           " + m.getAttendanceCount() + "/"
                        + m.getNumOfDaysLeftInMonth());

            }
        }
    }


    private void calculateMonthlyBillUI(Scanner scanner) {
        System.out.print("Enter member name: ");
        String billMemberName = scanner.nextLine();
        double result = financiallyFitModel.calculateMonthlyBillPublic(membersManager.getMembers(), billMemberName);
        if (result != -1) {
            System.out.println("Monthly Bill for " + billMemberName + ": $" + result);
            System.out.println("Note that as you attend the gym more often your total amount due will go down");
        } else {
            System.out.println("Member not found.");
        }
    }



    // Constructs the GymMember Object
    // REQUIRES: this
    // MODIFIES: this
    // EFFECTS: creates a GymMember with a username, allowed missed days,
    // base membership cost, and an attendance log, (amongst another things)
    // !!! allowedMiss and registrationDate are governed by a REQUIRES clause
    // !!! stated in the calling function (registerMember in FinanciallyFit)
    private void logMemberAttendance(Scanner scanner) {
        System.out.print("Enter member name: ");
        String memberName = scanner.nextLine();
        System.out.println("Enter date to log attendance for member " + memberName + " (YYYY-mm-dd):");
        String logDate = scanner.nextLine();

        GymMember foundMember = financiallyFitModel.findGymMemberPublic(membersManager.getMembers(), memberName);

        if (foundMember != null) {
            System.out.print("Enter time spent at the gym (hours): ");
            double hours = scanner.nextDouble();
            foundMember.logAttendance(hours, logDate);
            System.out.println(hours + " hours logged for " + memberName);
        } else {
            System.out.println("Member not found.");
        }
    }


    // Constructs the GymMember Object
    // REQUIRES: this
    // MODIFIES: this
    // EFFECTS: creates a GymMember with a username, allowed missed days,
    // base membership cost, and an attendance log, (amongst another things)
    // !!! allowedMiss and registrationDate are governed by a REQUIRES clause
    // !!! stated in the calling function (registerMember in FinanciallyFit)
    private void registerMember(Scanner scanner) {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of registration (YYYY-mm-dd): ");
        String regDate = scanner.next();
        System.out.print("Enter number of days allowed missed: ");
        Integer allowedMiss = scanner.nextInt();


        GymMember gymMember = new GymMember(name, regDate, allowedMiss);
        membersManager.addMember(gymMember);
        System.out.println(name + " has been registered.");
    }


    private void deregisterMember(Scanner scanner) {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        GymMember foundMember = financiallyFitModel.findGymMemberPublic(membersManager.getMembers(), name);

        if (foundMember != null) {
            membersManager.removeMember(foundMember);
            System.out.println(name + " has been deregistered.");
        } else {
            System.out.println("Member not found.");
        }

    }

}