package com.mine.app.engine;

import com.mine.app.entry.Music;

/**
 * 播放业务接口
 * @author zhu
 *
 */
public interface PlayEngine {
    /**
     * 播放音乐
     * @param music 要播放的音乐
     * @return　播放状态
     */
	public int startPlay(Music music);
	
	/**
	 * 暂停
	 * @param music
	 */
	public void pausePlay(Music music);
	
	/**
	 * 播放下一个
	 * @return
	 */
	public int nextPlay(Music music);
	
	/**
	 * 播放前一个
	 * @param music
	 * @return
	 */
	public int prePlay(Music music);
	
	/**
	 * 播放模式 1.顺序2.随机3.循环
	 * @return
	 */
	public int playMode();
	
}
