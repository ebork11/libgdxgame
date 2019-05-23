package com.apcs.game.enemies;

import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.player.PlayerCombat;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.math.Rectangle;

public class Wizard extends Entity {

    private Rectangle collider;
    private float speed, moveX, moveY, targetX, targetY;
    private boolean moving, firstTime;
    private long moveTimer, shootCooldown;
    String fireDir;

    public Wizard() {
        super();

        speed = 4f;

        fireDir = "";
        moving = false;
        firstTime = false;
        moveTimer = System.currentTimeMillis();
        shootCooldown = System.currentTimeMillis();

        int x = (int)(Math.random() * 550) + 300;
        int y = (int)(Math.random() * 300) + 200;

        collider = new Rectangle(x, y, 100, 100);
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void move() {

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
                RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + getTexture().getWidth() / 2, collider.y + getTexture().getHeight() / 2, 1, 10, fireDir, 500));
                shootCooldown = System.currentTimeMillis();
            }
        }
    }

    public void dropItems() {

    }
}
