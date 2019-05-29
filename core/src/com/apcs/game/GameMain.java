package com.apcs.game;

import com.apcs.game.enemies.Entity;
import com.apcs.game.enemies.SpikeBoss;
import com.apcs.game.items.FatSword;
import com.apcs.game.items.Item;
import com.apcs.game.items.Wand;
import com.apcs.game.items.projectiles.Projectile;
import com.apcs.game.menu.MenuManager;
import com.apcs.game.object.Spike;
import com.apcs.game.player.PlayerAnimation;
import com.apcs.game.player.PlayerCombat;
import com.apcs.game.player.PlayerInventory;
import com.apcs.game.rooms.HealingRoom;
import com.apcs.game.rooms.LevelGeneration;
import com.apcs.game.rooms.Room;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.apcs.game.player.PlayerHandler;

import java.util.ArrayList;

public class GameMain extends ApplicationAdapter {

	// libgdx batch
	private static SpriteBatch batch;
	private BitmapFont font;

	// class accessors
	private MenuManager mm;
	private PlayerHandler player;
	private static RoomManager rm;
	private PlayerAnimation pa;


	boolean menu, pause, helpMenu;
	public static boolean overItem, enterHealing;
	private Texture invSelectTex; // inventory outline texture

	//drawing weapon during combat
	public static boolean attacking = false, hit = false;
	public static Texture wepTex;
	public static float wepX;
	public static float wepY;
	private long lastHit; // also spike I believe
	private long cooldown; // spike cooldown??? needs to be redone
	public static long enteredNewRoom;

	@Override
	public void create () {

		batch = new SpriteBatch();

		font = new BitmapFont();
		font.setColor(Color.WHITE);

		mm = new MenuManager();
		player = new PlayerHandler();
		rm = new RoomManager();
		pa = new PlayerAnimation();

		enterHealing = false;
		menu = true;
		helpMenu = false;
		pause = false;
		overItem = false;
		invSelectTex = new Texture("items/outlineselection.png");

		lastHit = System.currentTimeMillis(); // spike stuff needs to be redone eventually and line below
		cooldown = 1500;

		enteredNewRoom = System.currentTimeMillis();
	}

	@Override
	/*
		Method that is called every single frame in the game. This is where we draw textures to the screen, calculate and
		formulas, and handle any other changes such as movement and/or combat
	 */
	public void render () {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1); // sets the basic background color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			pause = !pause;
		}

		if (!pause) {
			playerManage(); // handles everything regarding the com.apcs.game.player
		}

		batch.begin(); // beginning of where everything is drawn

		if (menu) {
			renderMenu();
			pause = false;
		} else {
			renderGameLevel();

			if (pause) {
				int mouseY = Math.abs(720 - Gdx.input.getY());

				batch.draw(mm.getQuitButton(), 500, 350);

				if (Gdx.input.getX() > 500 && Gdx.input.getX() < 500 + mm.getQuitButton().getWidth() && mouseY > 350 && mouseY < 350 + mm.getQuitButton().getHeight()) {
					batch.draw(mm.getQuitButtonDown(), 500, 350);
					if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
						Gdx.app.exit();
					}
				}
			}
		}

		batch.end(); // ending of where everything is drawn
	}

	public void renderMenu() {

		batch.draw(mm.getBackground(), 0, 0);
		if (!helpMenu) {
			batch.draw(mm.getPlayButton(), 50, 400);
			batch.draw(mm.getHelpButton(), 50, 225);
			batch.draw(mm.getQuitButton(), 50, 50);
			batch.draw(mm.getTitleAnim(), 50, 550);
		} else {
			batch.draw(mm.getBackButton(), 500, 50);
		}

		int mouseY = Math.abs(720 - Gdx.input.getY());

		//System.out.println(mouseY);

		if (!helpMenu) {
			if (Gdx.input.getX() > 50 && Gdx.input.getX() < 50 + mm.getPlayButton().getWidth() && mouseY > 398 && mouseY < 400 + mm.getPlayButton().getHeight()) {
				batch.draw(mm.getPlayButtonDown(), 50, 400);
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					menu = false;
				}
			} else if (Gdx.input.getX() > 50 && Gdx.input.getX() < 50 + mm.getHelpButton().getWidth() && mouseY > 224 && mouseY < 226 + mm.getHelpButton().getHeight()) {
				batch.draw(mm.getHelpButtonDown(), 50, 225);
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					helpMenu = true;
				}
			} else if (Gdx.input.getX() > 50 && Gdx.input.getX() < 50 + mm.getQuitButton().getWidth() && mouseY > 50 && mouseY < 50 + mm.getQuitButton().getHeight()) {
				batch.draw(mm.getQuitButtonDown(), 50, 50);
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					Gdx.app.exit();
				}
			}
		} else {
			if (Gdx.input.getX() > 500 && Gdx.input.getX() < 500 + mm.getQuitButton().getWidth() && mouseY > 50 && mouseY < 50 + mm.getQuitButton().getHeight()) {
				if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
					helpMenu = false;
				}
			}
		}


	}

	public void renderGameLevel() {
		drawRoom();
		drawItems();
		drawSpikes();
		drawEntities();
		batch.draw(pa.getFrameTex(), player.getCollider().x - (pa.getFrameTex().getWidth() / 4), player.getCollider().y); // draws the com.apcs.game.player at the colliders location
		if (hit){
			batch.draw(pa.hitAnim(), player.getCollider().x - (pa.getFrameTex().getWidth() / 4), player.getCollider().y);
		}
		if (attacking) {
			drawWeapon();
		}
		drawEnemProjectiles();
		if (PlayerHandler.getInventory().getWeapon().getItemClass().equals("ranged_weapon")) {
			drawProjectiles();
		}
		if (overItem) {
			font.draw(batch, "[E] to pick up", player.getCollider().x - 20, player.getCollider().y);
		}
		drawInventory(); // drawing right side stuff
		drawMap();
	}

	/*
		Handles all player things i.e. movement
	 */
	public void playerManage() {
		player.movementHandler(); // checks the keyboard for input and moves the com.apcs.game.player accordingly
		player.checkForPickup();
		if (HealingRoom.health != 0) {
			player.checkStandingHealth();
		}
		player.getCombat().checkAttack();
	}

	public void drawMap() {
		for (int cnt = 0; cnt < LevelGeneration.getLevel()[0].length; cnt++) {
			for (int cnt2 = 0; cnt2 < LevelGeneration.getLevel().length; cnt2++) {
				if (LevelGeneration.getLevel()[cnt][cnt2] != null) {
					if (LevelGeneration.getLevel()[cnt][cnt2].getEntities().size() > 0 || (LevelGeneration.getLevel()[cnt][cnt2] instanceof HealingRoom && !enterHealing)) {
						batch.draw(rm.getUncleared(), 1050 + (cnt2 * 25), 650 - (cnt * 25));
					} else {
						batch.draw(rm.getCleared(), 1050 + (cnt2 * 25), 650 - (cnt * 25));
					}

					if (LevelGeneration.getLevel()[cnt][cnt2] == RoomManager.getCurrentRoom()) {
						batch.draw(rm.getPlayer(), 1050 + (cnt2 * 25), 650 - (cnt * 25));
					}
				} else {
					// nothing
				}
			}
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
			if (cnt == player.getCurrentSlot()) { // draws current com.apcs.game.player inventory slot selection
				batch.draw(invSelectTex, 1094 + (cnt * (14 + 46)), 27);
			}
		}

		if (player.getInventory().getWeapon() != null) {
			batch.draw(player.getInventory().getWeapon().getIcon(), 1019, 81);
		} if (player.getInventory().getArmor() != null) {
			batch.draw(player.getInventory().getArmor().getIcon(), 1019, 16);
		}


		/*
			Drawing Health
		 */
		for (int cnt = 1; cnt <= PlayerCombat.getHealth(); cnt++) {
			if (cnt + 1 <= PlayerCombat.getHealth()) {
				batch.draw(PlayerCombat.getHeart(), 975 + (cnt * 35), 160);
				cnt++;
			} else {
				batch.draw(PlayerCombat.getHalfHeart(), 975 + (cnt * 35), 160);
			}
		}

		/*
			Drawing Armor health
		 */
		if (PlayerInventory.getArmor() != null) {
			for (int cnt = 1; cnt <= PlayerInventory.getArmor().getStat(); cnt++) {
				if (cnt + 1 <= PlayerInventory.getArmor().getStat()) {
					batch.draw(PlayerCombat.getArmorHeart(), 975 + (cnt * 35), 210);
					cnt++;
				} else {
					batch.draw(PlayerCombat.getArmorHalfHeart(), 975 + (cnt * 35), 210);
				}
			}
		}
	}

	public void drawRoom() {
		Room room = rm.getCurrentRoom();

		batch.draw(room.getFloor(), 0, 0); // draw the room floor

		for (int cnt = 0; cnt < room.getDoors().size(); cnt++) {
			if (room.getEntities().size() > 0) {
				switch (room.getDoors().get(cnt).getLocation()) {
					case "top":
						batch.draw(room.getDoors().get(cnt).getClosedTex(), room.getDoors().get(cnt).getxLoc(), room.getDoors().get(cnt).getyLoc() + 10);
						break;
					case "bottom":
						batch.draw(room.getDoors().get(cnt).getClosedTex(), room.getDoors().get(cnt).getxLoc(), room.getDoors().get(cnt).getyLoc() - 10);
						break;
					case "left":
						batch.draw(room.getDoors().get(cnt).getClosedTex(), room.getDoors().get(cnt).getxLoc() - 5, room.getDoors().get(cnt).getyLoc());
						break;
					case "right":
						batch.draw(room.getDoors().get(cnt).getClosedTex(), room.getDoors().get(cnt).getxLoc() + 5, room.getDoors().get(cnt).getyLoc());
						break;
					default:
						break;
				}
			} else {
				switch (room.getDoors().get(cnt).getLocation()) {
					case "top":
						batch.draw(room.getDoors().get(cnt).getOpenTex(), room.getDoors().get(cnt).getxLoc(), room.getDoors().get(cnt).getyLoc() + 10);
						break;
					case "bottom":
						batch.draw(room.getDoors().get(cnt).getOpenTex(), room.getDoors().get(cnt).getxLoc(), room.getDoors().get(cnt).getyLoc() - 10);
						break;
					case "left":
						batch.draw(room.getDoors().get(cnt).getOpenTex(), room.getDoors().get(cnt).getxLoc() - 5, room.getDoors().get(cnt).getyLoc());
						break;
					case "right":
						batch.draw(room.getDoors().get(cnt).getOpenTex(), room.getDoors().get(cnt).getxLoc() + 5, room.getDoors().get(cnt).getyLoc());
						break;
					default:
						break;
				}
				if (room.getDoors().get(cnt).getCollider().overlaps(player.getCollider())) {
					rm.setCurrentRoom(room.getDoors().get(cnt).getNextRoom());

					switch (room.getDoors().get(cnt).getLocation()) {
						case "top":
							player.getCollider().y = 70;
							break;
						case "bottom":
							player.getCollider().y = 575;
							break;
						case "left":
							player.getCollider().x = 875;
							break;
						case "right":
							player.getCollider().x = 80;
							break;
						default:
							break;
					}

					enteredNewRoom = System.currentTimeMillis();

				}
			}
		}
	}

	public void drawSpikes(){
		ArrayList<Spike> hazard = RoomManager.getCurrentRoom().getHazards();
		for(int loop = 0; loop < hazard.size(); loop++) {
			batch.draw(hazard.get(loop).getIcon(), hazard.get(loop).getCollider().x,hazard.get(loop).getCollider().y);
			if(hazard.get(loop).getCollider().overlaps(player.getCollider())){
				if (System.currentTimeMillis() - lastHit > cooldown) {
					lastHit = System.currentTimeMillis();
					PlayerCombat.takeDamage(hazard.get(loop).getDamage());
				}
			}
		}
	}

	public void drawItems() {
		for(int loop = 0; loop < rm.getCurrentRoom().getGroundItems().size(); loop++) {
			batch.draw(rm.getCurrentRoom().getGroundItems().get(loop).getIcon(), rm.getCurrentRoom().getGroundItems().get(loop).getCollider().x, rm.getCurrentRoom().getGroundItems().get(loop).getCollider().y);
		}
	}

	public void drawEntities() {
		ArrayList<Entity> entities = rm.getCurrentRoom().getEntities();

        for(int loop = 0; loop < entities.size(); loop++) {
        	if (!entities.get(loop).isHit()) {
				batch.draw(entities.get(loop).getTexture(), entities.get(loop).getCollider().x, entities.get(loop).getCollider().y);
			} else {
				batch.draw(entities.get(loop).getHitTex(), entities.get(loop).getCollider().x, entities.get(loop).getCollider().y);
				entities.get(loop).nowHit();
        	}

			if (!pause) {
				entities.get(loop).move();

				if (entities.get(loop).getCollider().overlaps(player.getCollider())) {
					entities.get(loop).attack();
				} else {
					if (entities.get(loop) instanceof SpikeBoss) {
						SpikeBoss sb = (SpikeBoss)(entities.get(loop));
						sb.needToAttack = false;
					}
				}
			}
        }
    }

    public void drawProjectiles() {

		ArrayList<Projectile> proj = rm.getCurrentRoom().getProjectile();

		for (int cnt = proj.size() - 1; cnt > 0; cnt--) {
			switch (proj.get(cnt).getDirection()) {
				case "up":
					proj.get(cnt).getCollider().y += proj.get(cnt).getSpeed();
					break;
				case"down":
					proj.get(cnt).getCollider().y -= proj.get(cnt).getSpeed();
					break;
				case"left":
					proj.get(cnt).getCollider().x -= proj.get(cnt).getSpeed();
					break;
				case"right":
					proj.get(cnt).getCollider().x += proj.get(cnt).getSpeed();
					break;
				default:
					break;
			}

			batch.draw(proj.get(cnt).getTexture(), proj.get(cnt).getCollider().x, proj.get(cnt).getCollider().y);

			ArrayList<Entity> entities = rm.getCurrentRoom().getEntities();

			boolean removed = false;

			Wand wep = (Wand)(PlayerInventory.getWeapon());

			for(int loop = 0; loop < entities.size(); loop++) {
				if (proj.get(cnt).checkCanHit(entities.get(loop))) {
					if(proj.get(cnt).getCollider().overlaps(entities.get(loop).getCollider()) && wep.getSubclass().equals("wand")) {
						entities.get(loop).hit(proj.get(cnt).getDamage());
						proj.remove(cnt);
						removed = true;
						break;
					} else if (proj.get(cnt).getCollider().overlaps(entities.get(loop).getCollider()) && wep.getSubclass().equals("bow")) {
						proj.get(cnt).getEnemList().add(entities.get(loop));
						entities.get(loop).hit(proj.get(cnt).getDamage());
					}
				}
			}

			if (!removed && (Math.abs(proj.get(cnt).getCollider().x - proj.get(cnt).getInitX()) + Math.abs(proj.get(cnt).getCollider().y - proj.get(cnt).getInitY()) > proj.get(cnt).getRange())){
				proj.remove(cnt);
			}
		}
	}

	public void drawEnemProjectiles() {

		ArrayList<Projectile> proj = rm.getCurrentRoom().getEnemProj();

		for (int cnt = proj.size() - 1; cnt > 0; cnt--) {
			switch (proj.get(cnt).getDirection()) {
				case "up":
					proj.get(cnt).getCollider().y += proj.get(cnt).getSpeed();
					break;
				case"down":
					proj.get(cnt).getCollider().y -= proj.get(cnt).getSpeed();
					break;
				case"left":
					proj.get(cnt).getCollider().x -= proj.get(cnt).getSpeed();
					break;
				case"right":
					proj.get(cnt).getCollider().x += proj.get(cnt).getSpeed();
					break;
				default:
					break;
			}

			batch.draw(proj.get(cnt).getTexture(), proj.get(cnt).getCollider().x, proj.get(cnt).getCollider().y);


			boolean removed = false;

			if (proj.get(cnt).getCollider().overlaps(player.getCollider())) {
				player.getCombat().takeDamage(proj.get(cnt).getDamage());
				proj.remove(cnt);
				removed = true;
			}

			if (!removed && (Math.abs(proj.get(cnt).getCollider().x - proj.get(cnt).getInitX()) + Math.abs(proj.get(cnt).getCollider().y - proj.get(cnt).getInitY()) > proj.get(cnt).getRange())){
				proj.remove(cnt);
			}
		}
	}

	public static void ifEnemyHit() {
		ArrayList<Entity> entities = rm.getCurrentRoom().getEntities();

		if (PlayerHandler.getInventory().getWeapon().getItemClass().equals("weapon")) {
			for(int loop = 0; loop < entities.size(); loop++) {
				if(PlayerHandler.getInventory().getWeapon().getCollider().overlaps(entities.get(loop).getCollider())) {
					FatSword wep = (FatSword)PlayerHandler.getInventory().getWeapon();
					entities.get(loop).hit(wep.getDamage());

				}
			}
		}
    }

    public static void drawWeapon() {
		batch.draw(wepTex, wepX, wepY);
	}

	public static RoomManager getRm() {
		return rm;
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		player.disposer(); // disposes all of the players textures
		rm.getCurrentRoom().disposer(); // disposes the textures for the room
	}
}
