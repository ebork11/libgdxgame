package com.apcs.game.items;

import com.apcs.game.player.PlayerInventory;
import com.badlogic.gdx.graphics.Texture;

public class Armor extends Item {

    private String myClass;
    private Texture icon;
    private int maxHealth, health;

    public Armor() {
        icon = new Texture("items/armor.png");
        setIcon(icon);

        maxHealth = 2;
        health = maxHealth;

        myClass = "";
        setClass("armor");
    }

    public int getStat() {
        return health;
    }

    public int getMaxHealth(){ return maxHealth;}

    public void damage(int dam) {
        health -= dam;
    }

    public void repair(int amount){ health += amount;}

    public void setClass(String newClass) {
        myClass = newClass;
    }

    public String getItemClass() {
        return myClass;
    }

    public static void breakArmor() {
        PlayerInventory.setArmor(null);
    }
}
