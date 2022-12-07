package persistence;

import model.Investment;
import model.InvestmentList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//REFERENCE: This class was written based on the code from the WorkRoom demo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class ReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        Reader reader = new Reader("./data/noSuchFile.json");
        try {
            InvestmentList t = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyInvestmentList() {
        Reader reader = new Reader("./data/testReaderEmptyInvestmentList.json");
        try {
            InvestmentList t = reader.read();
            assertEquals(0, t.getNumInvestments());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralInvestmentList() {
        Reader reader = new Reader("./data/testReaderGeneralInvestmentList.json");
        try {
            InvestmentList t = reader.read();
            List<Investment> investments = t.getInvestmentList();
            assertEquals(2, t.getNumInvestments());
            checkInvestment("BTC", "Crypto", 200, investments.get(0));
            checkInvestment("House", "Real Estate", 500, investments.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}


