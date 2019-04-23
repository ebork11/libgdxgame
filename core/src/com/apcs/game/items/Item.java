package com.apcs.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Item {
    private Texture myTexture;
    private Rectangle collider;

    public Item() {
        myTexture = new Texture("core/assets/badlogic.jpg"); // default image for items
        collider = new Rectangle(0, 0, myTexture.getWidth(), myTexture.getHeight()); // sets collider to the size of the image
    }

}
