package com.apcs.game.rooms;

import com.badlogic.gdx.graphics.Texture;

public class DefaultRoom {
    private Texture myFloor;
    private DefaultRoom nextRoom;
    private DefaultRoom previousRoom;

    public DefaultRoom(DefaultRoom previous) {
        myFloor = new Texture("core/assets/Drawing.jpeg");

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
