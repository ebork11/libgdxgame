package com.apcs.game.enemies.level1;

import com.apcs.game.EnemyAnimation;
import com.apcs.game.GameMain;
import com.apcs.game.enemies.Entity;
import com.apcs.game.items.armor.Armor;
import com.apcs.game.items.HealthPotion;
import com.apcs.game.items.armor.ArmorT2;
import com.apcs.game.items.armor.ArmorT3;
import com.apcs.game.items.weapons.Item;
import com.apcs.game.items.weapons.SpearT2;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Basic extends Entity {

    private Rectangle collider;
    private float speed;
    private boolean isHit;
   // private Texture hitTex;
    private EnemyAnimation animation;

    public Basic() {
        super();

        isHit = false;
        //hitTex = new Texture("clarkhit.png");

        //movement
        speed = 4f;

        int x = (int)(Math.random() * 550) + 300;
        int y = (int)(Math.random() * 300) + 200;

        ArrayList<Texture> anim = new ArrayList<Texture>();
        anim.add(new Texture("enemies/basic/basic1.png"));
        anim.add(new Texture("enemies/basic/basic2.png"));
        anim.add(new Texture("enemies/basic/basic3.png"));
        anim.add(new Texture("enemies/basic/basic4.png"));
        anim.add(new Texture("enemies/basic/basic5.png"));
        anim.add(new Texture("enemies/basic/basic6.png"));
        anim.add(new Texture("enemies/basic/basic7.png"));
        anim.add(new Texture("enemies/basic/basic8.png"));

        ArrayList<Texture> hit = new ArrayList<Texture>();
        hit.add(new Texture("enemies/basic/basic1hit.png"));
        hit.add(new Texture("enemies/basic/basic2hit.png"));
        hit.add(new Texture("enemies/basic/basic3hit.png"));
        hit.add(new Texture("enemies/basic/basic4hit.png"));
        hit.add(new Texture("enemies/basic/basic5hit.png"));
        hit.add(new Texture("enemies/basic/basic6hit.png"));
        hit.add(new Texture("enemies/basic/basic7hit.png"));
        hit.add(new Texture("enemies/basic/basic8hit.png"));

        collider = new Rectangle(x, y, anim.get(0).getWidth(), anim.get(0).getHeight());

        animation = new EnemyAnimation(anim, hit);
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void dropItems() {
        int ifDrop = (int)(Math.random() * 100)+1;

        if (ifDrop <= 25) {
            ArrayList<Item> droppable = new ArrayList<Item>();

            ifDrop = (int)(Math.random() * 100) +1;
            if (droppable.size() == 0 && ifDrop <= 90) {
                droppable.add(new Armor());
                droppable.add(new ArmorT2());
                droppable.add(new HealthPotion());
            }else if (droppable.size() == 0){
                droppable.add(new ArmorT3());
            }

            Item temp = droppable.get((int)(Math.random() * droppable.size()));

            temp.getCollider().setPosition(collider.x, collider.y);

            RoomManager.getCurrentRoom().getGroundItems().add(temp);
        }
    }

    public void move() {
        if (System.currentTimeMillis() - GameMain.enteredNewRoom > 700) {
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

    public boolean isHit() {
        return isHit;
    }

    public void nowHit() {
        isHit = !isHit;
    }

    public Texture getHitTex() {
        return animation.getHitTex();
    }

    public Texture getTexture() {
        return animation.getTexture();
    }
}
