package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class Spear extends FatSword {
    private Texture texU, texD, texR, texL, icon;
    private String myClass;

    private int damage;

    public Spear() {
        super();

        myClass = "";

        damage = 1;

        texU = new Texture("items/spear/spearu.png");
        texD = new Texture("items/spear/speard.png");
        texR = new Texture("items/spear/spearr.png");
        texL = new Texture("items/spear/spearl.png");
        icon = new Texture("items/spear/spearicon.png");

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
