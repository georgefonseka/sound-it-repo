package com.example.soundit;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GuessSoundPage extends TalkingActivity {
	
	private SoundResource soundResource;
	private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soundResource = ApplicationProperties.getInstance().getSoundResource();
        setContentView(R.layout.activity_guess_sound_page);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

    }
    
    private void playSound(int rid) {
    	if(mediaPlayer == null) {
    		mediaPlayer = MediaPlayer.create(this.getApplicationContext(), rid);
    	}
    	mediaPlayer.start();
    }
    
    public void replay(View v) {
    	playSound(soundResource.getResourceId());
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	playSound(soundResource.getResourceId());
    }
    
	@Override
	protected void onStop() {
		super.onStop();
		if(mediaPlayer != null) {
			if(mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
			}
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_guess_sound_page, menu);
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
    public void goBackHome(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }

	@Override
	public String getUtteranceId() {
		return "GuessSound";
	}

	@Override
	public String getMessageKey() {
		return "GuessSound";
	}

	@Override
	public String getFullMessage() {
		return "Guess sound";
	}

	@Override
	public String getShortMessage() {
		return "Guess sound";
	}

}
