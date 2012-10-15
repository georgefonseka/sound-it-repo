package com.example.soundit;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.NavUtils;

public class SendFriend extends Activity {
	
	private static final String LOG_TAG = "SendFriend";
	
	private MediaPlayer mPlayer = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_friend);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
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
    	
    	Intent intent = new Intent(this, FriendList.class);
    	startActivity(intent);
    }
    
    @Override
    public void onPause() {
    	Log.d(LOG_TAG, "onPause");
        super.onPause();

        if (mPlayer != null) {
        	stopAndReleasePlayer();
        }
    }

}
