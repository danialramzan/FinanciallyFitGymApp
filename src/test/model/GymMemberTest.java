package model;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GymMemberTest {

    @BeforeEach
    void BeforeEach() {
        GymMember testGymMember;
    }

    @Test
    void testConstructorEmpty() {
        GymMember testGymMember = new GymMember("danial", "2023-01-01", 0);
        assertEquals(((testGymMember.getNumOfDaysLeftInMonth())
                * testGymMember.getDailyFeeMultiplier())
                + (testGymMember.getDailyPenalty() * ((testGymMember.getNumOfDaysLeftInMonth()
                - testGymMember.getAttendanceCount())  - testGymMember.getAllowedMiss())),
                testGymMember.getMonthlyBill());
        assertEquals("danial",testGymMember.getName());
        assertEquals("2023-01-01",testGymMember.getRegDate());
        assertEquals(0, testGymMember.getTotalHours());


    }

    @Test
    void testConstructorPopulated() {
        GymMember testGymMember = new GymMember("danial", "2023-01-01", 0);
        testGymMember.logAttendance(4.5, "2023-01-01");
        assertEquals(4.5, testGymMember.getTotalHours());
        testGymMember.logAttendance(5.5, "2023-01-01");
        assertEquals("2023-01-01",testGymMember.getRegDate());
        assertEquals(5.5, testGymMember.getTotalHours());


    }

    @Test
    void testConstructorAttendanceMoreThanRequirement() {
        GymMember testGymMember = new GymMember("danial", "2020-02-29", 0);
        testGymMember.logAttendance(4.5, "2020-02-29");
        assertEquals(((testGymMember.getNumOfDaysLeftInMonth())
                        * testGymMember.getDailyFeeMultiplier())
                        + (testGymMember.getDailyPenalty() * ((testGymMember.getNumOfDaysLeftInMonth()
                        - testGymMember.getAttendanceCount())  - testGymMember.getAllowedMiss())),
                testGymMember.getMonthlyBill());


    }




}
