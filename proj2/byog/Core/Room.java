package byog.Core;

import javax.print.attribute.standard.MediaSize;

public class Room {
    /*
    p: the left-bottom position
     */
    Position p;
    int room_width;
    int room_height;
    public Room(Position position, int w, int h) {
        p = position;
        room_width = w;
        room_height = h;
    }

    public static Boolean leftRoom(Room r1, Room r2) {
        if (r1.p.x < r2.p.x) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean bottomRoom(Room r1, Room r2) {
        // bottom side
        if (r1.p.y < r2.p.y) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isLap(Room r1, Room r2) {
        Room l_room;
        Room r_room;
        Room b_room;
        Room t_room;
        if (Room.leftRoom(r1, r2)) {
            l_room = r1;
            r_room = r2;
        } else {
            l_room = r2;
            r_room = r1;
        }
        if (Room.bottomRoom(r1, r2)) {
            b_room = r1;
            t_room = r2;
        } else {
            b_room = r2;
            t_room = r1;
        }
        if (l_room.p.x + l_room.room_width + 2 >= r_room.p.x && b_room.p.y + b_room.room_height +2 >= t_room.p.y) {
            return true;
        }
        return false;
    }
    public static Boolean roomsLap(Room[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = i + 1; j < rooms.length; j++) {
                if (Room.isLap(rooms[i], rooms[j])) {
                    return true;
                }
            }
        }
        return false;
    }
}

