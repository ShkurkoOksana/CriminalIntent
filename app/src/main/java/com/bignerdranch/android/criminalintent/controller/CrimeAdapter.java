package com.bignerdranch.android.criminalintent.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding;
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeRequiredPoliceBinding;
import com.bignerdranch.android.criminalintent.model.Crime;

import java.text.SimpleDateFormat;
import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_1 = 0;
    private final int TYPE_2 = 1; // Require police

    public static final String EXTRA_CRIME_ID = "crime_id";

    private List<Crime> mCrimes;

    public CrimeAdapter(List<Crime> crimes) {
        mCrimes = crimes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_1:
                ListItemCrimeBinding listItemCrimeBinding = ListItemCrimeBinding.inflate(layoutInflater, parent, false);
                CrimeHolder crimeHolder = new CrimeHolder(listItemCrimeBinding);
                return crimeHolder;
            case TYPE_2:
                ListItemCrimeRequiredPoliceBinding listItemCrimeRequiredPoliceBinding = ListItemCrimeRequiredPoliceBinding.inflate(layoutInflater, parent, false);
                CrimeHolderRequiredPolice crimeHolderRequiredPolice = new CrimeHolderRequiredPolice(listItemCrimeRequiredPoliceBinding);
                return crimeHolderRequiredPolice;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Crime crime = mCrimes.get(position);
        if (crime != null) {
            switch (crime.getType()) {
                case TYPE_1:
                    ((CrimeHolder) holder).bindViewCrimeHolder(crime);
                    break;
                case TYPE_2:
                    ((CrimeHolderRequiredPolice) holder).bindViewCrimeHolderRequiredPolice(crime);
                    break;
            }
        }

        notifyItemChanged(position);
    }


    @Override
    public int getItemCount() {
        if (mCrimes == null) {
            return 0;
        }

        return mCrimes.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mCrimes != null) {
            Crime crime = mCrimes.get(position);
            if (crime != null) {
                return crime.getType();
            }
        }

        return 0;
    }



    public static class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context mContext;

        private ListItemCrimeBinding mListItemCrimeBinding;
        private Crime mCrime;

        public CrimeHolder(ListItemCrimeBinding listItemCrimeBinding) {
            super(listItemCrimeBinding.getRoot());
            mContext = itemView.getContext();
            this.mListItemCrimeBinding = listItemCrimeBinding;

            itemView.setOnClickListener(this);
        }

        @SuppressLint("SimpleDateFormat")
        public void bindViewCrimeHolder(Crime crime) {
            mCrime = crime;
            mListItemCrimeBinding.crimeTitle.setText(mCrime.getTitle());
            mListItemCrimeBinding.crimeDate.setText(new SimpleDateFormat("EEEE, MMM dd, yyyy.").format(mCrime.getDate()));
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            intent = new Intent(mContext, CrimeActivity.class);
            intent.putExtra(EXTRA_CRIME_ID, mCrime.getId());
            mContext.startActivity(intent);
        }
    }

    public static class CrimeHolderRequiredPolice extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context mContext;

        private ListItemCrimeRequiredPoliceBinding mListItemCrimeRequiredPoliceBinding;
        private Crime mCrime;

        public CrimeHolderRequiredPolice(ListItemCrimeRequiredPoliceBinding listItemCrimeBinding) {
            super(listItemCrimeBinding.getRoot());
            mContext = itemView.getContext();
            this.mListItemCrimeRequiredPoliceBinding = listItemCrimeBinding;

            itemView.setOnClickListener(this);
        }

        @SuppressLint("SimpleDateFormat")
        public void bindViewCrimeHolderRequiredPolice(Crime crime) {
            mCrime = crime;

            mListItemCrimeRequiredPoliceBinding.crimeTitleRequiredPolice.setText(mCrime.getTitle());
            mListItemCrimeRequiredPoliceBinding.crimeDateRequiredPolice.setText(new SimpleDateFormat("EEEE, MMM dd, yyyy.").format(mCrime.getDate()));
            mListItemCrimeRequiredPoliceBinding.handcuffs.setOnClickListener(this::onClickButtonContactPolice);
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            intent = new Intent(mContext, CrimeActivity.class);
            intent.putExtra(EXTRA_CRIME_ID, mCrime.getId());
            mContext.startActivity(intent);
        }

        private void onClickButtonContactPolice(View v) {
            Toast.makeText(itemView.getContext(), "Police number 911!", Toast.LENGTH_SHORT).show();
        }
    }
}
