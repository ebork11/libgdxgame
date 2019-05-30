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

    public Projectile(float x, float y, int damage, float sped, String direction, int rng, String type) {
        switch(direction) {
            case "up":
                if (type.equals("spell"))
                    tex = new Texture("items/projectile/whitespell/pojectileu.png");
                else if (type.equals("rock"))
                    tex = new Texture("items/projectile/rock/rocku.png");
                else
                    tex = new Texture("items/projectile/arrow/arrowu.png");
                break;
            case "down":
                if (type.equals("spell"))
                    tex = new Texture("items/projectile/whitespell/projectiled.png");
                else if (type.equals("rock"))
                    tex = new Texture("items/projectile/rock/rockd.png");
                else
                    tex = new Texture("items/projectile/arrow/arrowd.png");
                break;
            case "left":
                if (type.equals("spell"))
                    tex = new Texture("items/projectile/whitespell/projectilel.png");
                else if (type.equals("rock"))
                    tex = new Texture("items/projectile/rock/rockl.png");
                else
                    tex = new Texture("items/projectile/arrow/arrowl.png");
                break;
            case "right":
                if (type.equals("spell"))
                    tex = new Texture("items/projectile/whitespell/projectiler.png");
                else if (type.equals("rock"))
                    tex = new Texture("items/projectile/rock/rockr.png");
                else
                    tex = new Texture("items/projectile/arrow/arrowr.png");
                break;
            case "ne":
                tex = new Texture("items/projectile/rock/rockne.png");
                break;
            case "nw":
                tex = new Texture("items/projectile/rock/rocknw.png");
                break;
            case "se":
                tex = new Texture("items/projectile/rock/rockse.png");
                break;
            case "sw":
                tex = new Texture("items/projectile/rock/rocksw.png");
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
