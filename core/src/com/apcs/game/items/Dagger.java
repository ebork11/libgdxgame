package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class Dagger extends FatSword {

    protected Texture texU, texD, texR, texL, icon;
    protected String myClass;

    protected int damage;

    public Dagger() {
        super();

        myClass = "";

        damage = 3;

        texU = new Texture("items/dagger/daggeru.png");
        texD = new Texture("items/dagger/daggerd.png");
        texR = new Texture("items/dagger/daggerr.png");
        texL = new Texture("items/dagger/daggerl.png");

        icon = new Texture("items/dagger/daggericon.png");

        setTextureU(texU);
        setTextureD(texD);
        setTextureR(texR);
        setTextureL(texL);
        setIcon(icon);

        setClass("weapon");

        getCollider().setSize(texU.getWidth(), texU.getHeight());
    }

    public void setClass(String newClass) {
        myClass = newClass;
    }

    public String getItemClass() {
        return myClass;
    }

    public int getDamage() {
        return damage;
    }
}

