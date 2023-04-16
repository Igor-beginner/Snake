package GameObjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefaultMap implements Map {

    private int x = 23;
    private int y = 20;
    private Dimension size = new Dimension(x, y);

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


    public Designations getObject(Point point){
        return map[point.y][point.x];
    }


    @Override
    public Designations[][] getBoard() {
        return map;
    }

    @Override
    public Dimension getSize() {
        return size;
    }
}
