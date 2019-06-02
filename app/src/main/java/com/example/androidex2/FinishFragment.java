package com.example.androidex2;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
    Button doneBtn;
    Button restartBtn;
    FinishFragmentInterface context;
    MediaPlayer applause;
    public FinishFragment() {}

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
        doneBtn = view.findViewById(R.id.done);
        restartBtn = view.findViewById(R.id.restart);
        int correctAns = getArguments().getInt("correctAns");

        textView.setText("you have got " + Integer.toString(correctAns) + " answers correct");
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.restart( false );
                new Handler( Looper.getMainLooper() ).postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        getFragmentManager().beginTransaction().remove(FinishFragment.this).commit();
                    }
                }, 750 );
            }
        });

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.restart( true );
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
        if (context instanceof FinishFragment.FinishFragmentInterface) {
            this.context = (FinishFragmentInterface) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement Fragment1Interface" );
        }
    }

    public interface FinishFragmentInterface {
        public void restart(boolean bool);
    }
}