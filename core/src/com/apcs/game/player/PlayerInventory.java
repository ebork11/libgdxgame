package com.apcs.game.player;

import com.apcs.game.GameMain;
import com.apcs.game.items.*;
import com.badlogic.gdx.graphics.Texture;

public class PlayerInventory {
    private Texture invTexture;
    private Texture equipTex;

    private int inventorySlots;
    private Item[] inventory;

    private static Item weapon;
    private static Armor armor;


    public PlayerInventory() {
        inventorySlots = 3;
        inventory = new Item[inventorySlots];

        invTexture = new Texture("gui/inventory.png");
        equipTex = new Texture("gui/armorweaponslot.png");

        weapon = new Spear(); // starting weapon
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

    public boolean equip(Item it) {
        if (it != null) {
            if (it.getItemClass().equalsIgnoreCase("weapon") || it.getItemClass().equalsIgnoreCase("ranged_weapon")) {
                if (weapon == null) {
                    weapon = it;
                    GameMain.getRm().getCurrentRoom().getGroundItems().remove(it);
                    return true;
                } else {
                    weapon.drop();
                    weapon = it;
                    GameMain.getRm().getCurrentRoom().getGroundItems().remove(it);
                    return true;
                }
            } else if (it.getItemClass().equalsIgnoreCase("armor")) {
                if (armor == null) {
                    armor = (Armor)it;
                    GameMain.getRm().getCurrentRoom().getGroundItems().remove(it);
                    return true;
                } else {
                    armor.drop();
                    armor = (Armor)it;
                    GameMain.getRm().getCurrentRoom().getGroundItems().remove(it);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static Item getWeapon() {
        return weapon;
    }

    public static Armor getArmor() {
        return armor;
    }

    public static void setArmor(Armor m) {
        armor = m;
    }

    public Texture getInvTexture() {
        return invTexture;
    }

    public Texture getEquipTex() {
        return equipTex;
    }


}
