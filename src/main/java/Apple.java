import java.awt.*;

public class Apple {
    private static final Designations APPLE = new Designations('@', 10);
    private DefaultMap map;
    private int x;
    private int y;

    public Apple(DefaultMap map) {
        this.map = map;
    }

    public void respawn(){
        map.draw(map.getRandomFreeSpace(), APPLE);
    }
}
