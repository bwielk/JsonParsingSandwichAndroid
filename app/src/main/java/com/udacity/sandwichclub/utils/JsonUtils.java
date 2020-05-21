package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try{
            JSONObject sandwichAsJson = new JSONObject(json);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return sandwich;
    }
}


