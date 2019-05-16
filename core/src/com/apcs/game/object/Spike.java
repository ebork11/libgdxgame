package com.apcs.game.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Spike {

    private int damage;
    private Texture text;
    private Rectangle collider;

    public Spike(int x, int y) {
        damage = 1;

        if ((int)(Math.random() * 2) + 1 == 1) {
            text = new Texture("objects/spikes/spikes.png");
        } else {
            text = new Texture("objects/spikes/spikes2.png");
        }


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
