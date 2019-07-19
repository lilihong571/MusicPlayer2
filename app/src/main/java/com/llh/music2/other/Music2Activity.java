package com.llh.music2.other;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.llh.music2.F1;
import com.llh.music2.R;

public class Music2Activity extends AppCompatActivity {
    //声明控件
    private ImageView imageView_picture;
    private TextView textView_songName;
    private TextView textView_singer;
    private ImageButton imageButton_last;
    private ImageButton imageButton_play;
    private ImageButton imageButton_next;
    //声明播放控制对象
    private ControlPlay controlPlay;
    //声明滑动条
    private SeekBar seekBar_song;
    //声明左右计时文本
    private TextView textView_left;
    private TextView textView_right;
    //声明动作
    //private Animation animation;
    private ObjectAnimator objectAnimator;

    //创建一个线程
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //时刻获取当前时长
            int progress = ControlPlay.getInstance().getMediaPlayer().getCurrentPosition();
            //分秒转换
            int m = progress / 1000 / 60;//单位为m
            int s = progress / 1000 % 60;//单位为s
            //如果分小于10
            if (m < 10) {
                //如果秒也小于10
                if (s < 10) {
                    //分秒前面同时补0
                    textView_left.setText("0" + String.valueOf(m) + ":" + "0" + String.valueOf(s));
                } else {
                    //只在分前面补0
                    textView_left.setText("0" + String.valueOf(m) + ":" + String.valueOf(s));
                }
            } else {
                //如果秒小于10
                if (s < 10) {
                    //秒前面补0
                    textView_left.setText(String.valueOf(m) + ":" + "0" + String.valueOf(s));
                } else {
                    //都不补0
                    textView_left.setText(String.valueOf(m) + ":" + String.valueOf(s));
                }
            }
            //设置滑动条当前值
            seekBar_song.setProgress(progress);
            //开启线程
            handler.post(runnable);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music2);
        //调用控件初始化函数
        init();
//        //图片旋转
//        picRotate();
        objectAnimator = ObjectAnimator.ofFloat(imageView_picture, "rotation", 0.0f, 360.0f);
        objectAnimator.setDuration(10000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        //开始旋转
        objectAnimator.start();

        //给播放控制对象赋值
        controlPlay = ControlPlay.getInstance();
        //列表循环
        ControlPlay.getInstance().getMediaPlayer().setLooping(true);
        //创建临时音乐对象
        Song song = MusicData.getInstance().getCurSong();

        F1.getTextView_playing().setText(song.getSongName());
        //更换歌名歌手
        textView_songName.setText(song.getSongName());
        textView_singer.setText(song.getSinger());
        //设置按钮监听
        imageButton_play.setOnClickListener(onClickListener);
        imageButton_last.setOnClickListener(onClickListener);
        imageButton_next.setOnClickListener(onClickListener);
        //获取歌曲最大时长
        int max = song.getDurations();//单位为ms
        //分秒转换
        setSeekBarMax(max);
        //设置滑动条滑动事件监听
        seekBar_song.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //b==true表示滑动条是用户拨动的
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b == true) {
                    //将歌曲对应时长滑动到用户拨动的长度处
                    controlPlay.getMediaPlayer().seekTo(i);
                    //将当前时长进行分秒转换
                    //分秒转换
                    int m = i / 1000 / 60;//单位为m
                    int s = i / 1000 % 60;//单位为s
                    //如果分小于10
                    if (m < 10) {
                        //如果秒也小于10
                        if (s < 10) {
                            //分秒前面同时补0
                            textView_left.setText("0" + String.valueOf(m) + ":" + "0" + String.valueOf(s));
                        } else {
                            //只在分前面补0
                            textView_left.setText("0" + String.valueOf(m) + ":" + String.valueOf(s));
                        }
                    } else {
                        //如果秒小于10
                        if (s < 10) {
                            //秒前面补0
                            textView_left.setText(String.valueOf(m) + ":" + "0" + String.valueOf(s));
                        } else {
                            //都不补0
                            textView_left.setText(String.valueOf(m) + ":" + String.valueOf(s));
                        }
                    }
                    //设置滑动条当前值
                    seekBar_song.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //手动开启线程
        handler.post(runnable);
    }

    //控件初始化函数
    public void init() {
        imageView_picture = (ImageView) findViewById(R.id.iv_picture);
        textView_songName = (TextView) findViewById(R.id.tv_songName);
        textView_singer = (TextView) findViewById(R.id.tv_singer);
        imageButton_last = (ImageButton) findViewById(R.id.iv_btn_last);
        imageButton_play = (ImageButton) findViewById(R.id.iv_btn_play);
        imageButton_next = (ImageButton) findViewById(R.id.iv_btn_next);
        seekBar_song = (SeekBar) findViewById(R.id.sb_song);
        textView_left = (TextView) findViewById(R.id.tv_left);
        textView_right = (TextView) findViewById(R.id.tv_right);
    }

    //按钮监听函数
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_btn_last:
                    //如果当前歌曲是第一首
                    if (MusicData.getInstance().getCurIndex() <= 0) {
                        //点击此按钮即跳到最后一首
                        MusicData.getInstance().setCurIndex(MusicData.getInstance().getSongList().size() - 1);
                    } else {
                        //点击此按钮即下标减一
                        MusicData.getInstance().setCurIndex(MusicData.getInstance().getCurIndex() - 1);
                    }
                    //创建临时音乐对象
                    Song song = MusicData.getInstance().getSongList().get(MusicData.getInstance().getCurIndex());
                    //播放音乐
                    controlPlay.playMusic(song.getPath());
                    //获取歌曲最大时长
                    int max = song.getDurations();//单位为ms
                    //分秒转换
                    setSeekBarMax(max);
                    //设置歌名，歌手内容
                    textView_songName.setText(song.getSongName());
                    textView_singer.setText(song.getSinger());
                    //停止旋转并重置
                    objectAnimator.end();
                    //开始旋转
                    objectAnimator.start();
//                    //图片旋转
//                    picRotate();
//                    //设置主界面的文本内容
                     F1.getTextView_playing().setText(song.getSongName());
                    //列表循环
                    ControlPlay.getInstance().getMediaPlayer().setLooping(true);
                    break;
                case R.id.iv_btn_play:
                    //如果歌曲正在播放
                    if (controlPlay.getMediaPlayer() != null && controlPlay.getMediaPlayer().isPlaying()) {
                        //点击此按钮即暂停
                        controlPlay.pauseMusic();
                        //切换到暂停时的图片
                        imageButton_play.setBackgroundResource(R.drawable.btn_pause);
                        //停止旋转
//                        animation.cancel();
                        //暂停
                        objectAnimator.pause();
                    } else {
                        //点击此按钮即恢复播放
                        controlPlay.resumeMusic();
                        //切换到播放时的图片
                        imageButton_play.setBackgroundResource(R.drawable.btn_play);
//                        imageView_picture.startAnimation(animation);
                        //继续
                        objectAnimator.resume();
                    }
                    break;
                case R.id.iv_btn_next:
                    //对列表中音乐的下标进行处理
                    MusicData.getInstance().setCurIndex((MusicData.getInstance().getCurIndex() + 1) % (MusicData.getInstance().getSongList().size()));
                    //创建临时音乐对象
                    Song song1 = MusicData.getInstance().getSongList().get(MusicData.getInstance().getCurIndex());
                    //播放音乐
                    controlPlay.playMusic(song1.getPath());
                    //获取歌曲最大时长
                    int max1 = song1.getDurations();//单位为ms
                    //分秒转换
                    setSeekBarMax(max1);
                    //设置歌名，歌手内容
                    textView_songName.setText(song1.getSongName());
                    textView_singer.setText(song1.getSinger());
//                    //图片旋转
//                    picRotate();
                    //停止旋转并重置
                    objectAnimator.end();
                    //开始旋转
                    objectAnimator.start();
                    //设置主界面的文本内容
                     F1.getTextView_playing().setText(song1.getSongName());
                    //列表循环
                    ControlPlay.getInstance().getMediaPlayer().setLooping(true);
                    break;

            }
        }
    };

    //设置最大时长//传入最大时长
    public void setSeekBarMax(int max) {
        //分秒转换
        int m = max / 1000 / 60;//单位为m
        int s = max / 1000 % 60;//单位为s
        //如果分小于10
        if (m < 10) {
            //如果秒也小于10
            if (s < 10) {
                //分秒前面同时补0
                textView_right.setText("0" + String.valueOf(m) + ":" + "0" + String.valueOf(s));
            } else {
                //只在分前面补0
                textView_right.setText("0" + String.valueOf(m) + ":" + String.valueOf(s));
            }
        } else {
            //如果秒小于10
            if (s < 10) {
                //秒前面补0
                textView_right.setText(String.valueOf(m) + ":" + "0" + String.valueOf(s));
            } else {
                //都不补0
                textView_right.setText(String.valueOf(m) + ":" + String.valueOf(s));
            }
        }
        //设置滑动条的最大值
        seekBar_song.setMax(max);
    }
}