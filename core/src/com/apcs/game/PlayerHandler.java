package com.apcs.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PlayerHandler {
    private Texture myTexture;
    private Rectangle collider;

    public PlayerHandler() {
        myTexture = new Texture("core/assets/badlogic.jpg");
        collider = new Rectangle(0, 0, myTexture.getWidth(), myTexture.getHeight());
    }

    /*
        Checks for input on WASD keys and moves the player according to what they press.
     */
    public void movementHandler() {
        float speed = 5f; // speed to move the player

        if (Gdx.input.isKeyPressed(Input.Keys.W)) { // if the w key is pressed
            collider.y += speed; // move the collider up
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) { // if the s key is pressed
            collider.y -= speed; // move the collider down
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) { // if the a key is pressed
            collider.x -= speed; // move the collider left
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) { // if the d key is pressed
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

    public void disposer() {
        myTexture.dispose();
    }




}
