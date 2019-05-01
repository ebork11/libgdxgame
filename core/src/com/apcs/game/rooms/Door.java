package com.apcs.game.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Door {
    private Texture closedDoor;
    private Texture openDoor;
    private Room nextRoom;
    Rectangle collider;
    private String loca;

    private int xLoc, yLoc;

    public Door(Room definedRoom, String loc) {
        nextRoom = definedRoom;
        loca = loc;

        switch (loc) {
            case "top":
                xLoc = 460;
                yLoc = 650;
                closedDoor = new Texture("core/assets/rooms/doors/closeddoorT.png");
                openDoor = new Texture("core/assets/rooms/doors/opendoorT.png");
                break;
            case "bottom":
                xLoc = 460;
                yLoc = 30;
                closedDoor = new Texture("core/assets/rooms/doors/closeddoorB.png");
                openDoor = new Texture("core/assets/rooms/doors/opendoorB.png");
                break;
            case "left":
                closedDoor = new Texture("core/assets/rooms/doors/closeddoorL.png");
                openDoor = new Texture("core/assets/rooms/doors/opendoorL.png");
                break;
            case "right":
                closedDoor = new Texture("core/assets/rooms/doors/closeddoorR.png");
                openDoor = new Texture("core/assets/rooms/doors/opendoorR.png");
                break;
            default:
                break;
        }

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

    public String getLocation() {
        return loca;
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
