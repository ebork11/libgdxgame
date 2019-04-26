package com.apcs.game;

import com.apcs.game.items.Item;
import com.apcs.game.items.Sword;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PlayerHandler {
    private Texture myTexture;
    private static Rectangle collider;
    private PlayerInventory inventory;
    private int currentSlot; // current selected slot

    public PlayerHandler() {
        myTexture = new Texture("core/assets/player.png"); // loading in the player texture
        collider = new Rectangle(100, 100, myTexture.getWidth(), myTexture.getHeight()); // creating the collider for the player
        inventory = new PlayerInventory(); // creating the inventory for the player
        currentSlot = 0; // the starting slot selected will be the first one at index 0
    }

    /*
        Checks for input on WASD keys and moves the player according to what they press and makes sure they are within the room boundaries
     */
    public void movementHandler() {
        float speed = 5f; // speed to move the player
        float diagSpeed = (float)Math.sqrt(Math.pow(speed, 2) / 2);

        /*
            Test to add items to inventory
         */
        if (Gdx.input.isKeyJustPressed((Input.Keys.J))) {
            inventory.addItem(new Sword());
        }

        /*
            Test to remove items from inventory. not to be kept in final game
         */
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            inventory.removeItem(0);
        } if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            inventory.removeItem(1);
        } if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            inventory.removeItem(2);
        } if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            inventory.removeItem(3);
        }



        /*
            Movement
         */
        if (Gdx.input.isKeyPressed(Input.Keys.W) && (collider.y + myTexture.getHeight()) < 650) { // if the w key is pressed
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                collider.x += diagSpeed;
                collider.y += diagSpeed;
                return;
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                collider.x -= diagSpeed;
                collider.y += diagSpeed;
                return;
            }

            collider.y += speed; // move the collider up
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && collider.y > 50) { // if the s key is pressed
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                collider.x += diagSpeed;
                collider.y -= diagSpeed;
                return;
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                collider.x -= diagSpeed;
                collider.y -= diagSpeed;
                return;
            }

            collider.y -= speed; // move the collider down
        } else if (Gdx.input.isKeyPressed(Input.Keys.A) && collider.x > 35) { // if the a key is pressed
            collider.x -= speed; // move the collider left
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && (collider.x + myTexture.getWidth()) < 1235) { // if the d key is pressed
            collider.x += speed; // move the collider right
        }
    }

    public void checkForPickup() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E) ) {
            for (int cnt = 0; cnt < GameMain.groundItems.size(); cnt++) {
                if (collider.overlaps(GameMain.groundItems.get(cnt).getCollider())) {
                    if (inventory.addItem(GameMain.groundItems.get(cnt))) {
                        GameMain.groundItems.remove(cnt);
                    }
                    break;
                }
            }
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
    public static Rectangle getCollider() {
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
