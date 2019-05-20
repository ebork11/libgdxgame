package com.apcs.game.items.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Projectile {
    Rectangle coll;
    Texture tex;
    float myX, myY;

    public Projectile(float x, float y) {
        tex = new Texture("gui/halfheart.png");

        coll = new Rectangle(x, y, tex.getWidth(), tex.getHeight());
    }

    public float getMyX() {
        return myX;
    }

    public float getMyY() {
        return myY;
    }
}
