package com.example.testsamp;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class GameView extends View{
	private Bitmap[] player = new Bitmap[5];
	
	private int playerX;
	private int playerY;
	private int canvasCX;
	private int canvasCY;
	private Bitmap bgImage;
	
	private Bitmap dora;
	private int doraX = 0;
	private int doraY = 0;
	private int doraVX = -2;
	
	
	private int frameIndex = 0;
	
//constructor
public GameView(Context context){
	super(context);
	
	//making object of resource
	Resources res = this.getContext().getResources();
	bgImage = BitmapFactory.decodeResource(res, R.drawable.image003);
	dora = BitmapFactory.decodeResource(res,R.drawable.dora);

	
	player[0] = BitmapFactory.decodeResource(res,R.drawable.grape);
	player[1] = BitmapFactory.decodeResource(res,R.drawable.grape1);
	player[2] = BitmapFactory.decodeResource(res,R.drawable.grape2);
	player[3] = BitmapFactory.decodeResource(res,R.drawable.grape3);
	player[4] = BitmapFactory.decodeResource(res,R.drawable.grape4);
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
	
	
	playerX = canvasCX - player[0].getWidth()/2;
	playerY = canvasCY - player[0].getHeight()/2;
	if(frameIndex > 4) frameIndex = 0;
	canvas.drawBitmap(player[frameIndex++], playerX, playerY,null);
}




}