package com.example.androidex2;
import android.os.Bundle;
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
import android.media.MediaPlayer;


public class MainActivity extends AppCompatActivity implements Fragment1.Fragment1Interface , Server.HandleQuestion , StartFragment.FragmentSrartInterface {
    private  int i = 0;
    private Button btnDone;
    private TextView questionText;
    private  int rightAns ;
    private MediaPlayer sound;
    Server.Question currentQuestion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound= MediaPlayer.create(MainActivity.this,R.raw.trivia_sound);
        sound.start();
        questionText = (TextView) findViewById(R.id.question) ;
        //btnDone = (Button) findViewById( R.id.done );
        //btnDone.setVisibility(View.GONE);
        rightAns = 0;
        //buildQuestions(questions);
        //questionText.setText(questions[0][0]);
        StartFragment myObj = new StartFragment();
        getSupportFragmentManager().beginTransaction().replace( R.id.mainActivity, StartFragment.newInstance() ).commit();
        //Server.getTriviaQuestion(this, "9", "medium");
    }


    @Override
    public void result(boolean bool) {
        if (bool == true){
            rightAns++;
        }
        i++;
        if ( i == 10){
            final int rightAnsCopy = rightAns;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    FinishFragment myObj = new FinishFragment();
                    getSupportFragmentManager().beginTransaction().replace( R.id.mainActivity, FinishFragment.newInstance(rightAnsCopy) ).commit();
                    i = 0;
                    rightAns = 0;
                    Server.getTriviaQuestion(MainActivity.this, "9", "medium");
                }
            }, 2000);
            i = 0;
            rightAns = 0;
        }


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Server.getTriviaQuestion(MainActivity.this, "9", "medium");
            }
        }, 2000);

    }

    @Override
    public void handleQuestion(Server.Question q) {
        currentQuestion = q;
        questionText.setText( q.question);

        //Passing the data from activity to fragment
        Bundle bundleAnswers = new Bundle();
        bundleAnswers.putString( "a1", q.answers.get( 0 ) );
        bundleAnswers.putString( "a2", q.correctAnswer );
        bundleAnswers.putString( "a3", q.answers.get( 1 )  );
        bundleAnswers.putString( "a4", q.answers.get( 2 )  );

        // set Fragment1 Arguments
        Fragment1 myObj = new Fragment1();
        myObj.setArguments(bundleAnswers);
        getSupportFragmentManager().beginTransaction().replace( R.id.con, Fragment1.newInstance(bundleAnswers) ).commit();
    }
    public void sound(boolean b) {
        if (b) sound.start();
        else   sound.pause();
    }

    @Override
    public void setGame(Bundle bundle) {
        Log.i("test 3",bundle.getString("category") + " " + bundle.getString("diffucalty"));
        Server.getTriviaQuestion(this, bundle.getString("category"),  bundle.getString("diffucalty").toLowerCase());
    }
}
