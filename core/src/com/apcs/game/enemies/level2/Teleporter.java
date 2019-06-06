package com.apcs.game.enemies.level2;

import com.apcs.game.EnemyAnimation;
import com.apcs.game.GameMain;
import com.apcs.game.enemies.Entity;
import com.apcs.game.enemies.level1.Charger;
import com.apcs.game.items.armor.ArmorT3;
import com.apcs.game.items.weapons.*;
import com.apcs.game.player.PlayerCombat;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Teleporter extends Charger {
    Rectangle collider;
    EnemyAnimation animation;
    private float moveX, moveY;
    private long moveCooldown, lastMove, teleportTimer;
    private boolean needToAttack;

    public Teleporter() {

        needToAttack = false;
        moveCooldown = 1750;
        teleportTimer = 500;
        lastMove = System.currentTimeMillis();
        setHealth(25);

        int x = (int)(Math.random() * 550) + 300;
        int y = (int)(Math.random() * 300) + 200;

        ArrayList<Texture> anim = new ArrayList<Texture>();
        anim.add(new Texture("enemies/teleporter/teleporter1.png"));

        ArrayList<Texture> hit = new ArrayList<Texture>();
        hit.add(new Texture("enemies/teleporter/teleporter1hit.png"));

        collider = new Rectangle(x, y, anim.get(0).getWidth(), anim.get(0).getHeight());

        animation = new EnemyAnimation(anim, hit, 80);
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
    @Override
    public void dropItems() {
        int ifDrop = (int)(Math.random() * 100)+1;

        if (ifDrop <= 40) {
            ArrayList<Item> droppable = new ArrayList<Item>();


            if (droppable.size() == 0) {
                droppable.add(new ArmorT3());
                droppable.add(new SpearT2());
                droppable.add(new Staff());
            }

            Item temp = droppable.get((int)(Math.random() * droppable.size()));

            temp.getCollider().setPosition(collider.x, collider.y);

            RoomManager.getCurrentRoom().getGroundItems().add(temp);
        }
    }

    @Override
    public Rectangle getCollider() {
        return collider;
    }

    @Override
    public Texture getTexture() {
        return animation.getTexture();
    }

    @Override
    public Texture getHitTex() {
        return animation.getHitTex();
    }


}
