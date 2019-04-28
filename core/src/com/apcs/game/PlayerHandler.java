package com.apcs.game;

import com.apcs.game.items.FatSword;
import com.apcs.game.items.Item;
import com.apcs.game.items.Sword;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PlayerHandler {
    // main player stuff
    private static Texture myTexture;
    private static Rectangle collider;

    // inventory stuff
    private static PlayerInventory inventory;
    private int currentSlot; // current selected slot

    public PlayerHandler() {
        myTexture = new Texture("core/assets/player3.png"); // loading in the player texture
        collider = new Rectangle(100, 100, myTexture.getWidth() / 2, myTexture.getHeight() / 2); // creating the collider for the player

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
            inventory.addItem(new FatSword());
        }

        /*
            Removes current selected slot
         */
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            inventory.equip(currentSlot);
        } if (Gdx.input.isKeyPressed(Input.Keys.T)) {
            inventory.removeItem(currentSlot);
        }

        /*
            Changes selected item
         */
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            currentSlot = 0;
        } if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            currentSlot = 1;
        } if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            currentSlot = 2;
        }



        /*
            Movement
         */
        if (Gdx.input.isKeyPressed(Input.Keys.W) && (collider.y + myTexture.getHeight()) < 720) { // if the w key is pressed
            if (Gdx.input.isKeyPressed(Input.Keys.D) && collider.x + collider.getWidth() + (myTexture.getWidth() / 4) < 960) {
                collider.x += diagSpeed;
                collider.y += diagSpeed;
                return;
            } else if (Gdx.input.isKeyPressed(Input.Keys.A) && collider.x - (myTexture.getWidth() / 4) > 40) {
                collider.x -= diagSpeed;
                collider.y += diagSpeed;
                return;
            }

            collider.y += speed; // move the collider up
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && collider.y > 40) { // if the s key is pressed
            if (Gdx.input.isKeyPressed(Input.Keys.D) && collider.x + collider.getWidth() + (myTexture.getWidth() / 4) < 960) {
                collider.x += diagSpeed;
                collider.y -= diagSpeed;
                return;
            } else if (Gdx.input.isKeyPressed(Input.Keys.A) && collider.x - (myTexture.getWidth() / 4) > 40) {
                collider.x -= diagSpeed;
                collider.y -= diagSpeed;
                return;
            }

            collider.y -= speed; // move the collider down
        } else if (Gdx.input.isKeyPressed(Input.Keys.A) && collider.x - (myTexture.getWidth() / 4) > 40) { // if the a key is pressed
            collider.x -= speed; // move the collider left
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && collider.x + collider.getWidth() + (myTexture.getWidth() / 4) < 960) { // if the d key is pressed
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

    public static Texture getTexture() {
        return myTexture;
    }

    public void setTexture(Texture newTex) {
        myTexture = newTex;
    }

    public static Rectangle getCollider() {
        return collider;
    }

    public static PlayerInventory getInventory() {
        return inventory;
    }

    public int getCurrentSlot() {
        return currentSlot;
    }

    public void disposer() {
        myTexture.dispose();
    }




}
