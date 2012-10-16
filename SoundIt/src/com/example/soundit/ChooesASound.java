package com.example.soundit;

import java.util.ArrayList;

import android.os.Bundle;
import android.content.Intent;
import android.util.DisplayMetrics;
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

    	Candidates.add("dog");
    	Candidates.add("cat");
    	Candidates.add("duck");
    	Candidates.add("pig");
    	//System.out.println("Candidates size: "+(Candidates.size()-1));   	
    	setRandomSound();   

        //定义DisplayMetrics 对象
        DisplayMetrics  dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm); 
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //窗口高度
        int screenHeight = dm.heightPixels;

        System.out.println("屏幕宽度: " + screenWidth + "\n屏幕高度： " + screenHeight);
    	//System.out.println("random number: "+tempNum1+", "+tempNum2);
        
    	//LinearLayout ll = new LinearLayout(this);
    	//this.getString(mElementButton1);
    	//this.getClass();
    	//Button test1 = (Button) this.findViewById(R.id.mElementButton1);
    	//test1.setText("test");
    	//this.
    	
    	//this.getComponentName(R.id.mElementButton1);
    	

    	//EditText test = (EditText) findViewById(R.id.mElementButton1);
    	//test1.setText("testing"); 	
    	//mElementButton1 = new ElementButton1(this);
    	//mElementButton2 = new ElementButton2(this);
    	
    	//mElementButton1.setLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT)
    	 
    	//ll.setLayoutParams(mElementButton1, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 230));
    	
    	//mElementButton1.setWidth(720);
    	//mElementButton1.setHeight(500);
    	//ll.addView(mElementButton1,
    	//		new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,100));
    	
    	//ll.addView(mElementButton2,
    	//		new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
    	//ll.addView(mElementButton1, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,300) );
    	//ll.addView(mElementButton2, 700,300);
    	//ll.addView(mElementButton2,
    	//    			new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,100));
    

    	//ll.addView(mElementButton1, 600, 500);
    	//ll.addView(mElementButton2, 600, 500);
        //setContentView(R.layout.activity_chooes_asound);
    	
    	//ll.addView(mElementButton2, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,100));
        //setContentView(ll);
        
        //R.layout.activity_chooes_asound
    	//System.out.println("mElementButton1 text: "+mElementButton1.getText());
        setContentView(R.layout.activity_chooes_asound);
       // System.out.println("R.layout.activity_chooes_asound");
        Button mElementButton1=(Button)super.findViewById(R.id.mElementButton1);
        Button mElementButton2=(Button)super.findViewById(R.id.mElementButton2);
        mElementButton1.setHeight(screenHeight/2);
        mElementButton2.setHeight(screenHeight/2);
        
        mElementButton1.setText(Candidates.get(tempNum1)+"tempNum1");
        mElementButton2.setText(Candidates.get(tempNum2)+"tempNum2");
        System.out.println(screenHeight/2);
        
        System.out.println("mElementButton1.getHeight()"+mElementButton1.getHeight());
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
        // Do something in response to button
    	Intent intent = new Intent(this, Recording.class);
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
