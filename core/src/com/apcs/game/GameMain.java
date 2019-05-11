package com.apcs.game;

import com.apcs.game.enemies.Entity;
import com.apcs.game.items.Item;
import com.apcs.game.menu.MenuManager;
import com.apcs.game.object.Spike;
import com.apcs.game.player.PlayerAnimation;
import com.apcs.game.player.PlayerCombat;
import com.apcs.game.player.PlayerInventory;
import com.apcs.game.rooms.Room;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.apcs.game.player.PlayerHandler;

import java.util.ArrayList;

public class GameMain extends ApplicationAdapter {

	// libgdx batch
	private static SpriteBatch batch;

	// class accessors
	private MenuManager mm;
	private PlayerHandler player;
	private static RoomManager rm;
	private PlayerAnimation pa;


	boolean menu;
	private Texture invSelectTex; // inventory outline texture

	//drawing weapon during combat
	public static boolean attacking = false;
	public static Texture wepTex;
	public static float wepX;
	public static float wepY;
	private long lastHit; // also spike I believe
	private long cooldown; // spike cooldown??? needs to be redone

	@Override
	public void create () {
		batch = new SpriteBatch();

		mm = new MenuManager();
		player = new PlayerHandler();
		rm = new RoomManager();
		pa = new PlayerAnimation();

		menu = true;
		invSelectTex = new Texture("items/outlineselection.png");

		lastHit = System.currentTimeMillis(); // spike stuff needs to be redone eventually and line below
		cooldown = 1500;
	}

	@Override
	/*
		Method that is called every single frame in the game. This is where we draw textures to the screen, calculate and
		formulas, and handle any other changes such as movement and/or combat
	 */
	public void render () {
		Gdx.gl.glClearColor(.1f, .1f, .1f, 1); // sets the basic background color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playerManage(); // handles everything regarding the com.apcs.game.player

		batch.begin(); // beginning of where everything is drawn

		if (menu) {
			renderMenu();
		} else {
			renderGameLevel();
		}

		batch.end(); // ending of where everything is drawn
	}

	public void renderMenu() {
		batch.draw(mm.getBackground(), 0, 0);
		batch.draw(mm.getPlayButton(), 500, 150);

		int mouseY = Math.abs(720 - Gdx.input.getY());

		if (Gdx.input.getX() > 500 && Gdx.input.getX() < 500 + mm.getPlayButton().getWidth() && mouseY > 150 && mouseY < 150 + mm.getPlayButton().getHeight()) {
			if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
				menu = false;
			}
		}

	}

	public void renderGameLevel() {
		drawRoom();
		drawItems();
		drawSpikes();
		drawEntities();
		batch.draw(pa.getFrameTex(), player.getCollider().x - (pa.getFrameTex().getWidth() / 4), player.getCollider().y); // draws the com.apcs.game.player at the colliders location
		if (attacking) {
			drawWeapon();
		}
		drawInventory(); // drawing right side stuff
	}

	/*
		Handles all player things i.e. movement
	 */
	public void playerManage() {
		player.movementHandler(); // checks the keyboard for input and moves the com.apcs.game.player accordingly
		player.checkForPickup();
		player.getCombat().checkAttack();
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
						batch.draw(room.getDoors().get(cnt).getClosedTex(), room.getDoors().get(cnt).getxLoc(), room.getDoors().get(cnt).getyLoc() + 30);
						break;
					case "bottom":
						batch.draw(room.getDoors().get(cnt).getClosedTex(), room.getDoors().get(cnt).getxLoc(), room.getDoors().get(cnt).getyLoc() - 30);
						break;
					case "left":
						batch.draw(room.getDoors().get(cnt).getClosedTex(), room.getDoors().get(cnt).getxLoc() - 30, room.getDoors().get(cnt).getyLoc());
						break;
					case "right":
						batch.draw(room.getDoors().get(cnt).getClosedTex(), room.getDoors().get(cnt).getxLoc() + 30, room.getDoors().get(cnt).getyLoc());
						break;
					default:
						break;
				}
			} else {
				switch (room.getDoors().get(cnt).getLocation()) {
					case "top":
						batch.draw(room.getDoors().get(cnt).getOpenTex(), room.getDoors().get(cnt).getxLoc(), room.getDoors().get(cnt).getyLoc() + 30);
						break;
					case "bottom":
						batch.draw(room.getDoors().get(cnt).getOpenTex(), room.getDoors().get(cnt).getxLoc(), room.getDoors().get(cnt).getyLoc() - 30);
						break;
					case "left":
						batch.draw(room.getDoors().get(cnt).getOpenTex(), room.getDoors().get(cnt).getxLoc() - 30, room.getDoors().get(cnt).getyLoc());
						break;
					case "right":
						batch.draw(room.getDoors().get(cnt).getOpenTex(), room.getDoors().get(cnt).getxLoc() + 30, room.getDoors().get(cnt).getyLoc());
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
            entities.get(loop).move();
            batch.draw(entities.get(loop).getTexture(), entities.get(loop).getCollider().x, entities.get(loop).getCollider().y);

            if (entities.get(loop).getCollider().overlaps(player.getCollider())) {
            	entities.get(loop).attack();
			}
        }
    }

	public static void ifEnemyHit() {
		ArrayList<Entity> entities = rm.getCurrentRoom().getEntities();

        for(int loop = 0; loop <entities.size(); loop++) {
            if(PlayerHandler.getInventory().getWeapon().getCollider().overlaps(entities.get(loop).getCollider())) {
                entities.get(loop).hit(1);
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
		player.disposer(); // disposes all of the players textures
		rm.getCurrentRoom().disposer(); // disposes the textures for the room
	}
}
