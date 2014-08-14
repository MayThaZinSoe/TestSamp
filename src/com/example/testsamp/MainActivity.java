package com.example.testsamp;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;



//import android.view.MenuItem;


public class MainActivity extends Activity {
	private View view;
	
	//setting handler
	private Handler handler = new Handler();
	private final static long MSEC = 30;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        view = new GameView(this);
        
        setContentView(view);
        
	Timer timer = new Timer(false);
		
		timer.schedule(new TimerTask(){
			public void run(){
				handler.post(new Runnable(){
					public void run(){
						view.invalidate();
						
					}});
				}
		},0,MSEC);
	
	}
        
       
    }



