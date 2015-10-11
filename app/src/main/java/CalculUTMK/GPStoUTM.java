package CalculUTMK;

import Point.DoublePoint;
public class GPStoUTM {
	public GPStoUTM() {

	}

	public DoublePoint getXY(DoublePoint dp) {
		
		double coorX2;// �浵 ������
		double coorY2;// ���� ������

		coorX2 = dp.getX();
		coorY2 = dp.getY();

		/**
		 * ����� INPUT : ���浵(������) OUTPUT : UTM-K XY��ǥ�� $F$2+$F$1*(N9-$G$5+M9*TAN(
		 * H9/180*PI())*(L9^2/2+L9^4*(5-J9+9*K9+4*K9^2
		 * )/24+L9^6*(61-58*J9+J9^2+600*K9-330*$C$5)))
		 */
		/*
		 * X�డ�갪 + ��ô��� X ( (
		 * 
		 * )
		 */
		double c1 = (double) 6378137; // ��ݰ�
		double c2 = (double) (1 / 298.257222101); // �����
		double c3 = (double) c1 * (1 - c2); // �ܹݰ�
		double c4 = (double) (dmth(c1) - dmth(c3)) / dmth(c1); // ��1�̽ɷ�
		double c5 = (double) (dmth(c1) - dmth(c3)) / dmth(c3); // ��2�̽ɷ�
		// -----------��������� Ȯ�οϷ�

		double f1 = (double) 0.9996; // ��ô���
		double f2 = (double) 2000000; // X�� ���갪
		double f3 = (double) 1000000; // Y�� ���갪
		double f4 = (double) 38.0; // ���� ����
		double f5 = (double) 127.5; // ���� �浵
		double g5;

		double h9 = (double) coorY2;
		double i9 = (double) coorX2;
		double j9 = dmth((double) Math.tan(h9 / 180 * Math.PI)); // T
		double k9 = (double) c5 * dmth((double) (Math.cos(h9 / 180 * Math.PI))); // C
		double l9 = (double) (((i9 / 180 * Math.PI) - (f5 / 180 * Math.PI)) * Math
				.cos(h9 / 180 * Math.PI)); // A
		// -------------������� Ȯ�� �Ϸ�

		double m9 = c1
				/ (double) Math.sqrt(1 - c4
						* dmth((double) Math.sin(h9 / 180 * Math.PI))); // N
		double n9 = (double) (c1 * ((1 - c4 / 4 - 3 * dmth(c4) / 64 - 5 * tmth(c4) / 256)
				* (h9 / 180 * Math.PI)
				+ (-3 * c4 / 8 + -3 * dmth(c4) / 32 + (-45 * tmth(c4)) / 1024)
				* Math.sin(2 * h9 / 180 * Math.PI)
				+ (15 * dmth(c4) / 256 + 45 * tmth(c4) / 1024)
				* Math.sin(4 * h9 / 180 * Math.PI) - 35 * tmth(c4) / 3072
				* Math.sin(6 * h9 / 180 * Math.PI))); // M

		// g5
		g5 = (double) (c1 * ((1 - c4 / 4 - 3 * dmth(c4) / 64 - 5 * tmth(c4) / 256)
				* (f4 / 180 * Math.PI)
				+ (-3 * c4 / 8 + -3 * dmth(c4) / 32 + -45 * tmth(c4) / 1024)
				* Math.sin(2 * f4 / 180 * Math.PI)
				+ (15 * dmth(c4) / 256 + 45 * tmth(c4) / 1024)
				* Math.sin(4 * f4 / 180 * Math.PI) - 35 * tmth(c4) / 3072
				* Math.sin(6 * f4 / 180 * Math.PI)));

		// ==== Tutorial ====

		double XNorth, YEast; // ���� UTM-K XY��ǥ��

		XNorth = (double) (f2 + f1
				* (n9 - g5 + m9
						* Math.tan(h9 / 180 * Math.PI)
						* (dmth(l9) / 2 + qmth(l9)
								* (5 - j9 + 9 * k9 + dmth(4 * k9)) / 24 + hmth(l9)
								* (61 - 58 * j9 + dmth(j9) + 600 * k9 - 330 * c5))));
		YEast = f3
				+ (m9 * (l9 + tmth(l9) * (1 - j9 + k9) / 6 + pmth(l9)
						* (5 - 18 * j9 + dmth(j9) + 72 * k9 - 58 * c5) / 120))
				* f1;
		DoublePoint dPoint = new DoublePoint(YEast, XNorth);

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
