package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Unit extends Rectangle {

    float health;
    float vspeed;
    float weight;
    int jumpFrames;

    Texture sprite; 

    public Unit() {

    }
    
    // Returns next position forced by gravity
    public void gravity(float delta) {
        if(getY() < 1) setY(0); 

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

    public boolean onFloor() {
        if(this.getY() < 1) {
            return true;
        } else {
            return false;
        }
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

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

    public Texture getSprite() {
        return sprite;
    }
}

