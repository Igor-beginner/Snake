import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultMap implements Map{
    private final static Designations FENCE = new Designations('#', -1);
    private final static Designations SPACE = new Designations(' ', 0);

    private int x = 10;
    private int y = 10;

    private Random random = new Random();
    private List<Point> freeSpace = new ArrayList<>();

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
    private void findFreeSpace(){
        for(int y = 0; y < this.y; y++){
            for(int x = 0; x < this.x; x++){
                if (map[y][x] == SPACE){
                    freeSpace.add(new Point(x, y));
                }
            }
        }
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
        findFreeSpace();
        return freeSpace.get((int)(Math.random() * freeSpace.size()));
    }

    public void draw(Point pos, Designations item){
        map[pos.y][pos.x] = item;
        System.out.println(map);
    }
}
