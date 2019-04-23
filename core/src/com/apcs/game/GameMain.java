package com.apcs.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMain extends ApplicationAdapter {

	private PlayerHandler player;

	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new PlayerHandler();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1); // sets the basic background color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playerManage(); // handles everything regarding the player

		batch.begin(); // beginning of where everything is drawn
		batch.draw(player.getTexture(), player.getCollider().x, player.getCollider().y); // draws the player at the colliders location
		batch.end(); // ending of where everything is drawn
	}


	public void playerManage() {
		player.movementHandler(); // checks the keyboard for input and moves the player accordingly
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.disposer(); // disposes all of the players textures
	}
}
