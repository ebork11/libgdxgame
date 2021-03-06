package com.apcs.game.rooms;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Door {
    private Texture closedDoor;
    private Texture openDoor, boss, bossopen;
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
                yLoc = 670;
                closedDoor = new Texture("rooms/doors/closeddoorT.png");
                openDoor = new Texture("rooms/doors/opendoorT.png");
                boss = new Texture("rooms/doors/closeddoorTboss.png");
                bossopen = new Texture("rooms/doors/opendoorTboss.png");
                break;
            case "bottom":
                xLoc = 460;
                yLoc = 10;
                closedDoor = new Texture("rooms/doors/closeddoorB.png");
                openDoor = new Texture("rooms/doors/opendoorB.png");
                boss = new Texture("rooms/doors/closeddoorBboss.png");
                bossopen = new Texture("rooms/doors/opendoorBboss.png");
                break;
            case "left":
                xLoc = 5;
                yLoc = 360;
                closedDoor = new Texture("rooms/doors/closeddoorL.png");
                openDoor = new Texture("rooms/doors/opendoorL.png");
                boss = new Texture("rooms/doors/closeddoorLboss.png");
                bossopen = new Texture("rooms/doors/opendoorLboss.png");
                break;
            case "right":
                xLoc = 955;
                yLoc = 360;
                closedDoor = new Texture("rooms/doors/closeddoorR.png");
                openDoor = new Texture("rooms/doors/opendoorR.png");
                boss = new Texture("rooms/doors/closeddoorRboss.png");
                bossopen = new Texture("rooms/doors/opendoorRboss.png");
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

    public Texture getBossDoor() {
        return boss;
    }

    public Texture getBossDoorOpen() {
        return bossopen;
    }
}
