package com.llh.music2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llh.music2.other.MusicData;
import com.llh.music2.other.Song;

/**
 * Created by 李利红 on 2019/4/25.
 */

public class F2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_f2,container,false);
        return view;
    }
}
