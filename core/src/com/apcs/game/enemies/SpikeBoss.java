package com.apcs.game.enemies;

import com.apcs.game.items.*;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class SpikeBoss extends Entity {

    private Texture text;

    // combat stuff
    private int health, strength;
    private long inColl;
    private long cooldown;
    private Rectangle col;
    private boolean isHit;
    private int x, y;

    SpikeBoss(){
        super();
        // basics
        text = new Texture("clark.png");

        // combat stuff
        strength = 2;
        health = 50;
        isHit = false;
        inColl = System.currentTimeMillis();
        cooldown = 1250;

        x = 500;
        y = 500;

        col = new Rectangle(x, y, text.getWidth(),text.getHeight());
    }

    public void move(){

    }

    public Rectangle getCollider() {return col;}

    public void dropItems(){
        ArrayList<Item> droppable = new ArrayList<Item>();

        droppable.add(new Armor());
        droppable.add(new Dagger());
        droppable.add(new Bow());
        droppable.add(new FatSword());
        droppable.add(new Wand());

        Item temp = droppable.get((int)(Math.random() * droppable.size()));

        temp.getCollider().setPosition(col.x, col.y);

        RoomManager.getCurrentRoom().getGroundItems().add(temp);
    }


    public boolean isHit(){
        return isHit;
    }

    public void nowHit(){
        isHit = !isHit;
    }

    public Texture getHitTex(){
        return text;
    }

}
