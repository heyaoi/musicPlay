package com.example.musicplay;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompatExtras;
import androidx.core.app.NotificationManagerCompat;

import com.example.musicplay.javabean.Music;
import com.example.musicplay.utils.MusicUtils;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {

    public final static String CHANNEL_ID = "aMusicPlayer_2";

    private ArrayList<Music> musiclist;
    private MyBinder myBinder;
    public MediaPlayer mediaPlayer;
    private int position;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private Notification notification;

    public MusicService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        Bundle bundle = intent.getExtras();
        position = bundle.getInt("position");
        myBinder = new MyBinder();
        return myBinder;
    }

    public class MyBinder extends Binder {

        Music music = musiclist.get(position);

        public void play() {

            if (mediaPlayer != null) {
                mediaPlayer.reset();
                mediaPlayer.release();
            }
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(music.getUrl());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public MusicService getService() {
            return MusicService.this;
        }

        public void showNotification() {

        }


    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "onCreate: ");
        mediaPlayer = new MediaPlayer();
        musiclist = new ArrayList<>();
        // todo ： 服务的上下文
        musiclist = MusicUtils.getMusic(getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "onStartCommand: ");

/*        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID="MusicPlayer";
        String CHANNEL_NAME = "音乐";
        NotificationChannel channel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);
        notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("音乐播放")
                .build();
        notificationManager.notify(1,notification);*/


//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"CHANNEL_NAME_2", NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);

        builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("播放音乐")
                .setContentTitle("Title")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        notificationManager.notify(121, builder.build());

        startForeground(121, builder.build());


        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: ");

    }


}