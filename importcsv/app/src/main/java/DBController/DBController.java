package DBController;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.out;

public class DBController extends SQLiteOpenHelper {
    private static final String LOGCAT = null;
   private static final String query1 = "CREATE TABLE IF NOT EXISTS stduentdetails ( Name VARCHAR,Email VARCHAR, Roll No INTEGER PRIMARY KEY)";
    SQLiteDatabase database1;
    public DBController(Context applicationcontext) {
        super(applicationcontext, "student.db", null, 1);  // creating DATABASE
        Log.d(LOGCAT, "Created");
        database1 = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(query1);
    }
    public void createTable(){
        database1.execSQL(query1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old,
                          int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS studentdetails";
        database.execSQL(query);
        onCreate(database);
    }

    public ArrayList<HashMap<String, String>> getAllProducts() {
        ArrayList<HashMap<String, String>> proList;
        proList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM studentdetails";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //Id, Company,Name,Price
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Name", cursor.getString(0));
                map.put("Email", cursor.getString(1));
                map.put("Roll No", cursor.getString(2));

                proList.add(map);
            } while (cursor.moveToNext());
        }

        return proList;
    }

}
