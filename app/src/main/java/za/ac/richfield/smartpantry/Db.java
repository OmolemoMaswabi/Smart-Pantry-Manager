package za.ac.richfield.smartpantry;
import android.content.*;import android.database.*;import android.database.sqlite.*;import java.util.*;
public class Db extends SQLiteOpenHelper {
 public Db(Context c){super(c,"pantry.db",null,1);}
 @Override public void onCreate(SQLiteDatabase d){
 d.execSQL("CREATE TABLE pantry(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,quantity REAL NOT NULL,unit TEXT NOT NULL,expiry TEXT)");
 d.execSQL("CREATE TABLE recipes(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,requirements TEXT NOT NULL,steps TEXT NOT NULL)");
 String[][] seed={
            {"Tomato Salad", "tomato:2:piece|salt:1:g", "Slice tomatoes and season with salt."},
            {"Boiled Eggs", "egg:2:piece|water:500:ml", "Boil eggs in water until cooked."},
            {"Scrambled Eggs", "egg:2:piece|milk:30:ml|butter:10:g", "Whisk and gently cook in butter."},
            {"Cheese Toast", "bread:2:slice|cheese:40:g", "Toast bread with cheese until melted."},
            {"Butter Toast", "bread:2:slice|butter:10:g", "Toast bread and spread with butter."},
            {"Tomato Sandwich", "bread:2:slice|tomato:1:piece|butter:5:g", "Slice tomato and assemble sandwich."},
            {"Banana Oats", "oats:50:g|banana:1:piece|milk:200:ml", "Cook oats with milk and add sliced banana."},
            {"Apple Oats", "oats:50:g|apple:1:piece|water:200:ml", "Cook oats and add chopped apple."},
            {"Rice Bowl", "rice:100:g|water:250:ml|salt:2:g", "Simmer rice in salted water."},
            {"Egg Fried Rice", "rice:100:g|egg:2:piece|oil:15:ml", "Cook rice, then stir fry with eggs."},
            {"Garlic Pasta", "pasta:100:g|garlic:2:piece|oil:15:ml|water:500:ml", "Boil pasta and toss with garlic oil."},
            {"Tomato Pasta", "pasta:100:g|tomato:2:piece|oil:10:ml|water:500:ml", "Boil pasta and mix with cooked tomato."},
            {"Mashed Potatoes", "potato:3:piece|milk:100:ml|butter:20:g", "Boil and mash potatoes with milk and butter."},
            {"Potato Wedges", "potato:3:piece|oil:20:ml|salt:2:g", "Slice potatoes and bake with oil and salt."},
            {"Carrot Salad", "carrot:2:piece|lemon:1:piece", "Grate carrots and squeeze lemon over them."},
            {"Fruit Salad", "apple:1:piece|banana:1:piece|orange:1:piece", "Chop fruit and combine."},
            {"Yoghurt Banana", "yoghurt:150:g|banana:1:piece", "Slice banana into yoghurt."},
            {"Peanut Butter Toast", "bread:2:slice|peanut butter:30:g", "Toast bread and spread peanut butter."},
            {"Cucumber Salad", "cucumber:1:piece|tomato:1:piece|salt:1:g", "Chop vegetables and season."},
            {"Simple Omelette", "egg:2:piece|cheese:30:g|oil:10:ml", "Beat eggs and cook with cheese in a pan."}
 };
 for(String[] r:seed){ContentValues v=new ContentValues();v.put("name",r[0]);v.put("requirements",r[1]);v.put("steps",r[2]);d.insert("recipes",null,v);}
 }
 @Override public void onUpgrade(SQLiteDatabase d,int oldV,int newV){}
 public long save(long id,String name,double quantity,String unit,String expiry){ContentValues v=new ContentValues();v.put("name",name.trim());v.put("quantity",quantity);v.put("unit",unit);v.put("expiry",expiry);SQLiteDatabase d=getWritableDatabase();if(id>0){d.update("pantry",v,"id=?",new String[]{""+id});return id;}return d.insert("pantry",null,v);}
 public void delete(long id){getWritableDatabase().delete("pantry","id=?",new String[]{""+id});}
 public Cursor pantry(){return getReadableDatabase().rawQuery("SELECT id,name,quantity,unit,expiry FROM pantry ORDER BY name",null);}
 public Cursor recipes(){return getReadableDatabase().rawQuery("SELECT id,name,requirements,steps FROM recipes ORDER BY name",null);}
 public Cursor recipe(long id){return getReadableDatabase().rawQuery("SELECT name,requirements,steps FROM recipes WHERE id=?",new String[]{""+id});}
 // Normalize simple singular/plural variants and common irregular plurals.
 public static String norm(String s){s=s.trim().toLowerCase(java.util.Locale.ROOT);if(s.equals("tomatoes"))return "tomato";if(s.equals("potatoes"))return "potato";if(s.equals("eggs"))return "egg";if(s.endsWith("ies")&&s.length()>4)return s.substring(0,s.length()-3)+"y";if(s.endsWith("s")&&!s.endsWith("ss")&&s.length()>3)return s.substring(0,s.length()-1);return s;}
 public static double convert(double amount,String from,String to){from=from.trim().toLowerCase();to=to.trim().toLowerCase();if(from.equals(to))return amount;if(from.equals("kg")&&to.equals("g"))return amount*1000;if(from.equals("g")&&to.equals("kg"))return amount/1000;if(from.equals("l")&&to.equals("ml"))return amount*1000;if(from.equals("ml")&&to.equals("l"))return amount/1000;return -1;}
 public boolean matches(String requirements){Map<String,Double> available=new HashMap<>();try(Cursor c=pantry()){while(c.moveToNext()){String name=norm(c.getString(1));double q=c.getDouble(2);String unit=c.getString(3);String[] canonical={"g","ml","piece","slice"};for(String target:canonical){double converted=convert(q,unit,target);if(converted>=0){String key=name+"|"+target;available.put(key,available.containsKey(key)?available.get(key)+converted:converted);}}}}
 for(String req:requirements.split("\\|")){String[] parts=req.split(":");if(parts.length!=3)return false;String name=norm(parts[0]);double needed=Double.parseDouble(parts[1]);String unit=parts[2];if(available.getOrDefault(name+"|"+unit,0.0)+0.000001<needed)return false;}return true;}
}
