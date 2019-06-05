package com.apcs.game.rooms;

import com.apcs.game.enemies.level1.Basic;
import com.apcs.game.enemies.level1.Charger;
import com.apcs.game.enemies.Entity;
import com.apcs.game.enemies.level1.Wizard;
import com.apcs.game.items.weapons.Item;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.object.Spike;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class DefaultRoom extends Room {
    private Texture myFloor;
    private ArrayList<Door> doors;
    private ArrayList<Entity> entities;
    private ArrayList<Spike> spikes;
    private ArrayList<Item> groundItems;
    private ArrayList<Projectile> proj, enemProj;

    public DefaultRoom() {
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        spikes = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();
        proj = new ArrayList<Projectile>();
        enemProj = new ArrayList<Projectile>();


        int choice = (int)(Math.random() * 3) + 1;
        switch (choice) {
            case 1:
                myFloor = new Texture("rooms/background1.png"); // sets texture
                spikes.add(new Spike(140, 40));
                spikes.add(new Spike(450, 320));
                spikes.add(new Spike(500, 360));
                spikes.add(new Spike(450, 360));
                spikes.add(new Spike(500, 320));
                entities.add(new Basic());
                entities.add(new Basic());
                entities.add(new Wizard());
                entities.add(new Wizard());
                break;
            case 2:
                myFloor = new Texture("rooms/background2.png"); // sets texture
                entities.add(new Charger());
                entities.add(new Charger());
                entities.add(new Charger());
                entities.add(new Charger());
                spikes.add(new Spike(800, 580));
                break;
            case 3:
                myFloor = new Texture("rooms/background3.png"); // sets texture
                spikes.add(new Spike(130, 580));
                spikes.add(new Spike(450, 320));
                spikes.add(new Spike(500, 360));
                spikes.add(new Spike(500, 320));
                entities.add(new Charger());
                entities.add(new Basic());
                entities.add(new Wizard());
                break;
            default:
                myFloor = new Texture("rooms/background1.png"); // sets texture
                entities.add(new Charger());
                entities.add(new Basic());
                entities.add(new Wizard());
                spikes.add(new Spike(450, 360));
                spikes.add(new Spike(780, 40));
                break;
        }
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

    public ArrayList<Projectile> getProjectile() { return proj; }

    public ArrayList<Projectile> getEnemProj() { return enemProj; }

    public ArrayList<Item> getGroundItems() {
        return groundItems;
    }

    @Override
    public int getRoomLevel() {
        return 1;
    }
}
