package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try{
            JSONObject sandwichAsJson = new JSONObject(json);
            sandwich = new Sandwich();
            String name = sandwichAsJson.getJSONObject("name").optString("mainName");

            List<String> alsoKnownAs = sandwichAsJson.optJSONArray("alsoKnownAs") != null ? Arrays.asList(sandwichAsJson.optJSONArray("alsoKnownAs").toString()
                    .replace("},{", " ,").split(",")) : Collections.<String>emptyList();

            String origins = sandwichAsJson.optString("placeOfOrigin");
            String description = sandwichAsJson.optString("description");
            String image = sandwichAsJson.optString("image");

            List<String> ingredients = sandwichAsJson.optJSONArray("ingredients") != null ? Arrays.asList(sandwichAsJson.optJSONArray("ingredients").toString()
                    .replace("},{", " ,")) : Collections.<String>emptyList();

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


