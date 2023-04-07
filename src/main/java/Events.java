import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Events implements NativeKeyListener{
    private Snake snake;

    public Events(Snake snake) {
        this.snake = snake;
    }

    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        Direction current = snake.getCurrentDirection();
        switch (nativeKeyEvent.getKeyCode()){
            case NativeKeyEvent.VC_UP:
                if(current != Direction.DOWN)
                 snake.setDirection(Direction.UP);
                break;
            case NativeKeyEvent.VC_DOWN:
                if(current != Direction.UP)
                    snake.setDirection(Direction.DOWN);
                break;
            case NativeKeyEvent.VC_LEFT:
                if(current != Direction.RIGHT)
                    snake.setDirection(Direction.LEFT);
                break;
            case NativeKeyEvent.VC_RIGHT:
                if(current != Direction.LEFT)
                    snake.setDirection(Direction.RIGHT);
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
