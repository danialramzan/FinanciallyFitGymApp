package model;

import java.util.*;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

/*
 * Represents a Gym Member.
 */
public class GymMember {
    private final String username;
    private int attendanceCount;
    private final double baseMembershipCost;
    private final double dailyPenalty;
    private final int allowedMiss;
    private final int numOfDaysLeftInMonth;
    private Map<String, Double> attendanceLog;

    private static final int DAILY_FEE_MULTIPLIER = 1;
    private static final int DAILY_PENALTY = 15;



    // Constructs the GymMember Object
    // MODIFIES: this
    // EFFECTS: creates a GymMember with a username, allowed missed days,
    // base membership cost, and an attendance log, (amongst another things)
    // !!! allowedMiss and registrationDate are governed by a REQUIRES clause
    // !!! stated in the calling function (registerMember in FinanciallyFit)
    public GymMember(String name, String regDate, Integer allowedMiss) {

        LocalDate registrationDate = LocalDate.parse(regDate);
        this.numOfDaysLeftInMonth = (registrationDate.with(lastDayOfMonth()).getDayOfMonth()
                - registrationDate.getDayOfMonth() + 1);
        this.username = (name + "_" + registrationDate);
        this.baseMembershipCost = numOfDaysLeftInMonth * DAILY_FEE_MULTIPLIER;
        this.dailyPenalty =  DAILY_PENALTY;
        this.allowedMiss = allowedMiss;
        this.attendanceCount = 0;
        this.attendanceLog = new HashMap<>();
    }


    // Logs Attendance
    // REQUIRES: - logDate should be in format YYYY-MM-DD
    //           - logDate should be on, or after regDate
    //           - logDate should be in the same month as regDate
    // MODIFIES: this
    // EFFECTS: logs the attendance of the user for a date
    // - If the date is already populated, it is replaced by the new entry
    // - If not, a new entry is created and the attendance count is incremented by 1
    public void logAttendance(double hours, String logDate) {
        if (attendanceLog.containsKey(String.valueOf(logDate))) {
            attendanceLog.put(String.valueOf(logDate), hours);
        } else {
            attendanceLog.put(String.valueOf(logDate), hours);
            attendanceCount++;
        }
    }


    // EFFECTS: returns monthly bill of user by applying the missed day algorithm.
    public double getMonthlyBill() {
        if (attendanceCount > (numOfDaysLeftInMonth - allowedMiss)) {
            return baseMembershipCost;
        } else {
            return baseMembershipCost + (dailyPenalty * ((numOfDaysLeftInMonth - attendanceCount)  - allowedMiss));
        }

    }


    // EFFECTS: returns the name of the user, extracts it from username
    public String getName() {
        return username.substring(0, username.length() - 11);
    }


    // EFFECTS: returns the registration date
    public String getRegDate() {
        return username.substring(username.length() - 10);
    }


    // EFFECTS: returns the number of days the user has logged their attendance
    public int getAttendanceCount() {
        return attendanceCount;
    }

    // EFFECTS: returns the number of days the user is allowed to miss their attendance
    public int getAllowedMiss() {
        return allowedMiss;
    }


    // EFFECTS: returns the number of days between registration date and the end of the month.
    public int getNumOfDaysLeftInMonth() {
        return numOfDaysLeftInMonth;
    }


    // EFFECTS: returns total number of hours spent by user in the gym since registration.
    public double getTotalHours() {
        double sum = 0;
        for (Double hours : attendanceLog.values()) {
            sum += hours;
        }
        return sum;
    }

    // EFFECTS: returns daily fee multiplier
    public int getDailyFeeMultiplier() {
        return DAILY_FEE_MULTIPLIER;
    }

    // EFFECTS: returns daily fee penalty
    public int getDailyPenalty() {
        return DAILY_PENALTY;
    }

}

