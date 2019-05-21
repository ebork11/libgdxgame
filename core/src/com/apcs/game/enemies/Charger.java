package com.apcs.game.enemies;

import com.apcs.game.items.Armor;
import com.apcs.game.items.FatSword;
import com.apcs.game.items.HealthPotion;
import com.apcs.game.items.Item;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Charger extends Entity {

    Rectangle collider;
    private int moveCooldown, chargeTime;
    private float moveX, moveY, speed;
    private long chargeCooldown, start;

    public Charger(){
        super();

        Texture temp = new Texture("enemies/charger/charger.png");

        int x = (int)(Math.random() * 550) + 300;
        int y = (int)(Math.random() * 300) + 200;

        collider = new Rectangle(x, y, temp.getWidth(), temp.getHeight());

        setHealth(8);
        setCharacterFirst("enemies/charger/charger.png");
        speed = 15f;
        setStrength(2);
        moveCooldown = 2000;
        chargeTime = 2000;
        chargeCooldown = System.currentTimeMillis();
    }

    public void move() {
        if (System.currentTimeMillis() - chargeCooldown > moveCooldown) {
            setCharacter("enemies/charger/charge1.png");
            walk(moveX, moveY);

            if (System.currentTimeMillis() - start > chargeTime) {
                chargeCooldown = System.currentTimeMillis();
                setCharacter("enemies/charger/charger.png");
            }
        } else {
            moveX = PlayerHandler.getCollider().x - collider.x;
            moveY = PlayerHandler.getCollider().y - collider.y;

            start = System.currentTimeMillis();
        }
    }

    public void walk(float x, float y){

        double ang1 = Math.atan(y / x);
        double ang2 = Math.atan(x / y);
        float sx = (float)(speed * (Math.sin(ang2)));
        float sy = (float)(speed * (Math.sin(ang1)));

        sx = Math.abs(sx);
        sy = Math.abs(sy);

        if (x > 0 && y > 0) {
            if (collider.x + getTexture().getWidth() < 960) {
                collider.x += sx;

            } if (collider.y < 610) {
                collider.y += sy;
            }
        } else if (x < 0 && y > 0) {
            if (collider.x > 40) {
                collider.x -= sx;
            } if (collider.y < 610) {
                collider.y += sy;
            }
        }  else if (x < 0 && y < 0) {
            if (collider.x > 40) {
                collider.x -= sx;
            } if (collider.y > 40) {
                collider.y -= sy;
            }
        }  else if (x > 0 && y < 0) {
            if (collider.x + getTexture().getWidth() < 960) {
                collider.x += sx;
            } if (collider.y > 40) {
                collider.y -= sy;
            }
        }
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void dropItems() {
        int ifDrop = (int)(Math.random() * 10) + 1;

        if (ifDrop <= 4) {
            ArrayList<Item> droppable = new ArrayList<Item>();

            if (droppable.size() == 0) {
                droppable.add(new Armor());
                droppable.add(new HealthPotion());
                droppable.add(new HealthPotion());
                droppable.add(new FatSword());
            }

            Item temp = droppable.get((int)(Math.random() * droppable.size()));

            temp.getCollider().setPosition(collider.x, collider.y);

            RoomManager.getCurrentRoom().getGroundItems().add(temp);
        }
    }
}
