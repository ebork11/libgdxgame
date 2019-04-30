package com.apcs.game;

import com.apcs.game.rooms.DefaultRoom;

public class RoomManager {
    private int roomsExplored;

    private static DefaultRoom startingRoom;
    private static DefaultRoom currentRoom;

    public RoomManager() {
        roomsExplored = 0;
        startingRoom = new DefaultRoom();
        currentRoom = startingRoom;
    }

    public int getRoomsExplored() {
        return roomsExplored;
    }

    public void increaseRoomsExplored() {
        roomsExplored++;
    }

    public void setCurrentRoom(DefaultRoom room) {
        currentRoom = room;
    }

    public static DefaultRoom getCurrentRoom() {
        return currentRoom;
    }

}
