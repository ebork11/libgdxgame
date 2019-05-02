package com.apcs.game.rooms;

import com.apcs.game.rooms.DefaultRoom;
import com.apcs.game.rooms.Room;
import com.apcs.game.rooms.StartingRoom;

public class RoomManager {
    private LevelGeneration lg;
    private static Room currentRoom;

    public RoomManager() {
        lg = new LevelGeneration();


    }

    public static void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

}
