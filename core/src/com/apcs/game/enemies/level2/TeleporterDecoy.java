package com.apcs.game.enemies.level2;

import com.apcs.game.enemies.level1.Charger;
import com.apcs.game.rooms.RoomManager;

public class TeleporterDecoy extends Charger {

    private long creation;

    public TeleporterDecoy(float x, float y) {

        getCollider().x = x;
        getCollider().y = y;
        creation = System.currentTimeMillis();

        System.out.println("Added new one");
    }

    @Override
    public void move() {
        if (System.currentTimeMillis() - creation > 500) {
            RoomManager.getCurrentRoom().getEntities().remove(this);
        }
    }

    @Override
    public void attack() {

    }
}
