package com.example.androidex2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Passing the data from activity to fragment
        //Bundle bundleQuestion = new Bundle();

        //bundleQuestion.putString( "q1(key)", "1(value)" );
        //bundleQuestion.putString( "q2(key correct answer)", "2(value)" );
        //bundleQuestion.putString( "q3(key)", "3(value)" );
        //bundleQuestion.putString( "q4(key)", "4(value)" );

        // set Fragment1 Arguments
        //Fragment1 myObj = new Fragment1();
        //myObj.setArguments( bundleQuestion );


        //getSupportFragmentManager().beginTransaction().replace( R.id.con, Fragment1.newInstance( bundleQuestion ) ).commit();
    }

}
