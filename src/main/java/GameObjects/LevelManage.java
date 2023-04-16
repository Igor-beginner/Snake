package GameObjects;

import java.awt.*;

public interface LevelManage {
    String render();
    Point getRandomFreeSpace();
    void add(GameObject go);
    void add(GameObject go, Point pos);
    void removeObject(Point pos);
    boolean isMovable(Point pos);
}
