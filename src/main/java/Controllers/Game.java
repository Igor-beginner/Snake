package Controllers;

import GUI.Console;
import GameObjects.Apple;
import GameObjects.Snake;
import GameObjects.Strategy;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    private static final Console CONSOLE = new Console();
    private static final Game GAME = new Game();
    private int score;
    private boolean isOver;
    private Strategy currentStrategy;
    private GameObjects.Level beginner = GameObjects.Level.BEGINNER;
    private Apple apple = new Apple(beginner.getRandomFreeSpace());
    private Snake snake = new Snake(beginner);
    private Events events = new Events(snake);

    public void init()throws NativeHookException{
        CONSOLE.clear();

        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
        GlobalScreen.addNativeKeyListener(events);
        GlobalScreen.registerNativeHook();

        CONSOLE.clear();

        snake.spawn();
        apple.updateLocation(beginner.getRandomFreeSpace(), beginner);
        List<Point> posSnake = snake.getBody();
        beginner.add(snake, posSnake.get(0));
        System.out.println(beginner.render());
        while (currentStrategy == null) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void showLoseWindow(){
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - \n");
        System.out.println("            * G A M E   O V E R * \n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - ");
    }

    public static void main(String[] args) throws NativeHookException {
        Game game = Game.getInstance();
        game.init();

        while (!game.isOver) {
            CONSOLE.clear();
            GAME.update();
        }
        game.showLoseWindow();
    }


    public void update() {
        try {
              snake.setStrategy(currentStrategy);
            //CONSOLE.draw("");
            snake.move();
            for(Point p : snake.getBody()){
                beginner.add(snake, p);
            }
            System.out.println(beginner.render());
            System.out.println("Score: " + score);
            if(!isOver)
                Thread.sleep(1000 / beginner.getFps());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void gameOver() {
        isOver = true;
    }

    public static Game getInstance() {
        return GAME;
    }

    public boolean getState(){
        return isOver;
    }

    public void setCurrentStrategy(Strategy currentStrategy) {
        this.currentStrategy = currentStrategy;
    }
}

