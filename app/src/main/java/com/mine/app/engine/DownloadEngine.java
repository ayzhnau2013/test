package com.mine.app.engine;

import com.mine.app.entry.Music;

/**
 * 下载管理接口
 * @author zhu
 *
 */
public interface DownloadEngine {

	/**
	 * 下载某个音乐
	 * @param music
	 * @return
	 */
	public int download(Music music);
}
