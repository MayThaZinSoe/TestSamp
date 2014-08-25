package com.example.testsamp;

import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;


public class GameView extends View{
	private Bitmap[] player = new Bitmap[5];
	
	private int playerX;
	private int playerY;
	private int canvasCX;
	
	private int canvasCY;
	private Bitmap bgImage;
	private int playerVY;
	private Bitmap dora;
	private int doraX = 0;
	private int doraY = 0;
	private int doraVX = -2;
	
	//setting for circle
	private int energyX;
	private int energyY;
	private int energyVX = -20;
	Paint energyPaint = new Paint();
	private int frameIndex = 0;
	
	// for score
private final String scoreLabel = "SCORE: ";
private int score;
Paint scorePaint = new Paint();
	
	
//constructor
public GameView(Context context){
	super(context);
	
	//making object of resource
	Resources res = this.getContext().getResources();
	bgImage = BitmapFactory.decodeResource(res, R.drawable.p2);
	dora = BitmapFactory.decodeResource(res,R.drawable.dora);

	
	player[0] = BitmapFactory.decodeResource(res,R.drawable.grape);
	player[1] = BitmapFactory.decodeResource(res,R.drawable.grape1);
	player[2] = BitmapFactory.decodeResource(res,R.drawable.grape2);
	player[3] = BitmapFactory.decodeResource(res,R.drawable.grape3);
	player[4] = BitmapFactory.decodeResource(res,R.drawable.grape4);
	
	//circle colour
	energyPaint.setColor(Color.GREEN);
	energyPaint.setAntiAlias(true);
	
	//for score.. setting color
	scorePaint.setColor(Color.BLACK);
	//score text size
	scorePaint.setTextSize(32);
	scorePaint.setAntiAlias(true);
	
	
}	
	

@SuppressLint("DrawAllocarion")

@Override
public void onDraw(Canvas canvas){
	canvasCX = canvas.getWidth()/2;
	canvasCY = canvas.getHeight()/2;
	bgImage = Bitmap.createScaledBitmap(bgImage,canvas.getWidth()*2,canvas.getHeight(),true);
	
	playScene(canvas);
}

//mesoto
public void playScene(Canvas canvas){
	
	canvas.drawBitmap(bgImage,0,0,null);
	
	doraX += doraVX;
	if(doraX < -dora.getWidth()){
		doraX = canvas.getWidth();
		doraY = (int)Math.floor(Math.random()*canvasCY);
	}
	
	canvas.drawBitmap(dora,doraX,doraY,null);
	
	//circle
	energyX += energyVX;
	if(energyX < 0 || hitCheck()){
		energyX = canvas.getWidth() + 20;
		energyY = (int)Math.floor(Math.random()* canvasCY);
		
	}
	canvas.drawCircle(energyX,energyY, 10,energyPaint);
	
	
	playerX = canvasCX - player[0].getWidth()/2;
	
	//playerY = canvasCY - player[0].getHeight()/2;
	
	playerY += playerVY;
	if(playerY < 0) playerY = 0;
	playerVY += 4;
	if(playerY > canvasCY) playerY = canvasCY;
	
	if(frameIndex > 4) frameIndex = 0;
	canvas.drawBitmap(player[frameIndex++], playerX, playerY,null);
	
	//score
	canvas.drawText(scoreLabel + score,10, 50,scorePaint);
	
}


public boolean onTouchEvent(MotionEvent me){
	if(me.getAction() == MotionEvent.ACTION_DOWN){
		playerVY = -20;
	}
	return true;
}

public boolean hitCheck(){
	if(playerX < energyX && 
			(playerX + player[0].getWidth()) > energyX &&
			playerY < energyY &&
			(playerY + player[0].getHeight()) > energyY){
		
		score  +=  10;
		return true;
	}else{
		return false;
		
	}
	}


}


