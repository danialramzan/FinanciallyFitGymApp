package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MembersManagerTest {

    private GymMember testGymMember;
    private GymMember testGymMember2;
    private ArrayList<GymMember> testList;
    private FinanciallyFitModel financiallyFitModel;

    @BeforeEach
    void BeforeEach() {
        testList = new ArrayList<>();
        testGymMember = new GymMember("danial", "2023-01-31", 0);
        testGymMember2 = new GymMember("gregor", "2023-01-02", 0);
        financiallyFitModel = new FinanciallyFitModel();
        testList.add(testGymMember);
        testList.add(testGymMember2);

    }

    @Test
    void testReturnFeeNotInList() {
        assertEquals(-1, financiallyFitModel.calculateMonthlyBillPublic(testList, "biden"));
    }

    @Test
    void testReturnFeeInList() {
        assertEquals(16,
                financiallyFitModel.calculateMonthlyBillPublic(testList, "danial"));
    }

    @Test
    void testReturnMemberNotInList() {
        assertNull(financiallyFitModel.findGymMemberPublic(testList, "biden"));
    }

    @Test
    void testReturnMemberInList() {
        assertEquals(testGymMember, financiallyFitModel.findGymMemberPublic(testList, "danial"));
    }
}
