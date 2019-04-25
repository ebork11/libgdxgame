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

    /*
        Method to access the texture of the item, all items will inherit this
     */
    public Texture getTexture() {
        return myTexture;
    }

    /*
        Method to set the texture of the item, this will be used for new items
     */
    public void setTexture(Texture newTex) {
        myTexture = newTex;
    }

    /*
        Method to access the items collider
     */
    public Rectangle getCollider() {
        return collider;
    }

    /*
        Dispose method for item textures
     */
    public void disposer() {
        myTexture.dispose();
    }


}
