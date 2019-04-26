package com.apcs.game;

import com.apcs.game.items.Item;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class GameMain extends ApplicationAdapter {

	private Texture invSelectTex;

	private PlayerHandler player;
	private RoomManager rm;
	public static ArrayList<Item> groundItems;

	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new PlayerHandler();
		rm = new RoomManager();
		groundItems = new ArrayList<Item>();
		invSelectTex = new Texture("core/assets/items/outlineselection.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1); // sets the basic background color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playerManage(); // handles everything regarding the player

		batch.begin(); // beginning of where everything is drawn
		batch.draw(rm.getCurrentRoom().getFloor(), 0, 0); // draw the room floor
		drawItems();
		batch.draw(player.getTexture(), player.getCollider().x, player.getCollider().y); // draws the player at the colliders location
		drawInventory();
		batch.end(); // ending of where everything is drawn
	}


	/*
		Handles all player things i.e. movement
	 */
	public void playerManage() {
		player.movementHandler(); // checks the keyboard for input and moves the player accordingly
		player.checkForPickup();
	}

	/*
		Manages the spawning of rooms
	 */
	public void roomManage() {

	}

	/*
		Where the players inventory in the bottom right is drawn with the items in it
	 */
	public void drawInventory() {
		batch.draw(player.getInventory().getInvTexture(), 1080, 0);
		batch.draw(player.getInventory().getEquipTex(), 1000,0);

		for (int cnt = 0; cnt < player.getInventory().getInventory().length; cnt++) {
			if (player.getInventory().getInventory()[cnt] != null) {
				batch.draw(player.getInventory().getInventory()[cnt].getTextureU(), 1096 + (cnt * (17 + 46)) , 29);
			}
			if (cnt == player.getCurrentSlot()) { // draws current player inventory slot selection
				batch.draw(invSelectTex, 1094 + (cnt * (14 + 46)), 27);
			}
		}

		if (player.getInventory().getWeapon() != null) {
			batch.draw(player.getInventory().getWeapon().getTextureU(), 1019, 81);
		} if (player.getInventory().getArmor() != null) {
			batch.draw(player.getInventory().getArmor().getTextureU(), 1019, 16);
		}

	}

	public void drawItems()
	{
		for(int loop = 0; loop < groundItems.size(); loop++)
		{
			batch.draw(groundItems.get(loop).getTextureU(), groundItems.get(loop).getX(), groundItems.get(loop).getY());
		}
	}

	public void drawAttack() {
		Item wep = player.getInventory().getWeapon();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.disposer(); // disposes all of the players textures
		rm.getCurrentRoom().disposer(); // disposes the textures for the room
	}
}
