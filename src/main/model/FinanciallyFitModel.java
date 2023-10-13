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
        return calculateMonthlyBillModel(members, billMemberName);
    }

    public GymMember findGymMemberPublic(List<GymMember> members, String memberName) {
        return findGymMember(members, memberName);
    }

}
