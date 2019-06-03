package com.apcs.game.rooms;

import com.apcs.game.enemies.Entity;
import com.apcs.game.enemies.SpikeBoss;
import com.apcs.game.enemies.Wizard;
import com.apcs.game.items.Item;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.object.Spike;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class StartingRoom extends Room {
    private Texture myFloor;
    private ArrayList<Door> doors;
    private static ArrayList<Entity> entities;
    private static ArrayList<Spike> hazards;
    private ArrayList<Item> groundItems;
    private ArrayList<Projectile> proj, enemProj;

    public StartingRoom() {
        myFloor = new Texture("rooms/background1.png"); // sets texture
        doors = new ArrayList<Door>();
        entities = new ArrayList<Entity>();
        hazards = new ArrayList<Spike>();
        groundItems = new ArrayList<Item>();
        proj = new ArrayList<Projectile>();
        enemProj = new ArrayList<Projectile>();

        //entities.add(new SpikeBoss());
    }

    public Texture getFloor() {
        return myFloor;
    }

    public ArrayList<Spike> getHazards() {
        return hazards;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }

    public ArrayList<Projectile> getProjectile() { return proj; }

    public ArrayList<Projectile> getEnemProj() { return enemProj; }

    public ArrayList<Item> getGroundItems() {
        return groundItems;
    }
}
