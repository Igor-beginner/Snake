package GameObjects;

public interface Observable {
    void register(Observer o);
    void remove(Observer o);
    void notifyAllGameObjects();
}
