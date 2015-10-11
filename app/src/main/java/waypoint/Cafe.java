package waypoint;

/**
 * Created by B102-12 on 2015-05-08.
 */
public class Cafe extends WayPoint {
    String information;
    public Cafe() {

    }
    public Cafe(float x, float y, String name ) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public Cafe(float x, float y, String name, String information ) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.information = information;
    }
}
