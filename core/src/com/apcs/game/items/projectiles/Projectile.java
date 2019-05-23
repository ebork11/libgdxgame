package com.apcs.game.items.projectiles;

import com.apcs.game.enemies.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Projectile {
    Rectangle coll;
    Texture tex;
    float myX, myY, speed, initX, initY;
    int dmg, range;
    String dir;
    ArrayList<Entity> enem = new ArrayList<Entity>();

    public Projectile(float x, float y, int damage, float sped, String direction, int rng) {
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
        initX = myX;
        initY = myY;
        range = rng;
    }

    public boolean checkCanHit(Entity temp) {
        for (int cnt = 0; cnt < enem.size(); cnt++) {
            if (enem.get(cnt) == temp) {
                return false;
            }
        }
        return true;
    }

    public Texture getTexture() {
        return tex;
    }

    public ArrayList<Entity> getEnemList() {
        return enem;
    }

    public Rectangle getCollider() {
        return coll;
    }

    public float getRange() {
        return range;
    }

    public float getMyX() {
        return myX;
    }

    public float getMyY() {
        return myY;
    }

    public float getInitX() {
        return initX;
    }

    public float getInitY() {
        return initY;
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
