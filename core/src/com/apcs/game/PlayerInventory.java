package com.apcs.game;

import com.apcs.game.items.Item;

public class PlayerInventory {

    private int inventorySlots;
    private Item[] inventory;


    public PlayerInventory() {
        inventorySlots = 4;
        inventory = new Item[inventorySlots];
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
        Item removedItem = inventory[slot]; // saving the object so we can return it later
        inventory[slot] = null; // sets the slot to null aka empty

        return removedItem; // returns the item we removed
    }




}
