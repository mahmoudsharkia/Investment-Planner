package model;

import org.json.JSONObject;
import persistence.Writable;

//REFERENCE: Some methods in this class were written based on the code from the WorkRoom demo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// An investment of one of three types: Cryptocurrency,Real Estate, or Stocks
public class Investment implements Writable {
    private String name;
    private int amount;
    private String type;


    //EFFECTS: if the amount > 0: Constructs a new Investment with a name,
    // a type, and the amount of money given as input; otherwise
    // constructs an Investment but the amount is zero.
    public Investment(String name, String type, int amount) {
        if (amount > 0) {
            this.name = name;
            this.amount = amount;
            this.type = type;
        } else {
            this.amount = 0;
        }
    }

    //setters

    //MODIFIES: this
    //EFFECTS: Sets the name of the investment to the value of a passed parameter
    public void setName(String n) {
        name = n;
    }

    //MODIFIES: this
    //EFFECTS: Sets the amount of money invested to the value of a passed parameter
    public void setAmount(int b) {
        amount = b;
    }

    //MODIFIES: this
    //EFFECTS: Sets the type of the investment to the value of a passed parameter
    public void setType(String t) {
        type = t;
    }

    //getters

    //EFFECTS: returns the amount of money invested
    public int getAmount() {
        return amount;
    }

    //EFFECTS: returns the name of investment
    public String getName() {
        return name;
    }

    //EFFECT: returns the type of investment
    public String getType() {
        return type;
    }

    // EFFECTS: returns string representation of this investment
    public String toString() {
        return type + ": " + name + ": " + amount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("amount", amount);
        return json;
    }
}




