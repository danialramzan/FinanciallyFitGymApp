package model;

import java.util.List;

public class FinanciallyFitModel {

    protected static double calculateMonthlyBillModel(List<GymMember> members, String billMemberName) {
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
