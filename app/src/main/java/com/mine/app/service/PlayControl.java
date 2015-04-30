package com.mine.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mine.app.constants.PlayConstant;
import com.mine.app.entry.Music;
import com.mine.app.utils.LogUtils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.text.TextUtils;

public class PlayControl implements OnCompletionListener{

	private int mPlayState;
	private int mPlayMode;
	private int mCurrIndex;
	private Random mRandom;
	private MediaPlayer mPlayer;
	private List<Music> mPlayLists;
	
	
	public PlayControl(){
		mPlayer = new MediaPlayer();
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mPlayer.setOnCompletionListener(this);
		
		mPlayLists = new ArrayList<Music>();
		mPlayState = PlayConstant.MPS_NOFILE;
		mPlayMode = PlayConstant.MPM_ORDER_PLAY;
		mCurrIndex = -1;
		
		mRandom = new Random(System.currentTimeMillis());
	}

	public void refeshPlayList(List<Music> lists){
		mPlayLists.clear();
		mPlayLists.addAll(lists);
		
		if(mPlayLists.size()==0){
			mPlayState = PlayConstant.MPS_NOFILE;
			mCurrIndex = -1;
		}
	}
	
	public boolean play(int position){
		if(!prepare(position)){
			return false;
		}
		if(mCurrIndex == position){
			//prepare方法中已经赋值‘
			if(!mPlayer.isPlaying()){
				mPlayer.start();
				mPlayState = PlayConstant.MPS_PLAYING;
			}else{
				pause(position);
			}
		}
		return true;
	}
	
	public boolean pause(int position){
		if(mPlayState != PlayConstant.MPS_PLAYING){
			return false;
		}
		mPlayer.pause();
		mPlayState = PlayConstant.MPS_PAUSE;
		return true;
	}
	
	public boolean repaly(){
		if(mPlayState == PlayConstant.MPS_INVALID || mPlayState == PlayConstant.MPS_NOFILE){
			return false;
		}
		mPlayer.start();
		mPlayState = PlayConstant.MPS_PLAYING;
		return true;
	}
	
	private boolean prepare(int position){
		mCurrIndex = position;
		mPlayer.reset();
		Music music = mPlayLists.get(position);
		if(music != null && !TextUtils.isEmpty(music.getSavePath())){
			try {
				mPlayer.setDataSource(music.getSavePath());
				mPlayer.prepare();
				mPlayState = PlayConstant.MPS_PREPARE;
			} catch (Exception e) {
				LogUtils.e("Error",e.getMessage());
				mPlayState = PlayConstant.MPS_INVALID;
				if(position < mPlayLists.size()){
					position ++ ;
					prepare(position);
				}
				return false;
			}
			
			return true;
		}else{
			LogUtils.e("Error","music == null or path == null");
			if(position < mPlayLists.size()){
				position ++ ;
				prepare(position);
			}
			return false;
		}
	}
	
	public boolean previous(){
		if(mPlayState == PlayConstant.MPS_NOFILE){
			return false;
		}
		mCurrIndex --;
		mCurrIndex = resetingIndex(mCurrIndex);
		if(!prepare(mCurrIndex)){
			return false;
		}
		return repaly();
	}
	
	public boolean next(){
		if(mPlayState == PlayConstant.MPS_NOFILE){
			return false;
		}
		mCurrIndex ++;
		mCurrIndex = resetingIndex(mCurrIndex);
		if(!prepare(mCurrIndex)){
			return false;
		}
		return repaly();
	}
	
	private int resetingIndex(int index) {
		int result;
		if(mPlayMode == PlayConstant.MPM_RANDOM_PLAY){
			result = mRandom.nextInt(mPlayLists.size());
		}else{
			if(index < 0){
				result = mPlayLists.size()-1;
			}else if(index >= mPlayLists.size()){
				result = 0;
			}else{
				result = index;
			}
		}
		return result;
	}

	public long getPosition(){
		return mPlayer.getCurrentPosition();
	}
	
	public int getCurrIndex(){
		return mCurrIndex;
	}
	
	public void setPlayMode(int mode){
		mPlayMode = mode;
	}
	
	public boolean setProgress(int progress){
		if(mPlayState == PlayConstant.MPS_INVALID || mPlayState == PlayConstant.MPS_NOFILE){
			return false;
		}
		int time = mPlayer.getDuration();
		int pro = (int)(((float)progress / 100) * time);
		mPlayer.seekTo(pro);
		return true;
	}
	
	public void exitPlayer(){
		mPlayer.stop();
		mPlayer.release();
		mCurrIndex = -1;
		mPlayState = PlayConstant.MPS_INVALID;
	}
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		switch(mPlayMode){
		case PlayConstant.MPM_SINGLE_LOOP_PLAY:
			play(mCurrIndex);
			break;
		case PlayConstant.MPM_RANDOM_PLAY:
			int tempResult = mRandom.nextInt(mPlayLists.size());
			if(tempResult < 0){
				mCurrIndex = 0;
			}else{
				mCurrIndex = tempResult;
			}
			if(prepare(mCurrIndex)){
				repaly();
			}
			break;
		case PlayConstant.MPM_ORDER_PLAY:
			int index = resetingIndex(mCurrIndex);
			if(prepare(index)){
				repaly();
			}
			break;
		case PlayConstant.MPM_LIST_LOOP_PLAY:
			next();
			break;
		}
	}
}
