package GameObjects;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Home on 16.04.2023.
 */
public class AppleTest{

    private Apple apple;
    private LevelManage levelManage;
    private Point applePos;

    @Before
    public void init(){
        levelManage = Level.BEGINNER;
        applePos = levelManage.getRandomFreeSpace();
        apple = new Apple(applePos);
        levelManage.add(apple);
    }

    @Test
    public void generate(){
        Point currentPos = getBy(apple).get(0);
        assertEquals(applePos, currentPos);
        levelManage.removeObject(currentPos);
        assertEquals(0, getBy(apple).size());
        apple.updateLocation(currentPos, levelManage);
        assertTrue(apple.getPosition().equals(getBy(apple).get(0)));
    }


    private List<Point> getBy(GameObject go){
        return ((Level)levelManage).getPositionBy(go.getDesignations());
    }
}