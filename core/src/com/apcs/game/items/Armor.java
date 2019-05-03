package com.apcs.game.items;

import com.apcs.game.player.PlayerInventory;
import com.badlogic.gdx.graphics.Texture;

public class Armor extends Item {

    private String myClass;
    private Texture icon;
    private int maxHealth, health;

    public Armor() {
        icon = new Texture("core/assets/items/armor.png");
        setIcon(icon);

        maxHealth = 2;
        health = maxHealth;

        myClass = "";
        setClass("armor");
    }

    public int getStat() {
        return health;
    }

    public void damage(int dam) {
        health -= dam;
    }

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
