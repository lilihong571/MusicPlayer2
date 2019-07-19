package com.llh.music2.other;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by 李利红 on 2019/5/27.
 */

public class ControlPlay {
    //创建一个媒体对象
    private MediaPlayer mediaPlayer;
    //创建一个静态的播放控制对象
    private static ControlPlay controlPlay;

    //构造函数私有化
    private ControlPlay(){
        //给媒体对象赋值
        mediaPlayer = new MediaPlayer();
    }
    //单例模式
    public static ControlPlay getInstance(){
        //如果没有创建过播放控制对象
        if(controlPlay == null){
            //创建一个播放控制对象
            controlPlay = new ControlPlay();
        }
        return controlPlay;
    }
    //播放
    public void playMusic(String path){
        //捕获异常
        try {
            //重置
            mediaPlayer.reset();
            //设置资源路径
            mediaPlayer.setDataSource(path);
            //设置异步加载
            mediaPlayer.prepareAsync();
            //设置加载监听
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    //播放
                    mediaPlayer.start();
                    //循环播放

                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //暂停
    public void pauseMusic(){
        //如果歌曲存在并且正在播放
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    //恢复
    public void resumeMusic(){
        //如果歌曲存在
        if(mediaPlayer!=null){
            mediaPlayer.start();
        }
    }
    //停止
    public void stopMusic(){
        //如果歌曲存在
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }

    //外部获取媒体对象

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

}
