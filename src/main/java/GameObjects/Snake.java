package GameObjects;

import Controllers.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Snake implements Observable, GameObject{

    public static final Designations SNAKE = new Designations('*', 4);
    private LinkedList<Point> body = new LinkedList<>();
    private Point hotStep;
    private Point head;
    private Point headNextPosition;
    private boolean spawn = false;

    private Strategy strategy;
    private List<Observer> observerList = new ArrayList<>();
    private LevelManage map;


    public Snake(LevelManage map) {
        this.map = map;
    }

    public LinkedList<Point> getBody() {
        return body;
    }


    public void move(){
        prepareState();
        tryToMove();
    }

    private void prepareState(){
        hotStep = getTailPosition();
        head = body.getFirst();
        headNextPosition = strategy.getNextPosition(head);
    }

    private void tryToMove(){
        if (map.isMovable(headNextPosition)){
            shift();
        }else {
            Game.getInstance().gameOver();
        }
    }

    private void shift(){
        if(!Game.getInstance().getState()){
            return;
        }
        body.addFirst(headNextPosition);
        Point point = body.removeLast();
        map.removeObject(point);
    }

    public void grow(){
        if(spawn){
            body.add(hotStep);
        }else {
            throw new RuntimeException("GameObjects.Snake is not exist");
        }
    }

    public void spawn(){
        if (spawn){
            throw new RuntimeException("Snake was created yet");
        }
        spawn = true;
        body.add(map.getRandomFreeSpace());

    }

    @Override
    public void register(Observer o) {
        observerList.add(o);
    }

    @Override
    public void remove(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyAllGameObjects() {
        for(Observer observer : observerList){
            observer.updateLocation(head, map);
        }
    }

    @Override
    public Designations getDesignations() {
        return SNAKE;
    }

    @Override
    public Point getPosition() {
        return head;
    }

    @Override
    public int getPoints() {
        return body.size();
    }

    private Point getTailPosition(){
        return body.getLast();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy(){
        return strategy;
    }

}
