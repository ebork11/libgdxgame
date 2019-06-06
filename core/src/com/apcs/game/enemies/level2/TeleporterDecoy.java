package com.apcs.game.enemies.level2;

import com.apcs.game.enemies.level1.Charger;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;

public class TeleporterDecoy extends Charger {

    Texture tex;
    private long creation;

    public TeleporterDecoy(float x, float y) {

        getCollider().x = x;
        getCollider().y = y;
        creation = System.currentTimeMillis();

        setHealth(1000);

        tex = new Texture("enemies/teleporter/teleportermark.png");
    }

    @Override
    public void move() {
        if (System.currentTimeMillis() - creation > 500) {
            RoomManager.getCurrentRoom().getEntities().remove(this);
        }
    }

    @Override
    public Texture getTexture() {
        return tex;
    }

    @Override
    public Texture getHitTex() {
        return tex;
    }

    @Override
    public void attack() {

    }
}
