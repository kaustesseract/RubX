package com.kaustubh.rubrics;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ActionBar actionBar;
    private ListView navlist;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    public void onrecieve()
    {
        Bundle bundle = getIntent().getExtras();
        int message = bundle.getInt("pid");
        Toast.makeText(getApplicationContext(), "Id is "+message , Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);




        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawlayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        navlist = (ListView) findViewById(R.id.navlist);
        ArrayList<String> navarray = new ArrayList<String>();
        navarray.add("Home");
        navarray.add("Manage class");
        navarray.add("Manage course");
        navarray.add("Manage rubrics");
        navarray.add("Start grading");
        navarray.add("View graphs");
        navarray.add("Feedback");
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
            case 0:  HomeFragment homeFragment = new HomeFragment();
                     fragmentTransaction = fragmentManager.beginTransaction();
                     fragmentTransaction.replace(R.id.fragmentholder, homeFragment);
                     fragmentTransaction.addToBackStack(null);
                     fragmentTransaction.commit();
                     break;
            case 1:  ClassFragment classFragment = new ClassFragment();
                     fragmentTransaction = fragmentManager.beginTransaction();
                     fragmentTransaction.replace(R.id.fragmentholder, classFragment);
                     fragmentTransaction.addToBackStack(null);
                     fragmentTransaction.commit();
                     break;
            case 2:  CourseFragment courseFragment = new CourseFragment();
                     fragmentTransaction = fragmentManager.beginTransaction();
                     fragmentTransaction.replace(R.id.fragmentholder, courseFragment);
                     fragmentTransaction.addToBackStack(null);
                     fragmentTransaction.commit();
                     break;
            case 3:  RubricsFragment rubricsFragment = new RubricsFragment();
                     fragmentTransaction = fragmentManager.beginTransaction();
                     fragmentTransaction.replace(R.id.fragmentholder, rubricsFragment);
                     fragmentTransaction.addToBackStack(null);
                     fragmentTransaction.commit();
                     break;
            case 4:  GradeFragment gradeFragment = new GradeFragment();
                     fragmentTransaction = fragmentManager.beginTransaction();
                     fragmentTransaction.replace(R.id.fragmentholder, gradeFragment);
                     fragmentTransaction.addToBackStack(null);
                     fragmentTransaction.commit();
                     break;
            case 5:  ViewGradesFragment viewgradeFragment = new ViewGradesFragment();
                     fragmentTransaction = fragmentManager.beginTransaction();
                     fragmentTransaction.replace(R.id.fragmentholder, viewgradeFragment);
                     fragmentTransaction.addToBackStack(null);
                     fragmentTransaction.commit();
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

}




/*
    int id=item.getItemId();
if(id==R.id.home) {
        if(mDrawerLayout.isDrawerOpen(navlist))
        {mDrawerLayout.closeDrawer(navlist);}

        else{mDrawerLayout.openDrawer(navlist);}
        }*/