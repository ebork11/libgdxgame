package com.apcs.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMain extends ApplicationAdapter {

	private PlayerHandler player;
	private RoomManager rm;

	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new PlayerHandler();
		rm = new RoomManager();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1); // sets the basic background color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playerManage(); // handles everything regarding the player

		batch.begin(); // beginning of where everything is drawn
		batch.draw(rm.getCurrentRoom().getFloor(), 35, 50); // draw the room floor
		batch.draw(player.getTexture(), player.getCollider().x, player.getCollider().y); // draws the player at the colliders location
		drawInventory();
		batch.end(); // ending of where everything is drawn
	}


	/*
		Handles all player things i.e. movement
	 */
	public void playerManage() {
		player.movementHandler(); // checks the keyboard for input and moves the player accordingly
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

		for (int cnt = 0; cnt < player.getInventory().getInventory().length; cnt++) {
			if (player.getInventory().getInventory()[cnt] != null) {
				batch.draw(player.getInventory().getInventory()[cnt].getTexture(), 1090 + (cnt * (17 + player.getInventory().getInventory()[cnt].getTexture().getWidth())) , 9);
			}
		}
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		player.disposer(); // disposes all of the players textures
		rm.getCurrentRoom().disposer(); // disposes the textures for the room
	}
}
