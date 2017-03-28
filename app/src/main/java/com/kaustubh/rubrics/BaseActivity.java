package com.kaustubh.rubrics;

import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ActionBar actionBar;
    private ListView navlist;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

   // private GoogleApiClient mGoogleApiClient;

    public void onrecieve()
    {
        Bundle bundle = getIntent().getExtras();
        int message = bundle.getInt("pid");
      //  Toast.makeText(getApplicationContext(), "Id is "+message , Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawlayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        navlist = (ListView) findViewById(R.id.navlist);
        ArrayList<String> navarray = new ArrayList<String>();
       // navarray.add("Home");
        navarray.add("Manage class");
        navarray.add("Manage course");
        navarray.add("Manage Assignment");
        navarray.add("Manage rubrics");
        navarray.add("Start grading");
        navarray.add("View graphs");
        navarray.add("Send Students Feedback");
        navarray.add("Generate results");
        navarray.add("Attendance");
        navarray.add("Manage Notes");
        navarray.add("Account Settings");
        navarray.add("Help & Support");



        navlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,navarray);
        navlist.setAdapter(adapter);
        navlist.setOnItemClickListener(this);
        mDrawerLayout.setDrawerListener(mToggle);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    /*   if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToggle.syncState();
        fragmentManager = getSupportFragmentManager();
        //loadselection(0);
    }
    public void loadselection(int i)
    {
        switch(i)
        {
           /* case 0:  HomeFragment homeFragment = new HomeFragment();
                     fragmentTransaction = fragmentManager.beginTransaction();
                     fragmentTransaction.replace(R.id.fragmentholder, homeFragment);
                     fragmentTransaction.addToBackStack(null);
                     fragmentTransaction.commit();
                     break;*/
            case 0:  Intent y = new Intent(getApplicationContext(), MainClass.class);
                     startActivity(y);
                     finish();
                     break;

            case 1:  Intent p = new Intent(getApplicationContext(), MainCourse.class);
                     startActivity(p);
                     finish();
                     break;

            case 2:  Intent l = new Intent(getApplicationContext(), MainAssignment.class);
                     startActivity(l);
                     finish();
                     break;

            case 3:  Intent u = new Intent(getApplicationContext(), MainRubrics.class);
                     startActivity(u);
                     finish();
                     break;

            case 4:  Intent t = new Intent(getApplicationContext(), MainStartGrading.class);
                     startActivity(t);
                     finish();
                     break;

            case 5:  Intent q = new Intent(getApplicationContext(), MainGraphs.class);
                     startActivity(q);
                     finish();
                     break;
            case 6:  Intent v = new Intent(getApplicationContext(), MainStudentfeedback.class);
                     startActivity(v);
                     finish();
                     break;

            case 7:  Intent z = new Intent(getApplicationContext(), Generate_class.class);
                     startActivity(z);
                     finish();
                     break;

            case 8 : Intent b = new Intent(getApplicationContext(), MainAttendance.class);
                startActivity(b);
                finish();
                break;

            case 9:  Intent j = new Intent(getApplicationContext(), All_Notes.class);
                     startActivity(j);
                     finish();
                     break;
            case 10:  Intent k = new Intent(getApplicationContext(), ViewAccount.class);
                     startActivity(k);
                     finish();
                     break;

            case 11:Intent m = new Intent(getApplicationContext(), MainHelpSupport.class);
                startActivity(m);
                finish();
                break;



        }





    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        loadselection(position);
        mDrawerLayout.closeDrawer(navlist);


    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
           // Intent j = new Intent(getApplicationContext(), BaseActivity.class);
           // startActivity(j);
            finishAffinity();
        }
        return super.onKeyDown(keyCode, event);
    }
}




/*
    int id=item.getItemId();
if(id==R.id.home) {
        if(mDrawerLayout.isDrawerOpen(navlist))
        {mDrawerLayout.closeDrawer(navlist);}

        else{mDrawerLayout.openDrawer(navlist);}
        }*/