package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//REFERENCE: Some methods in this class were written based on the code from the WorkRoom demo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//The collection of all the investments made by the user in the app
public class InvestmentList implements Writable {
    private int num;
    private ArrayList<Investment> investments;
    private int total;


    // REQUIRES: num >= 0, since it's a number of items in a list
    // EFFECTS: constructs an empty list of investments, number of items in list referenced by input parameter num
    public InvestmentList(int num) {
        {
            this.num = num;
            this.investments = new ArrayList<>();
        }
    }

    //MODIFIES: this
    //EFFECTS: sets the number of items in the list to parameter s value
    public void setNumInvestments(int s) {
        num = s;
    }

    //EFFECTS: returns the value for current number investments in the list
    public int getNumInvestments() {
        return num;
    }

    //MODIFIES: this
    //EFFECTS: inserts an investment (i) to the list of investments
    public void addInvestment(Investment i) {
        investments.add(i);
        EventLog.getInstance().logEvent(new Event("New investment added to list: " + i.getName()));
    }

    //EFFECTS: returns the value an investment from a list of investments
    public Investment getInvestment(int i) {
        return investments.get(i);

    }

    //EFFECTS: returns the list of all currently added investments
    public ArrayList<Investment> getInvestmentList() {
        return investments;
    }

    public void emptyList() {
        investments.clear();
        EventLog.getInstance().logEvent(new Event("Investment list was reset"));
    }

    //EFFECTS: Adds up all the amounts from different investments in the list and returns the total
    //MODIFIES: this
    public int totalInvested() {
        for (int i = 0; i < investments.size(); i++) {
            total = total + investments.get(i).getAmount();
        }
        EventLog.getInstance().logEvent(new Event("Calculated the total money invested: $" + total));

        return total;
    }


    //EFFECTS: Sets a given investment's parameters to the data of the input parameters
    public void setInvestmentData(Investment i, String n, String t, int a) {
        i.setType(t);
        i.setName(n);
        i.setAmount(a);
        EventLog.getInstance().logEvent(new Event("An investment was updated in the list"));
    }


    @Override
    public JSONObject toJson() {
        EventLog.getInstance().logEvent(new Event("All investments were saved"));
        JSONObject json = new JSONObject();
        json.put("size", investments.size());
        json.put("investments", investmentsToJson());
        return json;

    }


    // EFFECTS: returns investments in this investment list as a JSON array
    private JSONArray investmentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Investment i : investments) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }


}






