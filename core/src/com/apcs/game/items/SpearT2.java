package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class SpearT2 extends Spear{

    public SpearT2(){
        super();

        myClass = "";

        damage = 2;

        texU = new Texture("items/spear/spearT2/spearT2u.png");
        texD = new Texture("items/spear/spearT2/spearT2d.png");
        texR = new Texture("items/spear/spearT2/spearT2r.png");
        texL = new Texture("items/spear/spearT2/spearT2l.png");
        icon = new Texture("items/spear/spearT2/spearT2icon.png");

        setTextureU(texU);
        setTextureD(texD);
        setTextureR(texR);
        setTextureL(texL);
        setIcon(icon);

        setClass("weapon");

        getCollider().setSize(texU.getWidth(), texU.getHeight());
    }
}
