package com.example.soundit;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class Continue extends TalkingActivity {
	
	private String sender;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        sender = getIntent().getExtras().getString("sound_sender");
        
        setContentView(R.layout.activity_continue);
        // getActionBar().setDisplayHomeAsUpEnabled(true);
        
        TextView element1 = (TextView) findViewById(R.id.btnReply);
        TextView element2 = (TextView) findViewById(R.id.btnEndGame);
		Typeface myfont = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Bold.ttf");
		element1.setTypeface(myfont);
		element2.setTypeface(myfont);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_continue, menu);
        return true;
    }
    
    public void onEnd(View v) {
    	Intent intent = new Intent(Continue.this, MainActivity.class);
    	startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
    
    public void onReply(View v) {
    	Intent intent = new Intent(Continue.this, ChooesASound.class);
    	intent.putExtra("sound_receiver", sender);
    	startActivity(intent);
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

	@Override
	public String getUtteranceId() {
		return "Continue";
	}

	@Override
	public String getMessageKey() {
		return "Continue";
	}

	@Override
	public String getFullMessage() {
		return "Continue or end game.";
	}

	@Override
	public String getShortMessage() {
		return "Continue or end game.";
	}

}
