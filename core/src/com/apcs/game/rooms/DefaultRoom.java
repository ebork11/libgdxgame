package com.apcs.game.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class DefaultRoom {
    private Texture myFloor;
    private ArrayList<Door> doors;


    // width is 1200, height is 600
    public DefaultRoom() {
        myFloor = new Texture("core/assets/rooms/background1.png");

        doors.add(new Door(new DefaultRoom(this)));

    }

    public DefaultRoom(DefaultRoom pastRoom) {
        myFloor = new Texture("core/assets/rooms/background1.png");

        doors.add(new Door(pastRoom));
    }

    public Texture getFloor() {
        return myFloor;
    }

    /*
        Trash dump for the room textures
     */
    public void disposer() {
        myFloor.dispose();
    }



}
