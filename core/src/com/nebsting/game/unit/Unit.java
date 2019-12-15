package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Unit extends Rectangle {

    float health;
    float hspeed;
    float vspeed;
    float weight;
    int jumpFrames;
    boolean onFloor; // Checks floor on getter

    char lastDirection; // l & r

    TextureRegion sprite; 

    public Unit() {
        this.lastDirection = 'r';
    }

    public void checkFloor() {
        if(this.getY() < 1) {
            setOnFloor(true);
        }
        else {
            setOnFloor(false);
        }
    }

    // Gets run every frame
    public void logic(float delta, Polygon[] map) {
        this.checkFloor();
        this.gravity(delta);
    }
    
    // Returns next position forced by gravity
    public void gravity(float delta) {
        if(getY() < 1) setY(0); 

        // Countdown jump frames
        if(jumpFrames > 0) jumpFrames -= 1; 

        // Increase falling speed
        if(!getOnFloor()) {
            if(getVspeed() < 20) {
                increaseVspeed(1);
            }
            decreaseY((this.getWeight() * getVspeed()) * delta);
        } 
        else {
            setVspeed(0);
            setJumpFrames(0);
        }
    }

    public boolean getOnFloor() {
        return onFloor;
    }

    public void setOnFloor(boolean onFloor) {
        this.onFloor = onFloor;
    }

    protected void increaseX(float x) {
        this.x += x;
    }

    protected void decreaseX(float x) {
        this.x -= x;
    }

    protected void increaseY(float y) {
        this.y += y;
    }

    protected void decreaseY(float y) {
        this.y -= y;
    }
    
    public void increaseHspeed(float hspeed) {
        this.hspeed += hspeed;
    }

    public void decreaseHspeed(float hspeed) {
        this.hspeed -= hspeed;
    }
    
    public void increaseVspeed(float vspeed) {
        this.vspeed += vspeed;
    }

    public void decreaseVspeed(float vspeed) {
        this.vspeed -= vspeed;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setHspeed(float hspeed) {
        this.hspeed = hspeed;
    }

    public float getHspeed() {
        return hspeed;
    }

    public float getVspeed() {
        return vspeed;
    }

    public void setVspeed(float vspeed) {
        this.vspeed = vspeed;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getJumpFrames() {
        return jumpFrames;
    }

    public void setJumpFrames(int jumpFrames) {
        this.jumpFrames = jumpFrames;
    }
}
