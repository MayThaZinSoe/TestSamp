package com.example.testsamp;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class GameView extends View{
	private Bitmap player;
	private int playerX;
	private int playerY;
	private int canvasCX;
	private int canvasCY;
	private Bitmap bgImage;
	

public GameView(Context context){
	super(context);
	
	//making object of resource
	Resources res = this.getContext().getResources();
	bgImage = BitmapFactory.decodeResource(res, R.drawable.img_mainvisual);

	
	player = BitmapFactory.decodeResource(res,R.drawable.apple);
}	
	

@SuppressLint("DrawAllocarion")

@Override
public void onDraw(Canvas canvas){
	canvasCX = canvas.getWidth()/2;
	canvasCY = canvas.getHeight()/2;
	bgImage = Bitmap.createScaledBitmap(bgImage,canvas.getWidth()*2,canvas.getHeight(),true);
	
	playScene(canvas);
}
public void playScene(Canvas canvas){
	playerX = canvasCX - player.getWidth()/2;
	playerY = canvasCY - player.getHeight()/2;
	
	canvas.drawBitmap(bgImage,0,0,null);
	canvas.drawBitmap(player, playerX, playerY,null);
}




}