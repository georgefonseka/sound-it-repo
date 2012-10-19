package com.example.soundit;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class SendFriend extends TalkingActivity {
	
	private static final String LOG_TAG = "SendFriend";
	
	private MediaPlayer mPlayer = null;
	
	private String receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
    	if(bundle != null) {
    		receiver = bundle.getString("sound_receiver");
    	}
        setContentView(R.layout.activity_send_friend);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        
        TextView element2 =(TextView) findViewById(R.id.btnSend);
        TextView element1 =(TextView) findViewById(R.id.btnReplay);
		Typeface myfont = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Bold.ttf");
		element1.setTypeface(myfont);
		element2.setTypeface(myfont);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_send_friend, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void startPlaying() {
    	Log.d(LOG_TAG, "start playing");
    	if(mPlayer == null) {
    		mPlayer = new MediaPlayer();
    		try {
        		mPlayer.setDataSource(ApplicationProperties.getInstance().getSoundFileName());
                mPlayer.prepare();
	        } catch (IOException e) {
	            Log.e(LOG_TAG, "prepare() failed");
	        }
    	}
    	
    	if(mPlayer != null && !mPlayer.isPlaying()) {
	        mPlayer.start();
    	}
    }
    
    private void stopAndReleasePlayer() {
    	if( mPlayer!= null) {
    		if( mPlayer.isPlaying()) {
    			mPlayer.stop();
    		}
    		mPlayer.release();
            mPlayer = null;
    	}
    }
    
    public void replay(View v) {
    	Log.d(LOG_TAG, "replay clicked");
    	startPlaying();
    }

    
    public void goToList(View view) {
    	stopAndReleasePlayer();
    	
    	if(receiver == null || receiver.isEmpty()) {
    		Intent intent = new Intent(this, FriendList.class);
        	startActivity(intent);
    	} else {
    		speak("Your sound has been sent.");
        	Toast toast = Toast.makeText(this, "Your sound has been sent.", Toast.LENGTH_SHORT);
        	toast.show();
        	
        	// this might be a bit dodgy
        	new Handler().postDelayed(
    	        new Runnable() {
    	        public void run() {
    	        	 // Do something in response to button
    	        	Intent intent = new Intent(SendFriend.this, MainActivity.class);
    	        	startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    	        }
            }, 2500);
    	}
    	
    }
    
    @Override
    public void onPause() {
    	Log.d(LOG_TAG, "onPause");
        super.onPause();

        if (mPlayer != null) {
        	stopAndReleasePlayer();
        }
    }

	@Override
	public String getUtteranceId() {
		return "confirmSound";
	}

	@Override
	public String getMessageKey() {
		return "confirmSound";
	}

	@Override
	public String getFullMessage() {
		return "Confirm sound. Replay sound or send to a friend;";
	}

	@Override
	public String getShortMessage() {
		return "Confirm sound.";
	}

}
