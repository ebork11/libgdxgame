package com.apcs.game.player;

import com.apcs.game.GameMain;
import com.badlogic.gdx.graphics.Texture;

public class PlayerAnimation {
    private Texture walk1, walk2, walk3, walk4, walk5, walk6, walk7, walk8;
    private Texture hit1, hit2, hit3, hit4, hit5, hit6, hit7, hit8;

    private long frame, start;

    public PlayerAnimation() {
        walk1 = new Texture("player/default/defaultwalk1.png");
        walk2 = new Texture("player/default/defaultwalk2.png");
        walk3 = new Texture("player/default/defaultwalk3.png");
        walk4 = new Texture("player/default/defaultwalk4.png");
        walk5 = new Texture("player/default/defaultwalk5.png");
        walk6 = new Texture("player/default/defaultwalk6.png");
        walk7 = new Texture("player/default/defaultwalk7.png");
        walk8 = new Texture("player/default/defaultwalk8.png");

        hit1 = new Texture("player/hit/defaultwalk1Hit.png");
        hit2 = new Texture("player/hit/defaultwalk2Hit.png");
        hit3 = new Texture("player/hit/defaultwalk3Hit.png");
        hit4 = new Texture("player/hit/defaultwalk4Hit.png");
        hit5 = new Texture("player/hit/defaultwalk5Hit.png");
        hit6 = new Texture("player/hit/defaultwalk6Hit.png");
        hit7 = new Texture("player/hit/defaultwalk7Hit.png");
        hit8 = new Texture("player/hit/defaultwalk8Hit.png");

        frame = 50;
        start = System.currentTimeMillis();
    }

    public Texture getFrameTex() {
        if (System.currentTimeMillis() - start < frame) {
            return walk1;
        } else if (System.currentTimeMillis() - start < frame * 2) {
            return walk2;
        } else if (System.currentTimeMillis() - start < frame * 3) {
            return walk3;
        } else if (System.currentTimeMillis() - start < frame * 4) {
            return walk4;
        } else if (System.currentTimeMillis() - start < frame * 5) {
            return walk5;
        } else if (System.currentTimeMillis() - start < frame * 6) {
            return walk6;
        } else if (System.currentTimeMillis() - start < frame * 7) {
            return walk7;
        } else if (System.currentTimeMillis() - start < frame * 8) {
            return walk8;
        } else {
            start = System.currentTimeMillis();
            return walk1;
        }
    }
    public Texture hitAnim()
    {
        GameMain.hit = false;
        if (System.currentTimeMillis() - start < frame) {
            return hit1;
        } else if (System.currentTimeMillis() - start < frame * 2) {
            return hit2;
        } else if (System.currentTimeMillis() - start < frame * 3) {
            return hit3;
        } else if (System.currentTimeMillis() - start < frame * 4) {
            return hit4;
        } else if (System.currentTimeMillis() - start < frame * 5) {
            return hit5;
        } else if (System.currentTimeMillis() - start < frame * 6) {
            return hit6;
        } else if (System.currentTimeMillis() - start < frame * 7) {
            return hit7;
        } else if (System.currentTimeMillis() - start < frame * 8) {
            return hit8;
        } else {
            start = System.currentTimeMillis();
            return hit1;
        }
    }
}
