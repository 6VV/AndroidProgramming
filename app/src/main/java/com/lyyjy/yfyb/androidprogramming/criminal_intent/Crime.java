package com.lyyjy.yfyb.androidprogramming.criminal_intent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2016/8/18.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;

    public Date getDate() {
        return mDate;
    }

    public String getDateText(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        return dateFormat.format(mDate);
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

    private boolean mSolved;

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Crime(){
        mId=UUID.randomUUID();
        mDate = new Date();
    }
}
