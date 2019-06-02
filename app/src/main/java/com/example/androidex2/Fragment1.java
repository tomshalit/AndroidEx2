package com.example.androidex2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */


public class Fragment1 extends Fragment {
    static Bundle arg1;
    String strFromActivity[] = new String[4];
    Fragment1Interface context;
    private boolean isCliked = false;
    private Button btnFrag1;
    private Button btnFrag2;
    private Button btnFrag3;
    private Button btnFrag4;
    private Button btnAnswer;
    int answer;

    public Fragment1() {
        // Required empty public constructor
    }

    public static Fragment1 newInstance(Bundle bun) {
        Fragment1 fragment = new Fragment1();
        arg1 = bun;

        fragment.setArguments( arg1 );

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate( R.layout.fragment_fragment1, container, false );
        FragmentTransaction fr = getFragmentManager().beginTransaction();

        btnFrag1 = (Button) view.findViewById( R.id.butt1 );
        btnFrag2 = (Button) view.findViewById( R.id.butt2 );
        btnFrag3 = (Button) view.findViewById( R.id.butt3 );
        btnFrag4 = (Button) view.findViewById( R.id.butt4 );
        Random r = new Random();
        int rand = r.nextInt(4);
        if (getArguments() != null) {
            strFromActivity[0] = getArguments().getString( "a1" );
            strFromActivity[1] = getArguments().getString( "a2" );
            strFromActivity[2] = getArguments().getString( "a3" );
            strFromActivity[3] = getArguments().getString( "a4" );
        }

        // show string into button
        switch (rand){
            case 0:
                btnFrag1.setText( strFromActivity[1] );
                btnFrag2.setText( strFromActivity[0] );
                btnFrag3.setText( strFromActivity[2] );
                btnFrag4.setText( strFromActivity[3] );
                answer = rand;
                btnAnswer = btnFrag1;
                break;
            case 1:
                btnFrag1.setText( strFromActivity[0] );
                btnFrag2.setText( strFromActivity[1] );
                btnFrag3.setText( strFromActivity[2] );
                btnFrag4.setText( strFromActivity[3] );
                answer = rand;
                btnAnswer = btnFrag2;
                break;
            case 2:
                btnFrag1.setText( strFromActivity[2] );
                btnFrag2.setText( strFromActivity[0] );
                btnFrag3.setText( strFromActivity[1] );
                btnFrag4.setText( strFromActivity[3] );
                answer = rand;
                btnAnswer = btnFrag3;
                break;
            case 3:
                btnFrag1.setText( strFromActivity[0] );
                btnFrag2.setText( strFromActivity[2] );
                btnFrag3.setText( strFromActivity[3] );
                btnFrag4.setText( strFromActivity[1] );
                answer = rand;
                btnAnswer = btnFrag4;
                break;
        }

        btnFrag1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 0){
                    btnAnswer.setBackgroundColor( Color.parseColor( "#00ff00" ) );
                    isCliked = true;
                    enabeldButtons();
                    ((MainActivity) getActivity()).result( true );
                }
                else{
                    btnFrag1.setBackgroundColor( Color.parseColor( "#ff0000" ) );
                    btnAnswer.setBackgroundColor( Color.parseColor( "#00ff00" ) );
                    isCliked = true;
                    enabeldButtons();
                    ((MainActivity) getActivity()).result( false );
                }
            }
        } );

        btnFrag2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 1){
                    btnAnswer.setBackgroundColor( Color.parseColor( "#00ff00" ) );
                    isCliked = true;
                    enabeldButtons();
                    ((MainActivity) getActivity()).result( true );
                }
                else{
                    btnFrag2.setBackgroundColor( Color.parseColor( "#ff0000" ) );
                    btnAnswer.setBackgroundColor( Color.parseColor( "#00ff00" ) );
                    isCliked = true;
                    enabeldButtons();
                    ((MainActivity) getActivity()).result( false );
                }
            }

        } );

        btnFrag3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 2){
                    btnAnswer.setBackgroundColor( Color.parseColor( "#00ff00" ) );
                    isCliked = true;
                    enabeldButtons();
                    ((MainActivity) getActivity()).result( true );
                }
                else{
                    btnFrag3.setBackgroundColor( Color.parseColor( "#ff0000" ) );
                    btnAnswer.setBackgroundColor( Color.parseColor( "#00ff00" ) );
                    isCliked = true;
                    enabeldButtons();
                    ((MainActivity) getActivity()).result( false );
                }
            }
        } );

        btnFrag4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer == 3){
                    btnAnswer.setBackgroundColor( Color.parseColor( "#00ff00" ) );
                    isCliked = true;
                    enabeldButtons();
                    ((MainActivity) getActivity()).result( true );
                }
                else{
                    btnFrag4.setBackgroundColor( Color.parseColor( "#ff0000" ) );
                    btnAnswer.setBackgroundColor( Color.parseColor( "#00ff00" ) );
                    isCliked = true;
                    enabeldButtons();
                    ((MainActivity) getActivity()).result( false );
                }
            }
        } );

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof Fragment1.Fragment1Interface) {
            this.context = (Fragment1Interface) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement Fragment1Interface" );
        }
    }

    private void enabeldButtons() {
        if (isCliked == true) {
            btnFrag1.setEnabled( false );
            btnFrag2.setEnabled( false );
            btnFrag3.setEnabled( false );
            btnFrag4.setEnabled( false );
        }
    }


    public interface Fragment1Interface {
        public void result(boolean bool);
    }
}
