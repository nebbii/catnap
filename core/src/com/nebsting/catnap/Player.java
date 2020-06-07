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

    public int lastOnGround;

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
        lastOnGround = 0;

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

        if(getOnGround()) lastOnGround = 5;
        if(lastOnGround>0) lastOnGround--;

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
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && lastOnGround > 0) {
            vy = jumpSpeed; lastOnGround = 0;

            this.setOnGround(false);
        }
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
