package com.apcs.game.player;

import com.apcs.game.GameMain;
import com.apcs.game.items.projectiles.Projectile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class PlayerCombat {
    private static int health;

    private static Texture heart, halfHeart, armorHeart, armorHalfHeart;

    public static ArrayList<Projectile> proj;

    public PlayerCombat() {
        health = 8;

        heart = new Texture("gui/fullheart.png");
        halfHeart = new Texture("gui/halfheart.png");

        armorHeart = new Texture("gui/armorheart.png");
        armorHalfHeart = new Texture("gui/armorhalfheart.png");

        proj = new ArrayList<Projectile>();
    }

    public static void takeDamage(int dam) {

        if (PlayerInventory.getArmor() != null && PlayerInventory.getArmor().getStat() > 0) {
            PlayerInventory.getArmor().damage(dam);
        } else {
            health -= dam;
        }

        if (PlayerInventory.getArmor() != null && PlayerInventory.getArmor().getStat() <= 0) {
            PlayerInventory.getArmor().breakArmor();
        }
      
        if (health <= 0) {
            Gdx.app.exit();
        }
    }

    public void checkAttack() {

        if (PlayerHandler.getInventory().getWeapon() != null) {

            if (PlayerHandler.getInventory().getWeapon().getItemClass().equals("weapon")) {
                Rectangle coll = PlayerHandler.getInventory().getWeapon().getCollider();
                Texture u = PlayerHandler.getInventory().getWeapon().getTextureU();
                Texture d = PlayerHandler.getInventory().getWeapon().getTextureD();
                Texture r = PlayerHandler.getInventory().getWeapon().getTextureR();
                Texture l = PlayerHandler.getInventory().getWeapon().getTextureL();

                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    coll.x = PlayerHandler.getCollider().x + (PlayerHandler.getCollider().getWidth() / 1.3f);
                    coll.y = PlayerHandler.getCollider().y + 10;
                    coll.width = r.getWidth();
                    coll.height = r.getHeight();

                    GameMain.attacking = true;
                    GameMain.wepTex = r;
                    GameMain.wepX = coll.x;
                    GameMain.wepY = coll.y;

                    if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                        GameMain.ifEnemyHit();
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    coll.x = PlayerHandler.getCollider().x - (l.getWidth() / 1.1f);
                    coll.y = PlayerHandler.getCollider().y + 10;
                    coll.width = l.getWidth();
                    coll.height = l.getHeight();

                    GameMain.attacking = true;
                    GameMain.wepTex = l;
                    GameMain.wepX = coll.x;
                    GameMain.wepY = coll.y;

                    if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                        GameMain.ifEnemyHit();
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    coll.x = PlayerHandler.getCollider().x;
                    coll.y = PlayerHandler.getCollider().y + (PlayerHandler.getCollider().getHeight() / 1.2f);
                    coll.width = u.getWidth();
                    coll.height = u.getHeight();

                    GameMain.attacking = true;
                    GameMain.wepTex = u;
                    GameMain.wepX = coll.x;
                    GameMain.wepY = coll.y;

                    if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                        GameMain.ifEnemyHit();
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    coll.x = PlayerHandler.getCollider().x;
                    coll.y = PlayerHandler.getCollider().y - (d.getHeight() / 1.4f);
                    coll.width = d.getWidth();
                    coll.height = d.getHeight();

                    GameMain.attacking = true;
                    GameMain.wepTex = d;
                    GameMain.wepX = coll.x;
                    GameMain.wepY = coll.y;

                    if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                        GameMain.ifEnemyHit();
                    }
                } else {
                    GameMain.attacking = false;
                }
            } else if (PlayerHandler.getInventory().getWeapon().getItemClass().equals("ranged_weapon")) {

                Rectangle coll = PlayerHandler.getInventory().getWeapon().getCollider();
                Texture u = PlayerHandler.getInventory().getWeapon().getTextureU();
                Texture d = PlayerHandler.getInventory().getWeapon().getTextureD();
                Texture r = PlayerHandler.getInventory().getWeapon().getTextureR();
                Texture l = PlayerHandler.getInventory().getWeapon().getTextureL();

                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    coll.x = PlayerHandler.getCollider().x + (PlayerHandler.getCollider().getWidth() / 1.3f);
                    coll.y = PlayerHandler.getCollider().y + 10;
                    coll.width = r.getWidth();
                    coll.height = r.getHeight();

                    GameMain.attacking = true;
                    GameMain.wepTex = r;
                    GameMain.wepX = coll.x;
                    GameMain.wepY = coll.y;

                    if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                        proj.add(new Projectile(PlayerHandler.getCollider().x + 20, PlayerHandler.getCollider().y + 20, 1, 15,  "right", 150));
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    coll.x = PlayerHandler.getCollider().x - (l.getWidth() / 1.1f);
                    coll.y = PlayerHandler.getCollider().y + 10;
                    coll.width = l.getWidth();
                    coll.height = l.getHeight();

                    GameMain.attacking = true;
                    GameMain.wepTex = l;
                    GameMain.wepX = coll.x;
                    GameMain.wepY = coll.y;

                    if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                        proj.add(new Projectile(PlayerHandler.getCollider().x - 20, PlayerHandler.getCollider().y + 20, 1, 15,  "left", 150));
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    coll.x = PlayerHandler.getCollider().x;
                    coll.y = PlayerHandler.getCollider().y + (PlayerHandler.getCollider().getHeight() / 1.2f);
                    coll.width = u.getWidth();
                    coll.height = u.getHeight();

                    GameMain.attacking = true;
                    GameMain.wepTex = u;
                    GameMain.wepX = coll.x;
                    GameMain.wepY = coll.y;

                    if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                        proj.add(new Projectile(PlayerHandler.getCollider().x + 10, PlayerHandler.getCollider().y + 35, 1, 15,  "up", 150));
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    coll.x = PlayerHandler.getCollider().x;
                    coll.y = PlayerHandler.getCollider().y - (d.getHeight() / 1.4f);
                    coll.width = d.getWidth();
                    coll.height = d.getHeight();

                    GameMain.attacking = true;
                    GameMain.wepTex = d;
                    GameMain.wepX = coll.x;
                    GameMain.wepY = coll.y;

                    if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                        proj.add(new Projectile(PlayerHandler.getCollider().x + 10, PlayerHandler.getCollider().y - 20, 1, 15,  "down", 150));
                    }
                } else {
                    GameMain.attacking = false;
                }
            }
        }
    }

    public static void printStats() {
        if (PlayerInventory.getArmor() != null) {
            System.out.println("Health: " + health + " Armor: " + PlayerInventory.getArmor().getStat());
        } else {
            System.out.println("Health: " + health + " Armor: " + 0);
        }
    }

    public static void addHealth(int num) {
        health += num;
    }

    public static Texture getHalfHeart() {
        return halfHeart;
    }

    public static Texture getHeart() {
        return heart;
    }

    public static Texture getArmorHalfHeart() {
        return armorHalfHeart;
    }

    public static Texture getArmorHeart() {
        return armorHeart;
    }

    public static int getHealth() {
        return health;
    }

}
