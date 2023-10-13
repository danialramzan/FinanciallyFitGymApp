package ui;

import model.GymMember;

import java.util.*;


/*
 * Represents the Gym Interface.
 */
public class FinanciallyFitUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<GymMember> members = new ArrayList<>();
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.println("Please enter your choice: ");
            String choice = scanner.next();

            if (choice.equals("1") || choice.equals("r")) {
                registerMember(scanner, members);

            } else if (choice.equals("2") || choice.equals("l")) {
                logMemberAttendance(scanner, members);

            } else if (choice.equals("3") || choice.equals("c")) {
                calculateMonthlyBill(scanner, members);

            } else if (choice.equals("4") || choice.equals("v")) {
                viewMembers(members);

            } else if (choice.equals("5") || choice.equals("e")) {
                running = exit();

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

    private static boolean exit() {
        System.out.println("Exiting the FinanciallyFit terminal. Goodbye!");
        System.exit(0);
        return false;
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


    private static void calculateMonthlyBill(Scanner scanner, List<GymMember> members) {
        System.out.print("Enter member name: ");
        String billMemberName = scanner.nextLine();
        GymMember billedMember = null;

        for (GymMember m : members) {
            if (m.getName().equalsIgnoreCase(billMemberName)) {
                billedMember = m;
                break;
            }
        }

        if (billedMember != null) {
            double monthlyBill = billedMember.getMonthlyBill();
            System.out.println("Monthly Bill for " + billMemberName + ": $" + monthlyBill);
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

        GymMember foundMember = null;
        for (GymMember m : members) {
            if (m.getName().equals(memberName)) {
                foundMember = m;
                break;
            }
        }

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