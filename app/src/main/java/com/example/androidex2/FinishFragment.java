package com.example.androidex2;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class FinishFragment extends Fragment {
    TextView textView;
    Button donebtn;
    MediaPlayer applause;

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

        Glide.with(this)
                .load("https://media.giphy.com/media/2lQCBjuFMLCOvXno4l/giphy.gif")
                //.placeholder(placeholder)
                .fitCenter()
                .into((ImageView)view.findViewById(R.id.gif));
        applause = MediaPlayer.create(getActivity(),R.raw.applause3);
        ((MainActivity) getActivity()).sound(false);
        applause.start();



        return view;
    }

    public void onPause() {
        super.onPause();
        applause.pause();
        ((MainActivity) getActivity()).sound(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
