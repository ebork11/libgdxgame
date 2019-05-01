package com.apcs.game.rooms;

import com.apcs.game.enemies.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class DefaultRoom extends Room {
    private Texture myFloor;
    private ArrayList<Door> doors;
    private static ArrayList<Entity> entities;

    public DefaultRoom() {
        myFloor = new Texture("core/assets/rooms/background2.png"); // sets texture
        doors = new ArrayList<Door>();
    }

    public DefaultRoom(Room pastRoom, String loc) {
        myFloor = new Texture("core/assets/rooms/background2.png"); // sets texture
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();

        entities.add(new Entity());

        doors.add(new Door(pastRoom, loc));
    }

    public Texture getFloor() {
        return myFloor;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }
}
