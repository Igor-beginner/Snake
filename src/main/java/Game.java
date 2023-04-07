import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.json.JSONObject;
import org.json.JSONWriter;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    private static final Console CONSOLE = new Console();
    private static final Game GAME = new Game();
    private int score;
    private boolean isOver;
    private DefaultMap defaultMap = new DefaultMap();
    private Apple apple = new Apple(defaultMap);
    private Snake snake = new Snake(defaultMap);
    private Events events = new Events(snake);

    public static void main(String[] args) throws NativeHookException {
        CONSOLE.clear();
        Game game = Game.getInstance();
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
        GlobalScreen.addNativeKeyListener(game.events);
        GlobalScreen.registerNativeHook();

        CONSOLE.clear();

        game.snake.spawn();
        game.apple.respawn();
        List<Point> posSnake = game.snake.getBody();
        game.defaultMap.draw(posSnake.get(0), Snake.getDestination());
        System.out.println(game.defaultMap.render());
        while (game.snake.getCurrentDirection() == null) {
            synchronized (game) {
                try {
                    game.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        while (!game.isOver) {
            CONSOLE.clear();
            GAME.update(8);
        }
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        String path = classLoader.getResource("Best score.txt").getPath();
//        JSONObject json = new JSONObject(path);
//        int bestScore = json.getInt("level1");
//        if(bestScore > game.score){
//            System.out.println("Your new best score - " + game.score);
//            System.out.println("Was - " + bestScore);
//        }
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - \n");
        System.out.println("            * G A M E   O V E R * \n");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - ");
    }

    public void update(int fps) {
        try {
            //CONSOLE.draw("");
            snake.move();
            for(Point p : snake.getBody()){
                defaultMap.draw(p, Snake.getDestination());
            }
            System.out.println(defaultMap.render());
            System.out.println("Score: " + score);
            if(!isOver)
                Thread.sleep(1000 / fps);
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

    public void eat(){
        score += apple.getPoints();
        apple.respawn();
        snake.grow();
    }
}

