package com.apcs.game.items.armor;

import com.badlogic.gdx.graphics.Texture;

public class ArmorT3 extends ArmorT2 {
    private Texture icon;

    public ArmorT3(){
        super();
        setMaxHealth(8);
        setHealth(8);
        icon = new Texture("items/armor3.png");//change later when textures created
        setIcon(icon);

        myClass = "";
        setClass("armor");
    }

}
