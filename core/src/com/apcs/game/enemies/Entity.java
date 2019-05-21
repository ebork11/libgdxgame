package com.apcs.game.enemies;

import com.apcs.game.GameMain;
import com.apcs.game.items.Armor;
import com.apcs.game.items.FatSword;
import com.apcs.game.items.HealthPotion;
import com.apcs.game.items.Item;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public abstract class Entity {
    // basics

    private Texture text;

    //movement


    // combat stuff
    private int health;
    private int strength;
    private long inColl;
    private long cooldown;

    public Entity()  {

        // basics
        text = new Texture("clark.png");




        // combat stuff
        strength = 1;
        health = 10;
        inColl = System.currentTimeMillis();
        cooldown = 1250;
    }

    public abstract Rectangle getCollider();

    public Texture getTexture()  {
        return text;
    }

    public void setStrength(int temp) {
        strength = temp;
    }

    public void setHealth(int temp){
        health = temp;
    }

    public void setCharacterFirst(String temp){
        text = new Texture(temp);
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

    public abstract void dropItems();

    public void die() {
        dropItems();
        RoomManager.getCurrentRoom().getEntities().remove(this);
    }
    public abstract void move();
}

