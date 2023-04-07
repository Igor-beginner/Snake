import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultMap implements Map{
    private final static Designations FENCE = new Designations('#', -1);
    private final static Designations SPACE = new Designations(' ', 0);

    private int x = 23;
    private int y = 20;

    private Random random = new Random();

    private Designations[][] map = new Designations[y][x];

    {
        Designations[] boardTop = buildTopBoard(FENCE);
        Designations[] side = buildSide();
        map[0] = boardTop;
        for (int i = 1; i < map.length - 1; i++){
            map[i] = side.clone();
        }
        map[map.length - 1] = boardTop;
    }
    private List<Point> findFreeSpace(){
        List<Point> freeSpace = new ArrayList<>();
        for(int y = 0; y < this.y; y++){
            for(int x = 0; x < this.x; x++){
                if (map[y][x] == SPACE){
                    freeSpace.add(new Point(x, y));
                }
            }
        }
        return freeSpace;
    }

    private Designations[] buildTopBoard(Designations stuff){
        Designations[] board = new Designations[x];
        for(int i = 0; i < x; i++){
            board[i] = stuff;
        }
        return board;
    }

    private Designations[] buildSide(){
        Designations[] board = buildTopBoard(SPACE);
        board[0] = FENCE;
        board[x - 1] = FENCE;
        return board;
    }

    @Override
    public String render() {
        StringBuilder map = new StringBuilder();
        for(int y = 0; y < this.y; y++){
            for(int x = 0; x < this.x; x++){
                map.append(this.map[y][x].getDesignation());
                map.append(SPACE.getDesignation());
            }
            map.append("\n");
        }
        return map.toString();
    }

    @Override
    public Point getRandomFreeSpace() {
        List<Point> freeSpace = findFreeSpace();
        return freeSpace.get((int)(Math.random() * freeSpace.size()));
    }

    public void draw(Point pos, Designations item){
        map[pos.y][pos.x] = item;
    }
    public boolean delete(Designations item){
        boolean anyDeleted = false;
        for (int y = 0; y < this.y; y++){
            for(int x = 0; x < this.x; x++){
                if(map[y][x] == item){
                    map[y][x] = SPACE;
                    anyDeleted = true;
                }
            }
        }
        return anyDeleted;
    }

    public void delete(Point pos){
        map[pos.y][pos.x] = SPACE;
    }

    public boolean ableForMove(Point point){
        return map[point.y][point.x] != FENCE && map[point.y][point.x] != Snake.getDestination();
    }

    public Designations getObject(Point point){
        return map[point.y][point.x];
    }
}
