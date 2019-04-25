package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;

public class Sword extends Item {
    private Texture myTexture;

    public Sword() {
        super();
        myTexture = new Texture("core/assets/items/Dagger.png");
        setTexture(myTexture);
    }
}
