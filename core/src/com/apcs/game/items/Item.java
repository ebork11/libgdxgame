package com.apcs.game.items;

import com.apcs.game.GameMain;
import com.apcs.game.player.PlayerHandler;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Item {
    private Texture icon, myTextureU, myTextureD, myTextureR, myTextureL;
    private Rectangle collider;
    private float myX;
    private float myY;

    public Item() {
        myTextureU = new Texture("core/assets/items/item.png"); // default image for items
        myTextureD = new Texture("core/assets/items/item.png"); // default image for items
        myTextureR = new Texture("core/assets/items/item.png"); // default image for items
        myTextureL = new Texture("core/assets/items/item.png"); // default image for items
        icon = new Texture("core/assets/items/item.png"); // default image for items
        collider = new Rectangle(200, 200, icon.getWidth(), icon.getHeight()); // sets collider to the size of the image
        myX = 200;
        myY = 200;
    }

    /*
        When an item is to be dropped this method is called
     */
    public void drop() {
        GameMain.groundItems.add(this);
        myX = PlayerHandler.getCollider().x;
        myY = PlayerHandler.getCollider().y;

        collider.x = myX; // moves collider to location of dropped item
        collider.y = myY; // ^^
    }

    public abstract void setClass(String newClass);

    public abstract String getItemClass();

    public float getX() { // returns colliders x
        return myX;
    }

    public float getY() { // returns colliders y
        return myY;
    }

    /*
        Method to access the texture of the item, all items will inherit this
     */
    public Texture getTextureU() {
        return myTextureU;
    }

    public Texture getTextureD() {
        return myTextureD;
    }

    public Texture getTextureR() {
        return myTextureR;
    }

    public Texture getTextureL() {
        return myTextureL;
    }

    public Texture getIcon() {
        return icon;
    }

    /*
        Method to set the texture of the item, this will be used for new items
     */
    public void setTextureU(Texture newTex) {
        myTextureU = newTex;
    }

    public void setTextureD(Texture newTex) {
        myTextureD = newTex;
    }

    public void setTextureR(Texture newTex) {
        myTextureR = newTex;
    }

    public void setTextureL(Texture newTex) {
        myTextureL = newTex;
    }

    public void setIcon(Texture newIcon) {
        icon = newIcon;
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
        myTextureU.dispose();
        myTextureD.dispose();
        myTextureR.dispose();
        myTextureL.dispose();
    }


}
