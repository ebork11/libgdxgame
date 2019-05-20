package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class Dagger extends FatSword {

    private Texture texU, texD, texR, texL, icon;
    private String myClass;

    private int damage;

    public Dagger() {
        super();

        myClass = "";

        damage = 3;

        texU = new Texture("items/dagger/Dagger3.png");
        texD = new Texture("items/dagger/Dagger3.png");
        texR = new Texture("items/dagger/Dagger3.png");
        texL = new Texture("items/dagger/Dagger3.png");
        icon = new Texture("items/dagger/Dagger3.png");

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

