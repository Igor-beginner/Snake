import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TickActionTest {
    TickAction<Integer> tickAction;

    @Before
    public void init(){
        tickAction = new TickAction<>();
    }

    @Test
    public void addOne(){
        assertFalse(tickAction.hasNext());
        tickAction.put(10);
        assertTrue(tickAction.hasNext());
        assertEquals(10, (int)tickAction.next());
        assertFalse(tickAction.hasNext());
    }

    @Test
    public void addSeveral(){
        Stream.generate(() -> (int)(Math.random()*1000)).limit(2000).forEach(tickAction::put);
        tickAction.put(-3);
        int last = 0;
        while (tickAction.hasNext()){
            last = tickAction.next();
        }
        assertEquals(-3, last);
        assertFalse(tickAction.hasNext());
    }
}