package com.example.soundit;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

public abstract class TalkingActivity extends Activity implements OnInitListener{
	
	private int DATA_CHECK_CODE = 0;
	
	private static final String LOG_TAG = "TalkingActivity";
	private TextToSpeech tts;
	
	/**
	 * The string that will be used as an utterance id for this activity.
	 */
	public abstract String getUtteranceId();
	
	/**
	 * This key used to indicate if this. 
	 */
	public abstract String getMessageKey();
	
	/**
	 * Full message explaining buttons on page
	 * @return
	 */
	public abstract String getFullMessage();
	
	/**
	 * Short version of message. Should be just page name
	 * @return
	 */
	public abstract String getShortMessage();
	
	 public void onCreate(Bundle savedInstanceState) {
	    	Log.d(LOG_TAG, "onCreate" );
	        super.onCreate(savedInstanceState);
	        
	        // check if tts supported
	        Intent checkTTSIntent = new Intent();
	        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
	        startActivityForResult(checkTTSIntent, DATA_CHECK_CODE);
	        
	    }
	
	
	public void checkTextToSpeech() {
		Log.d(LOG_TAG, "checking tts.");
		Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, DATA_CHECK_CODE);
	}
	
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d(LOG_TAG, "onActivityResult: " + requestCode );
    	if (requestCode == DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            	Log.d(LOG_TAG, "tts available.");
                tts = new TextToSpeech(this, this);
            }
            else {
            	Log.d(LOG_TAG, "tts not available.");
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
		if(ApplicationProperties.getInstance().getProperties().containsKey(getMessageKey())) {
			tts.speak(getShortMessage(), TextToSpeech.QUEUE_ADD, null);
		} else {
			// set up onCompleteListener to we can record full instructions have been delivered once
			tts.setOnUtteranceCompletedListener( new TextToSpeech.OnUtteranceCompletedListener() {
				public void onUtteranceCompleted(String utteranceId) {
					ApplicationProperties.getInstance().getProperties().put(getMessageKey(), 1);
				}	
			});
			// params are needed to the utterence id can be passed.
			// no utterance id results in onCompleteListener not being called
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, getUtteranceId());
			tts.speak(getFullMessage(),
							TextToSpeech.QUEUE_ADD, params);
		}
	}

}
