package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldofEllen {
    private final int Width;
    private final int Height;
    // 应该是final？private
    private TETile[][] world;
    private final TERenderer ter = new TERenderer();
    private Random r;
    private Room[] rooms;

    //constructor
    public WorldofEllen(int seed, int w, int h) {
        Width = w;
        Height = h;
        r = new Random(seed);
        ter.initialize(w, h);
        int n = RandomUtils.uniform(r, 3, 15);
        rooms = new Room[n];
        world = new TETile[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }
    public void showWorld() {
        randomRoomsGenerator();
        noLappingRoomsGenerator();
        drawRooms();
        ter.renderFrame(world);
    }
    /*
    draw a vertical line at starting point start_p, with length l
     */
    public void drawVerticalLine(Position start_p, int l) {
        for (int i = start_p.y; i < start_p.y + l; i++) {
            world[start_p.x][i] = Tileset.WALL;
        }
    }


    /*
    draw a horizontal line at starting point start_p, with length l
     */
    public void drawHorizontalLine(Position start_p, int l) {
        for (int i = start_p.x; i < start_p.x + l; i++) {
            world[i][start_p.y] = Tileset.WALL;
        }
    }

    public void drawSquare(Position p, int w, int h, TETile t) {
        for (int x = p.x; x < p.x + w; x++) {
            for (int y = p.y; y < p.y + h; y++) {
                world[x][y] = t;
            }
        }
    }

    public Room randomRoomGen() {
        int w = RandomUtils.uniform(r, Width / 20, Width / 3);
        int h = RandomUtils.uniform(r, Height / 20, Height / 3);
        Position p = Position.randomPos(r, 0, Width - w - 6, Height / 5, Height - h - 6);
        Room room = new Room(p, w, h);
        return room;
    }

    /*
    generate random rooms
     */
    public void randomRoomsGenerator() {
        for (int i = 0; i < rooms.length; i++) {
            rooms[i] = randomRoomGen();
        }
    }

    public void noLappingRoomsGenerator() {
        while (Room.roomsLap(rooms)) {
           randomRoomsGenerator();
        }
    }

    /*
    draw a room
     */
    public void drawOneRoom(Room r) {
       /*
       draw outer wall
        */
        Position p = r.p;
        int h = r.room_height;
        int w = r.room_width;
        drawVerticalLine(p, h + 2);
        drawHorizontalLine(p, w + 2);
        drawHorizontalLine(new Position(p.x, p.y + h + 1), w +2);
        drawVerticalLine(new Position(p.x + w + 1, p.y), h + 2);
        /*
        draw the floor of the wall using grass
         */
        drawSquare(new Position(p.x + 1, p.y + 1), w, h, Tileset.GRASS);
    }


    public void drawRooms() {
        for (Room r: rooms) {
            drawOneRoom(r);
        }
        }



    // String[] 和args之间没有,
    public static void main(String[] args) {
        WorldofEllen world1 = new WorldofEllen(440, 100, 60);
        world1.showWorld();

    }




}
