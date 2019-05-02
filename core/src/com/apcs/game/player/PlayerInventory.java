package com.apcs.game.player;

import com.apcs.game.items.Item;
import com.badlogic.gdx.graphics.Texture;

public class PlayerInventory {
    private Texture invTexture;
    private Texture equipTex;

    private int inventorySlots;
    private Item[] inventory;

    private static Item weapon, armor;


    public PlayerInventory() {
        inventorySlots = 3;
        inventory = new Item[inventorySlots];

        invTexture = new Texture("core/assets/inventory.png");
        equipTex = new Texture("core/assets/armorweaponslot.png");

        weapon = null;
        armor = null;
    }

    /*
        Returns the players inventory
     */
    public Item[] getInventory() {
        return inventory;
    }

    /*
        Attempts to add an item to the players inventory
        returns true if the item was added successfully, returns false if the item was unable to be added due to a full inventory
     */
    public boolean addItem(Item toAdd) {
        for (int cnt = 0; cnt < inventorySlots; cnt++) { // checks all inventory slots
            if (inventory[cnt] == null) { // if the inventory slot is empty
                inventory[cnt] = toAdd; // sets the inventory slot to the item
                return true; // item was successfully added to the inventory
            }
        }
        return false; // item was unable to be added to the inventory
    }

    /*
        Attempts to remove an item from the inventory
        returns the removed item
     */
    public Item removeItem(int slot) {
        if (inventory[slot] != null) {
            inventory[slot].drop();
        }

        Item removedItem = inventory[slot]; // saving the object so we can return it later
        inventory[slot] = null; // sets the slot to null aka empty

        return removedItem; // returns the item we removed
    }

    public void equip(int slot) {
        if (inventory[slot] != null) {
            if (inventory[slot].getItemClass().equalsIgnoreCase("weapon")) {
                if (weapon == null) {
                    weapon = inventory[slot];
                    inventory[slot] = null;
                } else {
                    Item temp = weapon;
                    weapon = inventory[slot];
                    inventory[slot] = temp;
                }
            } else if (inventory[slot].getItemClass().equalsIgnoreCase("armor")) {
                if (armor == null) {
                    armor = inventory[slot];
                    inventory[slot] = null;
                } else {
                    Item temp = armor;
                    armor = inventory[slot];
                    inventory[slot] = temp;
                }
            }
        } else {
            inventory[slot] = weapon;
            weapon = null;
        }
    }

    public static Item getWeapon() {
        return weapon;
    }

    public static Item getArmor() {
        return armor;
    }

    public Texture getInvTexture() {
        return invTexture;
    }

    public Texture getEquipTex() {
        return equipTex;
    }


}
