package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        //Json properties
        final String NAME = "name";

        final String MAIN_NAME = "mainName";

        final String ALSO_KNOWN = "alsoKnownAs";

        final String PLACE_OF_ORIGIN = "placeOfOrigin";

        final String DESCRIPTION = "description";

        final String IMAGE = "image";

        final String INGREDIENTS = "ingredients";

        Sandwich sandwich = new Sandwich();

        try {

            JSONObject sandwichJsonObject = new JSONObject(json);

            JSONObject nameJsonObject = sandwichJsonObject.getJSONObject(NAME);

            JSONArray alsoKnownJsonArray = nameJsonObject.getJSONArray(ALSO_KNOWN);

            List<String> alsoKnownTempList = new ArrayList<>();

            List<String> ingredientsTempList = new ArrayList<>();

            for (int i = 0; i < alsoKnownJsonArray.length(); i++) {
                alsoKnownTempList.add(alsoKnownJsonArray.getString(i));
            }

            String imageJson = sandwichJsonObject.getString(IMAGE);

            String placeOfOriginJson = sandwichJsonObject.getString(PLACE_OF_ORIGIN);

            String descriptionJson = sandwichJsonObject.getString(DESCRIPTION);

            JSONArray ingredientsJsonArray = sandwichJsonObject.getJSONArray(INGREDIENTS);

            for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                ingredientsTempList.add(ingredientsJsonArray.getString(i));
            }

            //Building Sandwich Object
            sandwich.setMainName(nameJsonObject.getString(MAIN_NAME));
            sandwich.setAlsoKnownAs(alsoKnownTempList);
            sandwich.setIngredients(ingredientsTempList);
            sandwich.setImage(imageJson);
            sandwich.setDescription(descriptionJson);
            sandwich.setPlaceOfOrigin(placeOfOriginJson);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sandwich;
    }
}
