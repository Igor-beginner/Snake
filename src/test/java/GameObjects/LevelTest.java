package GameObjects;

import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by Home on 16.04.2023.
 */
public class LevelTest {
    private Level level;

    @Before
    public void init(){
        level = Level.BEGINNER;
    }

    @Test
    public void addItems(){
        Apple apple = new Apple(level.getRandomFreeSpace());
        level.add(apple);
        assertTrue(apple.getPosition().equals(level.getPositionBy(apple.getDesignations()).get(0)));
        System.out.println(level.render());
    }

    @Test
    public void getFreeSpace(){
        class TestMap extends DefaultMap{
            {
                for (int y = 1; y < getSize().height - 1; y++){
                    for(int x = 1; x < getSize().width - 1; x++){
                        getBoard()[y][x] = FENCE;
                    }
                }
                getBoard()[getSize().height - 2][getSize().width - 2] = SPACE;
            }
        }
        Level level = new Level(new TestMap(), 2);
        Point point = level.getRandomFreeSpace();
        Set<Point> points = new HashSet<>();
        points.add(point);
        Stream.generate(level::getRandomFreeSpace).limit(1000).forEach(points::add);
        assertEquals(1, points.size());
    }
}