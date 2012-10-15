package com.example.soundit;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements OnInitListener{
	
	private int DATA_CHECK_CODE = 0;
	private String MAIN_UTTERANCE_ID = "Main";
	
	private static final String LOG_TAG = "MainActivity";
	private TextToSpeech tts;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(LOG_TAG, "onCreate" );
        super.onCreate(savedInstanceState);
        
        // check if tts supported
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, DATA_CHECK_CODE);
        
        setContentView(R.layout.activity_main);       
        
        //this thing is use to initiate the font
        TextView txt = (TextView) findViewById(R.id.imageButtonSelector); 
        TextView text = (TextView) findViewById(R.id.guessing_btn);
		Typeface myfont = Typeface.createFromAsset(getAssets(), "fonts/ballpark_weiner.ttf");
		txt.setTypeface(myfont);
		text.setTypeface(myfont);
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d(LOG_TAG, "onActivityResult: " + requestCode );
    	if (requestCode == DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(this, this);
            }
            else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }
	
	 @Override
	 public void onPause() {
		 	super.onPause();
	    	Log.d(LOG_TAG, "onPause" );
	    	if(tts != null) {
	    		tts.stop();
	    	}
	 }
	 
	 @Override
	 public void onDestroy() {
	    	Log.d(LOG_TAG, "onDestroy" );
	    	if(tts != null) {
	    		tts.stop();
	    		tts.shutdown();
	    		tts = null;
	    	}
	    	
	    	//ApplicationProperties.getInstance().getProperties().clear();
	    	
	    	super.onDestroy();
	 }
	 
	
	@Override
	public void onStart() {
		Log.d(LOG_TAG, "onStart" );
		super.onStart();
	}
	
	@Override
	public void onRestart() {
		Log.d(LOG_TAG, "onRestart" );
		super.onStart();
		if(tts != null) {
			speak();
		}	
	}
	
	public void onInit(int status) {
		// TODO Auto-generated method stub
		speak();
	}
	
	@SuppressWarnings("deprecation")
	public void speak() {
		if(ApplicationProperties.getInstance().getProperties().containsKey("HOME")) {
			tts.speak("Home page.", TextToSpeech.QUEUE_FLUSH, null);
		} else {
			// set up onCompleteListener to we can record full instructions have been delivered once
			tts.setOnUtteranceCompletedListener( new TextToSpeech.OnUtteranceCompletedListener() {
				public void onUtteranceCompleted(String utteranceId) {
					ApplicationProperties.getInstance().getProperties().put("HOME", 1);
				}	
			});
			// params are needed to the utterence id can be passed.
			// no utterance id results in onCompleteListener not being called
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, MAIN_UTTERANCE_ID);
			tts.speak("Home page. Start a game. Guess a sound. Or view tutorial.",
							TextToSpeech.QUEUE_FLUSH, params);
		}
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
    	startActivity(intent);
    }
}
