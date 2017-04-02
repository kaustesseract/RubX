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
    private static final String TABLE_STARTGRADE = "start_grading";
    private static final String TABLE_ATTENDANCE = "attendance";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DOB = "dob";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_STUDENT = "student";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_ANSWER = "answer";
    private static final String COLUMN_TOTALMARKS = "totalmarks";
    private static final String COLUMN_CLASSNAME = "classname";
    private static final String COLUMN_ROLL = "roll";
    private static final String COLUMN_CLSID = "cls_id";
    public static final String COLUMN_CLASS = "cname";
    private static final String COLUMN_TID = "tid";
    private static final String COLUMN_CRID = "cr_id";
    public static final String COLUMN_CONAME = "coursename";
    private static final String COLUMN_CLID = "cl_id";
    private static final String COLUMN_RID = "r_id";
    public static final String COLUMN_RNAME = "rname";
    private static final String COLUMN_LIMIT = "limits";
    private static final String COLUMN_ROWID = "row_id";
    private static final String COLUMN_COLUMNID = "column_id";
    private static final String COLUMN_ROWNAME = "rows";
    private static final String COLUMN_COLUMNNAME = "columns";
    private static final String COLUMN_LWEIGHT = "lweight";
    private static final String COLUMN_HWEIGHT = "hweight";
    private static final String HIGH = "high";
    private static final String LOW = "low";
    private static final String COLUMN_PRIORITY = "priority";
    private static final String COLUMN_ASSID = "a_id";
    public static final String COLUMN_ASSNAME = "ass_name";
    private static final String COLUMN_DAY = "day";
    private static final String COLUMN_MONTH = "month";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_GRADE = "grade";
    private static final String COLUMN_HOUR = "hour";
    private static final String COLUMN_MINUTE = "minute";
    private static final String COLUMN_GNAME = "tgrade";
    private static final String COLUMN_STID = "st_id";
    private static final String COLUMN_TGRADE = "tg_id";
    //private static final String COLUMN_ASSGRADE = "Agrade";
    private static final String COLUMN_GRADEID = "Gr_id";
    private static final String COLUMN_SGID = "sg_id";
    private static final String COLUMN_TOTAL = "total";
    private static final String ROLL = "Roll";
    private static final String COLUMN_ATTENDANCEID = "at_id";
    private static final String ATTID = "att_id";
    // private static String k = " ";






    private DatabaseHelper ourhelper;

    SQLiteDatabase db;

    public static final String TABLE_CRT =
            "CREATE TABLE "+TABLE_CNAME+" (" +
                    COLUMN_CLSID + " INTEGER PRIMARY KEY , " +
                    COLUMN_CLASS + " VARCHAR , " +
                    COLUMN_TOTALMARKS + " INTEGER , " +
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
                    COLUMN_RNAME + " VARCHAR , " +
                    COLUMN_GRADE + " INTEGER);";
                  //  COLUMN_LIMIT +  " INTEGER);";


    public static final String TABLE_STGRADE =
             "CREATE TABLE "+TABLE_STARTGRADE+"(" +
                     COLUMN_STID + " INTEGER PRIMARY KEY , " +
                     COLUMN_GNAME + " VARCHAR);";


    public static final String TABLE_ATTENDANCES =
            "CREATE TABLE " +TABLE_ATTENDANCE + "(" +
                    COLUMN_ATTENDANCEID + " INTEGER PRIMARY KEY , " +
                    COLUMN_CLSID + " INTEGER , " +
                    COLUMN_CRID + " INTEGER , " +
                    COLUMN_DAY + " INTEGER , " +
                    COLUMN_MONTH + " INTEGER , " +
                    COLUMN_YEAR + " INTEGER); ";



    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " + " name VARCHAR not null , password VARCHAR not null , email VARCHAR not null  , day INTEGER not null , month INTEGER , year INTEGER , question VARCHAR , answer VARCHAR );";
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

        db.execSQL(TABLE_STGRADE);
        this.db = db;

        db.execSQL(TABLE_ATTENDANCES);
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
        values.put(COLUMN_DAY , c.getDate());
        values.put(COLUMN_MONTH , c.getMonth());
        values.put(COLUMN_YEAR , c.getYear());
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
        values.put(COLUMN_TOTALMARKS,c2.getTotalmarks());
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

    public Cursor showrubricslist()
    {

        db = this.getWritableDatabase();
        String qr = "select  r_id as _id, rname from "+TABLE_RUBRIC;
        Cursor res = db.rawQuery(qr,null);
        return res;
    }

    public Cursor getcourseid(int cls)
    {
        db = this.getWritableDatabase();
        String qr = "select cr_id as _id , coursename from course where cls_id = "+cls;
        Cursor res = db.rawQuery(qr,null);
        return res;
    }

    public Cursor searchassign(String table)
    {
        db = this.getWritableDatabase();
        String qr = "select a_id as _id , ass_name from "+table;
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
        values.put(COLUMN_GRADE,c3.getGrade());
       // values.put(LOW,c3.getLow());
       // values.put(HIGH,c3.getHigh());
        db.insert(TABLE_RUBRIC,null,values);
        db.close();


    }
    public void createdrowcol(String row , String col)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //String query = "create table contacts (id integer primary key not null , " + " name VARCHAR not null , password VARCHAR not null , email VARCHAR not null);";
        String rows =
                "CREATE TABLE "  +row+ "(" +
                        COLUMN_ROWID + " INTEGER PRIMARY KEY , " +
                        COLUMN_ROWNAME + " VARCHAR); ";
        db.execSQL(rows);

        String cols =
                "CREATE TABLE " +col+ "(" +
                        COLUMN_COLUMNID + " INTEGER PRIMARY KEY , " +
                        COLUMN_COLUMNNAME + " VARCHAR , " +
                        COLUMN_LWEIGHT + " INTEGER , " +
                        COLUMN_HWEIGHT + " INTEGER); ";

        db.execSQL(cols);

    }

    public void createatttable(String tablename)
    {
        db = this.getWritableDatabase();
        String rows =

                "CREATE TABLE "  +tablename+ "(" +
                        ATTID + " INTEGER PRIMARY KEY , " +
                        COLUMN_STUDENT + " VARCHAR ); ";

        db.execSQL(rows);

    }

    public void createrow(String row)
    {
        db = this.getWritableDatabase();
        String rows =
                "CREATE TABLE "  +row+ "(" +
                        COLUMN_RID + " INTEGER PRIMARY KEY , " +
                        COLUMN_RNAME + " VARCHAR , " +
                        COLUMN_LWEIGHT + " INTEGER , " +
                        COLUMN_HWEIGHT + " INTEGER , " +
                        COLUMN_PRIORITY + " INTEGER); ";
        db.execSQL(rows);
    }

    public void createassignment(String assname)
    {
        db = this.getWritableDatabase();
        String assnames =
                "CREATE TABLE "  +assname+ "(" +
                        COLUMN_ASSID + " INTEGER PRIMARY KEY , " +
                        COLUMN_ASSNAME + " VARCHAR , " +
                        COLUMN_DAY + " INTEGER , " +
                        COLUMN_MONTH + " INTEGER , " +
                        COLUMN_YEAR + " INTEGER , " +
                        COLUMN_HOUR + " INTEGER , " +
                        COLUMN_MINUTE + " INTEGER); ";
        db.execSQL(assnames);
    }

    public void creategradetable(String tgrade)
    {
        db = this.getWritableDatabase();
        String gradename =
                "CREATE TABLE " +tgrade+ "(" +
                        COLUMN_TGRADE + " INTEGER PRIMARY KEY , " +
                        COLUMN_CONAME + " VARCHAR , " +
                        COLUMN_CRID + " INTEGER); ";
        db.execSQL(gradename);
    }

    public void courseassignment(String courseass)
    {
        db = this.getWritableDatabase();
        String courseas =
                "CREATE TABLE " +courseass+ "(" +
                        COLUMN_GRADEID + " INTEGER PRIMARY KEY , " +
                        COLUMN_ASSID + " INTEGER , " +
                        COLUMN_ASSNAME + " VARCHAR ); ";

        db.execSQL(courseas);

    }

    public void createstartgrade(String[] rparam, String studentgrade , int rcount)
    {
        int j = rcount - 1;
        String k = " ";
        db = this.getWritableDatabase();
        String studentgradetable =
                "CREATE TABLE "+studentgrade+ "(" +
                        COLUMN_SGID + " INTEGER PRIMARY KEY , " +
                        ROLL + " INTEGER , " ;


                        for(int i=0;i<rcount;i++)
                        {


                            studentgradetable += rparam[i] + " INTEGER , ";

                        }
                         studentgradetable += "" +
                        COLUMN_TOTAL + " INTEGER ); ";

        db.execSQL(studentgradetable);
    }

    public void insertgrades(int put ,String[] criteria,int[] val,int total,String gradetable,int counts)
    {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from "+gradetable;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_SGID,count);
        values.put(ROLL,put);
        for(int i=0;i<counts;i++)
        {
            values.put(criteria[i],val[i]);
        }

        values.put(COLUMN_TOTAL,total);
        db.insert(gradetable,null,values);

    }

    public void insertatttable(String tablename , String students)
    {
        db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from "+tablename;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(ATTID,count);
        values.put(COLUMN_STUDENT,students);
        db.insert(tablename,null,values);
        db.close();

    }

    public void insertcourseassignment(String assname , int assid , String courseass)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from "+courseass;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_GRADEID,count);
        values.put(COLUMN_ASSNAME, assname);
        values.put(COLUMN_ASSID, assid);
        db.insert(courseass,null,values);

    }



    public void insertanswerques(Contact c, String uname)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION,c.getForget());
        values.put(COLUMN_ANSWER, c.getAnswer());
        db.update(TABLE_NAME,values,"name = ?",new String[] {uname});
        db.close();
    }


    public void updatepass(String answer,String unames,String passs)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME,unames);
        values.put(COLUMN_PASSWORD,passs);
        db.update(TABLE_NAME,values,"answer = ?",new String[] {answer});
        db.close();



    }




    public void insertteacher(String tgrade)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+TABLE_STARTGRADE;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_STID,count);
        values.put(COLUMN_GNAME, tgrade);
        db.insert(TABLE_STARTGRADE,null,values);
    }

    public void insertgrade(int coid , String course , String tgrade)
    {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+tgrade;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_TGRADE,count);
        values.put(COLUMN_CRID,coid);
        values.put(COLUMN_CONAME,course);
        db.insert(tgrade,null,values);
        db.close();


    }

    public void insertdrow(Contact4 c4 , String mrow )
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

    public void insertdcol(Contact4 c5 , String mcol)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+mcol;
        Cursor cr = db.rawQuery(query,null);
        int count = cr.getCount();
        values.put(COLUMN_COLUMNID,count);
        values.put(COLUMN_COLUMNNAME,c5.getColumn());
        db.insert(mcol,null,values);
        db.close();

    }




    public void insertRow(Contact4 c4 , String mrow)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+mrow;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_RID,count);
        values.put(COLUMN_RNAME,c4.getRow());
        values.put(COLUMN_LWEIGHT,c4.getLweight());
        values.put(COLUMN_HWEIGHT,c4.getHweight());
       // values.put(COLUMN_PRIORITY,prior);
        db.insert(mrow,null,values);
        db.close();

    }

    public void insertassignment(ContactAssignment ca ,String assname)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+assname;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ASSID,count);
        values.put(COLUMN_ASSNAME,ca.getAssname());
        values.put(COLUMN_DAY,ca.getDay());
        values.put(COLUMN_MONTH,ca.getMonth());
        values.put(COLUMN_YEAR,ca.getYear());
        values.put(COLUMN_HOUR,ca.getHour());
        values.put(COLUMN_MINUTE,ca.getMinute());
        db.insert(assname,null,values);
        db.close();

    }

    public boolean insertattendance(int clsid,int coid,int dayx,int mont,int yearx)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+TABLE_ATTENDANCE;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ATTENDANCEID,count);
        values.put(COLUMN_CLSID,clsid);
        values.put(COLUMN_CRID,coid);
        values.put(COLUMN_DAY,dayx);
        values.put(COLUMN_MONTH,mont);
        values.put(COLUMN_YEAR,yearx);
       long result =  db.insert(TABLE_ATTENDANCE,null,values);
        db.close();

        if(result == -1)
        {
            return false;
        }
        else {
            return true;
        }


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


    public ArrayList<String> getcoursespinnerdata() {
        ArrayList<String> list = new ArrayList<String>();
        db = this.getReadableDatabase();
        db.beginTransaction();
        String query = "select * from "+TABLE_COURSE;
        Cursor cursor = db.rawQuery(query, null);
        try{
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    // Add province name to arraylist
                    String coname = cursor.getString(cursor.getColumnIndex(COLUMN_CONAME));
                    list.add(coname);
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


    public int searchcoid(String cours)
    {

        db = this.getReadableDatabase();
        //String query = "select name,password,id from "+TABLE_CNAME;
        String query = "select coursename,cr_id from "+TABLE_COURSE;
        Cursor cursor = db.rawQuery(query,null);
        String a;
        int c = 0;

        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                // c = cursor.getInt(0);

                if(a.equals(cours)) {
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


    public String[] getrubricsparam(String rname) {
        String[] columns = new String[]{COLUMN_RNAME};
        Cursor c = getReadableDatabase().query(rname,columns,null,null,null,null,null);
        String result = "";
        int i=0;

        int rubname = c.getColumnIndex(COLUMN_RNAME);
        String[] rubs = new String[c.getCount()];
        // int email = c.getColumnIndex(COLUMN_EMAIL);
       // int roll = c.getColumnIndex(COLUMN_ROLL);
        if(c.moveToFirst())
        {
            for(i=0;i<c.getCount();i++) {
                rubs[i] =  c.getString(rubname) ;
                c.moveToNext();
            }
        }

        return rubs;

    }

    public String[] getstudentname(String studenttable)
    {
        String[] columns = new String[]{COLUMN_STUDENT};
        Cursor c = getReadableDatabase().query(studenttable,columns,null,null,null,null,null);
        String result = "";
        int i=0;
        int studentname = c.getColumnIndex(COLUMN_STUDENT);
        String[] studs = new String[c.getCount()];


        if(c.moveToFirst())
        {
            for(i=0;i<c.getCount();i++) {
                studs[i] =  c.getString(studentname) ;
                c.moveToNext();
            }
        }
        return  studs;

    }


    public int[] getstudentroll(String studentgrade)
    {
        String[] columns = new String[]{ROLL};
        Cursor c = getReadableDatabase().query(studentgrade,columns,null,null,null,null,null);
        int i=0;
        int studentroll = c.getColumnIndex(ROLL);
        int[] rolls = new int[c.getCount()];
        if(c.moveToFirst())
        {
            for(i=0;i<c.getCount();i++) {
                rolls[i] =  c.getInt(studentroll) ;
                c.moveToNext();
            }
        }
        return  rolls;

    }





    public String[] getgradeparam(String gradetable)
    {

        Cursor dbCursor = getReadableDatabase().query(gradetable, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;

    }



    public int getgradecolumncount(String gradetable)
    {

        Cursor dbCursor = getReadableDatabase().query(gradetable, null, null, null, null, null, null);
        int columncount = dbCursor.getColumnCount();
        return columncount;

    }

    public int[] gettotalmarks(String gradetable)
    {
        String[] columns = new String[]{COLUMN_TOTAL};
        Cursor c = getReadableDatabase().query(gradetable,columns,null,null,null,null,null);
        int i=0;
        int total = c.getColumnIndex(COLUMN_TOTAL);
        int[] totals = new int[c.getCount()];
        if(c.moveToFirst())
        {
            for(i=0;i<c.getCount();i++) {
                totals[i] =  c.getInt(total) ;
                c.moveToNext();
            }
        }


        return totals;

    }


    public int gettotalcount(String gradetable)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+gradetable;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        return count;
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


    public String getunamemail(String uname , String email)
    {
        db = this.getReadableDatabase();
        String query = "select name , email , question from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b,c;
        c = "not";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);
                b = cursor.getString(1);

                if (a.equals(uname) && b.equals(email)) {
                    c = cursor.getString(2);
                    break;

                }
            }
                while (cursor.moveToNext()) ;
            }
            return c;

    }

    public String matchanswer(String answer)
    {
        db = this.getReadableDatabase();
        String query = "select answer from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b = "not";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);
                if (a.equals(answer)) {
                    b = cursor.getString(0);
                    break;

                }

            }
            while (cursor.moveToNext()) ;

            }
        return b;


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

    public String searchtable(String tname)
    {
        db = this.getReadableDatabase();
        String query = "SELECT name FROM sqlite_master WHERE type='table'";
        Cursor cursor = db.rawQuery(query,null);
        String a = "Not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);
                //b = cursor.getString(1);

                if(a.equals(tname)) {
                   // b = cursor.getString(1);
                    break;

                }

            }
            while(cursor.moveToNext());
        }
        return a;


    }

    public String searchstgrade(String tename)
    {
        db = this.getReadableDatabase();
        String query = "SELECT tgrade FROM "+TABLE_STARTGRADE;
        Cursor cursor = db.rawQuery(query,null);
        String a = "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(tename))
                {
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return a;
    }

    public int searchassid(String assname , String asstablename)
    {
        db = this.getReadableDatabase();
        String query = "SELECT ass_name , a_id FROM "+asstablename;
        Cursor cursor = db.rawQuery(query,null);
        String b = "Not found";
        int a = 0;
        if(cursor.moveToFirst())
        {
            do{
                b = cursor.getString(0);

                if(b.equals(assname))
                {
                    a = cursor.getInt(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return a;


    }

    public String getstudentname(int i , String clas)
    {
        db = this.getReadableDatabase();
        String query = "SELECT  cl_id , student FROM "+clas;
        Cursor cursor = db.rawQuery(query,null);
       String b = "Not found";
        int a = 0;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getInt(0);

                if(a==i)
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;



    }



    public int getstudentroll(int i , String clas)
    {
        db = this.getReadableDatabase();
        String query = "SELECT  cl_id , roll FROM "+clas;
        Cursor cursor = db.rawQuery(query,null);
        String b = "Not found";
        int c = 0;
        int a = 0;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getInt(0);

                if(a==i)
                {
                    c = cursor.getInt(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return c;
    }

    public String[] getemail(String clas,int[] roll)
    {
        db = this.getReadableDatabase();
        String query = "SELECT roll , email FROM "+clas;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        String b = "Not found";
        String[] c = new String[count];
        int a = 0;
        int i=0;
        if(cursor.moveToFirst())
        {
            do{

                a = cursor.getInt(0);

                if(a==roll[i])
                {
                    c[i] = cursor.getString(1);
                }
                i++;
            }
            while(cursor.moveToNext());
        }
        return c;



    }


    public int getrubricscount(String rname)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+rname;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        return count;
    }

    public int getstudentcount(String clas)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from "+clas;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        return count;

    }

   /* protected void createTables(SQLiteDatabase db) {
        if (db != null) {
            db.execSQL(TABLE_CREATE1);
        }
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        onCreate(db);

        String query1 = "DROP TABLE IF EXISTS "+TABLE_NAME1;
        db.execSQL(query1);
        onCreate(db);

        String query2 = "DROP TABLE IF EXISTS "+TABLE_CNAME;
        db.execSQL(query2);
        onCreate(db);

        String query3 = "DROP TABLE IF EXISTS "+TABLE_COURSE;
        db.execSQL(query3);
        onCreate(db);

        String query4 = "DROP TABLE IF EXISTS "+TABLE_STARTGRADE;
        db.execSQL(query4);
        onCreate(db);

        String query5 = "DROP TABLE IF EXISTS "+TABLE_ATTENDANCE;
        db.execSQL(query5);
        onCreate(db);




    }

}
