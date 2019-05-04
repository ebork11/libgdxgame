package com.apcs.game.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Spike {

    private int damage;
    private Texture text;
    private Rectangle collider;

    public Spike() {
        damage = 1;
        text = new Texture("objects/bigspikes.png");
        collider = new Rectangle(500, 300, text.getWidth(), text.getHeight());
    }

    public Spike(int x, int y) {
        damage = 1;
        text = new Texture("objects/bigspikes.png");
        collider = new Rectangle(x, y, text.getWidth(), text.getHeight());
    }

    public Texture getIcon(){
        return text;
    }

    public int getDamage(){
        return damage;
    }

    public Rectangle getCollider(){
        return collider;
    }

}
