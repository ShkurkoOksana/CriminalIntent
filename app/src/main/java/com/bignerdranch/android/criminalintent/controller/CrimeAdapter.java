package com.bignerdranch.android.criminalintent.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding;
import com.bignerdranch.android.criminalintent.model.Crime;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {
    private List<Crime> mCrimes;

    public CrimeAdapter(List<Crime> crimes) {
        mCrimes = crimes;
    }

    @NonNull
    @Override
    public CrimeAdapter.CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemCrimeBinding listItemCrimeBinding = ListItemCrimeBinding.inflate(layoutInflater, parent, false);

        return new CrimeHolder(listItemCrimeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeAdapter.CrimeHolder holder, int position) {
        Crime crime = mCrimes.get(position);
        holder.bindView(crime);
    }

    @Override
    public int getItemCount() {
        return mCrimes.size();
    }

    class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ListItemCrimeBinding mListItemCrimeBinding;
        private Crime mCrime;

        public CrimeHolder(ListItemCrimeBinding listItemCrimeBinding) {
            super(listItemCrimeBinding.getRoot());
            this.mListItemCrimeBinding = listItemCrimeBinding;

            itemView.setOnClickListener(this);
        }

        public void bindView(Crime crime) {
            mCrime = crime;

            mListItemCrimeBinding.crimeTitle.setText(mCrime.getTitle());
            mListItemCrimeBinding.crimeDate.setText(mCrime.getDate().toString());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(itemView.getContext(), mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}
