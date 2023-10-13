package model;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a GymMembers List (Y).
 */
public class MembersManager {

    List<GymMember> members;


    // Constructs the MembersManager Object (Y)
    // EFFECTS: creates a MembersManager object containing a list members.
    public MembersManager() {
        this.members = new ArrayList<>();
    }

    // EFFECTS: returns list of members.
    public List<GymMember> getMembers() {
        return members;
    }


    // MODIFIES: this
    // EFFECTS: adds a GymMember to the MemberManager object. (X's in Y)
    public void addMember(GymMember member) {
        members.add(member);
    }

    // MODIFIES: this
    // EFFECTS: removes a GymMember from the MemberManager object.
    public void removeMember(GymMember member) {
        members.remove(member);
    }


    // EFFECTS: returns size of members list.
    public int getSize() {
        return members.size();
    }

    // REQUIRES: date input needs to follow YYYY-MM-DD format
    // EFFECTS: returns a list of all patrons who attended the gym on a certain date.
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
