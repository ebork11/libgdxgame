package com.apcs.game.rooms;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Room {
    private Texture myFloor;
    private ArrayList<Door> doors;

    // width is 1000, height is 720
    public Room() {
        myFloor = new Texture("core/assets/rooms/background1.png");
    }

    public Texture getFloor() {
        return myFloor;
    }

    public void disposer() {
        myFloor.dispose();
    }
}
