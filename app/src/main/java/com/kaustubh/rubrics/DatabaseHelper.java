package com.kaustubh.rubrics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by KAUSTUBH on 13-10-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String TABLE_NAME1 = "class";
    private static final String TABLE_CNAME = "classes";
    private static final String TABLE_COURSE = "course";
    private static final String TABLE_RUBRIC = "rubrics";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_STUDENT = "student";
    private static final String COLUMN_CLASSNAME = "classname";
    private static final String COLUMN_ROLL = "roll";
    private static final String COLUMN_CLSID = "cls_id";
    public static final String COLUMN_CLASS = "cname";
    private static final String COLUMN_TID = "tid";
    private static final String COLUMN_CRID = "cr_id";
    public static final String COLUMN_CONAME = "coursename";
    private static final String COLUMN_CLID = "cl_id";
    private static final String COLUMN_RID = "r_id";
    private static final String COLUMN_RNAME = "rname";
    private static final String COLUMN_LIMIT = "limits";
    private static final String COLUMN_ROWID = "row_id";
    private static final String COLUMN_COLUMNID = "column_id";
    private static final String COLUMN_ROWNAME = "rows";
    private static final String COLUMN_COLUMNNAME = "columns";
    private static final String COLUMN_LWEIGHT = "lweight";
    private static final String COLUMN_HWEIGHT = "hweight";






    private DatabaseHelper ourhelper;

    SQLiteDatabase db;

    public static final String TABLE_CRT =
            "CREATE TABLE "+TABLE_CNAME+" (" +
                    COLUMN_CLSID + " INTEGER PRIMARY KEY , " +
                    COLUMN_CLASS + " VARCHAR, " +
                    COLUMN_TID + " INTEGER);";

    public static final String TABLE_COURSES =
            "CREATE TABLE "+TABLE_COURSE+"(" +
                    COLUMN_CRID + " INTEGER PRIMARY KEY , " +
                    COLUMN_CONAME + " VARCHAR, " +
                    COLUMN_TID + " INTEGER , " +
                    COLUMN_CLSID +  " INTEGER);";

    public static final String TABLE_RUBRICS =
            "CREATE TABLE "+TABLE_RUBRIC+"(" +
                    COLUMN_RID + " INTEGER PRIMARY KEY , " +
                    COLUMN_RNAME + " VARCHAR );";
                  //  COLUMN_LIMIT +  " INTEGER);";

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " + " name VARCHAR not null , password VARCHAR not null , email VARCHAR not null);";
  //  private static final String TABLE_CREATE1 = "create table class (id integer primary key not null , " + " classname VARCHAR not null , student VARCHAR not null );";
 // private static final String TABLE_CLASS = "create table classes (cid integer primary key not null , " + " classname VARCHAR not null , );";

   /* String Classes =
            "CREATE TABLE "  +COLUMN_CNAME+ " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY , " +
                    COLUMN_EMAIL + " VARCHAR, " +
                    COLUMN_STUDENT + " VARCHAR, " +
                    COLUMN_ROLL + " INTEGER);";*/



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

        db.execSQL(TABLE_CRT);
        this.db = db;

        db.execSQL(TABLE_COURSES);
        this.db = db;

        db.execSQL(TABLE_RUBRICS);
        this.db = db;



       // db.execSQL(TABLE_CREATE1);
       // this.db = db;
    }
    public void createclass(String str)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //String query = "create table contacts (id integer primary key not null , " + " name VARCHAR not null , password VARCHAR not null , email VARCHAR not null);";
        String Class =
                "CREATE TABLE "  +str+ "(" +
                        COLUMN_CLID + " INTEGER PRIMARY KEY , " +
                        COLUMN_EMAIL + " VARCHAR, " +
                        COLUMN_STUDENT + " VARCHAR, " +
                        COLUMN_ROLL + " INTEGER);";
        db.execSQL(Class);


    }

    public void insertclass(Contact1 c1 , String message)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from "+message;

        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_CLID,count);
        values.put(COLUMN_STUDENT,c1.getStudentc());
        values.put(COLUMN_EMAIL,c1.getEmail());
        values.put(COLUMN_ROLL,c1.getRoll());
        db.insert(message,null,values);
        db.close();


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

    public void inclass(Contact2 c2)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+TABLE_CNAME;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_CLSID,count);
        values.put(COLUMN_CLASS,c2.getCname());
        values.put(COLUMN_TID,c2.getTid());
        db.insert(TABLE_CNAME,null,values);
        db.close();


    }
/*
    public String classlist() {
        String[] columns = new String[]{COLUMN_CLSID,COLUMN_CLASS};
        Cursor c = db.query(TABLE_NAME1,columns,null,null,null,null,null);
        String result = "";
        int clsid = c.getColumnIndex(COLUMN_CLSID);
        int classname = c.getColumnIndex(COLUMN_CLASS);
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + "\n"+"\n" + c.getString(classname) ;
        }

        return result;

    }*/

    public Cursor showclasslist()
    {

        db = this.getWritableDatabase();
        String qr = "select  cls_id as _id, cname from "+TABLE_CNAME;
        Cursor res = db.rawQuery(qr,null);
        return res;
    }

    public Cursor showcourselist()
    {

        db = this.getWritableDatabase();
        String qr = "select  cr_id as _id, coursename from "+TABLE_COURSE;
        Cursor res = db.rawQuery(qr,null);
        return res;
    }



    public void insertcourse(Contactcourse cr)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+TABLE_COURSE;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_CRID,count);
        values.put(COLUMN_CONAME, cr.getCourse());
        values.put(COLUMN_CLSID, cr.getClasid());
        db.insert(TABLE_COURSE,null,values);
        db.close();
    }

    public void insertrubrics(Contact3 c3)
    {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from rubrics";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_RID,count);
        values.put(COLUMN_RNAME,c3.getRubric());
      //values.put(COLUMN_LIMIT,c3.getLimit());
        db.insert(TABLE_RUBRIC,null,values);
        db.close();


    }
    public void createrow(String row)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //String query = "create table contacts (id integer primary key not null , " + " name VARCHAR not null , password VARCHAR not null , email VARCHAR not null);";
        String rows =
                "CREATE TABLE "  +row+ "(" +
                        COLUMN_ROWID + " INTEGER PRIMARY KEY , " +
                        COLUMN_ROWNAME + " VARCHAR); ";
        db.execSQL(rows);

    }

    public void createcol(String column)
    {
        db = this.getWritableDatabase();
        String columns =
                "CREATE TABLE "  +column+ "(" +
                        COLUMN_COLUMNID + " INTEGER PRIMARY KEY , " +
                        COLUMN_COLUMNNAME + " VARCHAR , " +
                        COLUMN_LWEIGHT + " INTEGER , " +
                        COLUMN_HWEIGHT + " INTEGER ); ";
        db.execSQL(columns);
    }

    public void insertrow(Contact4 c4 , String mrow )
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+mrow;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ROWID,count);
        values.put(COLUMN_ROWNAME,c4.getRow());
        db.insert(mrow,null,values);
        db.close();

    }

    public void insertcolumn(Contact4 c4 , String mcolumn, int lweight ,int hweight)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+mcolumn;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_COLUMNID,count);
        values.put(COLUMN_COLUMNNAME,c4.getColumn());
        values.put(COLUMN_LWEIGHT,lweight);
        values.put(COLUMN_HWEIGHT,hweight);
        db.insert(mcolumn,null,values);
        db.close();

    }

    public ArrayList<String> getspinnerdata() {
        ArrayList<String> list = new ArrayList<String>();
        db = this.getReadableDatabase();
        db.beginTransaction();
        String query = "select * from "+TABLE_CNAME;
        Cursor cursor = db.rawQuery(query, null);
        try{
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                // Add province name to arraylist
                String cname = cursor.getString(cursor.getColumnIndex(COLUMN_CLASS));
                list.add(cname);
            }
        }
            db.setTransactionSuccessful();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }


    public int searchcid(String clas)
    {

        db = this.getReadableDatabase();
        //String query = "select name,password,id from "+TABLE_CNAME;
        String query = "select cname,cls_id from "+TABLE_CNAME;
        Cursor cursor = db.rawQuery(query,null);
        String a;
        int c = 0;

        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                // c = cursor.getInt(0);

                if(a.equals(clas)) {
                    c = cursor.getInt(1);

                    break;

                }

            }
            while(cursor.moveToNext());
        }
        return c;
    }


   public String searchclass(String cname) {
       String[] columns = new String[]{COLUMN_STUDENT,COLUMN_EMAIL,COLUMN_ROLL};
       Cursor c = db.query(cname,columns,null,null,null,null,null);
       String result = "";
       int student = c.getColumnIndex(COLUMN_STUDENT);
       int email = c.getColumnIndex(COLUMN_EMAIL);
       int roll = c.getColumnIndex(COLUMN_ROLL);
       for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
           result = result + "\n"+"\n" + c.getString(student) + "            " + c.getString(email) + "          " + c.getString(roll) + "\n";
       }

       return result;

   }


    public int searchId(String name)
    {

        db = this.getReadableDatabase();
        String query = "select name,password,id from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a;
        int c = 0;

        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                // c = cursor.getInt(0);

                if(a.equals(name)) {
                    c = cursor.getInt(2);

                    break;

                }

            }
            while(cursor.moveToNext());
        }
        return c;
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

        String query2 = "DROP TABLE IF EXISTS"+TABLE_CNAME;
        db.execSQL(query2);
        onCreate(db);

        String query3 = "DROP TABLE IF EXISTS"+TABLE_COURSE;
        db.execSQL(query3);
        onCreate(db);



    }

}
