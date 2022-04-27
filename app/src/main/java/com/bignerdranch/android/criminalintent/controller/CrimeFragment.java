package com.bignerdranch.android.criminalintent.controller;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeBinding;
import com.bignerdranch.android.criminalintent.model.Crime;

public class CrimeFragment extends Fragment {
    private static final String CRIME = "crime";

    private FragmentCrimeBinding mFragmentCrimeBinding;

    private Crime mCrime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCrime();
    }

    private void initCrime() {
        mCrime = new Crime();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentCrimeBinding = FragmentCrimeBinding.inflate(inflater, container, false);
        View view = mFragmentCrimeBinding.getRoot();

        mFragmentCrimeBinding.crimeTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This space intentionally left blank
            }
        });

        mFragmentCrimeBinding.crimeDate.setText(mCrime.getDate().toString());
        mFragmentCrimeBinding.crimeDate.setEnabled(false);

        mFragmentCrimeBinding.crimeSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CRIME, mCrime);
    }
}
