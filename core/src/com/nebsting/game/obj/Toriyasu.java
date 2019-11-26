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
        if((getY() < 0) && (getVspeed() <= 0)) {
            setY(0);
            setVspeed(0);
        }
        else {
            setY(getY() - (vspeed * 2 ));
            vspeed = (vspeed - 1f);
        }

        Gdx.app.log("Gravity", "Current gravity is ");
        Gdx.app.log("Gravity", Float.toString(this.getVspeed()));
    }
    
    public void jump() {
        setY(1);
        setVspeed(getVspeed()+40);
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
