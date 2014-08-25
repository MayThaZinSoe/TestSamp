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
import android.graphics.Paint.Align;


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

// for start button & image
private Bitmap startImage;
private Bitmap startButton;
private Bitmap retryButton;
private Bitmap retryImage;

//display the state of game
public final static int GAME_START = 0;
public final static int GAME_PLAY = 1;
public final static int GAME_OVER = 2;
private int gameState;

//play time
private final static long TIME = 60;
private long gameStarted;
private long remainedTime;

Paint timePaint = new Paint();
Paint titlePaint = new Paint();
	
	
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
	
	//for score.. setting colour
	scorePaint.setColor(Color.BLACK);
	//score text size
	scorePaint.setTextSize(32);
	scorePaint.setAntiAlias(true);
	
	//for start button
	//startImage  is added
	startImage = BitmapFactory.decodeResource(res, R.drawable.s1);
	// startButton is added
	startButton = BitmapFactory.decodeResource(res,R.drawable.start);
	
	retryImage = BitmapFactory.decodeResource(res, R.drawable.s1);
	retryButton = BitmapFactory.decodeResource(res,R.drawable.retry);
	
	gameState = GAME_START;
	
	//setting play time
	timePaint.setColor(Color.RED);
	timePaint.setTextSize(32);
	timePaint.setAntiAlias(true);
	gameState = GAME_START;
	
}	
	

@SuppressLint("DrawAllocarion")

@Override
public void onDraw(Canvas canvas){
	canvasCX = canvas.getWidth()/2;
	canvasCY = canvas.getHeight()/2;
	bgImage = Bitmap.createScaledBitmap(bgImage,canvas.getWidth()*2,canvas.getHeight(),true);
	
	switch(gameState){
	case GAME_START:
		bgImage = Bitmap.createScaledBitmap(bgImage,
				canvas.getWidth() * 2,canvas.getHeight(),true);
		startScene(canvas);
		break;
		
	case GAME_PLAY:
		playScene(canvas);
		break;
		
	case GAME_OVER:
		overScene(canvas);
		break;
	
	}
	
	
	
}

public void startScene(Canvas canvas){
	score = 0;
	startImage = Bitmap.createScaledBitmap(startImage,canvas.getWidth(),
			canvas.getHeight(),true);
			canvas.drawBitmap(startImage,0,0,null);
			
			titlePaint.setAntiAlias(true);
			titlePaint.setColor(Color.RED);
			titlePaint.setTextSize(56);
			titlePaint.setTextAlign(Align.RIGHT);
			canvas.drawText("HaPPy TouCH", canvasCY, canvasCY - 200, titlePaint);
			canvas.drawBitmap(startButton, 
					canvasCX - startButton.getWidth()/2,
					canvasCY - startButton.getHeight(), null);
					
					
}
public void overScene(Canvas canvas){
	retryImage = Bitmap.createScaledBitmap(retryImage,canvas.getWidth(),
			canvas.getHeight(),true);
			canvas.drawBitmap(retryImage,0,0,null);
	
	canvas.drawBitmap(retryButton, 
			canvasCX - retryButton.getWidth()/2,
			canvasCY - retryButton.getHeight(), null);
			titlePaint.setAntiAlias(true);
			titlePaint.setColor(Color.BLACK);
			titlePaint.setTextSize(66);
			titlePaint.setTextAlign(Align.CENTER);
			canvas.drawText("Time up", canvasCX, canvasCY - 200, titlePaint);
			
			titlePaint.setColor(Color.GREEN);
			titlePaint.setTextSize(64);
			titlePaint.setTextAlign(Align.CENTER);
			canvas.drawText("Your score: " + score, canvasCX, canvasCY + 200,titlePaint);
			
}

//mensoto
public void playScene(Canvas canvas){
	
	//play time
	remainedTime = TIME - (System.currentTimeMillis() - gameStarted) / 1000;
	if(remainedTime < 0){
		gameState = GAME_OVER;
		return;
	}
	
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
	
	//last time
	canvas.drawText( remainedTime +"sec"+  "left",10,100, timePaint);
	
}


public boolean onTouchEvent(MotionEvent me){
	//coordinate of X and Y for touch
	int x = (int)me.getX();
	int y = (int)me.getY();
	
	if(me.getAction() == MotionEvent.ACTION_DOWN){
		//getting info of condition of the game
		switch (gameState){
		case GAME_START:
			if(buttonOn(startButton,x,y)){
				gameState = GAME_PLAY;
				gameStarted = System.currentTimeMillis();
		}
		break;
		case GAME_PLAY:
			playerVY = -20;
		break;
		case GAME_OVER:
			if(buttonOn(startButton,x,y)){
				gameState = GAME_START;
			}
			break;
		}
		
	}
	return true;
}

public boolean buttonOn(Bitmap button, int x, int y){
	int posX = canvasCX - startButton.getWidth()/2;
	int posY = canvasCY - startButton.getHeight();
	
	if(x > posX && x < posX + startButton.getWidth() &&
		y > posY && y < posY + startButton.getHeight()){
		return true;
	}else{
		return false;
	}
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


