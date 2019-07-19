package com.llh.music2.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.llh.music2.R;

/**
 * Created by 李利红 on 2019/5/21.
 */

public class MyGridViewAdapter extends BaseAdapter {

    //4.创建上下文
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    //3.构造方法
    public MyGridViewAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    //1
    public static  class ViewHolder{
        //把控件声明一下
        public ImageView imageView;
        public TextView textView;
    }
    //获取数量
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    //2.每个格子长什么样子
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view==null){
            view = mLayoutInflater.inflate(R.layout.layout_grid_item,null);
            //viewHolder实例化一下
            viewHolder = new ViewHolder();
            //给viewHolder里面的控件findViewByID一下
            viewHolder.imageView = (ImageView) view.findViewById(R.id.iv_grid);
            viewHolder.textView = (TextView) view.findViewById(R.id.tv_title);
            //设置标签
            view.setTag(viewHolder);
        }else {
            //需要强制转换
            viewHolder = (ViewHolder) view.getTag();
        }
//        //进行赋值
        switch (i){
            case 0:
                //进行赋值
                viewHolder.textView.setText("我的音乐");
                viewHolder.imageView.setImageResource(R.drawable.my_music_self_normal);
                break;
            case 1:
                //进行赋值
                viewHolder.textView.setText("音乐馆");
                viewHolder.imageView.setImageResource(R.drawable.my_music_libry_normal);
                break;
            case 2:
                //进行赋值
                viewHolder.textView.setText("乐库");
                viewHolder.imageView.setImageResource(R.drawable.my_music_box_normal);
                break;
            case 3:
                //进行赋值
                viewHolder.textView.setText("更多");
                viewHolder.imageView.setImageResource(R.drawable.my_music_setting_normal);
                break;
        }
        return view;
    }

}
//getTag()返回的是Object