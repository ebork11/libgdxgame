package com.apcs.game.menu;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class MenuManager {
    Texture background;
    Texture play;

    public MenuManager() {
        background = new Texture("gui/menu.png");
        play = new Texture("gui/playbutton.png");
    }

    public Texture getBackground() {
        return background;
    }

    public Texture getPlayButton() {
        return play;
    }
}
