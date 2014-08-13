package com.example.testsamp;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;

//import android.view.MenuItem;


public class MainActivity extends Activity {
	private View view;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        view = new GameView(this);
        
        setContentView(view);
        
       
    }


}
