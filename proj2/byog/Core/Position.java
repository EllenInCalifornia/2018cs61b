package byog.Core;

import java.util.Random;

public class Position {
    int x;
    int y;
    public Position(int a, int b) {
        x = a;
        y = b;
    }

    public static Position randomPos(Random r, int x_b1, int x_b2, int y_b1, int y_b2) {
        int x = RandomUtils.uniform(r, x_b1, x_b2);
        int y = RandomUtils.uniform(r, y_b1, y_b2);
        Position p = new Position(x, y);
        return p;
    }
}
