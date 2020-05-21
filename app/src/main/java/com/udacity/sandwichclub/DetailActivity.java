package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mDescription;
    private TextView mIngredients;
    private TextView mAlsoKnownAs;
    private TextView mOrigins;
    private ImageView ingredientsIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
        mDescription = findViewById(R.id.description_tv);
        mIngredients = findViewById(R.id.ingredients_tv);
        mAlsoKnownAs = findViewById(R.id.also_known_tv);
        mOrigins = findViewById(R.id.origin_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }else{
            populateUI(sandwich);
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        StringBuilder aliases = new StringBuilder();
        for(int alias=0; alias<sandwich.getAlsoKnownAs().size(); alias++){
            aliases.append(sandwich.getAlsoKnownAs().get(alias));
            if(alias<sandwich.getAlsoKnownAs().size()-2){
                aliases.append(", ");
            }
        }
        mAlsoKnownAs.setText(aliases);
        mDescription.setText(sandwich.getDescription());
        mOrigins.setText(sandwich.getPlaceOfOrigin());
        StringBuilder ingredients = new StringBuilder();
        for(int ingredient=0; ingredient<sandwich.getIngredients().size(); ingredient++){
            ingredients.append(sandwich.getIngredients().get(ingredient));
            if(ingredient<sandwich.getIngredients().size()-2){
                ingredients.append(", ");
            }
        }
        mIngredients.setText(ingredients.toString());
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }
}
