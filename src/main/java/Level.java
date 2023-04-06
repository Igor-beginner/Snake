import java.awt.*;

public class Level {
    private Map map;
    private int fps;


    public Level(Map map, int fps) {
        this.map = map;
        this.fps = fps;
    }


    public int getFps() {
        return fps;
    }
}
