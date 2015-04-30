package com.mine.app.entry;
/**
 * 歌曲
 * @author zhu
 *
 */
public class Music {
	private String mID;
	private String mSongName;
	private String mArtist;
	private String mAlbum;//专辑名称
	private String mStyle;
	
	private String mDuration;//时长
	private String mCapacity;//大小
	private String mFormat;//文件格式
	private String mPeriod;//年代
	private String mSavePath;
	
	private String mFavorite;//最爱
	
	public String getFavorite() {
		return mFavorite;
	}
	public void setFavorite(String mFavorite) {
		this.mFavorite = mFavorite;
	}
	public String getID() {
		return mID;
	}
	public void setID(String mID) {
		this.mID = mID;
	}
	public String getSongName() {
		return mSongName;
	}
	public void setSongName(String mSongName) {
		this.mSongName = mSongName;
	}
	public String getArtist() {
		return mArtist;
	}
	public void setArtist(String mArtist) {
		this.mArtist = mArtist;
	}
	public String getAlbum() {
		return mAlbum;
	}
	public void setAlbum(String mAlbum) {
		this.mAlbum = mAlbum;
	}
	public String getStyle() {
		return mStyle;
	}
	public void setStyle(String mStyle) {
		this.mStyle = mStyle;
	}
	public String getDuration() {
		return mDuration;
	}
	public void setDuration(String mDuration) {
		this.mDuration = mDuration;
	}
	public String getCapacity() {
		return mCapacity;
	}
	public void setCapacity(String mCapacity) {
		this.mCapacity = mCapacity;
	}
	public String getFormat() {
		return mFormat;
	}
	public void setFormat(String mFormat) {
		this.mFormat = mFormat;
	}
	public String getPeriod() {
		return mPeriod;
	}
	public void setPeriod(String mPeriod) {
		this.mPeriod = mPeriod;
	}
	public String getSavePath() {
		return mSavePath;
	}
	public void setSavePath(String mSavePath) {
		this.mSavePath = mSavePath;
	}
	
	
}
