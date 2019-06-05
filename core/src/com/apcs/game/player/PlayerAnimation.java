package com.apcs.game.player;

import com.apcs.game.GameMain;
import com.badlogic.gdx.graphics.Texture;

public class PlayerAnimation {
    private static Texture walk1, walk2, walk3, walk4, walk5, walk6, walk7, walk8;
    private Texture heals1, heals2, heals3, heals4, heals5, heals6, heals7, heals8;
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

        heals1 = new Texture("player/healing/healing1.png");
        heals2 = new Texture("player/healing/healing2.png");
        heals3 = new Texture("player/healing/healing3.png");
        heals4 = new Texture("player/healing/healing4.png");
        heals5 = new Texture("player/healing/healing5.png");
        heals6 = new Texture("player/healing/healing6.png");
        heals7 = new Texture("player/healing/healing7.png");
        heals8 = new Texture("player/healing/healing8.png");

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

    public static void changeShirt(String color) {
        switch(color) {
            case "default":
                walk1 = new Texture("player/default/defaultwalk1.png");
                walk2 = new Texture("player/default/defaultwalk2.png");
                walk3 = new Texture("player/default/defaultwalk3.png");
                walk4 = new Texture("player/default/defaultwalk4.png");
                walk5 = new Texture("player/default/defaultwalk5.png");
                walk6 = new Texture("player/default/defaultwalk6.png");
                walk7 = new Texture("player/default/defaultwalk7.png");
                walk8 = new Texture("player/default/defaultwalk8.png");
                break;
            case "white":
                walk1 = new Texture("player/armor1/armor1walk1.png");
                walk2 = new Texture("player/armor1/armor1walk2.png");
                walk3 = new Texture("player/armor1/armor1walk3.png");
                walk4 = new Texture("player/armor1/armor1walk4.png");
                walk5 = new Texture("player/armor1/armor1walk5.png");
                walk6 = new Texture("player/armor1/armor1walk6.png");
                walk7 = new Texture("player/armor1/armor1walk7.png");
                walk8 = new Texture("player/armor1/armor1walk8.png");
                break;
            case "blue":
                walk1 = new Texture("player/armor2/1armorwalk.png");
                walk2 = new Texture("player/armor2/2armorwalk.png");
                walk3 = new Texture("player/armor2/3armorwalk.png");
                walk4 = new Texture("player/armor2/4armorwalk.png");
                walk5 = new Texture("player/armor2/5armorwalk.png");
                walk6 = new Texture("player/armor2/6armorwalk.png");
                walk7 = new Texture("player/armor2/7armorwalk.png");
                walk8 = new Texture("player/armor2/8armorwalk.png");
                break;
            default:
                break;
        }
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

    public Texture healAnim()
    {
        GameMain.heal = false;
        if (System.currentTimeMillis() - start < frame) {
            return heals1;
        } else if (System.currentTimeMillis() - start < frame * 2) {
            return heals2;
        } else if (System.currentTimeMillis() - start < frame * 3) {
            return heals3;
        } else if (System.currentTimeMillis() - start < frame * 4) {
            return heals4;
        } else if (System.currentTimeMillis() - start < frame * 5) {
            return heals5;
        } else if (System.currentTimeMillis() - start < frame * 6) {
            return heals6;
        } else if (System.currentTimeMillis() - start < frame * 7) {
            return heals7;
        } else if (System.currentTimeMillis() - start < frame * 8) {
            return heals8;
        } else {
            start = System.currentTimeMillis();
            return heals1;
        }
    }
}
