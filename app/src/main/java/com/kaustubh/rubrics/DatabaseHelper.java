package com.kaustubh.rubrics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by KAUSTUBH on 13-10-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String TABLE_NAME1 = "class";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_STUDENT = "student";
    private static final String COLUMN_CLASSNAME = "classname";
    private static final String COLUMN_ROLL = "roll";
    private DatabaseHelper ourhelper;

    SQLiteDatabase db;

    public static final String TABLE_CREATE1 =
            "CREATE TABLE "  +TABLE_NAME1 + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY , " +
                    COLUMN_CLASSNAME + " VARCHAR, " +
                    COLUMN_STUDENT + " VARCHAR, " +
                    COLUMN_ROLL + " INTEGER);";

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " + " name VARCHAR not null , password VARCHAR not null , email VARCHAR not null);";
  //  private static final String TABLE_CREATE1 = "create table class (id integer primary key not null , " + " classname VARCHAR not null , student VARCHAR not null );";

    protected Context context;
    public DatabaseHelper(Context context)
    {

        super(context , DATABASE_NAME , null , DATABASE_VERSION);
        this.context = context;


    }

    public DatabaseHelper open()
    {
        ourhelper = new DatabaseHelper(context);
        db = ourhelper.getWritableDatabase();
        return this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        //this.db = db;

        db.execSQL(TABLE_CREATE1);
       // this.db = db;
    }

    public void insertclass(Contact1 c1)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from class";
        try{
           Cursor cursor = db.rawQuery(query, null);
            int count = cursor.getCount();
            values.put(COLUMN_ID, count);
            values.put(COLUMN_STUDENT, c1.getStudentc());
            values.put(COLUMN_CLASSNAME, c1.getClassname());
            values.put(COLUMN_ROLL, c1.getRoll());
            db.insert(TABLE_NAME1, null, values);
        }
        finally {
            db.close();
        }


    }

    public void insertcontact(Contact c)
    {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_PASSWORD,c.getPassword());
        values.put(COLUMN_EMAIL,c.getEmail());
         db.insert(TABLE_NAME,null,values);
        db.close();


    }
   /*public String searchclass()
    {

        String classn = " ";
        String studentn = " ";
        String rolln = " " ;
        String result =" ";
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from class WHERE 1";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        while(cursor.isAfterLast())
        {
            if(cursor.getString(cursor.getColumnIndex("roll"))!=null)
            {
                classn +=  cursor.getString(cursor.getColumnIndex("classname"));
                studentn +=  cursor.getString(cursor.getColumnIndex("studentname"));
                rolln +=  cursor.getString(cursor.getColumnIndex("roll"));
                result = result + classn + " " + studentn + " " + rolln + "\n";


            }

        }
        return result ;

        }*/
       /* db = this.getReadableDatabase();
        String[] columns = new String[]{COLUMN_STUDENTNAME,COLUMN_CLASSNAME,COLUMN_ROLL};

        return null;*/
   public String searchclass() {
       String[] columns = new String[]{COLUMN_STUDENT,COLUMN_CLASSNAME,COLUMN_ROLL};
       Cursor c = db.query(TABLE_NAME1,columns,null,null,null,null,null);
       String result = "";
       int student = c.getColumnIndex(COLUMN_STUDENT);
       int classname = c.getColumnIndex(COLUMN_CLASSNAME);
       int roll = c.getColumnIndex(COLUMN_ROLL);
       for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
           result = result + "\n"+"\n" + c.getString(student) + "                            " + c.getString(classname) + "                         " + c.getString(roll) + "\n";
       }

       return result;

   }





    public String search(String name)
    {

        db = this.getReadableDatabase();
        String query = "select name,password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b = "not Found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                //b = cursor.getString(1);

                if(a.equals(name)) {
                    b = cursor.getString(1);
                    break;

                }

            }
            while(cursor.moveToNext());
        }
        return b;
    }

   /* protected void createTables(SQLiteDatabase db) {
        if (db != null) {
            db.execSQL(TABLE_CREATE1);
        }
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        onCreate(db);

        String query1 = "DROP TABLE IF EXISTS"+TABLE_NAME1;
        db.execSQL(query1);
        onCreate(db);




    }

}
