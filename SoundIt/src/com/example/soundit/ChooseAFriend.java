package com.example.soundit;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseAFriend extends TalkingActivity {

	private static final String LOG_TAG = "ChooseFriend";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(LOG_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_afriend);
        // getActionBar().setDisplayHomeAsUpEnabled(true);
        
      //this thing is use to initiate the font
        TextView element1 = (TextView) findViewById(R.id.btnFromGeorge);
        TextView element2 = (TextView) findViewById(R.id.btnFromShu);
		Typeface myfont = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Bold.ttf");
		element1.setTypeface(myfont);
		element2.setTypeface(myfont);
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
    	Button b = (Button) view;
    	
        // Do something in response to button
    	Intent intent = new Intent(this, GuessSoundPage.class);
    	SoundResource res = ApplicationProperties.getInstance().getSoundResource();
    	intent.putExtra("sound_name", res.getName());
    	intent.putExtra("sound_resource_id", res.getResourceId());
    	intent.putExtra("sound_sender", b.getText());
    	startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
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
