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

    public Map map;
    public CollisionRectangle colRec;
    public CollisionPolygon colPoly;

    public Unit() {
        colRec = new CollisionRectangle(this);
        colPoly = new CollisionPolygon(this);
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

    public void collideLayers() {
        boolean land = false;

        // Collide with rectangles
        for(int i = 0; i<map.rectangleObjects.length; i++) {
            Rectangle col = map.rectangleObjects[i];

            colRec.top(col);
            colRec.left(col);
            colRec.right(col);

            // check if unit is standing on any rectangle
            if(colRec.bottom(col)) {
                land = true;
            }
        }

        // Collide with polygons
        for(int i = 0; i<map.polygonObjects.length; i++) {
            Polygon col = map.polygonObjects[i];

            colPoly.top(col);
            colPoly.left(col);
            colPoly.right(col);

            // check if unit is standing on any rectangle
            if(colPoly.bottom(col)) {
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
