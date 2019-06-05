package com.apcs.game.items.armor;

import com.apcs.game.items.weapons.Item;
import com.apcs.game.player.PlayerInventory;
import com.badlogic.gdx.graphics.Texture;

public class Armor extends Item {

    protected String myClass;
    private Texture icon;
    protected int maxHealth, health;

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

    protected void setMaxHealth(int max){ maxHealth = max;}

    protected void setHealth(int hp){ health = hp;}

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
