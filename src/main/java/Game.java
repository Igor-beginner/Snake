import org.jnativehook.NativeHookException;

public class Game {

    private static final Console CONSOLE = new Console();
    private static final Game GAME = new Game();
    private int score;
    private boolean gameOver;

    public static void main(String[] args) throws NativeHookException {
        Snake snake = new Snake();
        DefaultMap defaultMap = new DefaultMap();
        Apple apple = new Apple(defaultMap);
        System.out.println(defaultMap.render());
        apple.respawn();
        System.out.println(defaultMap.render());

//        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
//
//        GlobalScreen.addNativeKeyListener(new Events());
//        GlobalScreen.registerNativeHook();
//
//        CONSOLE.clear();
//
//        while (true) {
//            GAME.update(1);
//        }
    }

    public void update(int fps){
        try {
            CONSOLE.draw("");
            Thread.sleep(1000 / fps);
            CONSOLE.clear();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void gameOver(){
        gameOver = true;
    }

    public Game getInstance(){
        return GAME;
    }
}

