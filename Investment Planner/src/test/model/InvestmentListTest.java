package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvestmentListTest {
    private InvestmentList testInvestmentList;


    @BeforeEach
    void runBefore() {
        testInvestmentList = new InvestmentList(5);
    }

    @Test
    void testConstructor() {
        assertEquals(5, testInvestmentList.getNumInvestments());
        testInvestmentList.setNumInvestments(0);
        assertTrue(testInvestmentList.getNumInvestments() == 0);
    }

    @Test
    void testConstructorNegTotalAssets() {
        assertFalse(testInvestmentList.getNumInvestments() == -10);

    }

    @Test
    void testConstructorIntegerTotalAssets() {
        assertFalse(testInvestmentList.getNumInvestments() == 2.5);
        assertFalse(testInvestmentList.getNumInvestments() == 0.5);
        assertFalse(testInvestmentList.getNumInvestments() == -5.5);


    }

    @Test
    void testAddInvestment() {
        Investment i = new Investment("tesla", "stocks", 5000);
        Investment c = new Investment("crypto", "bitcoin", 500);
        testInvestmentList = new InvestmentList(3);
        testInvestmentList.addInvestment(i);
        testInvestmentList.addInvestment(c);
        assertEquals(i, testInvestmentList.getInvestment(0));
        assertTrue(testInvestmentList.getInvestment(1) == c);

    }

    @Test
    void emptyListTest() {
        Investment i = new Investment("tesla", "stocks", 5000);
        Investment c = new Investment("crypto", "bitcoin", 500);
        testInvestmentList.addInvestment(i);
        testInvestmentList.addInvestment(c);
        assertEquals(2, testInvestmentList.getInvestmentList().size());
        testInvestmentList.emptyList();
        assertEquals(0, testInvestmentList.getInvestmentList().size());
    }

    @Test
    void totalInvestedTest() {
        Investment i = new Investment("tesla", "stocks", 5000);
        Investment c = new Investment("crypto", "bitcoin", 500);
        testInvestmentList.addInvestment(i);
        testInvestmentList.addInvestment(c);
        assertEquals(5500, testInvestmentList.totalInvested());
    }

    @Test
    void setInvestmentDataTest(){
        Investment i = new Investment("tesla", "stocks", 5000);
        testInvestmentList.addInvestment(i);
        testInvestmentList.setInvestmentData(i,"test", "test2", 10);
        assertTrue(i.getName() == "test");
        assertTrue(i.getType() == "test2");
        assertTrue(i.getAmount() == 10);
        assertFalse(i.getName() == "tesla");
        assertFalse(i.getType() == "stocks");
        assertFalse(i.getAmount() == 5000);


    }
    


}