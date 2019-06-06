package com.apcs.game.items;

import com.apcs.game.GameMain;
import com.apcs.game.items.weapons.Item;
import com.badlogic.gdx.graphics.Texture;

public class Key extends Item {
    private String myClass;
    private Texture texU;
    private int level;


    public Key() {
        myClass = "";

        texU = new Texture("items/key.png");

        setTextureU(texU);
        setIcon(texU);

        setClass("");

        level = 1;
    }

    public Key(int temp) {
        myClass = "";

        texU = new Texture("items/key.png");

        setTextureU(texU);
        setIcon(texU);

        setClass("");

        level = temp;
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
        if (level == 1)
            GameMain.hasKey = false;
        else
            GameMain.hasKey2 = false;
    }
}
