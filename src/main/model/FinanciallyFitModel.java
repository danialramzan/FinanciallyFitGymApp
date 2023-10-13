package model;

import java.util.List;

public class FinanciallyFitModel {

    private double calculateMonthlyBillModel(List<GymMember> members, String billMemberName) {
        GymMember billedMember = null;

        for (GymMember m : members) {
            if (m.getName().equalsIgnoreCase(billMemberName)) {
                billedMember = m;
                break;
            }
        }

        if (billedMember != null) {
            return billedMember.getMonthlyBill();
        } else {
            return -1;
        }
    }

    private GymMember findGymMember(List<GymMember> members, String memberName) {
        GymMember foundMember = null;
        for (GymMember m : members) {
            if (m.getName().equals(memberName)) {
                foundMember = m;
                break;
            }
        }
        return foundMember;
    }


    public double calculateMonthlyBillPublic(List<GymMember> members, String billMemberName) {
        // This is a public method that acts as an interface to the private method.
        return calculateMonthlyBillModel(members, billMemberName);
    }

    public GymMember findGymMemberPublic(List<GymMember> members, String memberName) {
        // This is a public method that acts as an interface to the private method.
        return findGymMember(members, memberName);
    }

}
