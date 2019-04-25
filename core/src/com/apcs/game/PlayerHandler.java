package com.apcs.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PlayerHandler {
    private Texture myTexture;
    private Rectangle collider;
    private PlayerInventory inventory;

    public PlayerHandler() {
        myTexture = new Texture("core/assets/player.png"); // loading in the player texture
        collider = new Rectangle(100, 100, myTexture.getWidth(), myTexture.getHeight()); // creating the collider for the player
        inventory = new PlayerInventory(); // creating the inventory for the player
    }

    /*
        Checks for input on WASD keys and moves the player according to what they press and makes sure they are within the room boundaries
     */
    public void movementHandler() {
        float speed = 5f; // speed to move the player

        if (Gdx.input.isKeyPressed(Input.Keys.W) && (collider.y + myTexture.getHeight()) < 650) { // if the w key is pressed
            collider.y += speed; // move the collider up
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && collider.y > 50) { // if the s key is pressed
            collider.y -= speed; // move the collider down
        } else if (Gdx.input.isKeyPressed(Input.Keys.A) && collider.x > 35) { // if the a key is pressed
            collider.x -= speed; // move the collider left
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && (collider.x + myTexture.getWidth()) < 1235) { // if the d key is pressed
            collider.x += speed; // move the collider right
        }
    }

    /*
        Returns the players texture
     */
    public Texture getTexture() {
        return myTexture;
    }

    /*
        Sets the players texture to whatever is given
     */
    public void setTexture(Texture newTex) {
        myTexture = newTex;
    }

    /*
        Returns the players collider object(aka the Rectangle)
     */
    public Rectangle getCollider() {
        return collider;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    /*
        Disposing the player textures
     */
    public void disposer() {
        myTexture.dispose();
    }




}
