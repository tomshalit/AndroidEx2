package com.example.androidex2;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements Fragment1.Fragment1Interface{
    private String[][]  questions = new String[5][10];
    private  int i = 0;
    private Button btnDone;
    private TextView questionText;
    private  int rightAns ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionText = (TextView) findViewById(R.id.question) ;
        btnDone = (Button) findViewById( R.id.done );
        btnDone.setVisibility(View.GONE);
        buildQuestions(questions);
        rightAns = 0;

        //Passing the data from activity to fragment
        Bundle bundleAnswers = new Bundle();
        questionText.setText(questions[0][0]);
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
        questions[0][2] = "Who killed Joffrey Baratheon?";
        questions[1][2] = "Sansa Stark";
        questions[2][2] = "Olenna Tyrell";
        questions[3][2] = "Tyrion Lannister";
        questions[4][2] = "Samuel Tarley";
        questions[0][3] = "Who is Jon Snow's mother?";
        questions[1][3] = "Lyanna Mormont";
        questions[2][3] = "Lyanna Stark";
        questions[3][3] = "Catelyn Stark";
        questions[4][3] = "A nameless hooker";
        questions[0][4] = "Which organ is Jaime Lannister missing?";
        questions[1][4] = "Left hand";
        questions[2][4] = "Right hand";
        questions[3][4] = "Penis";
        questions[4][4] = "Left leg";
        questions[0][5] = "Who did Sansa stark marry first?";
        questions[1][5] = "Ramsey Bolton";
        questions[2][5] = "Tyrion Lannister";
        questions[3][5] = "Joffrey Baratheon";
        questions[4][5] = "Theon Greyjoy";
        questions[0][6] = "Who killed Robert Baratheon?";
        questions[1][6] = "Ned Stark";
        questions[2][6] = "A boar";
        questions[3][6] = "A dragon";
        questions[4][6] = "Arya Stark";
        questions[0][7] = "Who married Robb Stark?";
        questions[1][7] = "Roslin Frey";
        questions[2][7] = "Talisa Stark";
        questions[3][7] = "Osha";
        questions[4][7] = "Arya Stark";
        questions[0][8] = "What is the name of Sansa Stark's direwolf?";
        questions[1][8] = "Nymeria";
        questions[2][8] = "Lady";
        questions[3][8] = "Ghost";
        questions[4][8] = "Shaggydog";
        questions[0][9] = "What is Jon Snow's real name?";
        questions[1][9] = "Rhaegar Targaryen";
        questions[2][9] = "Aegon Targaryen";
        questions[3][9] = "Viserys Targaryen";
        questions[4][9] = "Aegon Snow";
    }

    @Override
    public void result(boolean bool) {
        if (bool == true){
            rightAns++;
        }
        i++;
        if ( i == 10){
            i = 0;
            rightAns = 0;

        }
        final Bundle bundleAnswers = new Bundle();

        bundleAnswers.putString( "a1", questions[1][i] );
        bundleAnswers.putString( "a2", questions[2][i] );
        bundleAnswers.putString( "a3", questions[3][i] );
        bundleAnswers.putString( "a4", questions[4][i] );

        Fragment1 myObj = new Fragment1();
        myObj.setArguments(bundleAnswers);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                questionText.setText(questions[0][i]);
                getSupportFragmentManager().beginTransaction().replace( R.id.con, Fragment1.newInstance(bundleAnswers) ).commit();
            }
        }, 2000);

    }
}
