package Dijkstra;

import android.util.Log;

import java.util.ArrayList;

import nodedata.NodeData;

public class Dijkstra {
    static int datasize;
	static float[][] Array;
	static ArrayList<NodeData> dataList;
    static int startPoint;
    static int endPoint;
	ArrayList<Integer> num2 = new ArrayList<Integer>();
    public Dijkstra() {

    }
	
	public Dijkstra(ArrayList<NodeData> dataList) {
		// TODO Auto-generated constructor stub
		
		this.dataList = dataList;
		datasize = dataList.size();
        Log.i("Dijkstra","datasize" + datasize);
		Array = new float[datasize][datasize];
		setDijkstraArray(); // ���ͽ�Ʈ�� �迭 �����
        Log.i("Dijkstra","SettingFinished");

	}
    public ArrayList<Integer> returnLoot() {
        return num2;
    }
	public void setDijkstraArray() {
		for (int i = 0; i < datasize; i++) {
			for (int j = 0; j < datasize; j++) {
				Array[i][j] = 999999;
			}
		}
		for (int i = 0; i < datasize; i++) {
			ArrayList<Integer> temp = dataList.get(i).getArray();

			for (int j = 0; j < temp.size(); j++) {
				for (int k = 0; k < datasize; k++) {
					ArrayList<Integer> temp2 = dataList.get(k).getArray();
					for (int l = 0; l < temp2.size(); l++) {
						if ((temp.get(j) - temp2.get(l)) == 0) {
                            float x1 = dataList.get(i).getX();
                            float y1 = dataList.get(i).getY();
                            float x2 = dataList.get(k).getX();
                            float y2 = dataList.get(k).getY();
							Array[i][k] = (float)Math.sqrt((x1 - x2) * (x1 - x2)
									+ (y1 - y2) * (y1 - y2));
						}

					}
				}
			}
		}

	}

	public void CalculationLoot(int start, int end) {
        num2 = null;
        num2 = new ArrayList<Integer>();
		startPoint = start;
		endPoint = end;
		int[] flag = new int[datasize]; // �Ÿ� �������� üũ �迭6
        float[] stack = new float[datasize];// �Ÿ� �迭
		double min;
		int p = 0;

		int[] index = new int[datasize];

		for (int i = 0; i < datasize; i++) { // �ʱ�ȭ �κ�
			stack[i] = (float)999999.0;
		}
		stack[startPoint] = 0;
		index[startPoint] = 0;
		for (int i = 0; i < datasize; i++) {
			// System.out.println(i + " >>>>> ===");
			min = 999999; // �ϴ� ���Ѵ�(�� ū��)�� �ʱ�ȭ �ϰ�
			for (int j = 0; j < datasize; j++) {
                if (min > stack[j] && flag[j] == 0) { // stack[i]�� ���� for j �����鼭
                    // ���� �ּҰ����� ������ �׸��� ����
                    // �������� ���� �����̶��

                    min = stack[j]; // �����մϴ�
                    p = j;// ���� ��ġ ���
                }
            }
			flag[p] = 1; // ���� ��ġ(����)�� �ּҰ����� �����Ǿ����� 1�� ���Ƴ���..
			for (int j = 0; j < datasize; j++) {

				if (stack[j] > Array[p][j] + stack[p]) //
				// ���� ��ġ������ j ��ġ�� ���µ������� ������ �̶����� ���� j������ �Ÿ��� �� ũ�� �׸��� �ش� �κ���
				// �������� �ʾҴٸ�
				{
					// System.out.println(index[j] + "/");
					stack[j] = Array[p][j] + stack[p]; // �Ÿ� �迭�� �����մϴ� }
					index[j] = p;
					// System.out.println("index["+j+"] >> " + index[j]);
				}

			}

		}

		int ttt;

		
		int[][] stage = new int[datasize][datasize];
		for (int i = 0; i < datasize; i++) {
			p = i;
			ttt = 0;
			while (index[p] != 0) {
				stage[i][ttt] = p;
				p = (int) index[p];
				ttt++;
			}
		}
		
		for (int i = 0; i < datasize; i++) {
			if (stage[endPoint][i] > 0) {
				num2.add(stage[endPoint][i]);
			} else {
				stage[endPoint][i] = startPoint;
				num2.add(stage[endPoint][i]);
				break;
			}
		}
        //Log.i("Dijkstra","returnFinished");

	}


}
