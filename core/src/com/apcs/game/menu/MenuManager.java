package com.apcs.game.menu;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.util.ArrayList;

public class MenuManager {
    Texture background, play, playDown, help, helpDown, quit, quitDown, back, walk1, walk2, walk3, walk4, walk5, walk6, walk7, walk8, tut;
    ArrayList<Texture> title, arrows;
    private long frame, start, start2, start3;

    public MenuManager() {
        title = new ArrayList<Texture>();
        title.add(new Texture("gui/menu/title/title1.png"));
        title.add(new Texture("gui/menu/title/title2.png"));
        title.add(new Texture("gui/menu/title/title3.png"));
        title.add(new Texture("gui/menu/title/title4.png"));
        title.add(new Texture("gui/menu/title/title5.png"));
        title.add(new Texture("gui/menu/title/title6.png"));
        title.add(new Texture("gui/menu/title/title7.png"));
        title.add(new Texture("gui/menu/title/title8.png"));
        title.add(new Texture("gui/menu/title/title9.png"));
        title.add(new Texture("gui/menu/title/title10.png"));
        title.add(new Texture("gui/menu/title/title11.png"));
        title.add(new Texture("gui/menu/title/title12.png"));
        title.add(new Texture("gui/menu/title/title13.png"));
        title.add(new Texture("gui/menu/title/title14.png"));
        walk1 = new Texture("player/armor2/1armorwalk.png");
        walk2 = new Texture("player/armor2/2armorwalk.png");
        walk3 = new Texture("player/armor2/3armorwalk.png");
        walk4 = new Texture("player/armor2/4armorwalk.png");
        walk5 = new Texture("player/armor2/5armorwalk.png");
        walk6 = new Texture("player/armor2/6armorwalk.png");
        walk7 = new Texture("player/armor2/7armorwalk.png");
        walk8 = new Texture("player/armor2/8armorwalk.png");

        arrows = new ArrayList<Texture>();
        arrows.add(new Texture("gui/menu/arrow1.png"));
        arrows.add(new Texture("gui/menu/arrow2.png"));

        frame = 50;
        start = System.currentTimeMillis();
        start2 = System.currentTimeMillis();
        start3 = System.currentTimeMillis();

        background = new Texture("gui/menu.png");
        play = new Texture("gui/menu/startoff.png");
        playDown = new Texture("gui/menu/starton.png");
        help = new Texture("gui/menu/helpoff.png");
        helpDown = new Texture("gui/menu/helpon.png");
        quit = new Texture("gui/menu/quitoff.png");
        quitDown = new Texture("gui/menu/quiton.png");
        back = new Texture("gui/backbutton.png");
        tut = new Texture("gui/menukeys.png");
    }

    public Texture getBackground() {
        return background;
    }

    public Texture getTitleAnim() {
        if (System.currentTimeMillis() - start > frame * 45) {
            start = System.currentTimeMillis();
        }

        for (int cnt = 1; cnt <= title.size(); cnt++) {
            if (System.currentTimeMillis() - start < frame * cnt) {
                return title.get(cnt - 1);
            }
        }
        return title.get(0);
    }

    public Texture playerAnim() {
        if (System.currentTimeMillis() - start3 < frame) {
            return walk1;
        } else if (System.currentTimeMillis() - start3 < frame * 2) {
            return walk2;
        } else if (System.currentTimeMillis() - start3 < frame * 3) {
            return walk3;
        } else if (System.currentTimeMillis() - start3 < frame * 4) {
            return walk4;
        } else if (System.currentTimeMillis() - start3 < frame * 5) {
            return walk5;
        } else if (System.currentTimeMillis() - start3 < frame * 6) {
            return walk6;
        } else if (System.currentTimeMillis() - start3 < frame * 7) {
            return walk7;
        } else if (System.currentTimeMillis() - start3 < frame * 8) {
            return walk8;
        } else {
            start3 = System.currentTimeMillis();
            return walk1;
        }
    }

    public Texture getArrowAnim() {
        if (System.currentTimeMillis() - start2 > frame* 10) {
            start2 = System.currentTimeMillis();
        }

        for (int cnt = 1; cnt <= arrows.size(); cnt++) {
            if (System.currentTimeMillis() - start2 < frame * cnt) {
                return arrows.get(cnt - 1);
            }
        }
        return arrows.get(0);
    }

    public Texture getMenuKeys() {
        return tut;
    }
}
