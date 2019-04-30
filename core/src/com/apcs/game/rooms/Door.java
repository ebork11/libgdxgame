package com.apcs.game.rooms;

import com.apcs.game.RoomManager;
import com.badlogic.gdx.graphics.Texture;

public class Door {
    private Texture closedDoor;
    private Texture openDoor;
    private DefaultRoom nextRoom;

    public Door() {
        closedDoor = new Texture("core/assets/rooms/closeddoor.png");
        openDoor = new Texture("core/assets/rooms/opendoor.png");
        nextRoom = null;
    }

    public Door(DefaultRoom definedRoom) {
        closedDoor = new Texture("core/assets/rooms/closeddoor.png");
        openDoor = new Texture("core/assets/rooms/opendoor.png");
        nextRoom = definedRoom;
    }

}
