package GameObjects;

import java.awt.*;

public interface Map {
    Designations FENCE = new Designations('#', -1);
    Designations SPACE = new Designations(' ', 0);

    Designations[][] getBoard();
    Dimension getSize();
}
