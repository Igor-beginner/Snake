package GameObjects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Home on 16.04.2023.
 */
public class SnakeTest {
    private Snake snake;
    private LevelManage levelManage;

    @Before
    public void init(){
        levelManage = Level.BEGINNER;
        snake = new Snake(levelManage);
        snake.spawn();
    }

    @Test
    public void grow(){
        System.out.println(levelManage.render());
    }
}