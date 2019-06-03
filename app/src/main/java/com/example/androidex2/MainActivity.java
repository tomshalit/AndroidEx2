package com.example.androidex2;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements Fragment1.Fragment1Interface , Server.HandleQuestion , StartFragment.FragmentSrartInterface, FinishFragment.FinishFragmentInterface {
    private int questionNum;
    private Button btnDone;
    private TextView questionText;
    private int rightAns ;
    private MediaPlayer sound;
    private Server.Question currentQuestion;
    private String category;
    private String difficulty;
    private CountDownTimer countDownTimer;
    private TextView timerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound= MediaPlayer.create(MainActivity.this,R.raw.trivia_sound);
        sound.start();
        questionText = (TextView) findViewById(R.id.question);
        timerText = findViewById( R.id.timer );
        questionNum = 0;
        rightAns = 0;

        StartFragment myObj = new StartFragment();
        getSupportFragmentManager().beginTransaction().replace( R.id.mainActivity, StartFragment.newInstance() ).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sound.pause();
        countDownTimer.cancel();
    }

    @Override
    public void result(boolean bool) {
        countDownTimer.cancel();
        if (bool == true){
            rightAns++;
        }
        questionNum++;
        if ( questionNum == 10){
            final int rightAnsCopy = rightAns;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction().replace( R.id.mainActivity, FinishFragment.newInstance(rightAnsCopy) ).commit();
                }
            }, 1000);
            questionNum = 0;
            rightAns = 0;
        }
        else {
            new Handler( Looper.getMainLooper() ).postDelayed( new Runnable() {
                @Override
                public void run() {
                    Server.getTriviaQuestion( MainActivity.this, category, difficulty );
                }
            }, 1000 );
        }
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

        startTimer();
        getSupportFragmentManager().beginTransaction().replace( R.id.con, Fragment1.newInstance(bundleAnswers) ).commit();
    }

    private void startTimer() {
        countDownTimer  = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int)millisUntilFinished/60000;
                int seconds = (int)millisUntilFinished%60000/1000;
                String timeLeft;
                timeLeft = minutes + ":";
                if (seconds < 10) timeLeft += "0";
                timeLeft += seconds;
                timerText.setText( timeLeft );
            }

            @Override
            public void onFinish() {
                questionText.setText( "Time's Up" );
                result( false );
            }
        }.start();
    }

    public void sound(boolean b) {
        if (b) sound.start();
        else   sound.pause();
    }

    @Override
    public void setGame(Bundle bundle) {
        category = bundle.getString("category");
        difficulty = bundle.getString("difficulty").toLowerCase();
        Server.getTriviaQuestion(this, category,  difficulty);
    }

    @Override
    public void restart(boolean bool) {
        if(bool == true) {
            getSupportFragmentManager().beginTransaction().replace( R.id.mainActivity, StartFragment.newInstance() ).commit();
        }
        else {
            Server.getTriviaQuestion(MainActivity.this, category, difficulty);
        }

    }
}
