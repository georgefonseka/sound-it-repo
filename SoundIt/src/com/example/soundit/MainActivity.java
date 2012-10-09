package com.example.soundit;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        
        MediaPlayer mediaPlayer = MediaPlayer.create(this.getApplicationContext(), R.raw.testing_voicein_homepage);
        mediaPlayer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        

        
        return true;
    }
    */
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	TextView txt = (TextView) findViewById(R.id.custom_font);  
    	Typeface font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");  
    	txt.setTypeface(font); 
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everything_demo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_everything_demo, menu);
        return true;
    }
	
    public void goToPlay(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, ChooesASound.class);
    	startActivity(intent);
    }
    public void goToGuess(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, ChooseAFriend.class);
    	startActivity(intent);
    }
}
