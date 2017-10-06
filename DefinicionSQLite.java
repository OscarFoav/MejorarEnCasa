version Realm

   private int id;
   private String default_values_column_push_ups;
   private String default_values_column_repetition_number_push_ups = "seriesnumberpushups";
   private String default_values_column_level_first_push_ups = "firstlevelpushups";
   private String default_values_column_level_second_push_ups = "secondlevelpushups";
   private String default_values_column_abs = "abs";
   private String default_values_column_repetition_number_abs = "seriesnumberabs";
   private String default_values_column_level_first_abs = "firstlevelabs";
   private String default_values_column_level_second_abs = "secondlevelabs";
   private String default_values_column_dips = "dips";
   private String default_values_column_repetition_number_dips = "seriesnumberdips";
   private String default_values_column_level_first_dips = "firstleveldips";
   private String default_values_column_level_second_dips = "secondleveldips";
   private String DEFAULT_VALUES_COLUMN_SQUATS = "squats";
   private String DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_SQUATS = "seriesnumbersquats";
   private String DEFAULT_VALUES_COLUMN_LEVEL_FIRST_SQUATS = "firstlevelsquats";
   private String DEFAULT_VALUES_COLUMN_LEVEL_SECOND_SQUATS = "secondlevelsquats";
   private String DEFAULT_VALUES_COLUMN_PULL_UPS = "pullups";
   private String DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_PULL_UPS = "seriesnumberpullups";
   private String DEFAULT_VALUES_COLUMN_LEVEL_FIRST_PULL_UPS = "firstlevelpullups";
   private String DEFAULT_VALUES_COLUMN_LEVEL_SECOND_PULL_UPS = "secondlevelpullups";
   private String DEFAULT_VALUES_COLUMN_CALVES = "calves";
   private String DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_CALVES = "seriescalves";
   private String DEFAULT_VALUES_COLUMN_LEVEL_FIRST_CALVES = "firstlevelcalves";
   private String DEFAULT_VALUES_COLUMN_LEVEL_SECOND_CALVES = "secondlevelcalves";



---------------------------------------------------------------------------------------------


-- https://www.tutorialspoint.com/android/android_sqlite_database.htm

-- upgrade tablas: https://riggaroo.co.za/android-sqlite-database-use-onupgrade-correctly/

-- Following is the content of Database class DBHelper.java:

package ...;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
   public static final String DATABASE_NAME = "GetFit.db";
   
   public static final String DEFAULT_VALUES_TABLE = "defaulvalues";
   public static final String DEFAULT_VALUES_COLUMN_ID = "id";
   public static final String DEFAULT_VALUES_COLUMN_PUSH_UPS = "pushups";
   public static final String DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_PUSH_UPS = "seriesnumberpushups";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_FIRST_PUSH_UPS = "firstlevelpushups";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_SECOND_PUSH_UPS = "secondlevelpushups";
   public static final String DEFAULT_VALUES_COLUMN_ABS = "abs";
   public static final String DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_ABS = "seriesnumberabs";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_FIRST_ABS = "firstlevelabs";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_SECOND_ABS = "secondlevelabs";
   public static final String DEFAULT_VALUES_COLUMN_DIPS = "dips";
   public static final String DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_DIPS = "seriesnumberdips";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_FIRST_DIPS = "firstleveldips";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_SECOND_DIPS = "secondleveldips";
   public static final String DEFAULT_VALUES_COLUMN_SQUATS = "squats";
   public static final String DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_SQUATS = "seriesnumbersquats";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_FIRST_SQUATS = "firstlevelsquats";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_SECOND_SQUATS = "secondlevelsquats";
   public static final String DEFAULT_VALUES_COLUMN_PULL_UPS = "pullups";
   public static final String DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_PULL_UPS = "seriesnumberpullups";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_FIRST_PULL_UPS = "firstlevelpullups";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_SECOND_PULL_UPS = "secondlevelpullups";
   public static final String DEFAULT_VALUES_COLUMN_CALVES = "calves";
   public static final String DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_CALVES = "seriescalves";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_FIRST_CALVES = "firstlevelcalves";
   public static final String DEFAULT_VALUES_COLUMN_LEVEL_SECOND_CALVES = "secondlevelcalves";
   public static final String defaultValues = "create table " + DEFAULT_VALUES_TABLE + " " +
         "(" + DEFAULT_VALUES_COLUMN_ID + " integer primary key, " + 
			DEFAULT_VALUES_COLUMN_PUSH_UPS + " text, " +
			DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_PUSH_UPS + " integer,  " +
			DEFAULT_VALUES_COLUMN_LEVEL_FIRST_PUSH_UPS + " integer, " +
			DEFAULT_VALUES_COLUMN_LEVEL_SECOND_PUSH_UPS + " integer, " +
			DEFAULT_VALUES_COLUMN_ABS + " text, " +
			DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_ABS + " integer,  " +
			DEFAULT_VALUES_COLUMN_LEVEL_FIRST_ABS + " integer, " +
			DEFAULT_VALUES_COLUMN_LEVEL_SECOND_ABS + " integer, " +
			DEFAULT_VALUES_COLUMN_DIPS + " text, " +
			DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_DIPS + " integer,  " +
			DEFAULT_VALUES_COLUMN_LEVEL_FIRST_DIPS + " integer, " +
			DEFAULT_VALUES_COLUMN_LEVEL_SECOND_DIPS + " integer, " +
			DEFAULT_VALUES_COLUMN_SQUATS + " text, " +
			DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_SQUATS + " integer,  " +
			DEFAULT_VALUES_COLUMN_LEVEL_FIRST_SQUATS + " integer, " +
			DEFAULT_VALUES_COLUMN_LEVEL_SECOND_SQUATS + " integer, " +
			DEFAULT_VALUES_COLUMN_PULL_UPS + " text, " +
			DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_PULL_UPS + " integer,  " +
			DEFAULT_VALUES_COLUMN_LEVEL_FIRST_PULL_UPS + " integer, " +
			DEFAULT_VALUES_COLUMN_LEVEL_SECOND_PULL_UPS + " integer, " +
			DEFAULT_VALUES_COLUMN_CALVES + " text, " +
			DEFAULT_VALUES_COLUMN_REPETITION_NUMBER_CALVES + " integer,  " +
			DEFAULT_VALUES_COLUMN_LEVEL_FIRST_CALVES + " integer, " +
			DEFAULT_VALUES_COLUMN_LEVEL_SECOND_CALVES + " integer,)"
			
   
   public static final String USERS_TABLE = "users";
   public static final String USERS_COLUMN_ID = "id";
   public static final String USERS_COLUMN_USER = "user";
   public static final String USERS_COLUMN_SERIES_NUMBER = "seriesnumber";
   public static final String USERS_COLUMN_REPETITION_SERIES_ONE = "repetitionseriesone";
   public static final String USERS_COLUMN_REPETITION_SERIES_TWO = "repetitionseriestwo";
   public static final String USERS_COLUMN_REPETITION_SERIES_THREE = "repetitionseriesthree";
   public static final String USERS_COLUMN_REPETITION_SERIES_FOUR = "repetitionseriefour";
   public static final String USERS_COLUMN_REPETITION_SERIES_FIVE = "repetitionseriesfive";
   public static final String tableUsers =  "create table " + USERS_TABLE + " " +
         "(" + USERS_COLUMN_ID + " integer primary key, " +
			USERS_COLUMN_USER + " text, " +
			USERS_COLUMN_SERIES_NUMBER + " integer, " +
			USERS_COLUMN_REPETITION_SERIES_ONE + " integer,  " +
			USERS_COLUMN_REPETITION_SERIES_TWO + " integer, " +
			USERS_COLUMN_REPETITION_SERIES_THREE + " integer, " +
			USERS_COLUMN_REPETITION_SERIES_FOUR + " integer, " +
			USERS_COLUMN_REPETITION_SERIES_FIVE + " integer) "
      
   
   public static final String HISTORICAL_TABLE = "historical";
   public static final String HISTORICAL_COLUMN_ID = "id";
   public static final String HISTORICAL_COLUMN_USER = "user";
   public static final String HISTORICAL_COLUMN_TYPE_SERIES = "typeseries";
   public static final String HISTORICAL_COLUMN_CALORIES = "calories";
   public static final String HISTORICAL_COLUMN_SERIES_NUMBER = "seriesnumber";
   public static final String HISTORICAL_COLUMN_REPETITION_SERIES_ONE = "repetitionseriesone";
   public static final String HISTORICAL_COLUMN_REPETITION_SERIES_TWO = "repetitionseriestwo";
   public static final String HISTORICAL_COLUMN_REPETITION_SERIES_THREE = "repetitionseriesthree";
   public static final String HISTORICAL_COLUMN_REPETITION_SERIES_FOUR = "repetitionseriefour";
   public static final String HISTORICAL_COLUMN_REPETITION_SERIES_FIVE = "repetitionseriesfive";
   public static final String tableHistorical = "create table " + HISTORICAL_TABLE + " " +
         "(" + HISTORICAL_COLUMN_ID + " integer primary key, " + 
			HISTORICAL_COLUMN_USER + " text, " + 
			HISTORICAL_COLUMN_TYPE_SERIES + " integer, " + 
			HISTORICAL_COLUMN_CALORIES + " integer, " + 
			HISTORICAL_COLUMN_SERIES_NUMBER + " integer,  " + 
			HISTORICAL_COLUMN_REPETITION_SERIES_TWO + " integer, " + 
			HISTORICAL_COLUMN_REPETITION_SERIES_THREE + " integer, " + 
			HISTORICAL_COLUMN_REPETITION_SERIES_FOUR + " integer, " + 
			HISTORICAL_COLUMN_REPETITION_SERIES_FIVE + " integer)"
      
   
   private HashMap hp;

   public DBHelper(Context context) {
      super(context, DATABASE_NAME , null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      // TODO Auto-generated method stub
	  // creacion de las tablas
        db.execSQL(defaultValues);
		db.execSQL(tableUsers);
	    db.execSQL(tableHistorical);
	  
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // TODO Auto-generated method stub
	  onCreate(db);
	  // se crean las tablas con versión V2 cuando se desarrolle
	  // db.execSQL("INSERT INTO defaultValues2 (campos) select (campos) from defaulvalues");
	  // db.execSQL("INSERT INTO users2 (campos) select (campos) from users");
      // db.execSQL("INSERT INTO historical2 (campos) select (campos) from historical");
	  // db.execSQL("DROP TABLE IF EXISTS defaulvalues");
	  // db.execSQL("DROP TABLE IF EXISTS users");
	  // db.execSQL("DROP TABLE IF EXISTS historical");
      // onCreate(db);
   }

   
   
   public boolean insertUser (String name, String phone, String email, String street,String place) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put("user", user);
	  contentValues.put("seriesnumber", seriesnumber);
      contentValues.put("typeseries", typeseries);
      contentValues.put("repetitionseriesone", repetitionseriesone);	
      contentValues.put("repetitionseriestwo", repetitionseriestwo);
      contentValues.put("repetitionseriesthree", repetitionseriesthree);
	  contentValues.put("repetitionseriesfour", repetitionseriesfour);
	  contentValues.put("repetitionseriesfive", repetitionseriesfive);
      db.insert("users", null, contentValues);
      return true;
   }
   
   public Cursor getDataUsers(int id) {
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from users where id="+id+"", null );
      return res;
   }
   
   public int numberOfRowsUsers(){
      SQLiteDatabase db = this.getReadableDatabase();
      int numRows = (int) DatabaseUtils.queryNumEntries(db, USERS_TABLE);
      return numRows;
   }
   
   public boolean updateUser (Integer id, String name, String phone, String email, String street,String place) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put("user", user);
	  contentValues.put("seriesnumber", seriesnumber);
      contentValues.put("typeseries", typeseries);
      contentValues.put("repetitionseriesone", repetitionseriesone);	
      contentValues.put("repetitionseriestwo", repetitionseriestwo);
      contentValues.put("repetitionseriesthree", repetitionseriesthree);
	  contentValues.put("repetitionseriesfour", repetitionseriesfour);
	  contentValues.put("repetitionseriesfive", repetitionseriesfive);
      db.update("users", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
      return true;
   }

   public Integer deleteUser (Integer id) {
      SQLiteDatabase db = this.getWritableDatabase();
      return db.delete("users", 
      "id = ? ", 
      new String[] { Integer.toString(id) });
   }
   
   public ArrayList<String> getAllUsers() {
      ArrayList<String> array_list = new ArrayList<String>();
      
      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from users", null );
      res.moveToFirst();
      
      while(res.isAfterLast() == false){
         array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
         res.moveToNext();
      }
      return array_list;
   }
}