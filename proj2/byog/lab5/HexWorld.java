package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


/**
 * Draws a world consisting of hexagonal regions.
 */


public class HexWorld {
    private final int WIDTH;
    private final int HEIGHT;
    TETile[][] world;
    private final Random r = new Random(2221);

    public HexWorld(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        //如果不加下面这一行，将会是一片黑
        world = new TETile[w][h];
    }

    public static class Position {
        int x;
        int y;

        public Position(int a, int b) {
            x = a;
            y = b;
        }
    }

    public void triagle_left_up(Position p, int size, TETile t) {
        for (int x = p.x; x < p.x + size; x++) {
            for (int y = p.y; y <= x - p.x + p.y; y++) {
                world[x][y] = t;
            }
        }
    }

    public Position p_left_up(Position p, int size) {
        Position plu = new Position(p.x, p.y + size);
        return plu;
    }

    public Position p_right_up(Position p, int size) {
        Position pru = new Position(p.x + 3 * size - 3, p.y + size);
        return pru;
    }

    public Position p_right_low(Position p, int size) {
        Position prl = new Position(p.x + 3 * size - 3, p.y + size - 1);
        return prl;
    }

    public Position p_left_low(Position p, int size) {
        Position pll = new Position(p.x, p.y + size - 1);
        return pll;
    }

    public Position p_sq(Position p, int size) {
        Position psq = new Position(p.x + size, p.y);
        return psq;
    }

    public void triagle_right_up(Position p, int size, TETile t) {
        for (int x = p.x; x > p.x - size; x--) {
            for (int y = p.y; y <= p.x + p.y - x; y++) {
                world[x][y] = t;
            }
        }
    }

    public void triagle_right_low(Position p, int size, TETile t) {
        for (int x = p.x; x > p.x - size; x--) {
            for (int y = p.y; y >= x - p.x + p.y; y--) {
                world[x][y] = t;
            }
        }
    }

    public void triagle_left_low(Position p, int size, TETile t) {
        for (int x = p.x; x < p.x + size; x++) {
            for (int y = p.y; y >= p.x + p.y - x; y--) {
                world[x][y] = t;
            }
        }
    }

    public void square(Position p, int size, TETile t) {
        for (int x = p.x; x < p.x + size - 2; x++) {
            for (int y = p.y; y < p.y + 2 * size; y++) {
                world[x][y] = t;
            }
        }
    }


    public void addHexagon(int size, Position p, TETile t) {
        // p specifies the lower left corner of the square of the hexagon
        Position plu = p_left_up(p, size);
        Position pru = p_right_up(p, size);
        Position pll = p_left_low(p, size);
        Position prl = p_right_low(p, size);
        Position psq = p_sq(p, size);
        triagle_left_up(plu, size, t);
        triagle_right_up(pru, size, t);
        triagle_left_low(pll, size, t);
        triagle_right_low(prl, size, t);
        square(psq, size, t);
    }

    public Position bottom_left(Position p, int size) {
        Position p_bl = new Position(p.x - 2 * size + 1, p.y - size);
        return p_bl;
    }

    public Position top_left(Position p, int size) {
        Position p_tl = new Position(p.x - 2 * size + 1, p.y + size);
        return p_tl;
    }

    public Position bottom_right(Position p, int size) {
        Position p_br = new Position(p.x + 2 * size - 1, p.y - size);
        return p_br;
    }

    public Position top_right(Position p, int size) {
        Position p_tr = new Position(p.x + 2 * size - 1, p.y + size);
        return p_tr;
    }



    public TETile randomTetile() {
        int tileNum = r.nextInt(3);
        switch (tileNum) {
            case 0:
                return Tileset.GRASS;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.WALL;
            default: return Tileset.NOTHING;

        }
    }

    public void drawRandomVerticalHex (Position p,int n, int size) {

       for (int i = 1; i <= n; i++) {
           addHexagon(size, p, randomTetile());
           p.y -= 2 * size;
       }
        }
    public Position bottom(Position p, int size) {
        Position p_b = new Position(p.x, p.y - 2 * size);
        return p_b;
    }
        public Position top(Position p,int size){
            Position p_top = new Position(p.x, p.y + 2 * size);
            return p_top;
        }


        public static void main(String[] args){
            HexWorld my_hexWord = new HexWorld(100, 100);
            Position position = new Position(10, 80);
            TERenderer ter = new TERenderer();
            ter.initialize(my_hexWord.WIDTH, my_hexWord.HEIGHT);
            for (int x = 0; x < my_hexWord.WIDTH; x++) {
                for (int y = 0; y < my_hexWord.HEIGHT; y++) {
                    my_hexWord.world[x][y] = Tileset.NOTHING;
                }
            }
            //my_hexWord.addHexagon(4, position, Tileset.FLOWER);
            Position col2 = my_hexWord.top_right(position, 4);
            Position col3 = my_hexWord.top_right(col2, 4);
            Position col4 = my_hexWord.bottom_right(col3, 4);
            Position col5 = my_hexWord.bottom_right(col4, 4);
            my_hexWord.drawRandomVerticalHex(position, 3, 4);
            my_hexWord.drawRandomVerticalHex(col2,4, 4);
            my_hexWord.drawRandomVerticalHex(col3,5, 4);
            my_hexWord.drawRandomVerticalHex(col4,4, 4);
            my_hexWord.drawRandomVerticalHex(col5,3, 4);

            ter.renderFrame(my_hexWord.world);

        }

    }

