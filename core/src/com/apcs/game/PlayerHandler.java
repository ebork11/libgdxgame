package com.apcs.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PlayerHandler {
    private Texture myTexture;
    private Rectangle collider;

    public PlayerHandler() {
        myTexture = new Texture("core/assets/badlogic.jpg");
        collider = new Rectangle(0, 0, myTexture.getWidth(), myTexture.getHeight());
    }


    public Texture getTexture() {
        return myTexture;
    }

    public void setTexture(Texture newTex) {
        myTexture = newTex;
    }


    public Rectangle getCollider() {
        return collider;
    }



}
