package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Toriyasu extends Rectangle {

    int health;
    int speed;
    float vspeed;

    Texture sprite; 

    public Toriyasu() {
        sprite = new Texture(Gdx.files.internal("bucket.png"));
        health = 100;
        vspeed = 0;
    }

    // Returns next position forced by gravity
    public void gravity() {
        Gdx.app.log("Gravity", Float.toString(this.getVspeed()));
        
        if(y < 1) {
            setY(0);
            setVspeed(0);
        } else {
            float result = vspeed*(vspeed+400);
            y = (y + result) * Gdx.graphics.getDeltaTime();

            vspeed--;
        }
    }
    
    public void jump() {
        if(y == 0) {
            y += 1;
            setVspeed(getVspeed()+20);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
}
