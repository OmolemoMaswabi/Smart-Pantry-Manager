
package za.ac.richfield.smartpantry;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipesActivity extends Activity {

 @Override
 public void onCreate(Bundle b) {
  super.onCreate(b);
 }

 @Override
 public void onResume() {
  super.onResume();
  showRecipes();
 }

 private void showRecipes() {

  Db db = new Db(this);

  LinearLayout layout = Ui.page(this, "Suggested Recipes");

  Ui.nav(this, layout);

  ArrayList<String> names = new ArrayList<>();
  ArrayList<Long> ids = new ArrayList<>();

  // Retrieve recipes from SQLite
  try (Cursor cursor = db.recipes()) {

   while (cursor.moveToNext()) {

    String ingredients = cursor.getString(2);

    // Only suggest recipes with enough ingredients
    if (db.matches(ingredients)) {

     ids.add(cursor.getLong(0));
     names.add(cursor.getString(1));
    }
   }
  }

  // Display the number of matching recipes
  if (names.isEmpty()) {
   Ui.text(
           this,
           layout,
           "No recipes match your pantry yet. Add more ingredients."
   );
  } else {
   Ui.text(
           this,
           layout,
           names.size() + " recipes you can make right now"
   );
  }

  // Create the recipe list
  ListView list = new ListView(this);

  ArrayAdapter<String> adapter =
          new ArrayAdapter<String>(
                  this,
                  android.R.layout.simple_list_item_1,
                  names
          ) {

           @Override
           public View getView(
                   int position,
                   View convertView,
                   ViewGroup parent
           ) {

            TextView row = (TextView) super.getView(
                    position,
                    convertView,
                    parent
            );

            row.setTextSize(16);

            int padding = (int) (
                    16 * getResources()
                            .getDisplayMetrics().density
            );

            row.setPadding(
                    padding,
                    padding,
                    padding,
                    padding
            );

            row.setMinHeight((int) (
                    56 * getResources()
                            .getDisplayMetrics().density
            ));

            return row;
           }
          };

  list.setAdapter(adapter);

  // FIX: Let ListView fill the remaining screen
  // and scroll independently.
  layout.addView(
          list,
          new LinearLayout.LayoutParams(
                  LinearLayout.LayoutParams.MATCH_PARENT,
                  0,
                  1.0f
          )
  );

  // Open recipe details when a recipe is selected
  list.setOnItemClickListener(
          (parent, view, position, id) -> {

           Intent intent = new Intent(
                   RecipesActivity.this,
                   RecipeDetailActivity.class
           );

           intent.putExtra(
                   "recipe_id",
                   ids.get(position)
           );

           startActivity(intent);
          }
  );
 }
}

