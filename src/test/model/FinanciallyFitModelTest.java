package model;


import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FinanciallyFitModelTest {

    private GymMember testGymMember;
    private GymMember testGymMember2;
    private ArrayList<GymMember> testList;

    @BeforeEach
    void BeforeEach() {
        testList = new ArrayList<>();
        testGymMember = new GymMember("danial", "2023-01-31", 0);
        testGymMember2 = new GymMember("gregor", "2023-01-02", 0);
        testList.add(testGymMember);
        testList.add(testGymMember2);

    }

    @Test
    void testReturnFeeNotInList() {
        assertEquals(-1, FinanciallyFitModel.calculateMonthlyBillModel(testList, "biden"));
    }

    @Test
    void testReturnFeeInList() {
        assertEquals(16, FinanciallyFitModel.calculateMonthlyBillModel(testList, "danial"));
    }

    @Test
    void testReturnMemberNotInList() {
        assertNull(FinanciallyFitModel.findGymMember(testList, "biden"));
    }

    @Test
    void testReturnMemberInList() {
        assertEquals(testGymMember, FinanciallyFitModel.findGymMember(testList, "danial"));
    }
}
