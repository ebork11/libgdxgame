package com.apcs.game.rooms;

import com.apcs.game.enemies.Entity;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class StartingRoom extends Room {
    private Texture myFloor;
    private ArrayList<Door> doors;
    private static ArrayList<Entity> entities;

    public StartingRoom() {
        myFloor = new Texture("core/assets/rooms/background1.png"); // sets texture
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();

        doors.add(new Door(new DefaultRoom(this, "bottom"), "top"));
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
