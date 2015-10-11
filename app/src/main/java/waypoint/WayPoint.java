package waypoint;

/**
 * Created by B102-12 on 2015-05-08.
 */
public class WayPoint {
    float x; //X포인트
    float y; //Y포인트
    String name; //현 위치 이름
    public WayPoint() {

    }
    public WayPoint(float x, float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
