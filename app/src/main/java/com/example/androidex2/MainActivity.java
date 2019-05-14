import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements Fragment1.Fragment1Interface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Passing the data from activity to fragment
        Bundle bundleQuestion1 = new Bundle();
        Bundle bundleQuestion2 = new Bundle();
        Bundle bundleQuestion3 = new Bundle();
        Bundle bundleQuestion4 = new Bundle();

        bundleQuestion1.putString( "q1(key)", "1(value)" );
        bundleQuestion1.putString( "q2(key correct answer)", "2(value)" );
        bundleQuestion1.putString( "q3(key)", "3(value)" );
        bundleQuestion1.putString( "q4(key)", "4(value)" );

        // set Fragment1 Arguments
        Fragment1 myObj = new Fragment1();
        myObj.setArguments( bundleQuestion1 );


        getSupportFragmentManager().beginTransaction().replace( R.id.con, Fragment1.newInstance( bundleQuestion1 ) ).commit();
    }


    @Override
    public void result(boolean bool) {
        if (bool == true) {
            Log.d( "tom a-Hamor", "result: sad" );
        }
    }
}
