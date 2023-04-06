import java.io.IOException;

public class Console {
    private ProcessBuilder processClean = new ProcessBuilder("cmd", "/c", "cls");

    public void draw(String graphic) {
        System.out.println(graphic);
    }

    public void clear() {
        try {
            processClean.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
