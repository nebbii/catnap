package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Unit extends Rectangle {

    float health;
    float hspeed;
    float vspeed;
    float weight;
    int jumpFrames;

    TextureRegion sprite; 

    public Unit() {

    }

    public boolean onFloor() {
        if(this.getY() < 1) {
            return true;
        } else {
            return false;
        }
    }

    // Gets run every frame
    public void logic(float delta) {
        this.gravity(delta);
    }
    
    // Returns next position forced by gravity
    public void gravity(float delta) {
        if(getY() < 1) setY(0); 

        // wrap around LAL
        //if(getX() < -100) setX(750); 
        //if(getX() > 750) setX(-100); 

        // Countdown jump frames
        if(jumpFrames > 0) jumpFrames -= 1; 

        //Gdx.app.log("Vspeed", Float.toString(this.getVspeed()));
        //Gdx.app.log("jumpFrames", Float.toString(this.getJumpFrames()));

        // Increase falling speed
        if(!onFloor()) {
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

    public void horizontalMove(float delta) {
        Gdx.app.log("horizontalMove", Float.toString(getHspeed() * delta));
        this.increaseX(getHspeed() * delta);
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

    public TextureRegion getSprite() {
        return sprite;
    }

    public void setSprite(TextureRegion sprite) {
        this.sprite = sprite;
    }
}

