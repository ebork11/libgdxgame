package com.apcs.game.items;

import com.apcs.game.player.PlayerCombat;
import com.apcs.game.player.PlayerHandler;
import com.badlogic.gdx.graphics.Texture;

public class HealthPotion extends Item {

    private String myClass;
    private Texture texU, icon;
    private int healthBonus;


    public HealthPotion() {
        super();

        myClass = "";
        healthBonus = 2;

        texU = new Texture("rooms/clearedroom.png");
        icon = new Texture("rooms/clearedroom.png");

        setTextureU(texU);
        setIcon(icon);

        setClass("consumable");

    }

    public int usePot() {
        if (PlayerCombat.getHealth() + healthBonus <= 8) {
            return healthBonus;
        } else {
            return 8 - PlayerCombat.getHealth();
        }
    }

    public void setClass(String newClass) {
        myClass = newClass;
    }

    public String getItemClass() {
        return myClass;
    }
}
