package com.example.soundit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ChooseAFriend extends TalkingActivity {

	private static final String LOG_TAG = "ChooseFriend";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(LOG_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_afriend);
        // getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_choose_afriend, menu);
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
    public void goToGuessPage(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, GuessSoundPage.class);
    	SoundResource res = ApplicationProperties.getInstance().getSoundResource();
    	intent.putExtra("sound_name", res.getName());
    	intent.putExtra("sound_resource_id", res.getResourceId());
    	startActivity(intent);
    }

	@Override
	public String getUtteranceId() {
		return "ChooseFriend";
	}

	@Override
	public String getMessageKey() {
		return "ChooseFriend";
	}

	@Override
	public String getFullMessage() {
		return "Choose a friend and guess the sound.";
	}

	@Override
	public String getShortMessage() {
		return "Choose a friend.";
	}

}
