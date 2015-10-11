package Scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import nodedata.NodeData;
import waypoint.Cafe;
import waypoint.ConvenienceStore;
import waypoint.Medical;
import waypoint.Restaurant;

/**
 * Created by Znfod on 2015-04-05.
 */
public class CSVScanner {
    ArrayList<NodeData> NodeDataArray;
    ArrayList<Cafe> cafeArray;
    ArrayList<Restaurant> restaurantArray;
    ArrayList<ConvenienceStore> convenienceArray;
    ArrayList<Medical> medicalArray;

    public CSVScanner(InputStream is, int streamNumber) throws FileNotFoundException {

        BufferedReader bf = null;

        try {

            bf = new BufferedReader(new InputStreamReader(is, "euc-kr"));
            StringBuffer sb = new StringBuffer();

            String strtemp = bf.readLine();
            if (streamNumber == 1) {
                //노드
                readNode(bf);
            } else if (streamNumber == 2) {
                //편의점
                readConvenience(bf);
            } else if (streamNumber == 3) {
                //의료
                readMedical(bf);
            } else if (streamNumber == 4) {
                //식당
                readRestaurant(bf);
            } else if (streamNumber == 5) {
                //카페
                readCafe(bf);
            }


        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                bf.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void readNode(BufferedReader bf) throws IOException {
        NodeDataArray = new ArrayList<NodeData>();
        float x;
        float y;
        int id;
        String line = "";
        while ((line = bf.readLine()) != null) {
            int turn = 0;
            String[] strline = new String[3];
            for (int i = 0; i < 3; i++) {
                strline[i] = line.split(",")[i];
            }
            x = Float.parseFloat(strline[0]);
            y = Float.parseFloat(strline[1]);
            id = Integer.parseInt(strline[2]);

            for (int i = 0; i < NodeDataArray.size(); i++) {
                double tempx = NodeDataArray.get(i).getX(); //받아온 X
                double tempy = NodeDataArray.get(i).getY(); //받아온 Y
                if (x == tempx && y == tempy) {
                    turn = i;
                    break;
                }
            }
            if (turn != 0) {
                NodeDataArray.get(turn).addArray(id);
            } else {
                NodeData node = new NodeData(id, x, y);
                NodeDataArray.add(node);
                int a = NodeDataArray.size();
                NodeDataArray.get(a - 1).addArray(id);
            }
        }
    }
    private void readConvenience(BufferedReader bf) {
        convenienceArray = new ArrayList<ConvenienceStore>();
        String name;
        float x;
        float y;
        String line = "";
        try {
            while ((line = bf.readLine()) != null) {
                int turn = 0;
                String[] strline = new String[3];
                for (int i = 0; i < 3; i++) {
                    strline[i] = line.split(",")[i];
                }
                name = strline[0];
                x = Float.parseFloat(strline[1]);
                y = Float.parseFloat(strline[2]);
                convenienceArray.add(new ConvenienceStore(x,y,name));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readMedical(BufferedReader bf) {
        medicalArray = new ArrayList<Medical>();
        String name;
        float x;
        float y;
        String line = "";
        try {
            while ((line = bf.readLine()) != null) {
                int turn = 0;
                String[] strline = new String[3];
                for (int i = 0; i < 3; i++) {
                    strline[i] = line.split(",")[i];
                }
                name = strline[0];
                x = Float.parseFloat(strline[1]);
                y = Float.parseFloat(strline[2]);
                medicalArray.add(new Medical(x,y,name));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readRestaurant(BufferedReader bf) {
        restaurantArray = new ArrayList<Restaurant>();
        String name;
        float x;
        float y;
        String line = "";
        try {
            while ((line = bf.readLine()) != null) {
                int turn = 0;
                String[] strline = new String[3];
                for (int i = 0; i < 3; i++) {
                    strline[i] = line.split(",")[i];
                }
                name = strline[0];
                x = Float.parseFloat(strline[1]);
                y = Float.parseFloat(strline[2]);
                restaurantArray.add(new Restaurant(x,y,name));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readCafe(BufferedReader bf) {
        cafeArray = new ArrayList<Cafe>();
        String name;
        float x;
        float y;
        String line = "";
        try {
            while ((line = bf.readLine()) != null) {
                int turn = 0;
                String[] strline = new String[3];
                for (int i = 0; i < 3; i++) {
                    strline[i] = line.split(",")[i];
                }
                name = strline[0];
                x = Float.parseFloat(strline[1]);
                y = Float.parseFloat(strline[2]);
                cafeArray.add(new Cafe(x,y,name));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<NodeData> getNodeData() {
        return NodeDataArray;
    }

    public ArrayList<Cafe> getCafeArray() {
        return cafeArray;
    }

    public ArrayList<Restaurant> getRestaurantArray() {
        return restaurantArray;
    }

    public ArrayList<ConvenienceStore> getConvenienceArray() {
        return convenienceArray;
    }

    public ArrayList<Medical> getMedicalArray() {
        return medicalArray;
    }
}
