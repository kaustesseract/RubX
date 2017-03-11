package com.kaustubh.rubrics;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class GradeFragment extends Fragment {

    Context context;

    public GradeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_grade, container, false);

        context = root.getContext();

        Button buto = (Button) root.findViewById(R.id.grade);

        buto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context,Grade_Students.class);
                startActivity(intent);
            }
        });

        Button but = (Button) root.findViewById(R.id.view);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Grade_Students.class);
                startActivity(intent);
            }
        });


        return root;

    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
