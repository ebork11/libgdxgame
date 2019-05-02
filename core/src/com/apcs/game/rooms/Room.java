package com.apcs.game.rooms;

import com.apcs.game.enemies.Entity;
import com.apcs.game.object.Spike;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public abstract class Room {
    private Texture myFloor;

    // width is 1000, height is 720
    public Room() {
        myFloor = new Texture("core/assets/rooms/background1.png");
    }

    public abstract Texture getFloor();

    public abstract ArrayList<Door> getDoors();

    public abstract ArrayList<Entity> getEntities();

    public abstract ArrayList<Spike> getHazards();

    public void disposer() {
        myFloor.dispose();
    }
}
