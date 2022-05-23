package com.bignerdranch.android.criminalintent.controller;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bignerdranch.android.criminalintent.R;
import com.bignerdranch.android.criminalintent.databinding.ActivityFragmentBinding;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    private ActivityFragmentBinding mActivityFragmentBinding;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityFragmentBinding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(mActivityFragmentBinding.getRoot());

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
