package com.mine.app.entry;

import java.util.List;
/**
 * 歌手
 * @author zhu
 *
 */
public class Artist {

	private String mID;
	private String mName;
	
	private List<Album> mAlbum;//专辑
	private List<Music> mSongs;//单曲 
	private List<Artist> mSimilar;//相似歌手
	
	private List<String> mPictures;//插图URL

	public String getID() {
		return mID;
	}

	public void setID(String mID) {
		this.mID = mID;
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public List<Album> getAlbum() {
		return mAlbum;
	}

	public void setAlbum(List<Album> mAlbum) {
		this.mAlbum = mAlbum;
	}

	public List<Music> getSongs() {
		return mSongs;
	}

	public void setSongs(List<Music> mSongs) {
		this.mSongs = mSongs;
	}

	public List<Artist> getSimilar() {
		return mSimilar;
	}

	public void setSimilar(List<Artist> mSimilar) {
		this.mSimilar = mSimilar;
	}

	public List<String> getPictures() {
		return mPictures;
	}

	public void setPictures(List<String> mPictures) {
		this.mPictures = mPictures;
	}
	
	
}
