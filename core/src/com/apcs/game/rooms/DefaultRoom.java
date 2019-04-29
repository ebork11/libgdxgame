package com.apcs.game.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class DefaultRoom {
    private Texture myFloor;
    private DefaultRoom nextRoom;
    private DefaultRoom previousRoom;



    // width is 1200, height is 600
    public DefaultRoom(DefaultRoom previous) {
        myFloor = new Texture("core/assets/rooms/background1.png");

        nextRoom = null;
        previousRoom = previous;

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
