package com.apcs.game.items.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Projectile {
    Rectangle coll;
    Texture tex;
    float myX, myY;
    int dmg;

    public Projectile(float x, float y, int damage) {
        tex = new Texture("gui/halfheart.png");

        coll = new Rectangle(x, y, tex.getWidth(), tex.getHeight());

        dmg = damage;
        myX = x;
        myY = y;
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
}
