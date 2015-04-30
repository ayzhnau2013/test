package com.mine.app.engine.impl;

import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.mine.app.entry.Music;
import com.mine.app.service.PlayService.MainBinder;

public class PlayServiceManager {

	private Context mContext;
	private ServiceConnection mConn;
	private MainBinder mBinder;
	public PlayServiceManager(Context ctx){
		mContext = ctx;
		initConnection();
	}

	private void initConnection() {
		mConn = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				mBinder = (MainBinder) service;
			}
		};
	}
	
	public void connService(){
		Intent intent = new Intent("com.mine.app.service.PlayService");
		mContext.bindService(intent, mConn,Context.BIND_AUTO_CREATE);
	}
	
	public void setPlayMode(int mode){
		if(mBinder!=null){
			mBinder.setPlayMode(mode);
		}
	}
	
	public int getPlayIndex(){
		if(mBinder!=null){
			return mBinder.getCurrIndex();
		}
		return -1;
	}
	
	public void setProgress(int progress){
		if(mBinder!=null){
			mBinder.setProgress(progress);
		}
	}
	
	public void previous(){
		if(mBinder!=null){
			mBinder.previous();
		}
	}
	
	public void next(){
		if(mBinder!=null){
			mBinder.next();
		}
	}
	
	public boolean pauseMusic(int position){
		if(mBinder!=null){
			mBinder.pause(position);
			return true;
		}
		return false;
	}
	
	public boolean playMusic(int position){
		if(mBinder != null){
			mBinder.play(position);
			return true;
		}
		return false;
	}
	
	public void refeshList(List<Music> musics){
		if(mBinder != null){
			mBinder.refeshPlayList(musics);
		}
	}
}
