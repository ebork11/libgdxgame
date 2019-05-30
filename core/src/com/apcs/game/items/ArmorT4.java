package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class ArmorT4 extends ArmorT3 {

    private Texture icon;

    ArmorT4() {
        super();
        setMaxHealth(8);
        setHealth(8);
        icon = new Texture("items/armor.png");//change later when textures created
        setIcon(icon);

        myClass = "";
        setClass("armor");
    }
}
