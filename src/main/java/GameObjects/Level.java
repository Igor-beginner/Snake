package GameObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static GameObjects.Map.FENCE;
import static GameObjects.Map.SPACE;

public class Level implements LevelManage{
    private Map map;
    private int fps;

    public static final Level BEGINNER = new Level(new DefaultMap(), 2);

    public Level(Map map, int fps) {
        this.map = map;
        this.fps = fps;
    }

    private List<Point> findFreeSpace(){
        List<Point> freeSpace = new ArrayList<>();
        for(int y = 0; y < map.getSize().height; y++){
            for(int x = 0; x < map.getSize().width; x++){
                if (map.getBoard()[y][x] == SPACE){
                    freeSpace.add(new Point(x, y));
                }
            }
        }
        return freeSpace;
    }

    @Override
    public Point getRandomFreeSpace() {
        List<Point> freeSpace = findFreeSpace();
        return freeSpace.get((int)(Math.random() * freeSpace.size()));
    }

    @Override
    public String render() {
        StringBuilder map = new StringBuilder();
        for(int y = 0; y < this.map.getSize().height; y++){
            for(int x = 0; x < this.map.getSize().width; x++){
                map.append(this.map.getBoard()[y][x].getDesignation());
                map.append(SPACE.getDesignation());
            }
            map.append("\n");
        }
        return map.toString();
    }

    @Override
    public void add(GameObject go, Point pos) {
        map.getBoard()[pos.y][pos.x] = go.getDesignations();
    }

    @Override
    public void removeObject(Point pos) {
        map.getBoard()[pos.y][pos.x] = SPACE;
    }

    @Override
    public boolean isMovable(Point pos) {
        return map.getBoard()[pos.y][pos.x] != FENCE && map.getBoard()[pos.y][pos.x] != Snake.SNAKE;
    }

    public int getFps() {
        return fps;
    }
}
