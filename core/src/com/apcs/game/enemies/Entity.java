package com.apcs.game.enemies;

import com.apcs.game.GameMain;
import com.apcs.game.PlayerHandler;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
    private Rectangle collider;
    private Texture text;
    private int health;
    private int strength;
    private float speed;

    public Entity()
    {
        text = new Texture("core/assets/clark.png");
        collider = new Rectangle(100, 100, text.getWidth(), text.getHeight());
        health = 10;
        strength = 0;
        speed = 5f;
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

    public void move()
    {
        float x = PlayerHandler.getCollider().x - collider.x;
        float y = PlayerHandler.getCollider().y - collider.y;
        double ang1 = Math.atan(y / x);
        double ang2 = Math.atan(x / y);
        float sx = (float)(speed*(Math.sin(ang2)));
        float sy = (float)(speed*(Math.sin(ang1)));

        sx = Math.abs(sx);
        sy = Math.abs(sy);
        if (x > 0 && y > 0) {
            collider.x += sx;
            collider.y += sy;
        } else if (x < 0 && y > 0) {
            collider.x -= sx;
            collider.y += sy;
        }  else if (x < 0 && y < 0) {
            collider.x -= sx;
            collider.y -= sy;
        }  else if (x > 0 && y < 0) {
            collider.x += sx;
            collider.y -= sy;
        }

    }
}
