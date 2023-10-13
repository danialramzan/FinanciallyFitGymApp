package model;

import java.util.List;

public class FinanciallyFitModel {

    protected static void calculateMonthlyBillModel(List<GymMember> members, String billMemberName) {
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

    protected static GymMember findGymMember(List<GymMember> members, String memberName) {
        GymMember foundMember = null;
        for (GymMember m : members) {
            if (m.getName().equals(memberName)) {
                foundMember = m;
                break;
            }
        }
        return foundMember;
    }

}
