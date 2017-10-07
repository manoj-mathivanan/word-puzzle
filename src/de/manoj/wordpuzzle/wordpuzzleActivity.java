package de.manoj.wordpuzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class wordpuzzleActivity extends Activity {
    /** Called when the activity is first created. */
	static String wordset="Last occured word: " + Home.line;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
 
        TextView word = (TextView)findViewById(R.id.textView8);
        TextView status = (TextView)findViewById(R.id.textView5);
		status.setText(Home.status);
		
		word.setText(wordset);
        final Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Home.current_level=0;
				finish();
				Intent nextScreen = new Intent(getApplicationContext(), Home.class);
                startActivity(nextScreen);
                
			}
			
		});
    }
    public void onResume(Bundle savedInstanceState) {
    	TextView word = (TextView)findViewById(R.id.textView8);
		word.setText(wordset);
		
    }
    public void onBackPressed() {
    	 System.exit(0);
    	    }

}