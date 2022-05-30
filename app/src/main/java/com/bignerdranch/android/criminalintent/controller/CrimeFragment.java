package com.bignerdranch.android.criminalintent.controller;

import android.app.Activity;
import android.content.Intent;
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
import androidx.fragment.app.FragmentManager;

import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeBinding;
import com.bignerdranch.android.criminalintent.model.Crime;
import com.bignerdranch.android.criminalintent.model.CrimeLab;

import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment {
    private static final String CRIME = "crime";
    public static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "dialog_date";
    public static final int REQUEST_DATE = 0;

    private FragmentCrimeBinding mFragmentCrimeBinding;

    private Crime mCrime;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCrime();
    }

    private void initCrime() {
        UUID crimeId = (UUID) getArguments().getSerializable(CrimeFragment.ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentCrimeBinding = FragmentCrimeBinding.inflate(inflater, container, false);
        View view = mFragmentCrimeBinding.getRoot();

        mFragmentCrimeBinding.crimeTitle.setText(mCrime.getTitle());
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

        updateDate();
        mFragmentCrimeBinding.crimeDate.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();
            DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
            dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
            dialog.show(fragmentManager, DIALOG_DATE);
        });

        mFragmentCrimeBinding.crimeSolved.setChecked(mCrime.isSolved());
        mFragmentCrimeBinding.crimeSolved.setOnCheckedChangeListener((buttonView, isChecked) -> mCrime.setSolved(isChecked));

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
    }

    private void updateDate() {
        mFragmentCrimeBinding.crimeDate.setText(mCrime.getDate().toString());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CRIME, mCrime);
    }
}
