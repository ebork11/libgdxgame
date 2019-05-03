package com.apcs.game.player;

import com.apcs.game.GameMain;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PlayerCombat {
    private static int health;

    private static Texture heart, halfHeart, armorHeart, armorHalfHeart;

    public PlayerCombat() {
        health = 8;

        heart = new Texture("core/assets/gui/fullheart.png");
        halfHeart = new Texture("core/assets/gui/halfheart.png");

        armorHeart = new Texture("core/assets/gui/armorheart.png");
        armorHalfHeart = new Texture("core/assets/gui/armorhalfheart.png");
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
            System.out.println("You lose");
        }
    }

    public void checkAttack() {

        if (PlayerHandler.getInventory().getWeapon() != null) {
            Rectangle coll = PlayerHandler.getInventory().getWeapon().getCollider();
            Texture u = PlayerHandler.getInventory().getWeapon().getTextureU();
            Texture d = PlayerHandler.getInventory().getWeapon().getTextureD();
            Texture r = PlayerHandler.getInventory().getWeapon().getTextureR();
            Texture l = PlayerHandler.getInventory().getWeapon().getTextureL();

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                coll.x = PlayerHandler.getCollider().x + PlayerHandler.getCollider().getWidth();
                coll.y = PlayerHandler.getCollider().y;
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
                coll.x = PlayerHandler.getCollider().x - l.getWidth();
                coll.y = PlayerHandler.getCollider().y;
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
                coll.y = PlayerHandler.getCollider().y + PlayerHandler.getCollider().getHeight();
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
                coll.y = PlayerHandler.getCollider().y - d.getHeight();
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
        }
    }

    public static void printStats() {
        if (PlayerInventory.getArmor() != null) {
            System.out.println("Health: " + health + " Armor: " + PlayerInventory.getArmor().getStat());
        } else {
            System.out.println("Health: " + health + " Armor: " + 0);
        }
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
