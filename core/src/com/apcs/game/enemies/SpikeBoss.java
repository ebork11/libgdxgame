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
    private Rectangle collider;
    private boolean isHit;
    private float speed;
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

        speed = 15f;
        x = 500;
        y = 500;

        collider = new Rectangle(x, y, text.getWidth(),text.getHeight());
    }

    public void move(){

    }

    public void walk(float x, float y){

        double ang1 = Math.atan(y / x);
        double ang2 = Math.atan(x / y);
        float sx = (float)(speed * (Math.sin(ang2)));
        float sy = (float)(speed * (Math.sin(ang1)));

        sx = Math.abs(sx);
        sy = Math.abs(sy);

        if (x > 0 && y > 0) {
            left = false;
            if (collider.x + getTexture().getWidth() < 960) {
                collider.x += sx;
            } if (collider.y < 610) {
                collider.y += sy;
            }
        } else if (x < 0 && y > 0) {
            left = true;
            if (collider.x > 40) {
                collider.x -= sx;
            } if (collider.y < 610) {
                collider.y += sy;
            }
        }  else if (x < 0 && y < 0) {
            left = true;
            if (collider.x > 40) {
                collider.x -= sx;
            } if (collider.y > 40) {
                collider.y -= sy;
            }
        }  else if (x > 0 && y < 0) {
            left = false;
            if (collider.x + getTexture().getWidth() < 960) {
                collider.x += sx;
            } if (collider.y > 40) {
                collider.y -= sy;
            }
        }
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
