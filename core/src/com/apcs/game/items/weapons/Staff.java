package com.apcs.game.items.weapons;

import com.badlogic.gdx.graphics.Texture;

public class Staff extends Wand {

    public Staff(){

        super();

        myClass = "";

        damage = 2;

        shots = 2;

        speed = 9;

        range = 300;

        texU = new Texture("items/wand/Staff/staffu.png");
        texD = new Texture("items/wand/Staff/staffd.png");
        texR = new Texture("items/wand/Staff/staffr.png");
        texL = new Texture("items/wand/Staff/staffl.png");
        icon = new Texture("items/wand/Staff/stafficon.png");

        setTextureU(texU);
        setTextureD(texD);
        setTextureR(texR);
        setTextureL(texL);
        setIcon(icon);

        setClass("ranged_weapon");
        subclass = "wand";

        getCollider().setSize(texU.getWidth(), texU.getHeight());
    }
}
