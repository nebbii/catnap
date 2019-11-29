package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Unit extends Rectangle {

    int health;
    float vspeed;
    int jumpFrames;

    public Unit() {

    }
    
    // Returns next position forced by gravity
    public void gravity(float delta) {
        if(getY() < 1) setY(0); 

        // Countdown jump frames
        if(jumpFrames > 0) jumpFrames -= 1; 

        // Increase falling speed
        if(!onFloor()) {
            if(getVspeed() < 20) {
                increaseVspeed(1);
            }

            decreaseY((50 * getVspeed()) * delta);
        } 
        else {
            setVspeed(0);
            setJumpFrames(0);
        }
    }

    public boolean onFloor() {
        if(y < 1) {
            return true;
        } else {
            return false;
        }
    }

    public void increaseX(float x) {
        this.x += x;
    }

    public void decreaseX(float x) {
        this.x -= x;
    }

    public void increaseY(float y) {
        this.y += y;
    }

    public void decreaseY(float y) {
        this.y -= y;
    }
    
    public void increaseVspeed(float vspeed) {
        this.vspeed += vspeed;
    }

    public void decreaseVspeed(float vspeed) {
        this.vspeed -= vspeed;
    }

    public void setVspeed(float vspeed) {
        this.vspeed = vspeed;
    }

    public float getVspeed() {
        return vspeed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getJumpFrames() {
        return jumpFrames;
    }

    public void setJumpFrames(int jumpFrames) {
        this.jumpFrames = jumpFrames;
    }
}

