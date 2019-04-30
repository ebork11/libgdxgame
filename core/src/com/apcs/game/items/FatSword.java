package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class FatSword extends Item {
    private Texture texU, texD, texR, texL, icon;

    public FatSword() {
        super();

        texU = new Texture("core/assets/items/fat sword/fattyu.png");
        texD = new Texture("core/assets/items/fat sword/fattyd.png");
        texR = new Texture("core/assets/items/fat sword/fattyr.png");
        texL = new Texture("core/assets/items/fat sword/fattyl.png");
        icon = new Texture("core/assets/items/fat sword/fattyu.png");

        setTextureU(texU);
        setTextureD(texD);
        setTextureR(texR);
        setTextureL(texL);
        setIcon(icon);

        setClass("weapon");
    }
}