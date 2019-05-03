package com.apcs.game.enemies;

import com.apcs.game.GameMain;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

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
        collider = new Rectangle(100, 100, text.getWidth(), text.getHeight());

        //movement
        speed = 5f;

        // combat stuff
        strength = 1;
        health = 5;
        inColl = System.currentTimeMillis();
        cooldown = 1500;
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

    public void setCooldown(long temp){
        cooldown = temp;
    }

    public float getSpeed(){
        return speed;
    }

    public long getIncoll(){
        return inColl;
    }

    public void setInColl(long temp){
        inColl = temp;
    }

    public void setCharcater(String temp){
        text = new Texture(temp);
        collider = new Rectangle(100, 100, text.getWidth(),text.getHeight());
    }

    public void hit(int damage)  {
        System.out.print("Enemy hit ");
        health -= damage;
        if(health <= 0){
            die();
        }
    }

    public void attack() {
        if (System.currentTimeMillis() - inColl > cooldown) {
            inColl = System.currentTimeMillis();
            PlayerHandler.getCombat().takeDamage(strength);
            System.out.println("Attacking player");
        }

    }

    public void die() {
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

