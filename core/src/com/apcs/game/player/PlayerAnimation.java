package com.apcs.game.player;

import com.badlogic.gdx.graphics.Texture;

public class PlayerAnimation {
    private Texture walk1, walk2, walk3, walk4, walk5, walk6, walk7, walk8;

    private long frame, start;

    public PlayerAnimation() {
        walk1 = new Texture("core/assets/player/walk1.png");
        walk2 = new Texture("core/assets/player/walk2.png");
        walk3 = new Texture("core/assets/player/walk3.png");
        walk4 = new Texture("core/assets/player/walk4.png");
        walk5 = new Texture("core/assets/player/walk5.png");
        walk6 = new Texture("core/assets/player/walk6.png");
        walk7 = new Texture("core/assets/player/walk7.png");
        walk8 = new Texture("core/assets/player/walk8.png");

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
}
