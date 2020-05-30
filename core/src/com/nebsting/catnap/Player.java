package com.nebsting.catnap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Rectangle {


    public int vx;
    public int vy;
    public boolean onGround;

    public int walkAcceleration;
    public int walkMaxSpeed;

    public int jumpSpeed;
    public int fallSpeed;

    public char lastDirection; // l & r
    public PlayerAnimation animation;
    public TextureRegion sprite;

    public Player() {
        width = 60;
        height = 100;
        x = 100;
        y = 184;
        vx = 0;
        vy = 0;

        walkAcceleration = 20;
        walkMaxSpeed = 400;

        jumpSpeed = 600;
        fallSpeed = 20;
        onGround = false;

        lastDirection = 'r';

        animation = new PlayerAnimation(this);
    }

    public void logic() {
        movePlayer();
        jumpPlayer();
        gravity();

        this.sprite = animation.setCurrentSprite();

        this.animation.timer += Gdx.graphics.getDeltaTime();
        /*
        Gdx.app.log("On Ground", Boolean.toString(onGround));
        Gdx.app.log("Speed", Float.toString(vy - jumpSpeed) + " of " + Float.toString(jumpSpeed));
        Gdx.app.log("X: ", Float.toString(x));
        Gdx.app.log("Y: ", Float.toString(y));
        Gdx.app.log("VY: ", Float.toString(vy));
        */
    }

    public void movePlayer() {
        // left
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            vx = Math.max(vx - walkAcceleration, (walkMaxSpeed * -1));
        } 
        else if(vx < 0){
            vx = Math.min(vx + (walkAcceleration * 2), 0);
        } 

        // right
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            vx = Math.min(vx + walkAcceleration, walkMaxSpeed);
        } 
        else if(vx > 0){
            vx = Math.max(vx - (walkAcceleration * 2), 0);
        }

        x += vx * Gdx.graphics.getDeltaTime();
    }

    public void jumpPlayer() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && onGround) {
            vy = jumpSpeed;
            this.setOnGround(false);
        }
    }

    public void gravity() {
        if(!onGround) {
            vy = vy - fallSpeed;
        }

        y += vy * Gdx.graphics.getDeltaTime();
    }

    public void collideTop(Rectangle col) {
        while(col.contains(this.x + (this.width / 2), this.y + this.height)) { 
            this.setVy(0);
            this.y--;
        }
    }

    public void collideBottom(Rectangle col) {
        this.setOnGround(true);
        for(int i=Math.round(this.width/6); i < Math.round(this.width/6*5); i++) {
            while(col.contains(this.x + i, this.y +1 - this.height / 16)) { 
                this.setVy(0);
                this.y++;
            }
        }
    }

    public void collideLeft(Rectangle col) {
        // check every pixel for collision
        while(col.contains(this.x - this.width / 8, this.y + (this.height / 3))) {
            this.setVx(0);
            this.x++;
        }
    }

    public void collideRight(Rectangle col) {
        while(col.contains(this.x + this.width, this.y + (this.height / 3))) {
            this.setVx(0);
            this.x--;
        }
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVx() {
        return vx;
    }

    public void setVy(int vy) {
        this.vy = vy;

        if(vy != 0) {
            this.onGround = false;
        }     
    }

    public int getVy() {
        return vy;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean getOnGround() {
        return onGround;
    }

}
