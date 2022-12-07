package model;

import model.Investment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvestmentTest {
    private Investment testInvestment;

    @BeforeEach
    void runBefore() {
        testInvestment = new Investment("BTC", "Crypto", 500);
    }

    @Test
    void testConstructor() {
        assertEquals("BTC", testInvestment.getName());
        assertEquals("Crypto", testInvestment.getType());
        assertEquals(500, testInvestment.getAmount());
        testInvestment.setAmount(0);
        testInvestment.setType("Real Estate");
        testInvestment.setName("House");
        assertTrue(0 == testInvestment.getAmount());
    }

    @Test
    void testConstructorNegAmount() {
        testInvestment = new Investment("AMAZN", "Stock", -54);
        assertEquals(0, testInvestment.getAmount());

    }

    @Test
    void toStringTest() {
        assertEquals("Crypto: BTC: 500", testInvestment.toString());
    }

}


