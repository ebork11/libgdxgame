package com.apcs.game.enemies;

import com.apcs.game.GameMain;
import com.apcs.game.items.*;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Charger extends Entity {

    Rectangle collider;
    private int moveCooldown, chargeTime, currentTex;
    private double temp;
    private float moveX, moveY, speed;
    private long chargeCooldown, start;
    private boolean isHit;
    private Texture hit1, hit2;
    ArrayList<Integer> chargeTimes = new ArrayList<>();

    public Charger(){
        super();

        isHit = false;

        Texture temp = new Texture("enemies/charger/charger.png");
        hit1 = new Texture("enemies/charger/chargerhit.png");
        hit2 = new Texture("enemies/charger/charge1hit.png");

        int x = (int)(Math.random() * 550) + 300;
        int y = (int)(Math.random() * 300) + 200;

        currentTex = 1;

        collider = new Rectangle(x, y, temp.getWidth(), temp.getHeight());

        setHealth(8);
        setCharacterFirst("enemies/charger/charger.png");
        speed = 15f;
        setStrength(2);
        moveCooldown = 2000;
        chargeTimes.add(2000);
        chargeTimes.add(1500);
        chargeTimes.add(2500);
        chargeTimes.add(3000);
        chargeTime = 2000;
        chargeCooldown = System.currentTimeMillis();
    }

    public void move() {
        if (System.currentTimeMillis() - GameMain.enteredNewRoom > 1000) {
            if (System.currentTimeMillis() - chargeCooldown > moveCooldown) {
                setCharacter("enemies/charger/charge1.png");
                currentTex = 2;
                walk(moveX,moveY);

                if (System.currentTimeMillis() - start > chargeTime) {
                    temp = (Math.random()*10);
                    if(temp < 2.5){
                        moveCooldown = chargeTimes.get(0);
                    }else if (temp < 5){
                        moveCooldown = chargeTimes.get(1);
                    }else if (temp < 7.5){
                        moveCooldown = chargeTimes.get(2);
                    }else{
                        moveCooldown = chargeTimes.get(3);
                    }
                    chargeCooldown = System.currentTimeMillis();
                    setCharacter("enemies/charger/charger.png");
                    currentTex = 1;
                }
            } else {
                moveX = PlayerHandler.getCollider().x - collider.x;
                moveY = PlayerHandler.getCollider().y - collider.y;

                start = System.currentTimeMillis();
            }
        }
    }

    public void walk(float x, float y){

        double ang1 = Math.atan(y / x);
        double ang2 = Math.atan(x / y);
        float sx = (float)(speed * (Math.sin(ang2)));
        float sy = (float)(speed * (Math.sin(ang1)));

        sx = Math.abs(sx);
        sy = Math.abs(sy);

        if (x > 0 && y > 0) {
            if (collider.x + getTexture().getWidth() < 960) {
                collider.x += sx;

            } if (collider.y < 610) {
                collider.y += sy;
            }
        } else if (x < 0 && y > 0) {
            if (collider.x > 40) {
                collider.x -= sx;
            } if (collider.y < 610) {
                collider.y += sy;
            }
        }  else if (x < 0 && y < 0) {
            if (collider.x > 40) {
                collider.x -= sx;
            } if (collider.y > 40) {
                collider.y -= sy;
            }
        }  else if (x > 0 && y < 0) {
            if (collider.x + getTexture().getWidth() < 960) {
                collider.x += sx;
            } if (collider.y > 40) {
                collider.y -= sy;
            }
        }
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void dropItems() {
        int ifDrop = (int)(Math.random() * 10) + 1;

        if (ifDrop <= 5) {
            ArrayList<Item> droppable = new ArrayList<Item>();

            if (droppable.size() == 0) {
                droppable.add(new Dagger());
                droppable.add(new FatSword());
            }

            Item temp = droppable.get((int)(Math.random() * droppable.size()));

            temp.getCollider().setPosition(collider.x, collider.y);

            RoomManager.getCurrentRoom().getGroundItems().add(temp);
        }
    }

    public boolean isHit() {
        return isHit;
    }

    public void nowHit() {
        isHit = !isHit;
    }

    public Texture getHitTex() {
        if (currentTex == 1) {
            return hit1;
        } else {
            return hit2;
        }
    }
}
