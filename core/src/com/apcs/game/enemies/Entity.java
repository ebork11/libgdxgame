package com.apcs.game.enemies;

import com.apcs.game.GameMain;
import com.apcs.game.items.Armor;
import com.apcs.game.items.FatSword;
import com.apcs.game.items.Item;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Entity {
    // basics
    private Rectangle collider;
    private Texture text;

    //movement
    private float speed;

    // combat stuff
    private int health;
    private int strength;
    private long inColl;
    private long cooldown;

    public Entity()  {


        // basics
        text = new Texture("clark.png");
        collider = new Rectangle(100, 100, 100, 100);

        //movement
        speed = 4f;

        // combat stuff
        strength = 1;
        health = 5;
        inColl = System.currentTimeMillis();
        cooldown = 1250;

        int x = (int)(Math.random() * 650) + 200;
        int y = (int)(Math.random() * 400) + 200;

        getCollider().x = x;
        getCollider().y = y;
    }

    public Rectangle getCollider()  {
        return collider;
    }

    public Texture getTexture()  {
        return text;
    }

    public void setStrength(int temp) {
        strength = temp;
    }

    public void setHealth(int temp){
        health = temp;
    }

    public void setSpeed(float temp){
        speed = temp;
    }

    public float getSpeed(){
        return speed;
    }


    public void setCharacterFirst(String temp){
        text = new Texture(temp);
        collider = new Rectangle(100, 100, text.getWidth(),text.getHeight());
    }

    public void setCharacter(String temp){
        text = new Texture(temp);
    }

    public void hit(int damage)  {
        health -= damage;
        if(health <= 0){
            die();
        }
    }

    public void attack() {
        if (System.currentTimeMillis() - inColl > cooldown) {
            inColl = System.currentTimeMillis();
            PlayerHandler.getCombat().takeDamage(strength);
        }
    }

    public void dropItems() {
        ArrayList<Item> droppable = new ArrayList<Item>();

        if (droppable.size() == 0) {
            droppable.add(new Armor());
            droppable.add(new FatSword());
        }

        Item temp = droppable.get((int)(Math.random() * droppable.size()));

        temp.getCollider().setPosition(collider.x, collider.y);

        RoomManager.getCurrentRoom().getGroundItems().add(temp);
    }

    public void die() {
        dropItems();
        RoomManager.getCurrentRoom().getEntities().remove(this);
    }

    public void move() {
        float x = PlayerHandler.getCollider().x - collider.x;
        float y = PlayerHandler.getCollider().y - collider.y;
        double ang1 = Math.atan(y / x);
        double ang2 = Math.atan(x / y);
        float sx = (float)(speed*(Math.sin(ang2)));
        float sy = (float)(speed*(Math.sin(ang1)));

        sx = Math.abs(sx);
        sy = Math.abs(sy);

        if (x > 0 && y > 0) {
            collider.x += sx;
            collider.y += sy;
        } else if (x < 0 && y > 0) {
            collider.x -= sx;
            collider.y += sy;
        }  else if (x < 0 && y < 0) {
            collider.x -= sx;
            collider.y -= sy;
        }  else if (x > 0 && y < 0) {
            collider.x += sx;
            collider.y -= sy;
        }

    }
}

