package com.apcs.game.rooms;

import com.apcs.game.enemies.Entity;
import com.apcs.game.items.Item;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.object.Spike;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class HealingRoom extends Room {

    private static Texture myFloor;
    public static Rectangle pool;
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
        health = 8;

        pool = new Rectangle(297, 105, 430,490);

        myFloor = new Texture("rooms/healingroom.png"); // sets texture
    }

    public Texture getFloor() {
        return myFloor;
    }

    public static void setFloor(Texture tex) {
        myFloor = tex;
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
