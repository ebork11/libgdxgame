package com.apcs.game.rooms;

import com.apcs.game.GameMain;
import com.apcs.game.rooms.DefaultRoom;
import com.apcs.game.rooms.Room;
import com.apcs.game.rooms.StartingRoom;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;

public class RoomManager {
    private LevelGeneration lg;
    private static Room currentRoom;

    private Texture uncleared, cleared, playerloc, healthroom, uncleared2;

    public RoomManager() {
        lg = new LevelGeneration();

        uncleared = new Texture("rooms/unclearedroom.png");
        uncleared2 = new Texture("rooms/unclearedlevel2.png");
        cleared = new Texture("rooms/clearedroom.png");
        healthroom = new Texture("rooms/healingRoomBox.png");
        playerloc = new Texture("rooms/playerloc.png");
    }

    public static void setCurrentRoom(Room room) {
        if (room instanceof HealingRoom) {
            if (room.getRoomLevel() == 1) {
                GameMain.enterHealing = true;
            } else {
                GameMain.enterHealingTwo = true;
            }
        }
        currentRoom = room;
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public Texture getHealthroom(){return healthroom;}

    public Texture getCleared() {
        return cleared;
    }

    public Texture getUncleared() {
        return uncleared;
    }

    public Texture getUncleared2() {
        return uncleared2;
    }

    public Texture getPlayer() {
        return playerloc;
    }

}
