package com.example.b102_12.ar_app;

import android.util.Log;

import java.util.ArrayList;

import Point.DoublePoint;
import Point.FloatPoint;
import nodedata.NodeData;
import waypoint.*;

/**
 * Created by B102-12 on 2015-05-08.
 */
public class PublicDataClass {
    static ArrayList<NodeData> dataList = new ArrayList<NodeData>();
    static ArrayList<Cafe> CafeList;
    static ArrayList<Medical> MedicalList;
    static ArrayList<Restaurant> RestaurantList;
    static ArrayList<ConvenienceStore> ConvenienceList;
    static DoublePoint currentXY;


    public PublicDataClass() {

    }
    public void setDataList(ArrayList<NodeData> dataList) {
        this.dataList = dataList;
    }
    public ArrayList<NodeData> getDataList() {
        Log.i("PDC","datalist Return");
        return dataList; }

    public ArrayList<ConvenienceStore> getConvenienceList() {
        return ConvenienceList;
    }

    public void setConvenienceList(ArrayList<ConvenienceStore> convenienceList) {
        ConvenienceList = convenienceList;
        Log.i("PublicData","SetConvenience Success"+ConvenienceList.size());
    }

    public ArrayList<Restaurant> getRestaurantList() {
        return RestaurantList;
    }

    public void setRestaurantList(ArrayList<Restaurant> restaurantList) {
        RestaurantList = restaurantList;
        Log.i("PublicData","SetRestaurant Success"+RestaurantList.size());
    }

    public ArrayList<Medical> getMedicalList() {
        return MedicalList;
    }

    public void setMedicalList(ArrayList<Medical> medicalList) {
        MedicalList = medicalList;
        Log.i("PublicData","SetMedical Success"+MedicalList.size());
    }

    public ArrayList<Cafe> getCafeList() {
        return CafeList;
    }

    public void setCafeList(ArrayList<Cafe> cafeList) {
        CafeList = cafeList;
        Log.i("PublicData","SetCafe Success"+CafeList.size());
    }

    public DoublePoint getCurrentXY() {
        return currentXY;
    }

    public void setCurrentXY(DoublePoint currentXY) {
        PublicDataClass.currentXY = currentXY;
    }
}
