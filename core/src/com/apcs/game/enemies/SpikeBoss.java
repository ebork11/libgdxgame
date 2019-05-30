package com.apcs.game.enemies;

import com.apcs.game.GameMain;
import com.apcs.game.items.*;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class SpikeBoss extends Entity {

    private Texture text;

    // combat stuff
    private int strength;
    private long inColl, moveCooldown, lastMove, attackCooldown, shootTimer;
    private Rectangle collider;
    private boolean isHit;
    public static boolean needToAttack;
    private float speed, moveX, moveY;
    private int x, y, shot;

    public SpikeBoss(){
        super();
        // basics
        text = new Texture("clark.png");

        // combat stuff
        strength = 2;
        setHealth(50);
        isHit = false;
        needToAttack = false;
        shot = 1;
        inColl = System.currentTimeMillis();
        moveCooldown = 10000;
        attackCooldown = 1000;
        lastMove = System.currentTimeMillis();
        shootTimer = System.currentTimeMillis();

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

            if (!needToAttack) {
                shootProjectiles();
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

    public void shootProjectiles() {
        if (System.currentTimeMillis() - shootTimer > 450) { // shoot
            switch (shot) {
                case 1:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x, collider.y, 2, 5, "up", 400, "rock"));
                    break;
                case 2:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x, collider.y, 2, 5, "ne", 500, "rock"));
                    break;
                case 3:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x, collider.y, 2, 5, "right", 400, "rock"));
                    break;
                case 4:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x, collider.y, 2, 5, "se", 500, "rock"));
                    break;
                case 5:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x, collider.y, 2, 5, "down", 400, "rock"));
                    break;
                case 6:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x, collider.y, 2, 5, "sw", 500, "rock"));
                    break;
                case 7:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x, collider.y, 2, 5, "left", 400, "rock"));
                    break;
                case 8:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x, collider.y, 2, 5, "nw", 500, "rock"));
                    break;
                default:
                    break;
            }
            shootTimer = System.currentTimeMillis();

            if (shot < 8)
                shot++;
            else
                shot = 1;
        }
    }

    public Rectangle getCollider() {return collider;}

    public void dropItems(){
        ArrayList<Item> droppable = new ArrayList<Item>();

        droppable.add(new Staff());
        //droppable.add(new Dagger());
        //droppable.add(new Bow());
        droppable.add(new SwordT2());
        //droppable.add(new Wand());

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
