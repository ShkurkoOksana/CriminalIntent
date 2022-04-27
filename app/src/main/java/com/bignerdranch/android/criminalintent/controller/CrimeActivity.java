package com.bignerdranch.android.criminalintent.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.bignerdranch.android.criminalintent.R;
import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeBinding;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}