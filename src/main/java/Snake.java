import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Snake {
    private static final Designations SNAKE = new Designations('*', 4);
    private DefaultMap map;
    private LinkedList<Point> body = new LinkedList<>();
    private boolean spawn = false;
    private Direction currentDirection;
    private Point hotStep;
    private Point head;


    public Snake(DefaultMap map) {
        this.map = map;
    }

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
        Point headNextPosition = null;
        switch (currentDirection) {
            case UP:
                headNextPosition = new Point(this.head.x, this.head.y - 1);
                break;
            case DOWN:
                headNextPosition = new Point(this.head.x, this.head.y + 1);
                break;
            case LEFT:
                headNextPosition = new Point(this.head.x - 1, this.head.y);
                break;
            case RIGHT:
                headNextPosition = new Point(this.head.x + 1, this.head.y);
                break;
        }
        if (!map.ableForMove(headNextPosition)){
            Game.getInstance().gameOver();
            return;
        }
        if(map.getObject(headNextPosition) == Apple.DESIGNATION){
            Game game = Game.getInstance();
            game.eat();
        }
        body.addFirst(headNextPosition);
        Point point = body.removeLast();
        map.delete(point);
    }

    public void setDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void spawn(){
        if (spawn){
            throw new RuntimeException("Snake was created yet");
        }
        spawn = true;
        body.add(map.getRandomFreeSpace());

    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public static Designations getDestination() {
        return SNAKE;
    }
}
