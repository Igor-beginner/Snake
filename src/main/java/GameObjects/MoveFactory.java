package GameObjects;

import java.awt.*;

public class MoveFactory {
    public static final Strategy UP = pos -> new Point(pos.x, pos.y - 1);
    public static final Strategy DOWN = pos -> new Point(pos.x, pos.y + 1);
    public static final Strategy LEFT = pos ->  new Point(pos.x - 1, pos.y);
    public static final Strategy RIGHT = pos -> new Point(pos.x + 1, pos.y);
}
