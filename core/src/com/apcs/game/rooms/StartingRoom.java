package com.apcs.game.rooms;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class StartingRoom extends Room {
    private Texture myFloor;
    private ArrayList<Door> doors;

    public StartingRoom() {
        myFloor = new Texture("core/assets/rooms/background1.png"); // sets texture
        doors = new ArrayList<Door>();

        doors.add(new Door(new DefaultRoom(this), 460, 650));
    }

    public Texture getFloor() {
        return myFloor;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }
}
