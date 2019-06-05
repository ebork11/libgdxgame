package com.apcs.game.items.armor;

import com.badlogic.gdx.graphics.Texture;

public class ArmorT2 extends Armor {

    private Texture icon;

    public ArmorT2(){
        super();
        setMaxHealth(4);
        setHealth(4);
        icon = new Texture("items/armor2.png");//change later when textures created
        setIcon(icon);

        myClass = "";
        setClass("armor");
    }
}
