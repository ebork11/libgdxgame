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
        tex = new Texture("gui/halfheart.png");

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
