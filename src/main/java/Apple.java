import java.awt.*;

public class Apple {
    public static final Designations DESIGNATION = new Designations('@', 10);
    private DefaultMap map;
    private int x;
    private int y;
    private int points = 10;

    public Apple(DefaultMap map) {
        this.map = map;
    }

    public void respawn(){
        map.delete(DESIGNATION);
        Point pos = map.getRandomFreeSpace();
        x = pos.x;
        y = pos.y;
        map.draw(pos, DESIGNATION);
    }

    public int getPoints() {
        return points;
    }
}
