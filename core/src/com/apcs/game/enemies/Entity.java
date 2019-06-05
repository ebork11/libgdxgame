package com.apcs.game.enemies;

import com.apcs.game.GameMain;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
    // basics

    private Texture text;

    //movement
    private Sound hurt;

    // combat stuff
    private int health, strength;
    private long inColl;
    private long cooldown;

    public Entity()  {

        // basics
        text = new Texture("clark.png");

        hurt = Gdx.audio.newSound(Gdx.files.internal("sounds/smack.mp3"));

        // combat stuff
        strength = 1;
        health = 6;
        inColl = System.currentTimeMillis();
        cooldown = 1250;
    }

    public void setHurt(Sound temp) {
        hurt = temp;
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
        hurt.play(1.0f);
        nowHit();
        health -= damage;
        if(health <= 0){
            die();
        }
    }

    public void attack() {
        if (System.currentTimeMillis() - GameMain.enteredNewRoom > 1000) {
            if (System.currentTimeMillis() - inColl > cooldown) {
                inColl = System.currentTimeMillis();
                PlayerHandler.getCombat().takeDamage(strength);
            }
        }
    }

    public abstract void dropItems();

    public abstract boolean isHit();

    public abstract void nowHit();

    public abstract Texture getHitTex();

    public void die() {
        dropItems();
        RoomManager.getCurrentRoom().getEntities().remove(this);
    }

    public abstract void move();
}

