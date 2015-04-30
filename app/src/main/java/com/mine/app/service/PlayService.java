package com.mine.app.service;

import java.util.List;

import com.mine.app.entry.Music;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class PlayService extends Service {

	private PlayControl mManager;
	private MainBinder mBinder;
	@Override
	public void onCreate() {
		mManager = new PlayControl();
		mBinder = new MainBinder();
		super.onCreate();
	}
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	public class MainBinder extends Binder {

		public void refeshPlayList(List<Music> lists){
			mManager.refeshPlayList(lists);
		}
		
		public void setPlayMode(int mode){
			mManager.setPlayMode(mode);
		}
		
		public int getCurrIndex(){
			return mManager.getCurrIndex();
		}
		
		public boolean setProgress(int progress){
			return mManager.setProgress(progress);
		}
		
		public boolean play(int position){
			return mManager.play(position);
		}
		
		public boolean pause(int position){
			return mManager.pause(position);
		}
		
		public boolean next(){
			return mManager.next();
		}
		
		public boolean previous(){
			return mManager.previous();
		}
		
		public void exit(){
			mManager.exitPlayer();
		}
	}

}
