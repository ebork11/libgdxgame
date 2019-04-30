package com.apcs.game.rooms;

import com.apcs.game.rooms.DefaultRoom;
import com.apcs.game.rooms.Room;
import com.apcs.game.rooms.StartingRoom;

public class RoomManager {
    private int roomsExplored;

    private static Room startingRoom;
    private static Room currentRoom;

    public RoomManager() {
        roomsExplored = 0;
        startingRoom = new StartingRoom();
        currentRoom = startingRoom;
    }

    public int getRoomsExplored() {
        return roomsExplored;
    }

    public void increaseRoomsExplored() {
        roomsExplored++;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

}
