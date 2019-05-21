package com.apcs.game.enemies;

import com.apcs.game.items.Armor;
import com.apcs.game.items.FatSword;
import com.apcs.game.items.HealthPotion;
import com.apcs.game.items.Item;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Basic extends Entity {

    private Rectangle collider;
    private float speed;

    public Basic() {
        super();

        //movement
        speed = 4f;

        int x = (int)(Math.random() * 550) + 300;
        int y = (int)(Math.random() * 300) + 200;

        collider = new Rectangle(x, y, 100, 100);
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

    public void move() {
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
