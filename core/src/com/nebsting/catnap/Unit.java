package com.nebsting.catnap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;
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

    public CollisionRectangle collisionRec;
    public CollisionPolygon collisionPoly;

    public Unit() {
        collisionRec = new CollisionRectangle(this);
        collisionPoly = new CollisionPolygon(this);
    }

    /**
     * Moves unit according to vy, increases velocity if not on ground
     */
    public void gravity() {
        if(!onGround) {
            vy = Math.max(vy - fallSpeed, (jumpSpeed * -2));
        }

        y += vy * Gdx.graphics.getDeltaTime();
    }

    public void collideLayers(Rectangle[] rectangles, Polygon[] polygons) {
        boolean land = false;

        for(int i = 0; i<rectangles.length; i++) {
            Rectangle col = rectangles[i];

            collisionRec.top(col);
            collisionRec.left(col);
            collisionRec.right(col);

            // check if unit is standing on any rectangle
            if(collisionRec.bottom(col)) {
                land = true;
            }
        }

        for(int i = 0; i<polygons.length; i++) {
            Polygon col = polygons[i];

            collisionPoly.top(col);
            collisionPoly.left(col);
            collisionPoly.right(col);

            // check if unit is standing on any rectangle
            if(collisionPoly.bottom(col)) {
                land = true;
            }
        }

        setOnGround(land);
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
