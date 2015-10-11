package CalculUTMK;

import Point.DoublePoint;

/**
 * Created by Znfod on 2015-03-31.
 */
public class CalculUTMK {
    public CalculUTMK() {
        /*


        // TODO Auto-generated method stub
        // 북단 : 37.701160 (37.41.04.2) - 127.018812 (127.01.07.7)
        // 동단 : 37.551015 (37.33.03.6) - 127.183957 (127.11.02.2)
        // 남단 : 37.425771 (37.25.32.8) - 127.043967(127.02.38.3)
        // 서단 : 37.555847 (37.33.21.1) - 126.763815(126.45.49.7)
        double coorX1_h;// 경도 시분초
        double coorY1_h;// 위도 시분초
        double coorX1_m;// 경도 시분초
        double coorY1_m;// 위도 시분초
        double coorX1_s;// 경도 시분초
        double coorY1_s;// 위도 시분초

        double coorX2;// 경도 도단위
        double coorY2;// 위도 도단위

        coorX1_h = 127.0;// 경도 시분초
        coorY1_h = 37.0;// 위도 시분초
        coorX1_m = 11.0;// 경도 시분초
        coorY1_m = 33.0;// 위도 시분초
        coorX1_s = 2.2;// 경도 시분초
        coorY1_s = 3.6;// 위도 시분초

        // 시분초를 위경도로 바꾸는 법 B9+C9/60+D9/3600
        coorX2 = coorX1_h + coorX1_m / 60 + coorX1_s / 3600;
        coorY2 = coorY1_h + coorY1_m / 60 + coorY1_s / 3600;

        coorX2 = 127.2564450833;
        coorY2 = 37.2150293056;

        /**
         * 계산방법 INPUT : 위경도(도단위) OUTPUT : UTM-K XY좌표계 $F$2+$F$1*(N9-$G$5+M9*TAN(
         * H9/180*PI())*(L9^2/2+L9^4*(5-J9+9*K9+4*K9^2
         * )/24+L9^6*(61-58*J9+J9^2+600*K9-330*$C$5)))
         */
		/*
		 * X축가산값 + 축척계수 X ( (
		 *
		 * )
		 */
        /*
        double c1 = 6378137; // 장반경
        double c2 = 1 / 298.257222101; // 편평률
        double c3 = c1 * (1 - c2); // 단반경
        double c4 = (dmth(c1) - dmth(c3)) / dmth(c1); // 제1이심률
        double c5 = (dmth(c1) - dmth(c3)) / dmth(c3); // 제2이심률
        //-----------여기까지는 확인완료

        double f1 = 0.9996; // 축척계수
        double f2 = 2000000; // X축 가산값
        double f3 = 1000000; // Y축 가산값
        double f4 = 38.0; // 원점 위도
        double f5 = 127.5; // 원점 경도
        double g5;

        double h9 = coorY2;
        double i9 = coorX2;
        double j9 = dmth(Math.tan(h9 / 180 * Math.PI)); // T
        double k9 = c5 * dmth((Math.cos(h9 / 180 * Math.PI))); // C
        double l9 = ((i9 / 180 * Math.PI) - (f5 / 180 * Math.PI))
                * Math.cos(h9 / 180 * Math.PI); // A
        //-------------여기까지 확인 완료

        double m9 = c1 / Math.sqrt(1 - c4 * dmth(Math.sin(h9 / 180 * Math.PI))); // N
        double n9 = c1
                * ((1 - c4 / 4 - 3 * dmth(c4) / 64 - 5 * tmth(c4) / 256)
                * (h9 / 180 * Math.PI)
                + (-3 * c4 / 8 + -3 * dmth(c4) / 32 + (-45 * tmth(c4)) / 1024)
                * Math.sin(2 * h9 / 180 * Math.PI)
                + (15 * dmth(c4) / 256 + 45 * tmth(c4) / 1024)
                * Math.sin(4 * h9 / 180 * Math.PI) - 35 * tmth(c4)
                / 3072 * Math.sin(6 * h9 / 180 * Math.PI)); // M

        // g5
        g5 = c1
                * ((1 - c4 / 4 - 3 * dmth(c4) / 64 - 5 * tmth(c4) / 256)
                * (f4 / 180 * Math.PI)
                + (-3 * c4 / 8 + -3 * dmth(c4) / 32 + -45 * tmth(c4) / 1024)
                * Math.sin(2 * f4 / 180 * Math.PI)
                + (15 * dmth(c4) / 256 + 45 * tmth(c4) / 1024)
                * Math.sin(4 * f4 / 180 * Math.PI) - 35 * tmth(c4)
                / 3072 * Math.sin(6 * f4 / 180 * Math.PI));

        // ==== Tutorial ====

        double XNorth, YEast; // 계산된 UTM-K XY좌표계

        XNorth = f2
                + f1
                * (n9 - g5 + m9
                * Math.tan(h9 / 180 * Math.PI)
                * (dmth(l9) / 2 + qmth(l9)
                * (5 - j9 + 9 * k9 + dmth(4 * k9)) / 24 + hmth(l9)
                * (61 - 58 * j9 + dmth(j9) + 600 * k9 - 330 * c5)));
        YEast = f3
                + (m9 * (l9 + tmth(l9) * (1 - j9 + k9) / 6 + pmth(l9)
                * (5 - 18 * j9 + dmth(j9) + 72 * k9 - 58 * c5) / 120))
                * f1;

        */
    }

    public DoublePoint getXY(double coordX, double coodY) {

        double coorX2;// 경도 도단위
        double coorY2;// 위도 도단위

        coorX2 = coordX;
        coorY2 = coodY;

        /**
         * 계산방법 INPUT : 위경도(도단위) OUTPUT : UTM-K XY좌표계 $F$2+$F$1*(N9-$G$5+M9*TAN(
         * H9/180*PI())*(L9^2/2+L9^4*(5-J9+9*K9+4*K9^2
         * )/24+L9^6*(61-58*J9+J9^2+600*K9-330*$C$5)))
         */
		/*
		 * X축가산값 + 축척계수 X ( (
		 *
		 * )
		 */
        double c1 = (double)6378137; // 장반경
        double c2 = (double)(1 / 298.257222101); // 편평률
        double c3 = (double)c1 * (1 - c2); // 단반경
        double c4 = (double)(dmth(c1) - dmth(c3)) / dmth(c1); // 제1이심률
        double c5 = (double)(dmth(c1) - dmth(c3)) / dmth(c3); // 제2이심률
        //-----------여기까지는 확인완료

        double f1 =(double) 0.9996; // 축척계수
        double f2 = (double)2000000; // X축 가산값
        double f3 = (double)1000000; // Y축 가산값
        double f4 = (double)38.0; // 원점 위도
        double f5 = (double)127.5; // 원점 경도
        double g5;

        double h9 = (double)coorY2;
        double i9 = (double)coorX2;
        double j9 = dmth((double)Math.tan(h9 / 180 * Math.PI)); // T
        double k9 = (double)c5 * dmth((double)(Math.cos(h9 / 180 * Math.PI))); // C
        double l9 = (double)(((i9 / 180 * Math.PI) - (f5 / 180 * Math.PI))
                * Math.cos(h9 / 180 * Math.PI)); // A
        //-------------여기까지 확인 완료

        double m9 = c1 / (double)Math.sqrt(1 - c4 * dmth((double)Math.sin(h9 / 180 * Math.PI))); // N
        double n9 = (double)(c1
                * ((1 - c4 / 4 - 3 * dmth(c4) / 64 - 5 * tmth(c4) / 256)
                * (h9 / 180 * Math.PI)
                + (-3 * c4 / 8 + -3 * dmth(c4) / 32 + (-45 * tmth(c4)) / 1024)
                * Math.sin(2 * h9 / 180 * Math.PI)
                + (15 * dmth(c4) / 256 + 45 * tmth(c4) / 1024)
                * Math.sin(4 * h9 / 180 * Math.PI) - 35 * tmth(c4)
                / 3072 * Math.sin(6 * h9 / 180 * Math.PI))); // M

        // g5
        g5 = (double)(c1
                * ((1 - c4 / 4 - 3 * dmth(c4) / 64 - 5 * tmth(c4) / 256)
                * (f4 / 180 * Math.PI)
                + (-3 * c4 / 8 + -3 * dmth(c4) / 32 + -45 * tmth(c4) / 1024)
                * Math.sin(2 * f4 / 180 * Math.PI)
                + (15 * dmth(c4) / 256 + 45 * tmth(c4) / 1024)
                * Math.sin(4 * f4 / 180 * Math.PI) - 35 * tmth(c4)
                / 3072 * Math.sin(6 * f4 / 180 * Math.PI)));

        // ==== Tutorial ====

        double XNorth, YEast; // 계산된 UTM-K XY좌표계

        XNorth = (double)(f2
                + f1
                * (n9 - g5 + m9
                * Math.tan(h9 / 180 * Math.PI)
                * (dmth(l9) / 2 + qmth(l9)
                * (5 - j9 + 9 * k9 + dmth(4 * k9)) / 24 + hmth(l9)
                * (61 - 58 * j9 + dmth(j9) + 600 * k9 - 330 * c5))));
        YEast = f3
                + (m9 * (l9 + tmth(l9) * (1 - j9 + k9) / 6 + pmth(l9)
                * (5 - 18 * j9 + dmth(j9) + 72 * k9 - 58 * c5) / 120))
                * f1;
        DoublePoint fPoint = new DoublePoint(YEast, XNorth);
        return fPoint;
     }

    public static double dmth(double ace) {
        return ace * ace;
    } //제곱

    public static double tmth(double ace) {
        return ace * ace * ace;
    } //세제곱

    public static double qmth(double ace) {
        return ace * ace * ace * ace;
    } //네제곱

    public static double pmth(double ace) {
        return ace * ace * ace * ace * ace;
    } //오제곱

    public static double hmth(double ace) {
        return ace * ace * ace * ace * ace * ace;
    } //육제곱
}
