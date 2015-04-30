package com.mine.app.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.mine.app.MusicApplication;

public class SharedPreferencesUtils{

    private SharedPreferences mSp;
    private Editor mEditor;
    public static final String SP_NAME = "appMusic";
    public static final String SP_LOGIN_USER_NAME = "login_user_name";
    public static final String SP_LOGIN_USER_PWD = "login_user_pwd";
    public static final String SP_LOGIN_USER_PIC = "login_user_pic";
    public static final String SP_IS_REMENBER_PWD = "is_remenber_pwd";
    public static final String SP_IS_SUCLOGIN = "is_suclogin";
    public static final String SP_IS_DELETEAPK = "is_deleteapk";
    public static final String SP_IS_AUTOINSTALL= "is_autoinstall";
    public static final String SP_IS_BOTUNINSTALL_CONFIG= "is_botuninstall_config";

    public static final String SP_CURRENT_PLAY = "current_position";

    public SharedPreferencesUtils(Context context) {
        mSp = MusicApplication.getApplication().getSharedPreferences(SP_NAME, Context.MODE_WORLD_WRITEABLE);
        mEditor = mSp.edit();
    }

    public void savePosition(int position){
        mEditor.putInt(SP_CURRENT_PLAY,position);
        mEditor.commit();
    }

    public int getPosition(){
        return mSp.getInt(SP_CURRENT_PLAY,-1);
    }

    public void saveLoginUserName(String mName) {
        mEditor.putString(SP_LOGIN_USER_NAME, mName);
        mEditor.commit();
    }

    public String getLoginUserName() {
        return mSp.getString(SP_LOGIN_USER_NAME, "");
    }

    public void saveLoginUserPwd(String pwd) {
        mEditor.putString(SP_LOGIN_USER_PWD, pwd);
        mEditor.commit();
    }

    public String getLoginUserPwd() {
        return mSp.getString(SP_LOGIN_USER_PWD, "");
    }

    public void saveLoginUserPic(String pic) {
        mEditor.putString(SP_LOGIN_USER_PIC, pic);
        mEditor.commit();
    }

    public String getLoginUserPic() {
        return mSp.getString(SP_LOGIN_USER_PIC, "");
    }
    
    public void saveIsRemenberPwd(boolean isRmbPwd) {
        mEditor.putBoolean(SP_IS_REMENBER_PWD, isRmbPwd);
        mEditor.commit();
    }

    public boolean isRemenberPwd() {
        return mSp.getBoolean(SP_IS_REMENBER_PWD, false);
    }
    
    public void saveIsSuccessLogin(boolean isLogined) {
        mEditor.putBoolean(SP_IS_SUCLOGIN, isLogined);
        mEditor.commit();
    }

    public boolean IsSuccessLogin() {
        return mSp.getBoolean(SP_IS_SUCLOGIN, false);
    }
    
    public void saveIsDeleteApkAfterInstall(boolean isdelete) {
        mEditor.putBoolean(SP_IS_DELETEAPK, isdelete);
        mEditor.commit();
    }

    public boolean IsDeleteApkAfterInstall() {
        return mSp.getBoolean(SP_IS_DELETEAPK, true);
    }
    
    public void saveIsAutoinstall(boolean isAutoInstall) {
        mEditor.putBoolean(SP_IS_AUTOINSTALL, isAutoInstall);
        mEditor.commit();
    }

    public boolean IsAutoinstall() {
        return mSp.getBoolean(SP_IS_AUTOINSTALL, true);
    }
    
    public void saveIsBothUninstallConfig(boolean isBothUninstallConfig) {
        mEditor.putBoolean(SP_IS_BOTUNINSTALL_CONFIG, isBothUninstallConfig);
        mEditor.commit();
        
    }

    public boolean IsBothUninstallConfig() {
        return mSp.getBoolean(SP_IS_BOTUNINSTALL_CONFIG, false);
    }
    
}
