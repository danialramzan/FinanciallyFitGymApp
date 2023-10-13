package model;

import com.sun.jdi.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
