package com.example.soundit;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GuessSoundPage extends TalkingActivity implements SensorEventListener {
	
	private static final String LOG_TAG = "GuessSound";
	private static final int SHAKE_THRESHOLD = 1000;
	
	private String soundName;
	private int soundResourceId;
	private MediaPlayer mediaPlayer;
	private SensorManager sensorManager;
	private Sensor accelerometer;
	private int tries;
	private long lastUpdate;
	private float lastX;
	private float lastY;
	private float lastZ;
	
	public GuessSoundPage() {
		
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        soundName = bundle.getString("sound_name");
    	soundResourceId = bundle.getInt("sound_resource_id");
    	
    	sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        setContentView(R.layout.activity_guess_sound_page);
    }
    
    private void playSound(int rid) {
    	if(mediaPlayer == null) {
    		mediaPlayer = MediaPlayer.create(this.getApplicationContext(), rid);
    	}
    	mediaPlayer.start();
    }
    
    public void replay(View v) {
    	playSound(soundResourceId);
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	playSound(soundResourceId);
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
	public void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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
    	EditText input = (EditText)findViewById(R.id.edit_message); 
    	String answer = input.getText().toString().toLowerCase();
    	if(tries < 3 && !soundName.equals(answer)) {
    		tries++;
    		String hint = createHint(tries,soundName);
    		speak(hint);
        	Toast toast = Toast.makeText(this, hint, Toast.LENGTH_SHORT);
        	toast.show();
    		
    	} else {
    		String msg = "Sorry. The correct answer was " + soundName;
    		if(soundName.equals(answer)) {
    			msg = "You are correct!";
    		}
    		speak(msg);
        	Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        	toast.show();
        	
        	// this might be a bit dodgy
        	new Handler().postDelayed(
    	        new Runnable() {
    	        public void run() {
    	        	 // Do something in response to button
    	        	Intent intent = new Intent(GuessSoundPage.this, MainActivity.class);
    	        	startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    	        }
            }, 2000);
    		
	        // Do something in response to button
	    	
    	}
    }
    
    private String createHint(int tries, String word) {
    	if(tries == 1) {
    		return "Incorrect. The word has " + word.length() + " letters.";
    	} else if(tries == 2) {
    		return "Incorrect. The word begins with a '" + word.charAt(0) + "'";
    	} else {
    		return "Incorrect. The word ends with a '" + word.charAt(word.length() - 1) + "'";
    	}
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
		return "Guess the sound. You have four guesses.";
	}

	@Override
	public String getShortMessage() {
		return "Guess the sound";
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// do nothing
	}

	public void onSensorChanged(SensorEvent event) {
		Sensor sensor = event.sensor;
		if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			float[] values = event.values;
		    long curTime = System.currentTimeMillis();
		    // only allow one update every 100ms.
		    if ((curTime - lastUpdate) > 100) {
		      long diffTime = (curTime - lastUpdate);
		      lastUpdate = curTime;

		      float x = values[0];
		      float y = values[1];
		      float z = values[2];

		      float speed = Math.abs(x + y + z - lastX - lastY - lastZ) / diffTime * 10000;

		      if (speed > SHAKE_THRESHOLD) {
		    	  Log.d(LOG_TAG, "shake detected w/ speed: " + speed);
		    	  EditText input = (EditText)findViewById(R.id.edit_message); 
		    	  input.getText().clear();
		      }
		      lastX = x;
		      lastY = y;
		      lastZ = z;
		    }
		  }
	}

}
