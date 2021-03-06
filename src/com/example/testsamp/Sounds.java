package com.example.testsamp;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public final class Sounds {
	private static Context context;
	private static MediaPlayer mediaPlayer;
	private static SoundPool soundPool;
	private static int sidSE;
	
	public static void init(final Context context){
		Sounds.context= context;
		soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		sidSE = soundPool.load(context,R.raw.laser3,1);
	}
	
	public static void playSE(){
		soundPool.play(sidSE, 1.0F,1.0F, 0, 0, 1.0F);
	}
	public static void playBGM(){
		initBGM(R.raw.porcupop1min);
	}
	public static void pauseBGM(){
		if(mediaPlayer != null) mediaPlayer.pause();
	}
	public static void stopBGM(){
		if(mediaPlayer != null) mediaPlayer.stop();
	}
	
	private static synchronized void initBGM(final int resourceId){
		if(mediaPlayer != null){
			mediaPlayer.release();
			mediaPlayer = null;
		}
		mediaPlayer = MediaPlayer.create(context, resourceId);
		mediaPlayer.setLooping(true);
		mediaPlayer.setVolume(0.1F,0.1F);
		mediaPlayer.start();
	}

}
