package com.apcs.game.enemies;

import com.apcs.game.player.PlayerHandler;

public class Charger extends Entity {

    private int moveCooldown, chargeTime;
    private float moveX, moveY;
    private long chargeCooldown, start;

    public Charger(){
        super();
        setHealth(10);
        setCharcater("clark.png");
        setSpeed(15f);
        setStrength(1);
        moveCooldown = 2000;
        chargeTime = 1000;
        chargeCooldown = System.currentTimeMillis();
    }

    public void move() {

        if (System.currentTimeMillis() - chargeCooldown > moveCooldown) {
            walk(moveX, moveY);

            if (System.currentTimeMillis() - start > chargeTime) {
                chargeCooldown = System.currentTimeMillis();
            }
        } else {
            moveX = PlayerHandler.getCollider().x - getCollider().x;
            moveY = PlayerHandler.getCollider().y - getCollider().y;

            start = System.currentTimeMillis();
        }
    }

    public void walk(float x, float y){
        double ang1 = Math.atan(y / x);
        double ang2 = Math.atan(x / y);
        float sx = (float)(getSpeed()*(Math.sin(ang2)));
        float sy = (float)(getSpeed()*(Math.sin(ang1)));

        sx = Math.abs(sx);
        sy = Math.abs(sy);

        if (x > 0 && y > 0) {
            if (getCollider().x + getTexture().getWidth() < 920) {
                getCollider().x += sx;
            } if (getCollider().y < 650) {
                getCollider().y += sy;
            }
        } else if (x < 0 && y > 0) {
            if (getCollider().x > 40) {
                getCollider().x -= sx;
            } if (getCollider().y < 650) {
                getCollider().y += sy;
            }
        }  else if (x < 0 && y < 0) {
            if (getCollider().x > 40) {
                getCollider().x -= sx;
            } if (getCollider().y > 40) {
                getCollider().y -= sy;
            }
        }  else if (x > 0 && y < 0) {
            if (getCollider().x + getTexture().getWidth() < 920) {
                getCollider().x += sx;
            } if (getCollider().y > 40) {
                getCollider().y -= sy;
            }
        }
    }

}
