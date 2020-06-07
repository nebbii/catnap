package com.nebsting.catnap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Inherited by units affected by gravity and collision
 */

public class Unit extends Rectangle {

    public int vx;
    public int vy;
    public boolean onGround;

    public int jumpSpeed;
    public int fallSpeed;

    public char lastDirection; // l & r

    public int walkAcceleration;
    public int walkMaxSpeed;

    public Unit() {}

    /**
     * Moves unit according to vy, increases velocity if not on ground
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

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean getOnGround() {
        return onGround;
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

}
