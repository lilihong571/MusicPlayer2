package com.llh.music2.other;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.llh.music2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李利红 on 2019/5/27.
 */

public class MusicData {
    //创建一个链表
    private List<Song> songList;
    //创建一个保存当前音乐的对象
    private Song curSong;
    //创建一个保存当前音乐ID的变量
    private int curIndex;
    //创建一个静态的MusicData对象
    private static MusicData musicData;
    //构造函数私有化
    private MusicData(Context context){
        //给链表赋值
        songList = new ArrayList<>();
        //给保存当前音乐的对象赋值
        curSong = null;
        //给保存当前音乐ID的变量赋值
        curIndex = 0;
        //获取歌曲
        getMusic(context);
    }
    //单例模式
    public static  MusicData getInstance(Context context){
        //如果对象不存在
        if(musicData == null){
            musicData = new MusicData(context);
        }
        return musicData;
    }
    //单例模式
    public static  MusicData getInstance(){
        //如果对象不存在
        if(musicData == null){
            musicData = new MusicData(null);
        }
        return musicData;
    }
    //获取歌曲逻辑
    public void getMusic(Context context){
        //创建数据库对象，数据保存对象
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        //捕获异常
        try {
            //给数据保存对象赋值
            cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
            //循环查找
            while (cursor.moveToNext()){
                //获取歌名,歌手，时长，路径
                String songName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                String singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                int durations = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                //创建临时音乐对象
                Song song = new Song(R.drawable.my_music_self_normal,songName,singer,path,durations);
                //添加歌曲到链表
                songList.add(song);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
        }
    }
    //外部获取,设置保存当前音乐的对象
    public Song getCurSong() {
        return curSong;
    }
    public void setCurSong(Song curSong) {
        this.curSong = curSong;
    }
    //外部获取，设置保存当前音乐ID的变量
    public int getCurIndex() {
        return curIndex;
    }

    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
}
