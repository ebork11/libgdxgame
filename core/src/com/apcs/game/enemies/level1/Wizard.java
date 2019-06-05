package com.apcs.game.enemies.level1;

import com.apcs.game.EnemyAnimation;
import com.apcs.game.GameMain;
import com.apcs.game.enemies.Entity;
import com.apcs.game.items.HealthPotion;
import com.apcs.game.items.armor.Armor;
import com.apcs.game.items.armor.ArmorT2;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.items.weapons.*;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Wizard extends Entity {

    private Sound hurt, spell;
    private EnemyAnimation animation;
    private Rectangle collider;
    private float speed, moveX, moveY, targetX, targetY;
    private boolean moving, firstTime, isHit;
    private long moveTimer, shootCooldown;
    String fireDir;

    public Wizard() {
        super();

        hurt = Gdx.audio.newSound(Gdx.files.internal("sounds/wizardhit.mp3"));
        spell = Gdx.audio.newSound(Gdx.files.internal("sounds/shootspell.mp3"));
        setHurt(hurt);
        speed = 3f;
        setHealth(8);
        isHit = false;
        fireDir = "";
        moving = false;
        firstTime = false;
        moveTimer = System.currentTimeMillis();
        shootCooldown = System.currentTimeMillis();

        int x = (int)(Math.random() * 550) + 300;
        int y = (int)(Math.random() * 300) + 200;
        collider = new Rectangle(x, y, 100, 100);

        ArrayList<Texture> anim = new ArrayList<Texture>();
        anim.add(new Texture("enemies/wizard/wizard1.png"));
        anim.add(new Texture("enemies/wizard/wizard2.png"));
        anim.add(new Texture("enemies/wizard/wizard3.png"));
        anim.add(new Texture("enemies/wizard/wizard4.png"));
        anim.add(new Texture("enemies/wizard/wizard5.png"));
        anim.add(new Texture("enemies/wizard/wizard6.png"));
        anim.add(new Texture("enemies/wizard/wizard7.png"));
        anim.add(new Texture("enemies/wizard/wizard8.png"));

        ArrayList<Texture> hit = new ArrayList<Texture>();
        hit.add(new Texture("enemies/wizard/hit/wizhit1.png"));
        hit.add(new Texture("enemies/wizard/hit/wizhit2.png"));
        hit.add(new Texture("enemies/wizard/hit/wizhit3.png"));
        hit.add(new Texture("enemies/wizard/hit/wizhit4.png"));
        hit.add(new Texture("enemies/wizard/hit/wizhit5.png"));
        hit.add(new Texture("enemies/wizard/hit/wizhit6.png"));
        hit.add(new Texture("enemies/wizard/hit/wizhit7.png"));
        hit.add(new Texture("enemies/wizard/hit/wizhit8.png"));

        animation = new EnemyAnimation(anim, hit);
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void move() {

        if (System.currentTimeMillis() - GameMain.enteredNewRoom > 700) {
            if (System.currentTimeMillis() - moveTimer > 2000) {
                moving = true;
                moveTimer = System.currentTimeMillis();
            }

            if (moving) {


                if (!firstTime) {
                    firstTime = true;

                    moveX = collider.x - PlayerHandler.getCollider().x;
                    moveY = collider.y - PlayerHandler.getCollider().y;
                }


                if (Math.abs(moveX) < Math.abs(moveY)) {
                    if (moveX < 0) {
                        collider.x += speed;
                    } else {
                        collider.x -= speed;
                    }

                    if (moveY < 0) {
                        fireDir = "up";
                    } else {
                        fireDir = "down";
                    }

                    if (Math.abs(collider.x - PlayerHandler.getCollider().x) <= 10) {
                        moving = false;
                        firstTime = false;
                        moveTimer = System.currentTimeMillis();
                    }

                } else {
                    if (moveY < 0) {
                        collider.y += speed;
                    } else {
                        collider.y -= speed;
                    }

                    if (moveX < 0) {
                        fireDir = "right";
                    } else {
                        fireDir = "left";
                    }

                    if (Math.abs(collider.y - PlayerHandler.getCollider().y) <= 10) {
                        moving = false;
                        firstTime = false;
                    }
                }

                if (System.currentTimeMillis() - shootCooldown > 700) {
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + getTexture().getWidth() / 2, collider.y + getTexture().getHeight() / 2, 1, 10, fireDir, 500, "spell"));
                    shootCooldown = System.currentTimeMillis();
                    spell.play(1.0f);
                }
            }
        }
    }

    public void dropItems() {
        int ifDrop = (int)(Math.random() * 100)+1;

        if (ifDrop <= 40) {
            ArrayList<Item> droppable = new ArrayList<Item>();

            ifDrop = (int)(Math.random() * 100) +1;
            if (droppable.size() == 0 && ifDrop <= 85) {
                droppable.add(new Wand());
                droppable.add(new Bow());
                droppable.add(new ArmorT2());
            }else if (droppable.size() == 0){
                droppable.add(new Staff());
            }

            Item temp = droppable.get((int)(Math.random() * droppable.size()));

            temp.getCollider().setPosition(collider.x, collider.y);

            RoomManager.getCurrentRoom().getGroundItems().add(temp);
        }
    }


    public void attack() {
        // override the wizards attack so it cannot melee
    }

    public boolean isHit() {
        return isHit;
    }

    public void nowHit() {
        isHit = !isHit;
    }

    public Texture getTexture() {
        return animation.getTexture();
    }

    public Texture getHitTex() {
        return animation.getHitTex();
    }

}
