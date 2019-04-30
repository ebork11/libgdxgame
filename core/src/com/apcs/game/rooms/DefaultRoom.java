package com.apcs.game.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class DefaultRoom extends Room {
    private Texture myFloor;
    private ArrayList<Door> doors;


    public DefaultRoom() {
        myFloor = new Texture("core/assets/rooms/background1.png"); // sets texture
        doors = new ArrayList<Door>();
    }

    public DefaultRoom(Room pastRoom) {
        myFloor = new Texture("core/assets/rooms/background1.png"); // sets texture
        doors = new ArrayList<Door>();

        doors.add(new Door(pastRoom));
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }
}
