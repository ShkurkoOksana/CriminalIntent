package com.bignerdranch.android.criminalintent.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Crime implements Serializable {
    public static final int SIMPLE_CRIME_TYPE = 0;
    public static final int CRIME_REQUIRED_POLICE_TYPE = 1;

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private int mType;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
