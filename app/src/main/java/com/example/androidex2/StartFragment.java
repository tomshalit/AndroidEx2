package com.example.androidex2;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;


public class StartFragment extends Fragment {

    private Spinner spinner_Category, spinner_diff, spinner_num_q;
    private int categoryIndex, num_q;
    private String diff, num_q_string;

    private FragmentSrartInterface context;

    private Button btnSatrt;
    static Bundle arg1;
    public interface FragmentSrartInterface {
        void setGame(Bundle bundle);
    }

    public StartFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        StartFragment fragment = new StartFragment();


        fragment.setArguments(arg1);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_start, container, false);
        FragmentTransaction fr = getFragmentManager().beginTransaction();
        // addListenerOnButton();

        spinner_Category = (Spinner) view.findViewById(R.id.category_q);
        spinner_diff = (Spinner) view.findViewById(R.id.diff_q);
        spinner_num_q = (Spinner) view.findViewById(R.id.q_num);
        btnSatrt = (Button) view.findViewById(R.id.btnSubmit);


        btnSatrt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                categoryIndex = spinner_Category.getSelectedItemPosition()+9;
                diff = (String) spinner_diff.getSelectedItem();
                num_q_string =(String) spinner_num_q.getSelectedItem();

                Bundle bundle = new Bundle();
                bundle.putString("difficulty",diff);
                bundle.putString("category",new StringBuilder().append(categoryIndex).toString());
                bundle.putString("qNum",num_q_string );
                Log.i("test 1",new StringBuilder().append(categoryIndex).toString() + " " + diff);
                Log.i("test 2",bundle.getString("category") + " " + bundle.getString("difficulty"));
                context.setGame(bundle);
                new Handler( Looper.getMainLooper() ).postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        getFragmentManager().beginTransaction().remove(StartFragment.this).commit();
                    }
                }, 750 );
            }

        });
        return  view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof StartFragment.FragmentSrartInterface) {
            this.context = (StartFragment.FragmentSrartInterface) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement Fragment1Interface" );
        }
    }
}
