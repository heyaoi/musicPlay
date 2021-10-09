package com.example.musicplay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplay.R;
import com.example.musicplay.javabean.Music;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private ArrayList<Music> localDataSet;
    public static TextView tvSong;
    public static TextView tvSinger;

    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void OnItemClickListener(View view, int position);
    }

    public SongAdapter(ArrayList<Music> dataSet){
        localDataSet = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvSong = itemView.findViewById(R.id.tv_song);
            tvSinger = itemView.findViewById(R.id.tv_singer);
        }

        public TextView getTvSong(){
            return tvSong;
        }
        public TextView getTvSinger(){
            return tvSinger;
        }

    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SongAdapter.ViewHolder holder, int position) {

         tvSong.setText(localDataSet.get(position).getName());
         tvSinger.setText(localDataSet.get(position).getSinger());

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onItemClickListener.OnItemClickListener(v,position);
             }
         });

    }



    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
