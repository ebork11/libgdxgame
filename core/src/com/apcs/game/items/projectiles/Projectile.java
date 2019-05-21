package com.apcs.game.items.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Projectile {
    Rectangle coll;
    Texture tex;
    float myX, myY, speed;
    int dmg;
    String dir;

    public Projectile(float x, float y, int damage, float sped, String direction) {
        switch(direction) {
            case "up":
                tex = new Texture("items/projectile/pojectileu.png");
                break;
            case "down":
                tex = new Texture("items/projectile/projectiled.png");
                break;
            case "left":
                tex = new Texture("items/projectile/projectilel.png");
                break;
            case "right":
                tex = new Texture("items/projectile/projectiler.png");
                break;
            default:
                break;
        }

        coll = new Rectangle(x, y, tex.getWidth(), tex.getHeight());

        dmg = damage;
        myX = x;
        myY = y;
        speed = sped;
        dir = direction;
    }

    public Texture getTexture() {
        return tex;
    }

    public Rectangle getCollider() {
        return coll;
    }

    public float getMyX() {
        return myX;
    }

    public float getMyY() {
        return myY;
    }

    public int getDamage() {
        return dmg;
    }

    public float getSpeed() {
        return speed;
    }

    public String getDirection() {
        return dir;
    }
}
