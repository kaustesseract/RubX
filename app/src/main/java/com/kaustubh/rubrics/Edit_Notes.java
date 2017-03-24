package com.kaustubh.rubrics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class Edit_Notes extends AppCompatActivity implements TextWatcher {
    int noteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__notes);

        EditText edit = (EditText) findViewById(R.id.enote);

        Intent i = getIntent();

        noteid = i.getIntExtra("noteId",-1);

        if(noteid != -1)
        {
           edit.setText(All_Notes.arrayList.get(noteid));


        }
        edit.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

       All_Notes.arrayList.set(noteid,String.valueOf(s));
        All_Notes.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.kaustubh.rubrics", Context.MODE_PRIVATE);

        if(All_Notes.set == null)
        {
            All_Notes.set = new HashSet<String>();
        }

        else
        {

            All_Notes.set.clear();
        }
        All_Notes.set.addAll(All_Notes.arrayList);

        sharedPreferences.edit().remove("notes").apply();
        sharedPreferences.edit().putStringSet("notes",All_Notes.set).apply();


    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
