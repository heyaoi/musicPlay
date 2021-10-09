package com.example.musicplay;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.musicplay.javabean.Music;
import com.example.musicplay.utils.MusicUtils;

import java.util.ArrayList;

public class DataManager {
    public ArrayList<Music> musicList;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public ArrayList<Music> getMusicList(Context context){
        if (musicList == null){
            musicList = MusicUtils.getMusic(context);
        }

        return musicList;
    }
}
