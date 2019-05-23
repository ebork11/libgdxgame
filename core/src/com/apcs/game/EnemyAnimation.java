package com.apcs.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class EnemyAnimation {
    ArrayList<Texture> anim, hit;
    private long frame, start;

    public EnemyAnimation(ArrayList<Texture> animTextures, ArrayList<Texture> hitTextures) {
        frame = 50;
        start = System.currentTimeMillis();

        anim = animTextures;
        hit = hitTextures;
    }

    public Texture getTexture() {
        if (System.currentTimeMillis() - start > frame * 8) {
            start = System.currentTimeMillis();
        }

        for (int cnt = 1; cnt <= anim.size(); cnt++) {
            if (System.currentTimeMillis() - start < frame * cnt) {
                return anim.get(cnt - 1);
            }
        }
        return anim.get(0);
    }

    public Texture getHitTex() {
        for (int cnt = 0; cnt < hit.size(); cnt++) {
            if (System.currentTimeMillis() - start < frame * cnt) {
                if (cnt == hit.size() - 1) {
                    start = System.currentTimeMillis();
                }
                return hit.get(cnt);
            }
        }
        return hit.get(0);
    }
}
