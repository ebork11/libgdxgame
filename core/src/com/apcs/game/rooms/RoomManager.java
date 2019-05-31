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

    private Texture uncleared, cleared, playerloc, healthroom;

    public RoomManager() {
        lg = new LevelGeneration();

        uncleared = new Texture("rooms/unclearedroom.png");
        cleared = new Texture("rooms/clearedroom.png");
        healthroom = new Texture("rooms/healingRoomBox.png");
        playerloc = new Texture("rooms/playerloc.png");
    }

    public static void setCurrentRoom(Room room) {
        if (room instanceof HealingRoom) {
            GameMain.enterHealing = true;
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

    public Texture getPlayer() {
        return playerloc;
    }

}
