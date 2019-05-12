package com.apcs.game.menu;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class MenuManager {
    Texture background, play, help, quit, back;

    public MenuManager() {
        background = new Texture("gui/menu.png");
        play = new Texture("gui/playbutton.png");
        help = new Texture("gui/helpbutton.png");
        quit = new Texture("gui/quitbutton.png");
        back = new Texture("gui/backbutton.png");
    }

    public Texture getBackground() {
        return background;
    }

    public Texture getPlayButton() {
        return play;
    }

    public Texture getHelpButton() {
        return help;
    }

    public Texture getQuitButton() {
        return quit;
    }

    public Texture getBackButton() {
        return back;
    }
}
