package com.apcs.game.items;

import com.apcs.game.GameMain;
import com.apcs.game.items.weapons.Item;
import com.badlogic.gdx.graphics.Texture;

public class Key extends Item {
    private String myClass;
    private Texture texU;


    public Key() {
        myClass = "";

        texU = new Texture("items/key.png");

        setTextureU(texU);
        setIcon(texU);

        setClass("");
    }

    public void setClass(String newClass) {
        myClass = newClass;
    }

    public String getItemClass() {
        return myClass;
    }

    @Override
    public void drop() {
        super.drop();
        GameMain.hasKey = false;
    }
}
