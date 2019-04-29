package com.apcs.game.enemies;

import com.apcs.game.GameMain;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
    private Rectangle collider;
    private Texture text;
    private int health;
    private int strength;

    public Entity()
    {
        text = new Texture("core/assets/clark.png");
        collider = new Rectangle(100, 100, text.getWidth(), text.getHeight());
        health = 10;
        strength = 0;
    }

    public Rectangle getCollider()
    {
        return collider;
    }

    public Texture getTexture()
    {
        return text;
    }

    public void hit(int damage)
    {
        health -= damage;
        if(health <= 0){
            die();
        }
    }

    public void die()
    {
        GameMain.entities.remove(this);
    }
}
