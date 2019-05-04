package com.apcs.game.rooms;

import com.apcs.game.enemies.Charger;
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
        int choice = (int)(Math.random() * 3) + 1;
        switch (choice) {
            case 1:
                myFloor = new Texture("rooms/background1.png"); // sets texture
                break;
            case 2:
                myFloor = new Texture("rooms/background2.png"); // sets texture
                break;
            case 3:
                myFloor = new Texture("rooms/background3.png"); // sets texture
                break;
            default:
                myFloor = new Texture("rooms/background1.png"); // sets texture
                break;
        }

        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        spikes = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();

        entities.add(new Charger());
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
