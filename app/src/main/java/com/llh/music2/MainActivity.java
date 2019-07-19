package com.llh.music2;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.llh.music2.gridview.MyGridViewAdapter;

public class MainActivity extends AppCompatActivity {
    //声明控件
    private GridView mGv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //赋值
        mGv = (GridView) findViewById(R.id.gv);
        //设置适配器
        mGv.setAdapter(new MyGridViewAdapter(this));
        //点击事件
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //获取当前点击的列表项
              ///  MyGridViewAdapter.ViewHolder viewHolder = (MyGridViewAdapter.ViewHolder)adapterView.getAdapter().getItem(i);
                //创建新的碎片
                Fragment fragment = null;
                switch (i){
                    case 0:
                       // viewHolder.imageView.setBackgroundResource(R.drawable.my_music_self_clicked);
                      //  viewHolder.textView.setTextColor(R.color.colorAccent);
//                        //进行赋值
//                        viewHolder.textView.setText("音乐馆");
//                        viewHolder.imageView.setImageResource(R.drawable.my_music_libry_normal);
                        //碎片实例化
                        fragment = new F1();
                        break;
                    case 1:
                        fragment = new F2();
                        break;
                    case 2:
                        fragment = new F3();
                        break;
                    case 3:
                        fragment = new F4();
                        break;
                }
                //创建
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                //替换
                fragmentTransaction.replace(R.id.fragment_main,fragment);
                //提交
                fragmentTransaction.commit();
            }
        });
        //长按事件
        mGv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //创建新的碎片
                Fragment fragment = null;
                switch (i){
                    case 0:
                        //碎片实例化
                        fragment = new F1();
                        break;
                    case 1:
                        fragment = new F2();
                        break;
                    case 2:
                        fragment = new F3();
                        break;
                    case 3:
                        fragment = new F4();
                        break;
                }
                //创建
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                //替换
                fragmentTransaction.replace(R.id.fragment_main,fragment);
                //提交
                fragmentTransaction.commit();
                return true;//这个事件，这个操作，已经处理完了，其他人不要再处理了
                //return false; //当执行了长按事件后，并不会把事件就此停止，点击事件同样也会被触发
            }
        });

        //默认开启界面
        //创建新的碎片
        Fragment fragment = new F1();
        //创建
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //添加
        fragmentTransaction.add(R.id.fragment_main, fragment);
        //提交
        fragmentTransaction.commit();
    }
}