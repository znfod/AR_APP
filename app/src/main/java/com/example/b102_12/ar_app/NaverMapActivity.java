package com.example.b102_12.ar_app;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.*;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPathData;
import com.nhn.android.maps.overlay.NMapPathLineStyle;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPathDataOverlay;

import java.util.ArrayList;

import CalculUTMK.GPStoUTM;
import CalculUTMK.UTMtoGPS;
import Dijkstra.Dijkstra;
import Point.DoublePoint;
import nodedata.NodeData;
import waypoint.Cafe;
import waypoint.ConvenienceStore;
import waypoint.Medical;
import waypoint.Restaurant;


public class NaverMapActivity extends NMapActivity implements OnMapStateChangeListener, OnMapViewTouchEventListener{

    public static final String API_KEY="f9247aceed048cd9c5a389d05162d1b8";
    NMapView mMapView = null;
    NMapController mMapController = null;
    NMapOverlayManager mOverlayManager;
    NMapViewerResourceProvider mMapViewerResourceProvider;

    LinearLayout MapContainer;

    PublicDataClass pdc;

    ArrayList<NodeData> dataList;
    ArrayList<Cafe> cafeList;
    ArrayList<Medical> MedicalList;
    ArrayList<Restaurant> RestaurantList;
    ArrayList<ConvenienceStore> ConvenienceList;

    /* Location GPS */

    double currentX;
    double currentY;

    private String locationProvider = null;

    private LocationManager locationManager;

    String placeName;
    float rDegree;
    float distanceX;

    float distanceY;
    float distance;
    float placeX;
    float placeY;

    int closeNode;
    int destiNode;

    Dijkstra dijkstra;

    NGeoPoint nGeoPoint = new NGeoPoint();
    GPStoUTM gpStoUTM;
    UTMtoGPS utMtoGPS;

    NMapPathData pathData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapContainer = (LinearLayout)findViewById(R.id.navermap);


         gpStoUTM = new GPStoUTM();
         utMtoGPS = new UTMtoGPS();

        dijkstra = new Dijkstra();

        Intent intent = getIntent();
        int number = intent.getIntExtra("number", 0);
        int position = intent.getIntExtra("position", 0);

        pdc = new PublicDataClass();
        dataList = pdc.getDataList();
        Log.i("navermap","datalist Return" + dataList.size());


        if (number == 1) {

        } else if (number == 2) {
            //편의점
            ConvenienceList = new ArrayList<ConvenienceStore>();
            ConvenienceList = pdc.getConvenienceList();

            placeX = ConvenienceList.get(position).getX();
            placeY = ConvenienceList.get(position).getY();
            placeName = ConvenienceList.get(position).getName();

        } else if (number == 3) {
            //카페
            cafeList = new ArrayList<Cafe>();
            cafeList = pdc.getCafeList();
            placeX = cafeList.get(position).getX();
            placeY = cafeList.get(position).getY();
            placeName = cafeList.get(position).getName();
        } else if (number == 4) {
            //의료
            MedicalList = new ArrayList<Medical>();
            MedicalList = pdc.getMedicalList();
            placeX = MedicalList.get(position).getX();
            placeY = MedicalList.get(position).getY();
            placeName = MedicalList.get(position).getName();
        } else if (number == 5) {
            //음식
            RestaurantList = new ArrayList<Restaurant>();
            RestaurantList = pdc.getRestaurantList();
            placeX = RestaurantList.get(position).getX();
            placeY = RestaurantList.get(position).getY();
            placeName = RestaurantList.get(position).getName();
        } else {
            // null
        }

        mMapView = new NMapView(this);
        mMapView.setApiKey(API_KEY);

        setContentView(mMapView);


        mOverlayManager  = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);




        mMapView.setClickable(true);
        mMapView.setOnMapStateChangeListener(this);
        mMapView.setOnMapViewTouchEventListener(this);
        mMapView.setBuiltInZoomControls(true, null);

        mMapController = mMapView.getMapController();


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER) == true) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            locationProvider = LocationManager.GPS_PROVIDER;
        }
        LocationListener locationListener = new LocationListener() {


            @Override
            public void onLocationChanged(Location location) {
                currentX = location.getLongitude();//경도 X
                currentY = location.getLatitude(); //위도 Y
                Log.i("naver", "currentXY >> " + currentX + " // " + currentY);
                nGeoPoint = new NGeoPoint(currentX, currentY);
                DoublePoint dp = new DoublePoint(currentX, currentY);
                //실험
                //942770.5638534039/1921618.7446061196
                //37.583174, 127.010626

                DoublePoint currentXY = gpStoUTM.getXY(dp);
                currentX = currentXY.getX();
                currentY = currentXY.getY();

                double closelength = 99999999.9999;
                double destilength = 99999999.9999;
                for (int i = 0; i < dataList.size(); i++) {

                    double tempx = currentX - dataList.get(i).getX();
                    double tempy = currentY - dataList.get(i).getY();
                    double temp = Math.sqrt((tempx * tempx) + (tempy * tempy));
                    double tempx2 = placeX - dataList.get(i).getX();
                    double tempy2 = placeY - dataList.get(i).getY();
                    double temp2 = Math.sqrt((tempx2 * tempx2) + (tempy2 * tempy2));

                    if (closelength > temp) {
                        closelength = temp;
                        closeNode = i;
                    }
                    if(destilength > temp2) {
                        destilength = temp2;
                        destiNode = i;
                    }
                }
                Log.i("naver", "closeNode >> " + closeNode + " // ");
                //closeNode = 845;
                dijkstra.CalculationLoot(closeNode, destiNode);
                //Log.i("","2134" + dataList.get(2134).getX() + "/"+dataList.get(2134).getY());
                ArrayList<Integer> Loot = dijkstra.returnLoot();
                //Log.i("NaverMap", "LOOT " + Loot.size() + " >> " + Loot.get(0) + " // " + Loot.get(Loot.size() - 1) + "/");
                pathData = null;
                pathData = new NMapPathData(Loot.size());
                pathData.initPathData();

                DoublePoint temp = utMtoGPS.getXY(new DoublePoint(placeX, placeY));
                pathData.addPathPoint(temp.getX(), temp.getY(), NMapPathLineStyle.TYPE_DASH);

                DoublePoint dd = new DoublePoint((double)dataList.get(Loot.get(0)).getX(), (double)dataList.get(Loot.get(0)).getY());
                temp = utMtoGPS.getXY(dd);
                Log.i("","" + dd.getX()+","+dd.getY());
                Log.i("","" + temp.getX()+","+temp.getY());
                pathData.addPathPoint(temp.getX(), temp.getY(), NMapPathLineStyle.TYPE_SOLID);
                for(int i=1;i<Loot.size()-1;i++){
                    dd = new DoublePoint((double)dataList.get(Loot.get(i)).getX(), (double)dataList.get(Loot.get(i)).getY());
                    temp = utMtoGPS.getXY(dd);

                    pathData.addPathPoint(temp.getX(), temp.getY(), 0);
                }
                dd = new DoublePoint((double)dataList.get(Loot.get(Loot.size()-1)).getX(), (double)dataList.get(Loot.get(Loot.size()-1)).getY());
                temp = utMtoGPS.getXY(dd);

                pathData.addPathPoint(temp.getX(), temp.getY(), 0);

                pathData.endPathData();


                NMapPathDataOverlay pathDataOverlay = mOverlayManager.createPathDataOverlay(pathData);
                pathDataOverlay.showAllPathData(15);

                NGeoPoint ntemp = new NGeoPoint(temp.getX(), temp.getY());
                //Log.i("","" + dd.getX()+","+dd.getY());
                //Log.i("","" + temp.getX()+","+temp.getY());
                //Log.i("","" + ntemp);
                mMapController.setMapCenter(ntemp);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
        //GPS받기 끝




    }

    @Override
    public void onMapInitHandler(NMapView nMapView, NMapError nMapError) {

    }

    @Override
    public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

    }

    @Override
    public void onMapCenterChangeFine(NMapView nMapView) {

    }

    @Override
    public void onZoomLevelChange(NMapView nMapView, int i) {

    }

    @Override
    public void onAnimationStateChange(NMapView nMapView, int i, int i2) {

    }

    @Override
    public void onLongPress(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onLongPressCanceled(NMapView nMapView) {

    }

    @Override
    public void onTouchDown(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {

    }

    @Override
    public void onScroll(NMapView nMapView, MotionEvent motionEvent, MotionEvent motionEvent2) {

    }

    @Override
    public void onSingleTapUp(NMapView nMapView, MotionEvent motionEvent) {

    }
}
