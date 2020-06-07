package com.nebsting.catnap;

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

    public Unit() {
    }
}

