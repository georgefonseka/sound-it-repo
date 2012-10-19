package com.example.soundit;

import java.util.List;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooesASound extends TalkingActivity {
	
	private String receiver;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
   
    	Bundle bundle = getIntent().getExtras();
    	if(bundle != null) {
    		receiver = bundle.getString("sound_receiver");
    	}
        setContentView(R.layout.activity_chooes_asound);
        
        Button mElementButton1=(Button)super.findViewById(R.id.mElementButton1);
        Button mElementButton2=(Button)super.findViewById(R.id.mElementButton2);
        
        List<String> sounds = ApplicationProperties.getInstance().getSoundSuggestions(2);
        mElementButton1.setText(sounds.get(0));
        mElementButton1.setContentDescription(sounds.get(0));
        mElementButton2.setText(sounds.get(1));
        mElementButton2.setContentDescription(sounds.get(1));
        
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //this thing is use to initiate the font
        TextView element1 =(TextView) findViewById(R.id.mElementButton1);
        TextView element2 =(TextView) findViewById(R.id.mElementButton2);
		Typeface myfont = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Bold.ttf");
		element1.setTypeface(myfont);
		element2.setTypeface(myfont);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_chooes_asound, menu);
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
    
    public void goToRecording(View view) {
    	Button b = (Button) view;
        // Do something in response to button
    	Intent intent = new Intent(this, Recording.class);
    	intent.putExtra("soundSuggestion", b.getText());
    	intent.putExtra("sound_receiver", receiver);
    	startActivity(intent);
    }

	@Override
	public String getUtteranceId() {
		return "ChooseSound";
	}

	@Override
	public String getMessageKey() {
		return "ChooseSound";
	}

	@Override
	public String getFullMessage() {
		return "Choose a sound from the following two options.";
	}

	@Override
	public String getShortMessage() {
		return "Choose a sound.";
	}

}
