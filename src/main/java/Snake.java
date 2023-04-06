import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private char designation = '*';
    private int code = 5;
    private LinkedList<Point> body = new LinkedList<>();
    private boolean spawn = false;
    private Direction currentDirection;
    private Point hotStep;
    private Point head;

    public LinkedList<Point> getBody() {
        return body;
    }

    public void grow(){
        if(spawn){
            body.add(hotStep);
        }else {
            throw new RuntimeException("Snake is not exist");
        }
    }

    private Point getTailPosition(){
        return body.getLast();
    }

    public void move(){
        hotStep = getTailPosition();
        head = body.getFirst();
        body.removeLast();
        body.addFirst(new Point(head.x, head.y + 1));
    }

    public void setDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void spawn(){
        if (spawn){
            throw new RuntimeException("Snake was created yet");
        }
        spawn = true;
        body.add(new Point(10,10));
    }

}
