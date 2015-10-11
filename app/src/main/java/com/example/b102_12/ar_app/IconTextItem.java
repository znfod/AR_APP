package com.example.b102_12.ar_app;

/**
 * Created by Znfod on 2015-05-11.
 */
public class IconTextItem {
    private String[] mData;

    public IconTextItem(String[] obj) {
        mData = obj;
    }
    public IconTextItem(String obj1, String obj2) {
        mData = new String[2];
        mData[0] = obj1;
        mData[1] = obj2;
    }

    public String[] getData() {
        return mData;
    }

    public String getData(int index) {
        if(mData == null || index >= mData.length)
        {
            return null;
        }
        return mData[index];
    }

    public void setData(String[] obj) {
        mData = obj;
    }

}
