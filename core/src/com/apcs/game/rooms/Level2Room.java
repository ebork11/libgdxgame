package com.apcs.game.rooms;

import com.apcs.game.enemies.*;
import com.apcs.game.enemies.level1.Basic;
import com.apcs.game.enemies.level1.Charger;
import com.apcs.game.enemies.level1.Wizard;
import com.apcs.game.enemies.level2.Archer;
import com.apcs.game.enemies.level2.Teleporter;
import com.apcs.game.items.weapons.Item;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.object.Spike;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Level2Room extends Room {

    private Texture myFloor;
    private ArrayList<Door> doors;
    private ArrayList<Entity> entities;
    private ArrayList<Spike> spikes;
    private ArrayList<Item> groundItems;
    private ArrayList<Projectile> proj, enemProj;

    public Level2Room() {
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        spikes = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();
        proj = new ArrayList<Projectile>();
        enemProj = new ArrayList<Projectile>();

        myFloor = new Texture("rooms/background1.png"); // sets texture

        int choice = (int)(Math.random() * 3);

        switch(choice) {
            case 0:
                entities.add(new Archer());
                entities.add(new Archer());
                entities.add(new Charger());
                entities.add(new Basic());
                entities.add(new Basic());
                break;
            case 1:
                entities.add(new Teleporter());
                entities.add(new Basic());
                entities.add(new Basic());
                entities.add(new Basic());
                entities.add(new Charger());
                break;
            default:
                entities.add(new Archer());
                entities.add(new Teleporter());
                entities.add(new Teleporter());
                entities.add(new Wizard());
                entities.add(new Archer());
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
        return 2;
    }
}
