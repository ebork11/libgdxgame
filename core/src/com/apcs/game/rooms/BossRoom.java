package com.apcs.game.rooms;

import com.apcs.game.enemies.Entity;
import com.apcs.game.enemies.level1.SpikeBoss;
import com.apcs.game.enemies.level1.Wizard;
import com.apcs.game.enemies.level2.SpikeBossT2;
import com.apcs.game.items.weapons.Item;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.object.Spike;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class BossRoom extends Room {

    private Texture myFloor;
    private ArrayList<Door> doors;
    private ArrayList<Entity> entities;
    private ArrayList<Spike> hazards;
    private ArrayList<Item> groundItems;
    private ArrayList<Projectile> proj, enemProj;
    private int level = 1;

    public BossRoom() {
        myFloor = new Texture("rooms/background1.png"); // sets texture
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        hazards = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();
        proj = new ArrayList<Projectile>();
        enemProj = new ArrayList<Projectile>();

        entities.add(new SpikeBoss());
    }

    public BossRoom(int temp) {
        myFloor = new Texture("rooms/background1.png"); // sets texture
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        hazards = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();
        proj = new ArrayList<Projectile>();
        enemProj = new ArrayList<Projectile>();

        entities.add(new SpikeBossT2());
        entities.add(new Wizard());
        entities.add(new Wizard());

        level = temp;
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
        return hazards;
    }

    public ArrayList<Projectile> getProjectile() { return proj; }

    public ArrayList<Projectile> getEnemProj() { return enemProj; }

    public ArrayList<Item> getGroundItems() {
        return groundItems;
    }

    @Override
    public int getRoomLevel() {
        return level;
    }
}
