package com.apcs.game;

import com.apcs.game.enemies.Entity;
import com.apcs.game.items.Item;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import player.PlayerHandler;

import java.util.ArrayList;

public class GameMain extends ApplicationAdapter {

	// libgdx batch
	private static SpriteBatch batch;

	// class accessors
	private PlayerHandler player;
	private RoomManager rm;

	// arraylists to hold present objects
	public static ArrayList<Item> groundItems;
	public static ArrayList<Entity> entities;

	// inventory outline texture
	private Texture invSelectTex;

	//drawing weapon during combat
	public static boolean attacking = false;
	public static Texture wepTex;
	public static float wepX;
	public static float wepY;

	// drawing doors
	private Texture closedDoor;
	private Texture openDoor;

	@Override
	public void create () {
		batch = new SpriteBatch();

		player = new PlayerHandler();
		rm = new RoomManager();

		groundItems = new ArrayList<Item>();
		entities = new ArrayList<Entity>();

		invSelectTex = new Texture("core/assets/items/outlineselection.png");

		closedDoor = new Texture("core/assets/rooms/closeddoor.png");
		openDoor = new Texture("core/assets/rooms/opendoor.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1); // sets the basic background color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playerManage(); // handles everything regarding the player

		batch.begin(); // beginning of where everything is drawn

		batch.draw(rm.getCurrentRoom().getFloor(), 0, 0); // draw the room floor
		drawDoors();

		drawItems(); // drawing items on ground

		drawEntities(); // drawing enemies

		batch.draw(player.getTexture(), player.getCollider().x - (player.getTexture().getWidth() / 4), player.getCollider().y); // draws the player at the colliders location

		if (attacking) {
			drawWeapon();
		}

		drawInventory(); // drawing right side stuff

		batch.end(); // ending of where everything is drawn
	}


	/*
		Handles all player things i.e. movement
	 */
	public void playerManage() {
		player.movementHandler(); // checks the keyboard for input and moves the player accordingly
		player.checkForPickup();
		player.getCombat().checkAttack();
	}

	/*
		Manages the spawning of rooms
	 */
	public void drawDoors() {
		if (entities.size() > 0) {
			batch.draw(closedDoor, 500 - closedDoor.getWidth() / 2, 680);
		} else {
			batch.draw(openDoor, 500 - closedDoor.getWidth() / 2, 680);
		}
	}

	/*
		Where the players inventory in the bottom right is drawn with the items in it
	 */
	public void drawInventory() {
		batch.draw(player.getInventory().getInvTexture(), 1080, 0);
		batch.draw(player.getInventory().getEquipTex(), 1000,0);

		for (int cnt = 0; cnt < player.getInventory().getInventory().length; cnt++) {
			if (player.getInventory().getInventory()[cnt] != null) {
				batch.draw(player.getInventory().getInventory()[cnt].getIcon(), 1096 + (cnt * (17 + 46)) , 29);
			}
			if (cnt == player.getCurrentSlot()) { // draws current player inventory slot selection
				batch.draw(invSelectTex, 1094 + (cnt * (14 + 46)), 27);
			}
		}

		if (player.getInventory().getWeapon() != null) {
			batch.draw(player.getInventory().getWeapon().getIcon(), 1019, 81);
		} if (player.getInventory().getArmor() != null) {
			batch.draw(player.getInventory().getArmor().getIcon(), 1019, 16);
		}

	}

	public void drawItems()
	{
		for(int loop = 0; loop < groundItems.size(); loop++)
		{
			batch.draw(groundItems.get(loop).getIcon(), groundItems.get(loop).getX(), groundItems.get(loop).getY());
		}
	}

	public void drawEntities()
    {
        for(int loop = 0; loop < entities.size(); loop++)
        {
            entities.get(loop).move();
            batch.draw(entities.get(loop).getTexture(), entities.get(loop).getCollider().x, entities.get(loop).getCollider().y);

            if (entities.get(loop).getCollider().overlaps(player.getCollider())) {
            	entities.get(loop).attack();
			}
        }
    }

	public static void ifEnemyHit()
    {
        for(int loop = 0; loop <entities.size(); loop++)
        {
            if(PlayerHandler.getInventory().getWeapon().getCollider().overlaps(entities.get(loop).getCollider()))
            {
                entities.get(loop).hit(1);
            }
        }

    }

    public static void drawWeapon() {
		batch.draw(wepTex, wepX, wepY);
	}

	@Override
	public void dispose () {
		batch.dispose();
		player.disposer(); // disposes all of the players textures
		rm.getCurrentRoom().disposer(); // disposes the textures for the room
	}
}
