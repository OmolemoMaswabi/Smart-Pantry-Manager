package za.ac.richfield.smartpantry;
import android.app.*;import android.database.*;import android.os.*;import android.widget.*;
public class RecipeDetailActivity extends Activity {
 @Override public void onCreate(Bundle b){super.onCreate(b);Db db=new Db(this);long id=getIntent().getLongExtra("recipe_id",-1);try(Cursor c=db.recipe(id)){if(!c.moveToFirst()){finish();return;}LinearLayout l=Ui.page(this,c.getString(0));Ui.text(this,l,"Required ingredients:");for(String part:c.getString(1).split("\\|")){String[] r=part.split(":");Ui.text(this,l,"• "+r[0]+": "+r[1]+" "+r[2]);}Ui.text(this,l,"Method:\n"+c.getString(2));Ui.button(this,l,"Back",()->finish());}}
}
