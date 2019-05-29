package com.apcs.game.enemies;

import com.apcs.game.GameMain;
import com.apcs.game.items.*;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class SpikeBoss extends Entity {

    private Texture text;

    // combat stuff
    private int strength;
    private long inColl, moveCooldown, lastMove, attackCooldown;
    private Rectangle collider;
    private boolean isHit;
    public static boolean needToAttack;
    private float speed, moveX, moveY;
    private int x, y;

    public SpikeBoss(){
        super();
        // basics
        text = new Texture("clark.png");

        // combat stuff
        strength = 2;
        setHealth(50);
        isHit = false;
        needToAttack = false;
        inColl = System.currentTimeMillis();
        moveCooldown = 10000;
        attackCooldown = 1000;
        lastMove = System.currentTimeMillis();

        speed = 15f;
        x = 500;
        y = 500;

        collider = new Rectangle(x, y, text.getWidth(),text.getHeight());
    }

    public void move(){
        if (System.currentTimeMillis() - GameMain.enteredNewRoom > 500) {
            if (System.currentTimeMillis() - lastMove > moveCooldown) {
                moveX = PlayerHandler.getCollider().x;
                moveY = PlayerHandler.getCollider().y;

                collider.x = moveX;
                collider.y = moveY;

                lastMove = System.currentTimeMillis();
                inColl = System.currentTimeMillis();
                needToAttack = true;
            }
        }
    }

    public void attack() {
        if (System.currentTimeMillis() - GameMain.enteredNewRoom > 500) {
            if (needToAttack && System.currentTimeMillis() - inColl > attackCooldown) {
                inColl = System.currentTimeMillis();
                PlayerHandler.getCombat().takeDamage(strength);
                needToAttack = false;
            }
        }
    }

    public Rectangle getCollider() {return collider;}

    public void dropItems(){
        ArrayList<Item> droppable = new ArrayList<Item>();

        droppable.add(new Armor());
        droppable.add(new Dagger());
        droppable.add(new Bow());
        droppable.add(new FatSword());
        droppable.add(new Wand());

        Item temp = droppable.get((int)(Math.random() * droppable.size()));

        temp.getCollider().setPosition(collider.x, collider.y);

        RoomManager.getCurrentRoom().getGroundItems().add(temp);
    }


    public boolean isHit(){
        return isHit;
    }

    public void nowHit(){
        isHit = !isHit;
    }

    public Texture getHitTex(){
        return text;
    }

}
