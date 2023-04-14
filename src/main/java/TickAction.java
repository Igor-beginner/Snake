import java.util.Iterator;

public class TickAction<T> implements Iterator<T> {
    private T[] ticks = (T[]) new Object[2];

    public void put(T tick){
        if(isEntire()){
            ticks[ticks.length - 1] = tick;
            return;
        }
        addInsteadEmpty(tick);
    }

    private void addInsteadEmpty(T tick){
        for (int i = 0; i < ticks.length; i++){
            if(ticks[i] == null){
                ticks[i] = tick;
                return;
            }
        }
    }

    public T take() throws Exception{
        T last = getLast();
        if(last != null){
            shiftLeft();
            return last;
        }
        throw new Exception("Array is empty");
    }

    private T getLast(){
        for (int i = ticks.length - 1; i >= 0; i--){
            T checking = ticks[i];
            if(checking != null){
                ticks[i] = null;
                return checking;
            }
        }
        return null;
    }

    private void shiftLeft(){
        for (int i = 0; i < ticks.length - 1; i++){
            ticks[i] = ticks[i + 1];
        }
    }

    private boolean isEntire(){
        for (T tick : ticks) {
            if (tick == null) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmpty(){
        for (T tick : ticks) {
            if (tick != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasNext() {
        return !isEmpty();
    }

    @Override
    public T next() {
        T el = null;
        try {
            el = take();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return el;
    }

    @Override
    public String toString() {
        return String.format("[ %s, %s ]", ticks[0], ticks[1]);
    }
}
