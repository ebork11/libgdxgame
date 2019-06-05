package com.apcs.game.enemies.level1;

import com.apcs.game.EnemyAnimation;
import com.apcs.game.GameMain;
import com.apcs.game.enemies.Entity;
import com.apcs.game.items.weapons.Dagger;
import com.apcs.game.items.weapons.FatSword;
import com.apcs.game.items.weapons.Item;
import com.apcs.game.player.PlayerHandler;
import com.apcs.game.rooms.RoomManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Charger extends Entity {

    private Sound hurt;
    EnemyAnimation idleAnimation, chargeAnimationR, chargeAnimationL;
    Rectangle collider;
    private int moveCooldown, chargeTime, currentTex;
    private double temp;
    private float moveX, moveY, speed;
    private long chargeCooldown, start;
    private boolean isHit, left;
    private Texture hit1, hit2;
    ArrayList<Integer> chargeTimes = new ArrayList<>();

    public Charger(){
        super();

        hurt = Gdx.audio.newSound(Gdx.files.internal("sounds/chargerhit.mp3"));
        setHurt(hurt);

        isHit = false;

        left = false;

        Texture temp = new Texture("enemies/charger/charger1.png");

        int x = (int)(Math.random() * 550) + 300;
        int y = (int)(Math.random() * 300) + 200;

        currentTex = 1;

        collider = new Rectangle(x, y, 90, 90);

        setHealth(8);
        setCharacterFirst("enemies/charger/charger1.png");
        speed = 14f;
        setStrength(2);
        moveCooldown = 2000;
        chargeTimes.add(2000);
        chargeTimes.add(1500);
        chargeTimes.add(2500);
        chargeTimes.add(3000);
        chargeTime = 2000;
        chargeCooldown = System.currentTimeMillis();

        ArrayList<Texture> anim = new ArrayList<Texture>();
        anim.add(new Texture("enemies/charger/charger1.png"));
        anim.add(new Texture("enemies/charger/charger2.png"));
        anim.add(new Texture("enemies/charger/charger3.png"));
        anim.add(new Texture("enemies/charger/charger4.png"));
        anim.add(new Texture("enemies/charger/charger5.png"));
        anim.add(new Texture("enemies/charger/charger6.png"));
        anim.add(new Texture("enemies/charger/charger7.png"));

        ArrayList<Texture> animH = new ArrayList<Texture>();
        animH.add(new Texture("enemies/charger/chargehit.png"));
        idleAnimation = new EnemyAnimation(anim, animH);

        ArrayList<Texture> animCR = new ArrayList<Texture>();
        animCR.add(new Texture("enemies/charger/charge1.png"));
        animCR.add(new Texture("enemies/charger/charge2.png"));
        animCR.add(new Texture("enemies/charger/charge3.png"));
        animCR.add(new Texture("enemies/charger/charge4.png"));
        animCR.add(new Texture("enemies/charger/charge5.png"));
        animCR.add(new Texture("enemies/charger/charge6.png"));
        animCR.add(new Texture("enemies/charger/charge7.png"));

        ArrayList<Texture> animHR = new ArrayList<Texture>();
        animHR.add(new Texture("enemies/charger/chargehitr.png"));

        chargeAnimationR = new EnemyAnimation(animCR, animHR);

        ArrayList<Texture> animCL = new ArrayList<Texture>();
        animCL.add(new Texture("enemies/charger/charge7.png"));
        animCL.add(new Texture("enemies/charger/charge6.png"));
        animCL.add(new Texture("enemies/charger/charge5.png"));
        animCL.add(new Texture("enemies/charger/charge4.png"));
        animCL.add(new Texture("enemies/charger/charge3.png"));
        animCL.add(new Texture("enemies/charger/charge2.png"));
        animCL.add(new Texture("enemies/charger/charge1.png"));

        ArrayList<Texture> animHL = new ArrayList<Texture>();
        animHL.add(new Texture("enemies/charger/chargehitl.png"));

        chargeAnimationL = new EnemyAnimation(animCL, animHL);
    }

    public void move() {
        if (System.currentTimeMillis() - GameMain.enteredNewRoom > 500) {
            if (System.currentTimeMillis() - chargeCooldown > moveCooldown) {
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
            left = false;
            if (collider.x + getTexture().getWidth() < 960) {
                collider.x += sx;
            } if (collider.y < 610) {
                collider.y += sy;
            }
        } else if (x < 0 && y > 0) {
            left = true;
            if (collider.x > 40) {
                collider.x -= sx;
            } if (collider.y < 610) {
                collider.y += sy;
            }
        }  else if (x < 0 && y < 0) {
            left = true;
            if (collider.x > 40) {
                collider.x -= sx;
            } if (collider.y > 40) {
                collider.y -= sy;
            }
        }  else if (x > 0 && y < 0) {
            left = false;
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

        if (ifDrop <= 4) {
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

    public Texture getTexture() {
        if (currentTex == 1) {
            return idleAnimation.getTexture();
        } else {
            if (left) {
                return chargeAnimationL.getTexture();
            } else {
                return chargeAnimationR.getTexture();
            }
        }
    }

    public Texture getHitTex() {
        if (currentTex == 1) {
            return idleAnimation.getHitTex();
        } else {
            if (left) {
                return chargeAnimationL.getHitTex();
            } else {
                return chargeAnimationR.getHitTex();
            }
        }
    }
}
