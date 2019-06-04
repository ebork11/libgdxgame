package com.apcs.game.menu;

import com.badlogic.gdx.graphics.Texture;

public class Button {

    private MenuManager mm;
    private Texture textOn, textOff;

    public Button(){
        mm = new MenuManager();
        textOff = new Texture("gui/menu/quitoff.png");
        textOn = new Texture("gui/menu/quiton.png");
    }
    public Button(String name){
        textOff = new Texture("gui/menu/"+name+"off.png");
        textOn = new Texture("gui/menu/"+name+"on.png");
    }

    public Texture getTextOn(){
        return textOn;
    }

    public Texture getTextOff(){
        return textOff;
    }
}
