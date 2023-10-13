package ui;

import model.FinanciallyFitModel;
import model.GymMember;

import java.util.*;


/*
 * Represents the Gym Interface.
 */
public class FinanciallyFitUI extends FinanciallyFitModel  {
    public static void main(String[] args) {
        List<GymMember> members = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            displayMenu();
            System.out.print("Please enter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1") || choice.equals("r")) {
                registerMember(scanner, members);

            } else if (choice.equals("2") || choice.equals("l")) {
                logMemberAttendance(scanner, members);

            } else if (choice.equals("3") || choice.equals("c")) {
                calculateMonthlyBillUI(scanner, members);

            } else if (choice.equals("4") || choice.equals("v")) {
                viewMembers(members);

            } else if (choice.equals("5") || choice.equals("e")) {
                exit();

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("__________________________");
        System.out.println("~FinanciallyFit Terminal~");
        System.out.println("__________________________");
        System.out.println("1. (r)egister member");
        System.out.println("2. (l)og member attendance");
        System.out.println("3. (c)alculate monthly bill");
        System.out.println("4. (v)iew members");
        System.out.println("5. (e)xit");
    }

    private static void exit() {
        System.out.println("Exiting the FinanciallyFit terminal. Goodbye!");
        System.exit(0);
    }

    private static void viewMembers(List<GymMember> members) {
        if (members.isEmpty()) {
            System.out.println("No members are registered!");
        } else {
            System.out.println("_______________________________________________________________________");
            System.out.println("Member Name     Registration Date    Total Hours    Days Attended");
            for (GymMember m : members) {
                System.out.println(m.getName() + "       " + m.getRegDate() + "                  "
                        + m.getTotalHours() + "           " + m.getAttendanceCount() + "/"
                        + m.getNumOfDaysLeftInMonth());

            }
        }
    }


    private static void calculateMonthlyBillUI(Scanner scanner, List<GymMember> members) {
        System.out.print("Enter member name: ");
        String billMemberName = scanner.nextLine();
        double result = calculateMonthlyBillModel(members, billMemberName);
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
    private static void logMemberAttendance(Scanner scanner, List<GymMember> members) {
        System.out.print("Enter member name: ");
        String memberName = scanner.nextLine();
        System.out.println("Enter date to log attendance for member " + memberName + " (YYYY-mm-dd):");
        String logDate = scanner.nextLine();

        GymMember foundMember = findGymMember(members, memberName);

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
    private static void registerMember(Scanner scanner, List<GymMember> members) {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of registration (YYYY-mm-dd): ");
        String regDate = scanner.next();
        System.out.print("Enter number of days allowed missed: ");
        Integer allowedMiss = scanner.nextInt();

        GymMember member = new GymMember(name, regDate, allowedMiss);
        members.add(member);
        System.out.println(name + " has been registered.");
    }

}