
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

public class MainActivity extends Activity {

 Db db;
 LinearLayout l;

 ArrayList<Long> ids = new ArrayList<>();
 ArrayList<String> labels = new ArrayList<>();

 @Override
 public void onCreate(Bundle b) {
  super.onCreate(b);
  db = new Db(this);
 }

 @Override
 public void onResume() {
  super.onResume();
  show();
 }

 void show() {

  // Create the main pantry screen
  l = Ui.page(this, "My Pantry");

  // Display navigation buttons
  Ui.nav(this, l);

  // Add ingredient button
  Ui.button(this, l, "+ Add ingredient", () -> {
   startActivity(
           new Intent(this, EditActivity.class)
   );
  });

  ids.clear();
  labels.clear();

  // Load pantry ingredients from SQLite
  try (Cursor c = db.pantry()) {

   while (c.moveToNext()) {

    ids.add(c.getLong(0));

    String name = c.getString(1);
    double quantity = c.getDouble(2);
    String unit = c.getString(3);
    String expiry = c.getString(4);

    String label = name + " — "
            + quantity + " " + unit;

    if (expiry != null && !expiry.isEmpty()) {
     label += " | expiry " + expiry;
    }

    labels.add(label);
   }
  }

  // Show message when pantry is empty
  if (labels.isEmpty()) {
   Ui.text(
           this,
           l,
           "No ingredients yet. Add your first item."
   );
  }

  // Create a scrollable ingredient list
  ListView list = new ListView(this);

  // Customize ingredient row appearance
  ArrayAdapter<String> adapter =
          new ArrayAdapter<String>(
                  this,
                  android.R.layout.simple_list_item_1,
                  labels
          ) {
           @Override
           public View getView(
                   int position,
                   View convertView,
                   ViewGroup parent) {

            TextView row = (TextView) super.getView(
                    position,
                    convertView,
                    parent
            );

            // Comfortable text size
            row.setTextSize(16);

            // Add spacing inside each row
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

            row.setMinHeight(
                    (int) (
                            56 * getResources()
                                    .getDisplayMetrics().density
                    )
            );

            return row;
           }
          };

  list.setAdapter(adapter);

  // Fill the remaining screen height
  LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(
                  LinearLayout.LayoutParams.MATCH_PARENT,
                  0,
                  1.0f
          );

  l.addView(list, params);

  // Open selected ingredient for editing
  list.setOnItemClickListener(
          (parent, view, position, id) -> {

           Intent intent = new Intent(
                   this,
                   EditActivity.class
           );

           intent.putExtra(
                   "id",
                   ids.get(position)
           );

           startActivity(intent);
          }
  );
 }
}
