package com.apcs.game;

import com.badlogic.gdx.math.Rectangle;

public class PlayerCombat {
    Rectangle l,r,u,d;

    public PlayerCombat() {
        l = new Rectangle();
        r = new Rectangle();
        u = new Rectangle();
        d = new Rectangle();
    }

    public void updateColliders(float x, float y) {
        l.x += x;
        r.x += x;
        u.x += x;
        d.x += x;

        l.y += y;
        r.y += y;
        u.y += y;
        d.y += y;
    }

    public Rectangle getL() {
        return l;
    }

    public Rectangle getR() {
        return r;
    }

    public Rectangle getU() {
        return u;
    }

    public Rectangle getD() {
        return d;
    }
}
