package com.apcs.game.enemies;

import com.apcs.game.player.PlayerHandler;

public class Charger extends Entity {

    private int moveCooldown;
    private float moveX, moveY;

    public Charger(){
        super();
        setHealth(10);
        setCharcater("core/assets/player.png");
        setSpeed(8f);
        setStrength(1);
        setCooldown(500);
        moveCooldown = 2000;
    }

    public void move() {
        if (System.currentTimeMillis() - getIncoll() > moveCooldown) {
           // setInColl(System.currentTimeMillis());
            walk();
        } else {
            moveX = PlayerHandler.getCollider().x;
            moveY = PlayerHandler.getCollider().y;
        }
    }

    public void walk(){
        float x = moveX;
        float y = moveY;
        double ang1 = Math.atan(y / x);
        double ang2 = Math.atan(x / y);
        float sx = (float)(getSpeed()*(Math.sin(ang2)));
        float sy = (float)(getSpeed()*(Math.sin(ang1)));

        sx = Math.abs(sx);
        sy = Math.abs(sy);

        if (x > 0 && y > 0) {
            getCollider().x += sx;
            getCollider().y += sy;
        } else if (x < 0 && y > 0) {
            getCollider().x -= sx;
            getCollider().y += sy;
        }  else if (x < 0 && y < 0) {
            getCollider().x -= sx;
            getCollider().y -= sy;
        }  else if (x > 0 && y < 0) {
            getCollider().x += sx;
            getCollider().y -= sy;
        }
    }

}
