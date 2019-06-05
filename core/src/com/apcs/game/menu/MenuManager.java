package com.apcs.game.menu;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.util.ArrayList;

public class MenuManager {
    Texture background, play, playDown, help, helpDown, quit, quitDown, back;
    ArrayList<Texture> title, arrows;
    private long frame, start;

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

        arrows = new ArrayList<Texture>();
        arrows.add(new Texture("gui/menu/arrow1.png"));
        arrows.add(new Texture("gui/menu/arrow2.png"));

        frame = 50;
        start = System.currentTimeMillis();

        background = new Texture("gui/menu.png");
        play = new Texture("gui/menu/startoff.png");
        playDown = new Texture("gui/menu/starton.png");
        help = new Texture("gui/menu/helpoff.png");
        helpDown = new Texture("gui/menu/helpon.png");
        quit = new Texture("gui/menu/quitoff.png");
        quitDown = new Texture("gui/menu/quiton.png");
        back = new Texture("gui/backbutton.png");
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

    public Texture getArrowAnim() {
        if (System.currentTimeMillis() - start > frame * 10) {
            start = System.currentTimeMillis();
        }

        for (int cnt = 1; cnt <= arrows.size(); cnt++) {
            if (System.currentTimeMillis() - start < frame * cnt) {
                return arrows.get(cnt - 1);
            }
        }
        return arrows.get(0);
    }
}
