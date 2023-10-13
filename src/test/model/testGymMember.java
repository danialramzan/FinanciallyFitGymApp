package model;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testGymMember {

    @BeforeEach
    void BeforeEach() {
        GymMember testGymMember;
    }

    @Test
    void testConstructor() {
        GymMember testGymMember = new GymMember("danial", "2023-01-01", 0);
        assertEquals(((testGymMember.getNumOfDaysLeftInMonth())
                * testGymMember.getDailyFeeMultiplier())
                + (testGymMember.getDailyPenalty() * ((testGymMember.getNumOfDaysLeftInMonth()
                - testGymMember.getAttendanceCount())  - testGymMember.getAllowedMiss())),
                testGymMember.getMonthlyBill());
    }


}
