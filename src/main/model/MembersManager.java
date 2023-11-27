package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a GymMembers List (Y).
 */
public class MembersManager implements Writable {

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
        EventLog.getInstance().logEvent(new Event(
                "Member " + member.getName() +
                        "registered on" +member.getRegDate() +
                        " with allowed missed days" + member.getAllowedMiss()
                ));
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

    // REQUIRES: date input needs to be a String and follow YYYY-MM-DD format
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

    // EFFECTS: puts the JSONArray of list of GymMembers into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Gym Members", gymMembersToJson());
        return json;
    }

    // EFFECTS: returns things in this MembersManager as a JSON array
    private JSONArray gymMembersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GymMember m : members) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }

    public static void printLog(EventLog el)
    //  throws LogException
    {
//        try {
        for (Event next : el) {
            System.out.println(next.toString());
            System.out.println("\n\n");
//            }
//        } catch (LogException e) {
//            throw new LogException("Cannot write to file");
//        }
        }
    }
}
