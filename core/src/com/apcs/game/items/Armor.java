package com.apcs.game.items;

public class Armor extends Item {

    private String myClass;

    public Armor() {
        myClass = "";
        setClass("armor");
    }

    public void setClass(String newClass) {
        myClass = newClass;
    }

    public String getItemClass() {
        return myClass;
    }
}
