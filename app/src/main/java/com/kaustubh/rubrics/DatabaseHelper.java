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
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String TABLE_NAME1 = "class";
    private static final String COLUMN_STUDENTNAME = "studentname";
    private static final String COLUMN_CLASSNAME = "calssname";
    private static final String COLUMN_ROLL = "roll";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " + " name VARCHAR not null , password VARCHAR not null , email VARCHAR not null);";
    private static final String TABLE_CREATE1 = "create table class (id integer primary key not null , " + " classname VARCHAR not null , studentname VARCHAR not null , roll INTEGER not null);";


    public DatabaseHelper(Context context)
    {

        super(context , DATABASE_NAME , null , DATABASE_VERSION);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    public void insertclass(Contact c1)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from class";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_STUDENTNAME,c1.getStudentc());
        values.put(COLUMN_CLASSNAME,c1.getClassname());
        values.put(COLUMN_ROLL,c1.getRoll());
        db.insert(TABLE_NAME1,null,values);
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

        String query1 = "DROP TABLE IF EXISTS"+TABLE_NAME1;
        db.execSQL(query1);
        this.onCreate(db);

    }

}
