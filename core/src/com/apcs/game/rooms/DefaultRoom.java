package com.apcs.game.rooms;

import com.apcs.game.enemies.Entity;
import com.apcs.game.items.Item;
import com.apcs.game.object.Spike;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class DefaultRoom extends Room {
    private Texture myFloor;
    private ArrayList<Door> doors;
    private ArrayList<Entity> entities;
    private ArrayList<Spike> spikes;
    private ArrayList<Item> groundItems;

    public DefaultRoom() {
        myFloor = new Texture("core/assets/rooms/background1.png"); // sets texture
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        spikes = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();

        entities.add(new Entity());
        spikes.add(new Spike());
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

    public ArrayList<Spike> getHazards() {
        return spikes;
    }

    public ArrayList<Item> getGroundItems() {
        return groundItems;
    }
}
