package com.apcs.game.enemies.level1;

import com.apcs.game.EnemyAnimation;
import com.apcs.game.GameMain;
import com.apcs.game.enemies.Entity;
import com.apcs.game.items.HealthPotion;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.items.weapons.Item;
import com.apcs.game.items.weapons.SpearT2;
import com.apcs.game.items.weapons.Staff;
import com.apcs.game.items.weapons.SwordT2;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.player.PlayerInventory;
import com.apcs.game.rooms.HealingRoom;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class SpikeBoss extends Entity {

    private Texture text;
    private EnemyAnimation animation;

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
        text = new Texture("enemies/spikeboss/boss1.png");

        // combat stuff
        strength = 1;
        setHealth(75);
        isHit = false;
        needToAttack = false;
        shot = 1;
        inColl = System.currentTimeMillis();
        moveCooldown = 5000;
        attackCooldown = 400;
        lastMove = System.currentTimeMillis();
        shootTimer = System.currentTimeMillis();

        speed = 15f;
        x = 500;
        y = 500;

        ArrayList<Texture> anim = new ArrayList<Texture>();
        anim.add(new Texture("enemies/spikeboss/boss1.png"));
        anim.add(new Texture("enemies/spikeboss/boss2.png"));
        anim.add(new Texture("enemies/spikeboss/boss3.png"));
        anim.add(new Texture("enemies/spikeboss/boss4.png"));
        anim.add(new Texture("enemies/spikeboss/boss5.png"));

        ArrayList<Texture> hit = new ArrayList<Texture>();
        hit.add(new Texture("enemies/spikeboss/boss1hit.png"));
        hit.add(new Texture("enemies/spikeboss/boss2hit.png"));
        hit.add(new Texture("enemies/spikeboss/boss3hit.png"));
        hit.add(new Texture("enemies/spikeboss/boss4hit.png"));
        hit.add(new Texture("enemies/spikeboss/boss5.png"));

        animation = new EnemyAnimation(anim, hit, 175);

        collider = new Rectangle(x, y, text.getWidth(),text.getHeight());
    }

    public void move(){
        if (System.currentTimeMillis() - GameMain.enteredNewRoom > 700) {
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
        if (System.currentTimeMillis() - shootTimer > 400) { // shoot
            switch (shot) {
                case 1:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 10, "up", 400, "rock"));
                    break;
                case 2:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 8, "ne", 500, "rock"));
                    break;
                case 3:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 10, "right", 400, "rock"));
                    break;
                case 4:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 8, "se", 500, "rock"));
                    break;
                case 5:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 10, "down", 400, "rock"));
                    break;
                case 6:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 8, "sw", 500, "rock"));
                    break;
                case 7:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 10, "left", 400, "rock"));
                    break;
                case 8:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 8, "nw", 500, "rock"));
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
        droppable.add(new SwordT2());
        droppable.add(new SpearT2());

        Item temp = droppable.get((int)(Math.random() * droppable.size()));

        Item temp2 = new HealthPotion();

        temp.getCollider().setPosition(collider.x, collider.y);

        temp2.getCollider().setPosition(100, 100);

        RoomManager.getCurrentRoom().getGroundItems().add(temp);

        RoomManager.getCurrentRoom().getGroundItems().add(temp2);

    }


    public boolean isHit(){
        return isHit;
    }

    public void nowHit(){
        isHit = !isHit;
    }

    public Texture getHitTex(){
        return animation.getHitTex();
    }

    public Texture getTexture() {
        if (System.currentTimeMillis() - lastMove > moveCooldown - 150) {
            return animation.getTexture();
        } else if (needToAttack) {
            return animation.getTextureReverse();
        } else {
            return text;
        }

    }


    public void hit(int damage)  {
        super.hit(damage);

        GameMain.beatFirstBoss = true;
    }

}
