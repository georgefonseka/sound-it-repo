package com.example.soundit;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class FriendList extends TalkingActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
        
        TextView element1 =(TextView) findViewById(R.id.btnGeorge);
        TextView element2 =(TextView) findViewById(R.id.btnShu);
		Typeface myfont = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Bold.ttf");
		element1.setTypeface(myfont);
		element2.setTypeface(myfont);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_friend_list, menu);
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
    	// audible message
    	toast("Your sound has been sent.");
    	
    	// this might be a bit dodgy
    	new Handler().postDelayed(
	        new Runnable() {
	        public void run() {
	        	 // Do something in response to button
	        	Intent intent = new Intent(FriendList.this, MainActivity.class);
	        	startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
	        }
        }, 2000);
       
    }

	@Override
	public String getUtteranceId() {
		return "SendFriendList";
	}

	@Override
	public String getMessageKey() {
		return "SendFriendList";
	}

	@Override
	public String getFullMessage() {
		return "Choose a friend.";
	}

	@Override
	public String getShortMessage() {
		return "Choose a friend.";
	}

    

}
