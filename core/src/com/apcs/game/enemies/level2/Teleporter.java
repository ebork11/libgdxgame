package com.apcs.game.enemies.level2;

import com.apcs.game.GameMain;
import com.apcs.game.enemies.Entity;
import com.apcs.game.enemies.level1.Charger;
import com.apcs.game.player.PlayerCombat;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.math.Rectangle;

public class Teleporter extends Charger {
    Rectangle collider;
    private float moveX, moveY;
    private long moveCooldown, lastMove, teleportTimer;
    private boolean needToAttack;

    public Teleporter() {

        needToAttack = false;
        moveCooldown = 3500;
        teleportTimer = 500;
        lastMove = System.currentTimeMillis();

        collider = getCollider();
    }

    @Override
    public void move() {

        if (System.currentTimeMillis() - GameMain.enteredNewRoom > 700) {
            if (!needToAttack && System.currentTimeMillis() - lastMove > moveCooldown) {
                moveX = PlayerHandler.getCollider().x;
                moveY = PlayerHandler.getCollider().y;

                RoomManager.getCurrentRoom().getEntities().add(new TeleporterDecoy(moveX, moveY));

                lastMove = System.currentTimeMillis();
                needToAttack = true;
            } else if (needToAttack && System.currentTimeMillis() - lastMove > teleportTimer) {
                collider.x = moveX;
                collider.y = moveY;
                needToAttack = false;

                if (collider.overlaps(PlayerHandler.getCollider())) {
                    PlayerCombat.takeDamage(3);
                }
            }
        }
    }

    @Override
    public void attack() {
        // do nothing
    }


}
