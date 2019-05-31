package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class DaggerT2 extends Dagger {

    public DaggerT2(){
        super();

        myClass = "";

        damage = 4;

        texU = new Texture("items/dagger/daggerT2/daggert2u.png");
        texD = new Texture("items/dagger/daggerT2/daggert2d.png");
        texR = new Texture("items/dagger/daggerT2/daggert2r.png");
        texL = new Texture("items/dagger/daggerT2/daggert2l.png");

        icon = new Texture("items/dagger/daggerT2/daggert2icon.png");

        setTextureU(texU);
        setTextureD(texD);
        setTextureR(texR);
        setTextureL(texL);
        setIcon(icon);

        setClass("weapon");

        getCollider().setSize(texU.getWidth(), texU.getHeight());
    }
}
