package model;

import java.util.ArrayList;
import java.util.List;

public class MembersManager {

    List<GymMember> members;

    public MembersManager() {
        this.members = new ArrayList<>();
    }

    public List<GymMember> getMembers() {
        return members;
    }

    public void addMember(GymMember member) {
        members.add(member);
    }

    public void removeMember(GymMember member) {
        members.remove(member);
    }

    public int getSize() {
        return members.size();
    }

    public List<String> returnAttendanceDay(String date) {
        List<String> memberlist = new ArrayList<>();
        for (GymMember m : members) {
            if (m.getAttendanceLog().containsKey(date)) {
                memberlist.add(m.getName());
            }
        }
        return memberlist;
    }
}
