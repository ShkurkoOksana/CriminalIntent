package com.bignerdranch.android.criminalintent.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bignerdranch.android.criminalintent.databinding.ActivityCrimePagerBinding;
import com.bignerdranch.android.criminalintent.model.Crime;
import com.bignerdranch.android.criminalintent.model.CrimeLab;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {
    private ActivityCrimePagerBinding mActivityCrimePagerBinding;
    private ViewPager2 mViewPager;
    private Button mGoToFirstButton;
    private Button mGoToLastButton;
    private List<Crime> mCrimes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityCrimePagerBinding = ActivityCrimePagerBinding.inflate(getLayoutInflater());
        setContentView(mActivityCrimePagerBinding.getRoot());

        UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeAdapter.EXTRA_CRIME_ID);

        mViewPager = mActivityCrimePagerBinding.crimeViewPager;

        mGoToFirstButton = mActivityCrimePagerBinding.goToFirstCrimeButton;
        mGoToLastButton = mActivityCrimePagerBinding.goToLastCrimeButton;

        mGoToFirstButton.setOnClickListener(v -> {
            mViewPager.setCurrentItem(0);
        });

        mGoToLastButton.setOnClickListener(v -> {
            mViewPager.setCurrentItem(mCrimes.size() - 1);
        });

        mCrimes = CrimeLab.get(this).getCrimes();

        mViewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Crime crime = mCrimes.get(position);

                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getItemCount() {
                return mCrimes.size();
            }
        });

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if (position == 0) {
                    mGoToFirstButton.setVisibility(View.INVISIBLE);
                    mGoToLastButton.setVisibility(View.VISIBLE);
                } else if (position == mCrimes.size() - 1) {
                    mGoToFirstButton.setVisibility(View.VISIBLE);
                    mGoToLastButton.setVisibility(View.INVISIBLE);
                } else {
                    mGoToFirstButton.setVisibility(View.VISIBLE);
                    mGoToLastButton.setVisibility(View.VISIBLE);
                }
            }
        });

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
