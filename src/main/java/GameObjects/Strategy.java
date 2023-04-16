package GameObjects;

import java.awt.*;

public interface Strategy {
    Point getNextPosition(Point currentPos);
}
