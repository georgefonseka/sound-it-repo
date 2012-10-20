package com.example.soundit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends TalkingActivity {
	
	private static final String LOG_TAG = "MainActivity";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(LOG_TAG, "onCreate" );
        super.onCreate(savedInstanceState);
        // hack to stop title being read out
        setTitle("");
        
        setContentView(R.layout.activity_main);       
        
        //this thing is use to initiate the font
        TextView score = (TextView) findViewById(R.id.my_score);
        TextView play = (TextView) findViewById(R.id.imageButtonSelector); 
        TextView guess = (TextView) findViewById(R.id.guessing_btn);
        TextView tutor = (TextView) findViewById(R.id.tutorial_btn);
		Typeface myfont = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Bold.ttf");
		play.setTypeface(myfont);
		guess.setTypeface(myfont);
		tutor.setTypeface(myfont);
		score.setTypeface(myfont);
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
        return true;
    }
    
    public void goToPlay(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, ChooesASound.class);
    	startActivity(intent);
    }
    public void goToGuess(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, ChooseAFriend.class);
    	startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	TextView score =(TextView) findViewById(R.id.my_score);
    	score.setText("Score: " + ApplicationProperties.getInstance().getPoints() + " pts");
    }

	@Override
	public String getUtteranceId() {
		return "HomePage";
	}

	@Override
	public String getMessageKey() {
		return "HomePage";
	}

	@Override
	public String getFullMessage() {
		return "Home page. Start a game. Guess a sound. Or view tutorial.";
	}

	@Override
	public String getShortMessage() {
		return "Home page.";
	}
	
	@Override
	public void onBackPressed() {
	    new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Exit SoundIt")
	        .setMessage("Are you sure you want exit?")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		            finish();    
		        }
		
		    })
	    .setNegativeButton("No", null)
	    .show();
	}
}

