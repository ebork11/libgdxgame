package com.apcs.game.rooms;

import com.apcs.game.enemies.Entity;
import com.apcs.game.items.weapons.Item;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.object.Spike;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class HealingRoom extends Room {

    private Texture myFloor;
    private Rectangle pool;
    private int health;
    private ArrayList<Door> doors;
    private ArrayList<Entity> entities;
    private ArrayList<Spike> spikes;
    private ArrayList<Item> groundItems;
    private ArrayList<Projectile> proj, enemProj;
    private int level = 1;

    public HealingRoom() {
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        spikes = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();
        proj = new ArrayList<Projectile>();
        enemProj = new ArrayList<Projectile>();
        health = 12;

        pool = new Rectangle(297, 105, 430,490);

        myFloor = new Texture("rooms/healingroom.png"); // sets texture
    }

    public HealingRoom(int temp) {
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        spikes = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();
        proj = new ArrayList<Projectile>();
        enemProj = new ArrayList<Projectile>();
        health = 12;

        pool = new Rectangle(297, 105, 430,490);

        myFloor = new Texture("rooms/healingroom.png"); // sets texture

        level = temp;
    }

    public Texture getFloor() {
        return myFloor;
    }

    public void setFloor(Texture tex) {
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

    public int getHealth(){return health;}

    public Rectangle getPool() {
        return pool;
    }

    public void setHealth() { health--; }

    public ArrayList<Item> getGroundItems() {
        return groundItems;
    }

    public ArrayList<Projectile> getProjectile() {
        return proj;
    }

    public ArrayList<Projectile> getEnemProj() {
        return enemProj;
    }

    @Override
    public int getRoomLevel() {
        return level;
    }
}
