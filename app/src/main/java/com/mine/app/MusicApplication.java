package com.mine.app;

import android.app.Application;

import com.mine.app.engine.impl.PlayServiceManager;
import com.mine.app.utils.SharedPreferencesUtils;

public class MusicApplication extends Application {

    private SharedPreferencesUtils mSharedUtils;

    private PlayServiceManager mServiceManager;

    private static MusicApplication mInstance;
	@Override
	public void onCreate() {
		super.onCreate();
        mInstance = this;

        mSharedUtils = new SharedPreferencesUtils(this);
        mServiceManager = new PlayServiceManager(this);
	}

    public static MusicApplication getApplication(){
        return mInstance;
    }

    public SharedPreferencesUtils getSharedUtils(){
        return mSharedUtils;
    }

}
