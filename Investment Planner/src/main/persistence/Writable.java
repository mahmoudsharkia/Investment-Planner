package persistence;

import org.json.JSONObject;

//REFERENCE: This class was written based on the code from the WorkRoom demo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
