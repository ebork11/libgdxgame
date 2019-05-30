package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class Staff extends Wand {

    public Staff(){

        super();

        myClass = "";

        damage = 2;

        shots = 1;

        speed = 9;

        range = 300;

        texU = new Texture("items/wand/staff/staffu.png");
        texD = new Texture("items/wand/staff/staffd.png");
        texR = new Texture("items/wand/staff/staffr.png");
        texL = new Texture("items/wand/staff/staffl.png");
        icon = new Texture("items/wand/staff/stafficon.png");

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
