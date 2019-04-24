package com.apcs.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.apcs.game.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GameMain(), config);

		config.width = 1280; // setting the width of the window
		config.height = 720; // setting the height of the window

		config.title = "Dunlea Dash"; // setting the name of the window in the top
	}
}
