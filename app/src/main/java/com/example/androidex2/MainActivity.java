package com.example.androidex2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends AppCompatActivity {
    private String[][]  questions = new String[5][10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildQuestions(questions);
        //Passing the data from activity to fragment
        Bundle bundleAnswers = new Bundle();

        bundleAnswers.putString( "q1(key)", questions[1][0] );
        bundleAnswers.putString( "q2(key correct answer)", questions[2][0] );
        bundleAnswers.putString( "q3(key)", questions[3][0] );
        bundleAnswers.putString( "q4(key)", questions[4][0] );

        // set Fragment1 Arguments
        Fragment1 myObj = new Fragment1();
        myObj.setArguments(bundleAnswers);


        getSupportFragmentManager().beginTransaction().replace( R.id.con, Fragment1.newInstance(bundleAnswers) ).commit();
    }

    private void buildQuestions(String[][] questions) {
        questions[0][0] = "In which episode of season 1 did Ned Stark die?";
        questions[1][0] = "8";
        questions[2][0] = "9";
        questions[3][0] = "13";
        questions[4][0] = "2";
    }

}
