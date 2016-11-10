package com.kaustubh.rubrics;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment {

    Context context;

  /*  public void submit(View view)
    {
        Intent start = new Intent(getActivity(),Addclass.class);
        getActivity().startActivity(start);
    }*/

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_class, container, false);
        View root = inflater.inflate(R.layout.fragment_class, container, false);
        //View root1 = inflater.inflate(R.layout.fragment_class, container, false);

        context = root.getContext();

        Button buto = (Button) root.findViewById(R.id.button6);

        buto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context,Addclass.class);
                startActivity(intent);
            }
        });

        Button but = (Button) root.findViewById(R.id.button5);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context,Showclass.class);
                startActivity(intent);
            }
        });
        return root;

    }
/*
    public void searchc(View view)
    {
                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context,Showclass.class);
                startActivity(intent);

    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
