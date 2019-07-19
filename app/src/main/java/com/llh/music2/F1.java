package com.llh.music2;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.llh.music2.other.ControlPlay;
import com.llh.music2.other.Music2Activity;
import com.llh.music2.other.MusicAdapter;
import com.llh.music2.other.MusicData;
import com.llh.music2.other.Song;

import java.util.List;

/**
 * Created by 李利红 on 2019/4/25.
 */

public class F1 extends Fragment {
    //创建文本
    private Context context;
    //创建链表
    private List<Song> songsList;
    //创建播放控制对象
    private ControlPlay controlMusic;
    private static TextView textView_playing;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_f1,container,false);
        //检验权限
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            //申请权限
            ActivityCompat.requestPermissions((Activity) context,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
        }
        else {
            //给链表赋值
            songsList = MusicData.getInstance(context).getSongList();
        }

        //给播放控制对象赋值
        controlMusic = ControlPlay.getInstance();
        //文本控件赋值
        textView_playing = (TextView)view.findViewById(R.id.playing_songName);
        //创建临时音乐对象
        Song song = MusicData.getInstance().getSongList().get(MusicData.getInstance().getCurIndex());
        //设置文本内容
        textView_playing.setText(song.getSongName());
        //创建适配器
        MusicAdapter songsAdapter = new MusicAdapter(context,songsList);
        //绑定视图
        ListView listView = (ListView)view.findViewById(R.id.list_item);
        //设置列表项单击事件监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //创建临时音乐对象
                Song songs = (Song) adapterView.getAdapter().getItem(i);
                //设置文本内容
                textView_playing.setText(songs.getSongName());
                //保存当前音乐
                MusicData.getInstance().setCurSong(songs);
                //保存ID
                MusicData.getInstance().setCurIndex(i);
                //播放歌曲
                controlMusic.playMusic(songs.getPath());
                //页面跳转
                Intent intent = new Intent(context, Music2Activity.class);
                startActivity(intent);
            }
        });
        //设置适配器
        listView.setAdapter(songsAdapter);
        return view;
    }
    //重写函数

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //保存文本
        this.context = context;
    }
    public static TextView getTextView_playing() {
        return textView_playing;
    }
}
