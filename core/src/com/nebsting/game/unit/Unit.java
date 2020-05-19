package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Unit extends Rectangle {

    float health;
    float hspeed;
    float vspeed;
    float weight;
    int jumpFrames;

    boolean onFloor; // Checks floor on getter
    int airFrames;

    Polygon hitbox;

    char lastDirection; // l & r

    TextureRegion sprite; 

    public Unit() {
        this.lastDirection = 'r';
        this.airFrames = 0;

        // Hitbox
        this.hitbox = new Polygon(new float[] { 0, 0, this.width, 0, this.width,
            this.height, 0, this.height });
    }

    // Check for floor collision, push em up!
    public void checkFloor(Polygon[] map) {
        boolean bool = false;

        if(this.getY() < 1) {
            bool = true;
        }

        for(int i = 0; i<map.length; i++) {
            // check for floor
            if(map[i].contains(this.x, this.y-this.height)) {
                bool = true;

                // check for ankle snap
                float snap = this.y;
                for(int j = 0; j < ( this.height / 2 ); j++) {
                    // raise the bar until half player height
                    float checkBar = this.y + (float) j - (this.height);

                    if(map[i].contains(this.x, checkBar)) {
                        snap = this.y + (float) j;
                    }
                }
                //Gdx.app.log("snapped to:", Float.toString(snap));
                this.y = snap;
            }
        }

        setOnFloor(bool);
    }

    // Gets run every frame
    public void logic(float delta, Polygon[] map) {
        this.hitbox.setPosition(this.x, this.y);

        this.checkFloor(map);
        this.gravity(delta);
    }
    
    // Returns next position forced by gravity
    public void gravity(float delta) {
        if(getY() < 1) setY(0); 

        // Countdown jump frames
        if(jumpFrames > 0) jumpFrames -= 1; 

        // Increase falling speed
        if(!getOnFloor()) {
            if(getAirFrames() > 3) {
                if(getVspeed() < 20) {
                    increaseVspeed(1);
                }
                decreaseY((this.getWeight() * getVspeed()) * delta);
            }
            increaseAirframes(1);
            //Gdx.app.log("Airframe count:", Integer.toString(getAirFrames()));
        } 
        else {
            setVspeed(0);
            setJumpFrames(0);
            setAirFrames(0);
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

    protected void increaseAirframes(float a) {
        this.airFrames += a;
    }

    protected void decreaseAirFrames(float a) {
        this.airFrames -= a;
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

    public void setAirFrames(int airFrames) {
        this.airFrames = airFrames;
    }

    public int getAirFrames() {
        return airFrames;
    }
}
