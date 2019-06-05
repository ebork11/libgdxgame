package com.apcs.game.enemies.level2;

import com.apcs.game.GameMain;
import com.apcs.game.enemies.level1.Wizard;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.math.Rectangle;

public class Archer extends Wizard {

    private Rectangle collider;
    private long moveTimer, shootCooldown;
    private boolean moving, firstTime;
    private float speed, moveX, moveY;
    String fireDir;

    public Archer() {
        super();

        moveTimer = 1000;
        shootCooldown = 1000;
        fireDir = "";
        speed = 5f;
        setHealth(20);

        collider = getCollider();
    }

    @Override
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
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + getTexture().getWidth() / 2, collider.y + getTexture().getHeight() / 2, 2, 10, fireDir, 800, "arrow"));
                    shootCooldown = System.currentTimeMillis();
                }
            }
        }
    }
}
