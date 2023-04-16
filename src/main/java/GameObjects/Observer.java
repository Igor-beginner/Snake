package GameObjects;

import java.awt.*;

public interface Observer {
    void updateLocation(Point pos, LevelManage map);
}
