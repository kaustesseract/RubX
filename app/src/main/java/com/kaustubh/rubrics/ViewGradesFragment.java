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


public class ViewGradesFragment extends Fragment {

    Context context;

    public ViewGradesFragment() {
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
        View root = inflater.inflate(R.layout.fragment_view_grades, container, false);
        context = root.getContext();

        Button buto = (Button) root.findViewById(R.id.simplexy);

        buto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context,SimplePieChartActivity.class);
                startActivity(intent);
            }
        });

        Button but = (Button) root.findViewById(R.id.bubble);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context,BubbleChartActivity.class);
                startActivity(intent);
            }
        });

        Button buts = (Button) root.findViewById(R.id.barplot);

        buts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context,Barchart.class);
                startActivity(intent);
            }
        });

        Button scatter = (Button) root.findViewById(R.id.scatter);

        scatter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                Intent intent = new Intent(context,Scatter_plot.class);
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
