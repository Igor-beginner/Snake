import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Events implements NativeKeyListener{
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        System.out.println("oda");
    }

    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }
}
