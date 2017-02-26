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


public class RubricsFragment extends Fragment {

    Context context;


    public RubricsFragment() {
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
        View root = inflater.inflate(R.layout.fragment_rubrics, container, false);

        context = root.getContext();

        Button buto = (Button) root.findViewById(R.id.button9);

        buto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context,Types_Of_Rubric.class);
                startActivity(intent);
            }
        });

        Button but = (Button) root.findViewById(R.id.vrubr);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Display_rubrics.class);
                startActivity(intent);

            }
        });

        return root;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
