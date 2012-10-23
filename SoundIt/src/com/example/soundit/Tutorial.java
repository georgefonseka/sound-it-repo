package com.example.soundit;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class Tutorial extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        
        
        Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Sansation_Regular.ttf");
        TextView title = (TextView) findViewById(R.id.tutorialTitle);
        TextView p1 = (TextView) findViewById(R.id.tutorialParagraph1);
        TextView p2 = (TextView) findViewById(R.id.tutorialParagraph2);
        TextView p3 = (TextView) findViewById(R.id.tutorialParagraph3);
		title.setTypeface(myFont);
		p1.setTypeface(myFont);
		p2.setTypeface(myFont);
		p3.setTypeface(myFont);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tutorial, menu);
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

}
