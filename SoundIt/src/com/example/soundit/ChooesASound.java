package com.example.soundit;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class ChooesASound extends TalkingActivity {
    //ElementButton1 mElementButton1 = null;
	//ElementButton2 mElementButton2 = null;
	private ArrayList<String> Candidates=new ArrayList<String>();

	int tempNum1 = 0;
	int tempNum2 = 0;
	int dpi = 319;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
   
        setContentView(R.layout.activity_chooes_asound);
        
        Button mElementButton1=(Button)super.findViewById(R.id.mElementButton1);
        Button mElementButton2=(Button)super.findViewById(R.id.mElementButton2);
        
        List<String> sounds = ApplicationProperties.getInstance().getSoundSuggestions(2);
        mElementButton1.setText(sounds.get(0));
        mElementButton1.setContentDescription(sounds.get(0));
        mElementButton2.setText(sounds.get(1));
        mElementButton2.setContentDescription(sounds.get(1));
        
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        
        
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
    	startActivity(intent);
    }
    
    public void setRandomSound(){

    	tempNum1 = (int) Math.round(Math.random()*(Candidates.size()-1));
    	tempNum2 = (int) Math.round(Math.random()*(Candidates.size()-1));
    	while(tempNum1==tempNum2){
    		tempNum2 = (int) (Math.random()*(Candidates.size()-1));	
    	}

    }
    /*
    class ElementButton1 extends Button {

		public ElementButton1(Context context) {
			super(context);
			setText(Candidates.get(tempNum1)+"tempNum1");
			System.out.println("setText(Candidates.get(tempNum1)+tempNum1);"+Candidates.get(tempNum1)+"tempNum1");
		}

    }
    class ElementButton2 extends Button {

		public ElementButton2(Context context) {
			super(context);
			setText(Candidates.get(tempNum2)+"tempNum2");
		}

    }
*/

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
