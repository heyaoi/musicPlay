package com.example.musicplay.utils;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import com.example.musicplay.javabean.Music;

import java.util.ArrayList;

public class MusicUtils {
    public static ArrayList<Music> dataSet;
    public static Music music;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static ArrayList<Music> getMusic(Context context){

        dataSet = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                ,null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        if (cursor!=null){
            while (cursor.moveToNext()){
                music = new Music();

                music.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                music.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)));
                music.setSinger(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                music.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                music.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                music.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                dataSet.add(music);
            }
        }
        return dataSet;
    }

}
