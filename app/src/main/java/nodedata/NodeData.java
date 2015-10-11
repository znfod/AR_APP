package nodedata;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by B102-12 on 2015-05-08.
 */
public class NodeData {
    int id;
    float x;
    float y;
    ArrayList<Integer> LineArray;//연결된 길번호
    public NodeData(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
        LineArray = new ArrayList<Integer>();
    }


    public NodeData() {
    }
    public int describeContents(){
        return 0;
    }

    // 쓰는 순서 주의
    public void writeToParcel( Parcel dest, int flags ){
        dest.writeInt( id );
        dest.writeFloat(x);
        dest.writeFloat(y);
        dest.writeSerializable(LineArray);
    }

    // 반드시 필요한, static filed 로 정의된 CREATOR object.
    public static final Parcelable.Creator<NodeData> CREATOR = new Parcelable.Creator<NodeData>(){
        // 읽는 순서에 주의
        public NodeData createFromParcel( Parcel source ){
            NodeData n = new NodeData();
            n.id = source.readInt();
            n.x = source.readFloat();
            n.y = source.readFloat();
            n.LineArray = (ArrayList<Integer>)source.readSerializable();
            return n;
        }

        public NodeData[] newArray( int size ){
            return new NodeData[size];
        }
    };
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    public void setArray(ArrayList<Integer> array) { LineArray = array; }
    public void addArray(int lineNo) {
        LineArray.add(lineNo);
    }
    public ArrayList<Integer> getArray() {
        return LineArray;
    }

}
