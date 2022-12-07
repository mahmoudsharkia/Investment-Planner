package persistence;

import model.Investment;
import model.InvestmentList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//REFERENCE: This class was written based on the code from the WorkRoom demo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads the list of investments from JSON data stored in file
public class Reader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public Reader(String source) {
        this.source = source;
    }


    // EFFECTS: reads list of investments from file and returns it;
    // throws IOException if an error occurs reading data from file
    public InvestmentList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInvestmentList(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses investment list from JSON object and returns it
    private InvestmentList parseInvestmentList(JSONObject jsonObject) {
        int num = jsonObject.getInt("size");
        InvestmentList t = new InvestmentList(num);
        addInvestments(t, jsonObject);
        return t;
    }

    // MODIFIES: t
    // EFFECTS: parses investments from JSON object and adds them to list of investments
    private void addInvestments(InvestmentList t, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("investments");
        for (Object json : jsonArray) {
            JSONObject nextInvestment = (JSONObject) json;
            addInvestment(t, nextInvestment);
        }
    }

    // MODIFIES: t
    // EFFECTS: parses investment from JSON object and adds it to InvestmentList
    private void addInvestment(InvestmentList t, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        int amount = jsonObject.getInt("amount");
        Investment i = new Investment(name, type, amount);
        t.addInvestment(i);
    }
}




