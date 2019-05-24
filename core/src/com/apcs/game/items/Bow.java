package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class Bow extends Wand {

    public Bow()
    {
        super();

        setSubclass("bow");

        damage = 2;

        shots = 1;

        speed = 10;

        range = 800;

        texU = new Texture("items/bow/bowu.png");
        texD = new Texture("items/bow/bowd.png");
        texR = new Texture("items/bow/bowr.png");
        texL = new Texture("items/bow/bowl.png");
        icon = new Texture("items/bow/bowicon.png");

        setTextureU(texU);
        setTextureD(texD);
        setTextureR(texR);
        setTextureL(texL);
        setIcon(icon);

        setClass("ranged_weapon");

        getCollider().setSize(texU.getWidth(), texU.getHeight());
    }

}
