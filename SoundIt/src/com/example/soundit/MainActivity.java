package com.example.soundit;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
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
