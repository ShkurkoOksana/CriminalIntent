package com.bignerdranch.android.criminalintent.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.criminalintent.R;
import com.bignerdranch.android.criminalintent.databinding.FragmentCrimeListBinding;
import com.bignerdranch.android.criminalintent.model.Crime;
import com.bignerdranch.android.criminalintent.model.CrimeLab;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private FragmentCrimeListBinding mFragmentCrimeListBinding;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentCrimeListBinding = FragmentCrimeListBinding.inflate(inflater, container, false);
        View view = mFragmentCrimeListBinding.getRoot();

        mFragmentCrimeListBinding.crimeRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mFragmentCrimeListBinding.crimeRecycleView.setAdapter(mAdapter);
    }
}
