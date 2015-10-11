package com.example.b102_12.ar_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import CalculUTMK.*;
import Dijkstra.Dijkstra;
import Point.*;
import nodedata.NodeData;
import waypoint.Cafe;
import waypoint.ConvenienceStore;
import waypoint.Medical;
import waypoint.Restaurant;


public class CameraActivity extends Activity implements SensorEventListener {

    FrameLayout fl;
    Preview mPreview;
    // device sensor manager
    private SensorManager mSensorManager;
    //화면 사이즈
    Point size;

    PublicDataClass pdc;

    ArrayList<NodeData> dataList;
    ArrayList<Cafe> cafeList;
    ArrayList<Medical> MedicalList;
    ArrayList<Restaurant> RestaurantList;
    ArrayList<ConvenienceStore> ConvenienceList;

    CustomButtonView customButtonView;

    String placeName;
    float rDegree;
    double distanceX;
    double distanceY;
    float distance;
    float placeX;
    float placeY;

    int closeNode;
    int destiNode;

    /* Location GPS */

    double currentX;
    double currentY;

    private String locationProvider = null;

    private LocationManager locationManager;


    CalculUTMK calutmk;
    //Sensor 시작
    boolean trigger = false;

    Dijkstra dijkstra;

    ImageView iv;//방향을 나타내주는
    arrowView arrowView;
    GPStoUTM gpStoUTM;
    UTMtoGPS utMtoGPS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        size = new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);

        dijkstra = new Dijkstra();
        gpStoUTM = new GPStoUTM();
        utMtoGPS = new UTMtoGPS();


        Intent intent = getIntent();
        final int number = intent.getIntExtra("number", 0);
        int position = intent.getIntExtra("position", 0);
        Log.i("camera", "number position >> " + number + "/" + position);

        pdc = new PublicDataClass();
        dataList = pdc.getDataList();
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

        fl = new FrameLayout(this);
        mPreview = new Preview(this);
        fl.addView(mPreview);


        iv = new ImageView(this);
        arrowView = new arrowView(this);

        fl.addView(arrowView);

        customButtonView = new CustomButtonView(this, number, placeName);

        customButtonView.setVisibility(View.INVISIBLE);

        fl.addView(customButtonView);

        setContentView(fl);

        //여기서부터 GPS받기

        calutmk = new CalculUTMK();
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER) == true) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            locationProvider = LocationManager.GPS_PROVIDER;
        }
        LocationListener locationListener = new LocationListener() {


            @Override
            public void onLocationChanged(Location location) {
                Log.i("Activity", "Location Get >> ");
                currentX = location.getLongitude();//경도 X
                currentY = location.getLatitude(); //위도 Y
                DoublePoint fp = calutmk.getXY(currentX, currentY);
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
                        //Log.i("Activity", "closeNode >> " + closeNode + " // ");
                    }
                    if (destilength > temp2) {
                        destilength = temp2;
                        destiNode = i;
                    }
                }
                distanceX = placeX - fp.getX();
                distanceY = placeY - fp.getY();
                // distance는 거리
                distance = (float) Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

                rDegree = (float) (90 - Math.toDegrees(Math.atan2(distanceY, distanceX)));
                //Log.i("Activity", "dis >> " + distanceY + " // " + distanceX);
                //Log.i("Activity", "dis >> " + distance + " // " + rDegree);
                customButtonView.setY((float) ((size.y / 16 * 8) - (distance * (0.3))));
                //customButtonView.setY(1000);
                customButtonView.setDistancetext((int) distance + "M");
                customButtonView.setImage(placeName, number);
                if(distance < 200) {
                    Log.i("Activity", "cbv >> visible");
                    customButtonView.setVisibility(View.VISIBLE);
                }
                else {
                    Log.i("Activity", "cbv >> Invisible");
                    customButtonView.setVisibility(View.INVISIBLE);
                }

                trigger = true;

                dijkstra.CalculationLoot(closeNode, destiNode);
                ArrayList<Integer> Loot = dijkstra.returnLoot();

                //가장 가까운 노드 X Y
                float closeX = dataList.get(closeNode).getX();
                float closeY = dataList.get(closeNode).getY();

                int quardrant = 0;
                double outputdis = 0;

                Log.i("A","Loot.size"+Loot.size());

                //double startX = dataList.get(Loot.size()-1).getX();//시작노드
                //double startY = dataList.get(Loot.size()-1).getY();//시작노드
                double startX = closeX;//시작노드
                double startY = closeY;//시작노드

                for(int i=Loot.size()-1;i>0;i--) {
                    double tempX = dataList.get(Loot.get(i-1)).getX();
                    double tempY = dataList.get(Loot.get(i-1)).getY();

                    double degree = Math.toDegrees(Math.atan2((tempY-startY),(tempX-startX)));
                    outputdis = Math.sqrt(((tempX-startX)*(tempX-startX))+((tempY-startY)*(tempY-startY)));
                    float tempX2 = dataList.get(Loot.get(Loot.size()-2)).getX();//진행방향 구하는 점
                    float tempY2 = dataList.get(Loot.get(Loot.size()-2)).getY();//진행방향 구하는 점

                    double direction = Math.toDegrees(Math.atan2((tempY2 - startY), (tempX2 - startX)));
                    Log.i("Acti","result >> "+ "/" + degree + "/" +direction);

                    degree = ((degree + 180) + 90) % 360;


                    direction = ((direction + 180) + 90) % 360;

                    if(direction != degree) {
                        double result = ((degree + 360) - direction) % 360;
                        double result2 = result - 180;

                        result2 = Math.abs(result2);
                        result2 = 180 - result2;
                        Log.i("Acti","result >> "+result + "/" + degree + "/" +direction);
                        int outputtext = (int) outputdis;
                        if (result2 > 10) {
                            if (result > (0) && result <= (90)) {
                                quardrant = 1;
                            } else if (result > (90) && result <= (180)) {
                                quardrant = 2;
                            } else if (result > (180) && result <= (270)) {
                                quardrant = 3;
                            } else if (result > (270) && result <= (360)) {
                                quardrant = 4;
                            }
                            Log.i("Camera", "quardrant >> " + quardrant);

                            if (quardrant == 1 || quardrant == 2) {
                                //iv.setImageResource(R.drawable.right);
                                arrowView.arrowiv.setImageResource(R.drawable.left);
                                arrowView.arrowtv.setText("약 " + outputtext + "M 에서 왼쪽으로 가시오");
                                break;
                            } else if (quardrant == 3 || quardrant == 4) {
                                //iv.setImageResource(R.drawable.left);
                                arrowView.arrowiv.setImageResource(R.drawable.right);
                                arrowView.arrowtv.setText("약 " + outputtext + "M 에서 오른쪽으로 가시오");
                                break;
                            } else {
                                //iv.setImageResource(R.drawable.straight);
                                arrowView.arrowiv.setImageResource(R.drawable.straight);
                                arrowView.arrowtv.setText("약 " + outputtext + "M 으로 가시오");
                            }
                        }

                    }

                }
                int nearCount = 0;
                if(number == 2) {
                    for (int i = 0; i < ConvenienceList.size(); i++) {
                        double x = ConvenienceList.get(i).getX();
                        double y = ConvenienceList.get(i).getY();

                        double dist = Math.sqrt(((currentX-x)*(currentX-x))+((currentY-y)*(currentY-y)));
                        if(dist < 30) {
                            nearCount++;
                        }

                    }
                    if(nearCount > 0) {
                        Log.i("Camera","Near > "+nearCount);
                        Toast toast = Toast.makeText(getApplicationContext(), nearCount+"개의 편의점이 있습니다.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }else if(number == 3) {
                    for (int i = 0; i < cafeList.size(); i++) {
                        double x = cafeList.get(i).getX();
                        double y = cafeList.get(i).getY();

                        double dist = Math.sqrt(((currentX-x)*(currentX-x))+((currentY-y)*(currentY-y)));
                        if(dist < 30) {
                            nearCount++;
                        }
                    }
                    if(nearCount > 0) {
                        Log.i("Camera","Near > "+nearCount);
                        Toast toast = Toast.makeText(getApplicationContext(), nearCount+"개의 카페가 있습니다..", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }else if(number == 4) {
                    for (int i = 0; i < MedicalList.size(); i++) {
                        double x = MedicalList.get(i).getX();
                        double y = MedicalList.get(i).getY();

                        double dist = Math.sqrt(((currentX-x)*(currentX-x))+((currentY-y)*(currentY-y)));
                        if(dist < 30) {
                            nearCount++;
                        }
                    }

                }else if(number == 5) {
                    for (int i = 0; i < RestaurantList.size(); i++) {
                        double x = RestaurantList.get(i).getX();
                        double y = RestaurantList.get(i).getY();

                        double dist = Math.sqrt(((currentX-x)*(currentX-x))+((currentY-y)*(currentY-y)));
                        if(dist < 30) {
                            nearCount++;
                        }
                    }
                    if(nearCount > 0) {
                        Log.i("Camera","Near > "+nearCount);
                        Toast toast = Toast.makeText(getApplicationContext(), nearCount+"개의 음식점이 있습니다..", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }


                fl.requestLayout();
                fl.invalidate();

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
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (trigger == true) {
            float degree = Math.round(event.values[0]);
            onARImageSetting(degree);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void setImage(ImageButton imageButton, String name, int number) {
        if(number == 0) {

        }else if(number == 1) {

        }
        else if(number == 2) {
            String str = name.substring(0,1);
            if(str == "cu") {
                imageButton.setImageResource(R.drawable.culogo);
            } else if(str == "gs") {
                imageButton.setImageResource(R.drawable.gs25logo);
            } else if(str == "세") {
                imageButton.setImageResource(R.drawable.sevenlogo);
            } else {

            }
        }
        else if(number == 3) {
            String str = name.substring(0,1);
            if(name == "할") {
                imageButton.setImageResource(R.drawable.hollyslogo);
            }
        }
        imageButton.setImageResource(R.drawable.culogo);

    }

    private void onARImageSetting(float degree) {

        //90 - rDegree[i] = 북극점을 중심으로 나한테 보여주는각도
        float thisdegree;


        float t1, t2;
        t1 = rDegree - degree;
        t2 = 360 + t1;
        if (t1 < 0) {
            t1 = t1 * -1;
        }
        if (t1 > t2) {
            thisdegree = t2;
        } else
            thisdegree = -1 * t1;
        /*
        if (degree > (rDegree)) {
            thisdegree = -1 * (degree - (rDegree)); //구해진 각도
        } else {
            thisdegree = (rDegree) - degree;
        }
        */
        float temp = size.x / 2 / 90;


        customButtonView.setX((float) ((size.x / 2) + thisdegree * temp));
        //Log.i("", "Degree >> " + ((size.x / 2) + thisdegree * temp));


        fl.requestLayout();
        fl.invalidate();
    }
}
