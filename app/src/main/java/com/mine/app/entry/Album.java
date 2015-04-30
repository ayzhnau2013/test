package com.mine.app.entry;

/**
 * 专辑
 * @author zhu
 *
 */
public class Album {

	private String mID;
	private String mAlbumName;//专辑名
	private String mIssueDate;//发行日期
	private String mType;//类型
	private String mLanuage;
	private String mIssueCompany;
	
	private String mDescription;

	public String getID() {
		return mID;
	}

	public void setID(String mID) {
		this.mID = mID;
	}

	public String getAlbumName() {
		return mAlbumName;
	}

	public void setAlbumName(String mAlbumName) {
		this.mAlbumName = mAlbumName;
	}

	public String getIssueDate() {
		return mIssueDate;
	}

	public void setIssueDate(String mIssueDate) {
		this.mIssueDate = mIssueDate;
	}

	public String getType() {
		return mType;
	}

	public void setType(String mType) {
		this.mType = mType;
	}

	public String getLanuage() {
		return mLanuage;
	}

	public void setLanuage(String mLanuage) {
		this.mLanuage = mLanuage;
	}

	public String getIssueCompany() {
		return mIssueCompany;
	}

	public void setIssueCompany(String mIssueCompany) {
		this.mIssueCompany = mIssueCompany;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String mDescription) {
		this.mDescription = mDescription;
	}
	
	 
}
