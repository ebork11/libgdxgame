package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class SwordT2 extends FatSword {

    public SwordT2(){
        super();

        myClass = "";

        damage = 3;

        texU = new Texture("items/fat sword/swordt2/swordt2u.png");
        texD = new Texture("items/fat sword/swordt2/swordt2d.png");
        texR = new Texture("items/fat sword/swordt2/swordt2r.png");
        texL = new Texture("items/fat sword/swordt2/swordt2l.png");
        icon = new Texture("items/fat sword/swordt2/swordt2icon.png");

        setTextureU(texU);
        setTextureD(texD);
        setTextureR(texR);
        setTextureL(texL);
        setIcon(icon);

        setClass("weapon");

        getCollider().setSize(texU.getWidth(), texU.getHeight());
    }

}
