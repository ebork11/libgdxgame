package com.apcs.game.rooms;

import com.badlogic.gdx.graphics.Texture;

public class Door {
    private Texture closedDoor;
    private Texture openDoor;
    private Room nextRoom;

    private int xLoc, yLoc;

    public Door(Room definedRoom) {
        closedDoor = new Texture("core/assets/rooms/closeddoor.png");
        openDoor = new Texture("core/assets/rooms/opendoor.png");
        nextRoom = definedRoom;

        xLoc = 460;
        yLoc = 680;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public int getxLoc() {
        return xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public Texture getClosedTex() {
        return closedDoor;
    }

    public Texture getOpenTex() {
        return openDoor;
    }
}
