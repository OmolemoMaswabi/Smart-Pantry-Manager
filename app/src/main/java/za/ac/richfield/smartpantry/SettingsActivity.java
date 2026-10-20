package za.ac.richfield.smartpantry;
import android.app.*;import android.content.*;import android.os.*;import android.widget.*;
public class SettingsActivity extends Activity {
 @Override public void onCreate(Bundle b){super.onCreate(b);LinearLayout l=Ui.page(this,"Settings");Ui.nav(this,l);Ui.text(this,l,"Smart Pantry Manager v1.0");CheckBox cb=new CheckBox(this);cb.setText("Show expiry reminders preference");cb.setChecked(getPreferences(0).getBoolean("expiry",false));l.addView(cb);cb.setOnCheckedChangeListener((v,checked)->getPreferences(0).edit().putBoolean("expiry",checked).apply());Ui.text(this,l,"Preference saved on this device. Automatic notifications are not implemented.");}
}
