package Controllers;

import GameObjects.Direction;
import GameObjects.MoveFactory;
import GameObjects.Snake;
import GameObjects.Strategy;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Events implements NativeKeyListener{
    private Snake snake;

    public Events(Snake snake) {
        this.snake = snake;
    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        Strategy current = snake.getStrategy();
        switch (nativeKeyEvent.getKeyCode()){
            case NativeKeyEvent.VC_UP:
                if(current != MoveFactory.DOWN)
                    Game.getInstance().setCurrentStrategy(MoveFactory.UP);
                break;
            case NativeKeyEvent.VC_DOWN:
                if(current != MoveFactory.UP)
                    Game.getInstance().setCurrentStrategy(MoveFactory.DOWN);
                break;
            case NativeKeyEvent.VC_LEFT:
                if(current != MoveFactory.RIGHT)
                    Game.getInstance().setCurrentStrategy(MoveFactory.LEFT);
                break;
            case NativeKeyEvent.VC_RIGHT:
                if(current != MoveFactory.LEFT)
                    Game.getInstance().setCurrentStrategy(MoveFactory.RIGHT);
                break;
        }
        synchronized (Game.getInstance()){
            Game.getInstance().notify();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }
}
