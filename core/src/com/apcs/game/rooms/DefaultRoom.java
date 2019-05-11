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
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        spikes = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();

        int choice = (int)(Math.random() * 3) + 1;
        switch (choice) {
            case 1:
                myFloor = new Texture("rooms/background1.png"); // sets texture
                spikes.add(new Spike(40, 40)); // 4 corners and 4 in middle
                spikes.add(new Spike(40, 640));
                spikes.add(new Spike(910, 40));
                spikes.add(new Spike(910, 640));
                spikes.add(new Spike(450, 320));
                spikes.add(new Spike(500, 360));
                spikes.add(new Spike(450, 360));
                spikes.add(new Spike(500, 320));
                entities.add(new Charger());
                entities.add(new Entity());
                break;
            case 2:
                myFloor = new Texture("rooms/background2.png"); // sets texture
                entities.add(new Charger());
                entities.add(new Entity());
                entities.add(new Entity());
                break;
            case 3:
                myFloor = new Texture("rooms/background3.png"); // sets texture
                spikes.add(new Spike(450, 320));
                spikes.add(new Spike(500, 360));
                spikes.add(new Spike(450, 360));
                spikes.add(new Spike(500, 320));
                entities.add(new Charger());
                entities.add(new Entity());
                break;
            default:
                myFloor = new Texture("rooms/background1.png"); // sets texture
                entities.add(new Charger());
                entities.add(new Entity());
                break;
        }

        entities.add(new Charger());

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
