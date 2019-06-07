package com.apcs.game.enemies.level2;

import com.apcs.game.EnemyAnimation;
import com.apcs.game.GameMain;
import com.apcs.game.enemies.level1.SpikeBoss;
import com.apcs.game.items.HealthPotion;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.items.weapons.*;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class SpikeBossT2 extends SpikeBoss {

    private Texture text;

    // combat stuff
    private int strength;
    private long inColl, moveCooldown, lastMove, attackCooldown, shootTimer;
    private Rectangle collider;
    private boolean isHit;
    private boolean needToAttack;
    private float speed, moveX, moveY;
    private int x, y, shot;

    public SpikeBossT2() {
        super();

        setHealth(150);
        text = new Texture("enemies/spikeboss/boss1.png");
        shot = 1;
        shootTimer = System.currentTimeMillis();

        collider = getCollider();
    }

    @Override
    public void shootProjectiles() {
        if (System.currentTimeMillis() - shootTimer > 400) { // shoot
            switch (shot) {
                case 1:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 10, "up", 400, "rock"));
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 10, "right", 400, "rock"));
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 10, "down", 400, "rock"));
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 10, "left", 400, "rock"));

                    break;
                case 2:
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 8, "ne", 500, "rock"));
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 8, "se", 500, "rock"));
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 8, "sw", 500, "rock"));
                    RoomManager.getCurrentRoom().getEnemProj().add(new Projectile(collider.x + (text.getWidth() / 2), collider.y + (text.getHeight() / 2), 2, 8, "nw", 500, "rock"));

                    break;
                default:
                    break;
            }
            shootTimer = System.currentTimeMillis();

            if (shot < 2)
                shot++;
            else
                shot = 1;
        }
    }

    public void dropItems(){
        GameMain.gameWon = true;
    }
}
