package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Toriyasu extends Rectangle {

    int health;
    float vspeed;
    int jumpFrames;

    Texture sprite; 

    public Toriyasu() {
        sprite = new Texture(Gdx.files.internal("bucket.png"));
        health      = 100;
        vspeed      = 0;
        jumpFrames  = 0;
    }

    // Returns next position forced by gravity
    public void gravity(float delta) {
        // Landing logic
        if(this.getY() < 1)     this.setY(0); 

        // Countdown jump frames
        if(this.jumpFrames > 0) this.jumpFrames -= 1; 

        // Increase falling speed
        if(!this.onFloor()) {
            if(this.getVspeed() < 20) {
                this.increaseVspeed(1);
            }

            this.decreaseY((50 * vspeed) * delta);
        } 
        else {
            this.setVspeed(0);
            this.setJumpFrames(0);
        }
    }
    
    public void jump(float delta, boolean justPressed) {
        // Start jump timer if just pressed
        if(justPressed && onFloor()) {
            jumpFrames += 40;
            if(y==0) this.increaseY(1);
        }
        if(!onFloor()) {
            if(jumpFrames > 30) {
                this.decreaseVspeed(100 * delta);
            } 
            else if(jumpFrames > 0) {
                this.decreaseVspeed(50 * delta);
            }
        }
    }

    public boolean onFloor() {
        if(y < 1) {
            return true;
        } else {
            return false;
        }
    }

    public void increaseY(float y) {
        this.y += y;
    }

    public void decreaseY(float y) {
        this.y -= y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getVspeed() {
        return vspeed;
    }

    public void setVspeed(float vspeed) {
        this.vspeed = vspeed;
    }

    public void increaseVspeed(float vspeed) {
        this.vspeed += vspeed;
    }

    public void decreaseVspeed(float vspeed) {
        this.vspeed -= vspeed;
    }

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

    public void setJumpFrames(int jumpFrames) {
        this.jumpFrames = jumpFrames;
    }

    public int getJumpFrames() {
        return jumpFrames;
    }
}
