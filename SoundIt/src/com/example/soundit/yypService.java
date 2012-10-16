package com.example.soundit;


import java.io.IOException;
import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class yypService extends Service {
	private MediaPlayer mp;
	private ArrayList<String> soundCandidate=new ArrayList<String>();

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		// 开始播放音乐
		mp.start();
		// 音乐播放完毕的事件处理
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				// 循环播放
				try {
					mp.start();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// 播放音乐时发生错误的事件处理
		mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {

			public boolean onError(MediaPlayer mp, int what, int extra) {
				// TODO Auto-generated method stub
				// 释放资源
				try {
					mp.release();
				} catch (Exception e) {
					e.printStackTrace();
				}

				return false;
			}
		});

		//super.onStart(intent, startId);
	}

	@Override
	public void onCreate() {
		soundCandidate.add("1");
		soundCandidate.add("2");
		soundCandidate.add("3");
		int tempNum = (int) (Math.random()*(soundCandidate.size()-1)+1);	
		// TODO Auto-generated method stub
		// 初始化音乐资源
		try {
			// 创建MediaPlayer对象
			mp = new MediaPlayer();
			// 将音乐保存在res/raw/xingshu.mp3,R.java中自动生成{public static final int xingshu=0x7f040000;}
			if(tempNum==1){
				mp = MediaPlayer.create(yypService.this, R.raw.testing_voicein_homepage);
			}
			if(tempNum==2){
				mp = MediaPlayer.create(yypService.this, R.raw.testing_voicein_homepage);
			}
			if(tempNum==3){
				mp = MediaPlayer.create(yypService.this, R.raw.testing_voicein_homepage);
			}
			// 在MediaPlayer取得播放资源与stop()之后要准备PlayBack的状态前一定要使用MediaPlayer.prepeare()
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.onCreate();
		

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		// 服务停止时停止播放音乐并释放资源
		mp.stop();
		mp.release();

		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}