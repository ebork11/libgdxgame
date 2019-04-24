package com.apcs.game;

import com.apcs.game.rooms.DefaultRoom;

public class RoomManager {
    private int roomsExplored;

    DefaultRoom startingRoom;
    DefaultRoom currentRoom;

    public RoomManager() {
        roomsExplored = 0;
        startingRoom = new DefaultRoom(null);
        currentRoom = startingRoom;
    }

    public int getRoomsExplored() {
        return roomsExplored;
    }

    public void increaseRoomsExplored() {
        roomsExplored++;
    }

    /*
        Logic to choose the next room when the player clears a room
     */
    public void nextRoom() {
        currentRoom = new DefaultRoom(currentRoom);
    }

    public DefaultRoom getCurrentRoom() {
        return currentRoom;
    }

}
