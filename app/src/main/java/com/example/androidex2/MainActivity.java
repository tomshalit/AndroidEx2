package com.example.androidex2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends AppCompatActivity implements Fragment1.Fragment1Interface{
    private String[][]  questions = new String[5][10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildQuestions(questions);
        //Passing the data from activity to fragment
        Bundle bundleAnswers = new Bundle();
        bundleAnswers.putString( "a1", questions[1][0] );
        bundleAnswers.putString( "a2", questions[2][0] );
        bundleAnswers.putString( "a3", questions[3][0] );
        bundleAnswers.putString( "a4", questions[4][0] );
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
        questions[0][1] = "How many dragons does Khalisi have?";
        questions[1][1] = "2";
        questions[2][1] = "3";
        questions[3][1] = "9";
        questions[4][1] = "0";
    }

    @Override
    public void result(boolean bool) {

        Bundle bundleAnswers = new Bundle();
        bundleAnswers.putString( "a1", questions[1][1] );
        bundleAnswers.putString( "a2", questions[2][1] );
        bundleAnswers.putString( "a3", questions[3][1] );
        bundleAnswers.putString( "a4", questions[4][1] );
        Fragment1 myObj = new Fragment1();
        myObj.setArguments(bundleAnswers);

        getSupportFragmentManager().beginTransaction().replace( R.id.con, Fragment1.newInstance(bundleAnswers) ).commit();
    }
}
