package com.apcs.game.items.weapons;

import com.badlogic.gdx.graphics.Texture;

public class Crossbow extends Bow {

    public Crossbow(){
        super();

        setSubclass("bow");

        damage = 4;

        shots = 1;

        speed = 11;

        range = 200;

        texU = new Texture("items/bow/Crossbow/crossbowu.png");
        texD = new Texture("items/bow/Crossbow/crossbowd.png");
        texR = new Texture("items/bow/Crossbow/crossbowr.png");
        texL = new Texture("items/bow/Crossbow/crossbowl.png");
        icon = new Texture("items/bow/Crossbow/crossbowicon.png");

        setTextureU(texU);
        setTextureD(texD);
        setTextureR(texR);
        setTextureL(texL);
        setIcon(icon);

        setClass("ranged_weapon");

        getCollider().setSize(texU.getWidth(), texU.getHeight());
    }
}
