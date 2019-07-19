package com.llh.music2.other;

/**
 * Created by 李利红 on 2019/5/27.
 */

public class Song {
    //声明头像，歌名，歌手，路径,时长
    private int headerID;
    private String songName;
    private String singer;
    private String path;
    private int durations;
    //构造函数
    public Song(int headerID, String songName, String singer, String path, int durations){
        this.headerID = headerID;
        this.songName = songName;
        this.singer = singer;
        this.path = path;
        this.durations = durations;
    }
    //外部获取，设置私有成员函数

    public void setHeaderID(int headerID) {
        this.headerID = headerID;
    }

    public int getHeaderID() {
        return headerID;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSinger() {
        return singer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDurations() {
        return durations;
    }
}
