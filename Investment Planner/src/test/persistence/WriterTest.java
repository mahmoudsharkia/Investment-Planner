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

public class WriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            InvestmentList t = new InvestmentList(1);
            Writer writer = new Writer("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyInvestmentList() {
        try {
            InvestmentList assets = new InvestmentList(0);
            Writer writer = new Writer("./data/testWriterEmptyInvestmentList.json");
            writer.open();
            writer.write(assets);
            writer.close();

            Reader reader = new Reader("./data/testWriterEmptyInvestmentList.json");
            assets = reader.read();
            assertEquals(0, assets.getNumInvestments());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralInvestmentList() {
        try {
            InvestmentList t = new InvestmentList(2);
            t.addInvestment(new Investment("BTC", "Crypto", 200));
            t.addInvestment(new Investment("House", "Real Estate", 500));
            Writer writer = new Writer("./data/testWriterGeneralInvestmentList.json");
            writer.open();
            writer.write(t);
            writer.close();

            Reader reader = new Reader("./data/testWriterGeneralInvestmentList.json");
            t = reader.read();
            List<Investment> investments = t.getInvestmentList();
            assertEquals(2, investments.size());
            checkInvestment("BTC", "Crypto", 200, investments.get(0));
            checkInvestment("House", "Real Estate", 500, investments.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
