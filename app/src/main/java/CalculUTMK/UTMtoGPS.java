package CalculUTMK;

import android.util.Log;

import Point.DoublePoint;

public class UTMtoGPS {
	double latitude;
	double longtitude;

	public UTMtoGPS() {
	}

	public DoublePoint getXY(DoublePoint dp) {

		double coorX2;// �浵 ������
		double coorY2;// ���� ������

		coorX2 = dp.getX(); // b9
		coorY2 = dp.getY(); // c9
		double b9 = coorY2;
		double c9 = coorX2;

		double c1 = (double) 6378137.0; // ��ݰ�
		double c2 = (double) (1.0 / 298.257223563); // �����
		//System.out.println("C2 >> " + c2);
		double c3 = (double) c1 * (1 - c2); // �ܹݰ�
		//System.out.println("C3 >> " + c3);
		double c4 = (double) (dmth(c1) - dmth(c3)) / dmth(c1); // ��1�̽ɷ�
		//System.out.println("C4 >> " + c4);
		double c5 = (double) (dmth(c1) - dmth(c3)) / dmth(c3); // ��2�̽ɷ�
		//System.out.println("C5 >> " + c5);
		// -----------��������� Ȯ�οϷ�

		double f1 = (double) 0.9996; // ��ô���
		double f2 = (double) 2000000.0; // X�� ���갪
		double f3 = (double) 1000000.0; // Y�� ���갪
		double f4 = (double) 38.0; // ���� ����
		double f5 = (double) 127.5; // ���� �浵
		double g5;
		// g5
		g5 = (double) (c1 * ((1 - c4 / 4 - 3 * dmth(c4) / 64 - 5 * tmth(c4) / 256)
				* (f4 / 180 * Math.PI)
				+ (-3 * c4 / 8 + -3 * dmth(c4) / 32 + -45 * tmth(c4) / 1024)
				* Math.sin(2 * f4 / 180 * Math.PI)
				+ (15 * dmth(c4) / 256 + 45 * tmth(c4) / 1024)
				* Math.sin(4 * f4 / 180 * Math.PI) - 35 * tmth(c4) / 3072
				* Math.sin(6 * f4 / 180 * Math.PI)));
		//System.out.println("G5 >> " + g5);
		// d9
		double d9 = g5 + (b9 - f2) / f1;
		//System.out.println("D9 >> " + d9);
		// e9
		double e9 = d9
				/ (c1 * (1.0 - c4 / 4.0 - 3.0 / 64 * dmth(c4) - 5.0 / 256 * tmth(c4)));
		//System.out.println("E9 >> " + e9);

		// f9
		double f9 = (1 - Math.sqrt(1 - c4)) / (1 + Math.sqrt(1 - c4));
		//System.out.println("F9 >> " + f9);
		// g9
		double g9 = e9
				+ ((3.0 / 2.0 * f9 - 27.0 / 32.0 * tmth(f9)) * (Math
						.sin(2.0 * e9)))
				+ ((21.0 / 16.0 * dmth(f9) - 55.0 / 32.0 * qmth(f9)) * Math
						.sin(4.0 * e9))
				+ ((151.0 / 96.0 * tmth(f9)) * Math.sin(6.0 * e9))
				+ ((1097.0 / 512.0 * qmth(f9)) * Math.sin(8 * e9));

		//System.out.println("G9 >> " + g9);
		// h9
		double h9 = (c1 * (1 - c4))
				/ (Math.sqrt(tmth((1 - c4 * dmth(Math.sin(g9))))));
		//System.out.println("H9 >> " + h9);
		// i9
		double i9 = c5 * dmth((Math.cos(g9)));
		//System.out.println("I9 >> " + i9);
		// j9
		double j9 = dmth(Math.tan(g9));
		//System.out.println("J9 >> " + j9);
		// k9
		double k9 = c1 / Math.sqrt(1.0 - c4 * dmth(Math.sin(g9)));
		//System.out.println("K9 >> " + k9);
		// l9
		double l9 = (c9 - f3) / k9 / f1;
		//System.out.println("L9 >> " + l9);
		// m9
		double m9 = (g9
				- (k9 / h9 * Math.tan(g9))
				* (dmth(l9) / 2 - (qmth(l9) / 24 * (5.0 + 3.0 * j9 + 10 * i9
						- 4.0 * (dmth(i9)) - 9.0 * c5))) + (hmth(l9) / 720.0 * (61.0
				+ 90.0 * j9 + 298.0 * i9 + 45.0 * dmth(j9) - 252.0 * c5 - 3.0 * (dmth(i9)))))
				* 180 / Math.PI;
		//System.out.println("M9 >> " + m9);
		// m9
		double n9 = f5
				+ ((1.0 / Math.cos(g9)) * (l9 - tmth(l9) / 6.0
						* (1.0 + 2.0 * j9 + i9) + pmth(l9)
						/ 120.0
						* (5.0 - 2.0 * i9 + 28.0 * j9 - 3.0 * dmth(i9) + 8.0
								* c5 + 24.0 * dmth(j9)))) * 180.0 / Math.PI;
		/*
		System.out.println("N9 >> " + n9);
		System.out.println("N91 >> " + ((1 / Math.cos(g9)) * (l9 - tmth(l9))));
		System.out
				.println("N92 >> " + (6.0 * (1.0 + 2.0 * j9 + i9) + pmth(l9)));
		System.out.println("N93 >> "
				+ (120.0 * (5.0 - 2.0 * i9 + 28.0 * j9 - 3.0 * dmth(i9) + 8.0
						* c5 + 24.0 * dmth(j9))));
		System.out.println(((1.0 / Math.cos(g9)) * (l9 - tmth(l9) / 6.0
						* (1.0 + 2.0 * j9 + i9) + pmth(l9)
						/ 120.0
						* (5.0 - 2.0 * i9 + 28.0 * j9 - 3.0 * dmth(i9) + 8.0
								* c5 + 24.0 * dmth(j9)))) * 180.0 / Math.PI);
		*/
		DoublePoint dPoint = new DoublePoint(n9, m9);
        //Log.i("UTMtoGPS","xy"+dPoint.getX() + "///" + dPoint.getY());
        return dPoint;
	}

	public static double dmth(double ace) {
		return ace * ace;
	} // ����

	public static double tmth(double ace) {
		return ace * ace * ace;
	} // ������

	public static double qmth(double ace) {
		return ace * ace * ace * ace;
	} // ������

	public static double pmth(double ace) {
		return ace * ace * ace * ace * ace;
	} // ������

	public static double hmth(double ace) {
		return ace * ace * ace * ace * ace * ace;
	} // ������

}
