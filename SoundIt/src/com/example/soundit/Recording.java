package com.example.soundit;

import java.io.IOException;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class Recording extends TalkingActivity {
	
    private static final String LOG_TAG = "Recording";
    private MediaRecorder mRecorder = null;
    private boolean mRecording;
    private Button mButton;
    private String mSoundSuggestion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSoundSuggestion = getIntent().getExtras().getString("soundSuggestion");
        setContentView(R.layout.activity_recording);
        
        mButton = (Button) findViewById(R.id.record_button);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_recording, menu);
        return true;
    }

    public void onClick(View view) {
    	if (!mRecording) {
            startRecording();
        } else {
            stopRecording();
            Intent intent = new Intent(this, SendFriend.class);
        	startActivity(intent);
        }
    	
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(ApplicationProperties.getInstance().getSoundFileName());
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
        	Log.d(LOG_TAG, "prepare media recorder.");
            mRecorder.prepare();
            mRecorder.start();
            mRecording = true;
	        // there seems to be a delay so we will  play the sound after start
	        playSound();
	        // change the text for the button
            mButton.setText("Stop");
            mButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.stop_btn));
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
            speak("Unable to record sound.");
        }
    }
    
    private void playSound() {
    	MediaPlayer mediaPlayer = MediaPlayer.create(this.getApplicationContext(), R.raw.beep);
        mediaPlayer.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        playSound();
        mRecorder.release();
        mRecorder = null;
        ApplicationProperties.getInstance().addPlayedSoundSuggestion(mSoundSuggestion);
        mSoundSuggestion =  null;
        reset();
    }
    
    private void reset() {
    	mRecording = false;
    	mButton.setText("Record");
    	
    }

    @Override
    public void onPause() {
    	Log.d(LOG_TAG, "pause");
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            if(mRecording) {
            	mRecorder.stop();
            	reset();
            	// TODO delete the file??
            }
            mRecorder = null;
        }
    }
    
    @Override
    public void onRestart() {
    	Log.d(LOG_TAG, "restart");
        super.onRestart();
        reset();
    }

	@Override
	public String getUtteranceId() {
		return "Record";
	}


	@Override
	public String getMessageKey() {
		return "Record";
	}


	@Override
	public String getFullMessage() {
		return "Record a sound using the button below.";
	}


	@Override
	public String getShortMessage() {
		return "Record a sound.";
	}

}
