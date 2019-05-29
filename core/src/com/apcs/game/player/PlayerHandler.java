package com.apcs.game.player;

import com.apcs.game.GameMain;
import com.apcs.game.enemies.Entity;
import com.apcs.game.items.Armor;
import com.apcs.game.items.FatSword;
import com.apcs.game.items.HealthPotion;
import com.apcs.game.object.Spike;
import com.apcs.game.rooms.HealingRoom;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PlayerHandler {
    // main com.apcs.game.player stuff
    private static Texture myTexture;
    private static Rectangle collider;
    private static long lastHit, lastHeal;
    private static long cooldown;

    // inventory stuff
    private static PlayerInventory inventory;
    private int currentSlot; // current selected slot

    // combat stuff
    private static PlayerCombat combat;

    public PlayerHandler() {
        myTexture = new Texture("player/default/defaultwalk1.png"); // loading in the com.apcs.game.player texture
        collider = new Rectangle(100, 100, myTexture.getWidth() / 2, myTexture.getHeight() / 2); // creating the collider for the com.apcs.game.player

        inventory = new PlayerInventory(); // creating the inventory for the com.apcs.game.player
        currentSlot = 0; // the starting slot selected will be the first one at index 0

        combat = new PlayerCombat();
        lastHit = System.currentTimeMillis();
        cooldown = 1500;

        lastHeal = System.currentTimeMillis();
    }

    /*
        Checks for input on WASD keys and moves the com.apcs.game.player according to what they press and makes sure they are within the room boundaries
     */
    public void movementHandler() {
        float speed = 7f; // speed to move the com.apcs.game.player
        float diagSpeed = (float)Math.sqrt(Math.pow(speed, 2) / 2);

        /*
            Removes current selected slot
         */

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            Gdx.graphics.setWindowedMode(1280, 720);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            inventory.removeItem(currentSlot);
        } if (Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT) && inventory.getInventory()[currentSlot] != null) {
            if (inventory.getInventory()[currentSlot].getItemClass().equals("consumable")) {
                HealthPotion hp = (HealthPotion)(inventory.getInventory()[currentSlot]);
                if (hp.usePot() != 0){
                    PlayerCombat.addHealth(hp.usePot());
                    inventory.getInventory()[currentSlot] = null;
                }
            }
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
            if (Gdx.input.isKeyPressed(Input.Keys.D) && collider.x + collider.getWidth() + (myTexture.getWidth() / 4) < 980) {
                collider.x += diagSpeed;
                collider.y += diagSpeed;
                return;
            } else if (Gdx.input.isKeyPressed(Input.Keys.A) && collider.x - (myTexture.getWidth() / 4) > 20) {
                collider.x -= diagSpeed;
                collider.y += diagSpeed;
                return;
            }

            collider.y += speed; // move the collider up
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && collider.y > 40) { // if the s key is pressed
            if (Gdx.input.isKeyPressed(Input.Keys.D) && collider.x + collider.getWidth() + (myTexture.getWidth() / 4) < 980) {
                collider.x += diagSpeed;
                collider.y -= diagSpeed;
                return;
            } else if (Gdx.input.isKeyPressed(Input.Keys.A) && collider.x - (myTexture.getWidth() / 4) > 20) {
                collider.x -= diagSpeed;
                collider.y -= diagSpeed;
                return;
            }

            collider.y -= speed; // move the collider down
        } else if (Gdx.input.isKeyPressed(Input.Keys.A) && collider.x - (myTexture.getWidth() / 4) > 20) { // if the a key is pressed
            collider.x -= speed; // move the collider left
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) && collider.x + collider.getWidth() + (myTexture.getWidth() / 4) < 980) { // if the d key is pressed
            collider.x += speed; // move the collider right
        }
    }

    public void checkForPickup() {
        GameMain.overItem = false;

        for (int cnt = 0; cnt < GameMain.getRm().getCurrentRoom().getGroundItems().size(); cnt++) {
            if (collider.overlaps(GameMain.getRm().getCurrentRoom().getGroundItems().get(cnt).getCollider())) {
                GameMain.overItem = true;
                if (Gdx.input.isKeyJustPressed(Input.Keys.E) ) {
                    if (inventory.equip(GameMain.getRm().getCurrentRoom().getGroundItems().get(cnt))) {
                    } else if (inventory.addItem(GameMain.getRm().getCurrentRoom().getGroundItems().get(cnt))) {
                        GameMain.getRm().getCurrentRoom().getGroundItems().remove(cnt);
                    }

                    GameMain.overItem = false;
                    break;
                }
            }
        }
    }

    public void checkStandingHealth() {
        if (RoomManager.getCurrentRoom() instanceof HealingRoom) {
            if(collider.overlaps(HealingRoom.pool)&& HealingRoom.health > 0){
                if(System.currentTimeMillis() - lastHeal > 500) {
                    if (PlayerCombat.getHealth()< 8) {
                        PlayerCombat.addHealth(1);
                        HealingRoom.health--; // removes total
                        lastHeal = System.currentTimeMillis();
                    } else if (PlayerInventory.getArmor() != null && PlayerInventory.getArmor().getStat()<PlayerInventory.getArmor().getMaxHealth()) {
                        PlayerInventory.getArmor().repair(1);
                        HealingRoom.health--;
                        lastHeal = System.currentTimeMillis();
                    }
                }
            }

            if (HealingRoom.health == 0) {
                HealingRoom.setFloor(new Texture("rooms/healingroomunsat.png"));
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

    public static PlayerCombat getCombat() {
        return combat;
    }

    public int getCurrentSlot() {
        return currentSlot;
    }

    public void disposer() {
        myTexture.dispose();
    }




}
