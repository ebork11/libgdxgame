package com.apcs.game.items.weapons;

import com.apcs.game.items.weapons.FatSword;
import com.badlogic.gdx.graphics.Texture;

public class Wand extends FatSword {

    protected Texture texU, texD, texR, texL, icon;
    protected String myClass, subclass;
    protected int damage, shots, range;
    protected float speed;


    public Wand() {
        super();

        myClass = "";

        damage = 1;

        shots = 1;

        speed = 10;

        range = 225;

        texU = new Texture("items/wand/wandu.png");
        texD = new Texture("items/wand/wandd.png");
        texR = new Texture("items/wand/wandr.png");
        texL = new Texture("items/wand/wandl.png");
        icon = new Texture("items/wand/wandicon.png");

        setTextureU(texU);
        setTextureD(texD);
        setTextureR(texR);
        setTextureL(texL);
        setIcon(icon);

        setClass("ranged_weapon");
        subclass = "wand";

        getCollider().setSize(texU.getWidth(), texU.getHeight());
    }

    public void setClass(String newClass) {
        myClass = newClass;
    }

    public String getItemClass() {
        return myClass;
    }

    public int getShots() { return shots;}

    public int getDamage() {
        return damage;
    }

    public int getRange() {return range;}

    public float getSpeed() {return speed;}

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String temp) {
        subclass = temp;
    }
}

