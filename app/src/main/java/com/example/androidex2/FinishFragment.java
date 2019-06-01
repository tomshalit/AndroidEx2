package com.example.androidex2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class FinishFragment extends Fragment {
    TextView textView;
    Button donebtn;

    public FinishFragment() {

    }

    public static FinishFragment newInstance(int correctAns) {
        FinishFragment fragment = new FinishFragment();
        Bundle args = new Bundle();
        args.putInt("correctAns", correctAns);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish, container, false);
        textView = view.findViewById(R.id.endMassage);
        donebtn = view.findViewById(R.id.done);
        int correctAns = getArguments().getInt("correctAns");

        textView.setText("you have got " + Integer.toString(correctAns) + " answers correct");
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(FinishFragment.this).commit();
            }

        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
