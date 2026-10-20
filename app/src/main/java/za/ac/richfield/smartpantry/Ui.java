
package za.ac.richfield.smartpantry;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ui {

 // Creates the main screen without nesting a ListView
 // inside a ScrollView.
 static LinearLayout page(Activity a, String heading) {

  LinearLayout layout = new LinearLayout(a);
  layout.setOrientation(LinearLayout.VERTICAL);

  int padding = (int) (
          16 * a.getResources().getDisplayMetrics().density
  );

  layout.setPadding(
          padding, padding, padding, padding
  );

  layout.setBackgroundColor(
          Color.rgb(247, 250, 246)
  );

  // Heading
  TextView title = new TextView(a);
  title.setText(heading);
  title.setTextSize(25);
  title.setTextColor(
          Color.rgb(22, 77, 49)
  );

  layout.addView(
          title,
          new LinearLayout.LayoutParams(
                  ViewGroup.LayoutParams.MATCH_PARENT,
                  ViewGroup.LayoutParams.WRAP_CONTENT
          )
  );

  // Display layout directly
  a.setContentView(layout);

  return layout;
 }

 // Creates a clickable button
 static Button button(
         Activity a,
         LinearLayout layout,
         String label,
         Runnable action) {

  Button button = new Button(a);
  button.setText(label);

  layout.addView(button);

  button.setOnClickListener(
          v -> action.run()
  );

  return button;
 }

 // Creates an input field
 static EditText input(
         Activity a,
         LinearLayout layout,
         String hint,
         String value) {

  EditText input = new EditText(a);

  input.setHint(hint);
  input.setSingleLine(true);
  input.setText(value);

  layout.addView(input);

  return input;
 }

 // Navigation between Activities
 static void nav(
         Activity a,
         LinearLayout layout) {

  button(
          a, layout, "Pantry",
          () -> a.startActivity(
                  new Intent(a, MainActivity.class)
          )
  );

  button(
          a, layout, "Suggested recipes",
          () -> a.startActivity(
                  new Intent(a, RecipesActivity.class)
          )
  );

  button(
          a, layout, "Settings",
          () -> a.startActivity(
                  new Intent(a, SettingsActivity.class)
          )
  );
 }

 // Displays informational text
 static TextView text(
         Activity a,
         LinearLayout layout,
         String value) {

  TextView text = new TextView(a);

  text.setText(value);
  text.setTextSize(17);

  text.setPadding(5, 12, 5, 12);

  layout.addView(text);

  return text;
 }
}
