package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Toriyasu extends Unit {

    Texture sprite; 

    public Toriyasu() {
        sprite = new Texture(Gdx.files.internal("bucket.png"));
        health      = 100;
    }

    public void jump(float delta, boolean justPressed) {
        // Start jump timer if just pressed
        if(justPressed && this.onFloor()) {
            this.jumpFrames += 40;
            if(y==0) this.increaseY(1);
        }
        if(!this.onFloor()) {
            if(this.getJumpFrames() > 30) {
                this.decreaseVspeed(100 * delta);
            } 
            else if(jumpFrames > 0) {
                this.decreaseVspeed(50 * delta);
            }
        }
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
