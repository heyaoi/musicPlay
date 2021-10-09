package com.example.musicplay.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musicplay.fragment.FirstFragment;
import com.example.musicplay.fragment.SecondFragment;
import com.example.musicplay.fragment.ThirdFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CollectionAdapter extends FragmentStateAdapter {

    ArrayList<Fragment> fragments;

    public CollectionAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle, ArrayList<Fragment> fragments) {

        super(fragmentManager, lifecycle);
        this.fragments = fragments;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Fragment createFragment(int position) {
          return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
