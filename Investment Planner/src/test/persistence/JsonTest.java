package persistence;

//REFERENCE: This class was written based on the code from the WorkRoom demo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Investment;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class JsonTest {
        protected void checkInvestment(String name, String type, int amount, Investment i) {
            assertEquals(name, i.getName());
            assertEquals(type, i.getType());
            assertEquals(amount, i.getAmount());
        }
    }
