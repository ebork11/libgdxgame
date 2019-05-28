package com.apcs.game.rooms;

import com.apcs.game.enemies.Entity;
import com.apcs.game.items.Item;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.object.Spike;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class HealingRoom extends Room {

    private Texture myFloor, healthPool, armorRepair;
    public static Rectangle arm, pool;
    public static int health;
    private ArrayList<Door> doors;
    private ArrayList<Entity> entities;
    private ArrayList<Spike> spikes;
    private ArrayList<Item> groundItems;
    private ArrayList<Projectile> proj, enemProj;

    public HealingRoom() {
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        spikes = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();
        proj = new ArrayList<Projectile>();
        enemProj = new ArrayList<Projectile>();
        health = 10;

        armorRepair = new Texture("objects/armorRepair.png");
        arm = new Rectangle(200, 200, armorRepair.getWidth(), armorRepair.getHeight());

        healthPool = new Texture("objects/pool.png");
        pool = new Rectangle(300, 200, healthPool.getWidth(),healthPool.getHeight());

        myFloor = new Texture("rooms/backgroundHealth.png"); // sets texture


    }

    public Texture getFloor() {
        return myFloor;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Spike> getHazards() {
        return spikes;
    }

    public ArrayList<Item> getGroundItems() {
        return groundItems;
    }

    public ArrayList<Projectile> getProjectile() {
        return proj;
    }

    public ArrayList<Projectile> getEnemProj() {
        return enemProj;
    }

}
