package com.nebsting.catnap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Handles player logic
 *
 * General actor logic can be found in the Unit class
 * Animation functions can be found in the PlayerAnimation class
 */
public class Player extends Unit {
    public PlayerAnimation animation;
    public TextureRegion sprite;

    public float spriteOffsetX;
    public float spriteOffsetY;

    public Player(float x, float y) {
        width = 100;
        height = 192;
        spriteOffsetX = 0;
        spriteOffsetY = 0;

        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;

        walkAcceleration = 30;
        walkMaxSpeed = 600;

        jumpSpeed = 1400;
        fallSpeed = 60;
        onGround = false;

        lastDirection = 'r';

        animation = new PlayerAnimation(this);
    }

    /**
     * This function gets called in render()
     */
    public void logic() {
        gravity();

        move();
        jump();

        this.sprite = animation.setCurrentSprite();

        this.animation.timer += Gdx.graphics.getDeltaTime();
    }

    /**
     * Moves player according to walk acceleration and speed
     */
    public void move() {
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

    /**
     * Jumps player by bumping vy
     */
    public void jump() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && onGround) {
            vy = jumpSpeed;
            this.setOnGround(false);
        }
    }

    /**
     * Moves player according to vy, increases velocity if not on ground
     */
    public void gravity() {
        if(!onGround) {
            vy = Math.max(vy - fallSpeed, (jumpSpeed * -2));;
        }

        y += vy * Gdx.graphics.getDeltaTime();
    }

    /**
     * Check collision with all objects of rectangle layer
     */    
    public void collideRectangleLayer(Rectangle[] objects) {
        boolean land = false;

        for(int i = 0; i<objects.length; i++) {
            Rectangle col = objects[i];

            collideTop(col);
            collideLeft(col);
            collideRight(col);

            // check if player is standing on any rectangle
            if(collideBottom(col)) {
                land = true;
            }
        }

        setOnGround(land);
    }

    /**
     * Sets vspeed to 0 when the top-center of rectangle collides
     */
    public void collideTop(Rectangle col) {
        while(col.contains(this.x + (this.width / 2), this.y + this.height)) { 
            this.setVy(0);
            this.y--;
        }
    }

    /** 
     * Returns whether the player should be considered landed or not
     */
    public boolean collideBottom(Rectangle col) {
        boolean landed = false;

        for(int i=Math.round(this.width/6); i < Math.round(this.width/6*5); i++) {
            // check for landing
            if(col.contains(this.x + i, this.y - this.height / 16)) { 
                landed = true;
            }

            // push out of floor
            while(col.contains(this.x + i, this.y +1 - this.height / 24)) { 
                this.setVy(0);
                this.y++;
            }
        }

        return landed;
    }

    /**
     * Sets vx to 0 when the left of rectangle collides
     */
    public void collideLeft(Rectangle col) {
        // check every pixel for collision
        while((col.contains(this.x - this.width / 8, this.y + (this.height / 3)))
               || (col.contains(this.x - this.width / 8, this.y + (this.height / 3 * 2)))
        ) {
            this.setVx(0);
            this.x++;
        }
    }

    /**
     * Sets vx to 0 when the right of rectangle collides
     */
    public void collideRight(Rectangle col) {
        while(col.contains(this.x + this.width, this.y + (this.height / 3))
              || (col.contains(this.x + this.width, this.y + (this.height / 3 * 2)))
        ) {
            this.setVx(0);
            this.x--;
        }
    }

    /**
     * Shows position data in log
     */
    public void dumpPosData() {
        Gdx.app.log("On Ground", Boolean.toString(onGround));
        Gdx.app.log("Speed", Float.toString(vy - jumpSpeed) + " of " + Float.toString(jumpSpeed));
        Gdx.app.log("X: ", Float.toString(x));
        Gdx.app.log("Y: ", Float.toString(y));
        Gdx.app.log("VY: ", Float.toString(vy));
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

    public void setSpriteOffsetX(float spriteOffsetX) {
        this.spriteOffsetX = spriteOffsetX;
    }

    public float getSpriteOffsetX() {
        return spriteOffsetX;
    }

    public void setSpriteOffsetY(float spriteOffsetY) {
        this.spriteOffsetY = spriteOffsetY;
    }

    public float getSpriteOffsetY() {
        return spriteOffsetY;
    }

    public float getSpritePosX() {
        return x + spriteOffsetX;
    }

    public float getSpritePosY() {
        return y + spriteOffsetY;
    }

}
