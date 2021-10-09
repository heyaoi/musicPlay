package com.example.musicplay.fragment;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicplay.DataManager;
import com.example.musicplay.MusicService;
import com.example.musicplay.R;
import com.example.musicplay.activity.MainActivity;
import com.example.musicplay.adapter.SongAdapter;
import com.example.musicplay.javabean.Music;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;


public class FirstFragment extends Fragment {
    private SongAdapter songAdapter;
    private ArrayList<Music> musicList;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private MusicService.MyBinder myBinder;
    private MusicService musicservice;

    public FirstFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        DataManager dataManager = new DataManager();
        musicList = dataManager.getMusicList(getContext());
        songAdapter = new SongAdapter(musicList);

        setEventListener();

    }

    private void setEventListener() {
        songAdapter.setOnItemClickListener(new SongAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void OnItemClickListener(View view, int position) {

                Intent intent = new Intent(getContext(), MusicService.class);
                intent.putExtra("position",position);
                getActivity().startForegroundService(intent);
                Log.d("TAG", "OnItemClickListener: " + "开始绑定");
                getActivity().bindService(intent,connection,Context.BIND_AUTO_CREATE);


                String CHANNEL_ID="aMusicPlayer";



                /*Music music = musicList.get(position);
                Log.d("TAG", "OnItemClickListener: 点击播放");
                try {
                    mediaPlayer.setDataSource(music.getUrl());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/


            }
        });
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MusicService.MyBinder) service;
            musicservice = myBinder.getService();
            myBinder.play();
//            myBinder.showNotification();
            Log.d("TAG", "onServiceConnected: " + "服务连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        RecyclerView viewContainer = view.findViewById(R.id.song_container);
        viewContainer.setLayoutManager(new LinearLayoutManager(view.getContext()));
        viewContainer.setAdapter(songAdapter);

        super.onViewCreated(view, savedInstanceState);
    }

}