package com.llh.music2.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.llh.music2.R;

import java.util.List;

/**
 * Created by 李利红 on 2019/5/27.
 */

public class MusicAdapter extends BaseAdapter {
    //创建上下文
    private Context context;
    //创建一个音乐链表
    private List<Song> songList;
    //构造函数
    public MusicAdapter(Context context, List<Song> songList){
        this.context = context;
        this.songList = songList;
    }
    //获取链表大小
    @Override
    public int getCount() {
        return songList.size();
    }
    //获取链表当前选中条目
    @Override
    public Object getItem(int i) {
        return songList.get(i);
    }
    //获取链表当前选中条目的ID
    @Override
    public long getItemId(int i) {
        return i;
    }
    //获取视图
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //填充视图
        view = LayoutInflater.from(context).inflate(R.layout.song_list,viewGroup,false);
        //获取视图中的控件
        ImageView imageView_header = (ImageView)view.findViewById(R.id.iv_header);
        TextView textView_songName = (TextView)view.findViewById(R.id.tv_songName);
        TextView textView_singer = (TextView)view.findViewById(R.id.tv_singer);
        //创建临时音乐对象保存当前选中的条目
        Song song = songList.get(i);
        //设置控件内容
        imageView_header.setImageResource(song.getHeaderID());
        textView_songName.setText(song.getSongName());
        textView_singer.setText(song.getSinger());
        //返回视图
        return view;
    }
}
