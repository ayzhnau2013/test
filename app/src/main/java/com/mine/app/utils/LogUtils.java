package com.mine.app.utils;

import android.util.Log;

/**
 * 日志工具
 * @author zhu
 *
 */
public class LogUtils {
	
	private static boolean isDebug = true;
	
	public static void i(String tag,String value){
		if(isDebug){
			Log.i(tag, value);
		}
	}
	
	public static void e(String tag,String value){
		if(isDebug){
			Log.e(tag, value);
		}
	}

}
