package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try{
            JSONObject sandwichAsJson = new JSONObject(json);
            sandwich = new Sandwich();
            String name = !sandwichAsJson.getJSONObject("name").optString("mainName").equals("") ? sandwichAsJson.getJSONObject("name").optString("mainName") : "We don't know";

            List<String> alsoKnownAs = sandwichAsJson.optJSONArray("alsoKnownAs") != null ? Arrays.asList(sandwichAsJson.optJSONArray("alsoKnownAs").toString()
                    .replace("},{", " ,").split(",")) : Collections.singletonList("No other name we know about");

            String origins = !sandwichAsJson.optString("placeOfOrigin").equals("") ? sandwichAsJson.optString("placeOfOrigin") : "Unknown";
            String description = !sandwichAsJson.optString("description").equals("") ? sandwichAsJson.optString("description") : "Contact us to tell us what this sandwich is about :)";
            String image = sandwichAsJson.optString("image");

            List<String> ingredients = sandwichAsJson.optJSONArray("ingredients") != null ? Arrays.asList(sandwichAsJson.optJSONArray("ingredients").toString()
                    .replace("},{", " ,").split(",")) : Collections.singletonList("We don't know yet! Contact us to tell us :)");

            sandwich.setMainName(name);
            sandwich.setAlsoKnownAs(alsoKnownAs);
            sandwich.setPlaceOfOrigin(origins);
            sandwich.setDescription(description);
            sandwich.setImage(image);
            sandwich.setIngredients(ingredients);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return sandwich;
    }
}


