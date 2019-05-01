package com.apcs.game.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Door {
    private Texture closedDoor;
    private Texture openDoor;
    private Room nextRoom;
    Rectangle collider;

    private int xLoc, yLoc;

    public Door(Room definedRoom, int x, int y) {
        closedDoor = new Texture("core/assets/rooms/closeddoor.png");
        openDoor = new Texture("core/assets/rooms/opendoor.png");
        nextRoom = definedRoom;

        xLoc = x;
        yLoc = y;

        collider = new Rectangle(xLoc, yLoc, openDoor.getWidth(), openDoor.getHeight());
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

    public Rectangle getCollider() {
        return collider;
    }
}
