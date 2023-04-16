package GameObjects;

import java.awt.*;

public class Apple implements Observer, GameObject {
    private static final Designations DESIGNATION = new Designations('@', 10);
    private static final int POINTS = 10;
    private Point location;

    public Apple(Point location) {
        this.location = location;
    }

    private void respawn(LevelManage map){
        Point pos = map.getRandomFreeSpace();
        location = new Point(pos.x, pos.y);
        map.add(this, pos);
    }

    @Override
    public void updateLocation(Point snakeHeadPosition, LevelManage map) {
        if (snakeHeadPosition.x == location.x && snakeHeadPosition.y == location.y){
            respawn(map);
        }
    }

    @Override
    public Designations getDesignations() {
        return DESIGNATION;
    }

    @Override
    public Point getPosition() {
        return location;
    }

    @Override
    public int getPoints() {
        return POINTS;
    }
}
