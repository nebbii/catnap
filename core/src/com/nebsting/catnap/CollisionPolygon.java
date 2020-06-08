package com.nebsting.catnap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;

public class CollisionPolygon {

    Unit unit;

    public CollisionPolygon(Unit unit) {
        this.unit = unit;
    }

    /**
     * Sets vspeed to 0 when the top-center of polygon collides
     */
    public void top(Polygon col) {
        float initial = unit.y;

        while(col.contains(unit.x + (unit.width / 2), unit.y + unit.height)) { 
            unit.setVy(0);
            unit.y--;

            if(unit.y < initial - unit.getHeight()) break;
        }
    }

    /** 
     * Returns whether the unit should be considered landed or not
     */
    public boolean bottom(Polygon col) {
        float initial;

        boolean landed = false;

        if(unit.getVy() < 0) {
            for(int i=Math.round(unit.width/6); i < Math.round(unit.width/6*5); i++) {
                // check for landing
                if(col.contains(unit.x + i, unit.y - unit.height / 16)) { 
                    landed = true;
                }

                initial = unit.y;

                // push out of floor
                while(col.contains(unit.x + i, unit.y + 1 - unit.height / 24)) { 
                    unit.setVy(0);
                    unit.setY(unit.getY() + 1);

                    if(unit.y > initial + unit.getHeight()) break;
                }
            }
        }

        return landed;
    }

    /**
     * Sets vx to 0 when the left of Polygon collides
     */
    public void left(Polygon col) {
        float initial = unit.x;

        // check every pixel for collision
        while((col.contains(unit.x - unit.width / 8, unit.y + (unit.height / 3)))
                   || col.contains(unit.x - unit.width / 8, unit.y + (unit.height / 3 * 2))) {
            unit.setVx(0);
            unit.x++;

            if(unit.x > initial + unit.getWidth()) break;
        }
    }

    /**
     * Sets vx to 0 when the right of Polygon collides
     */
    public void right(Polygon col) {
        float initial = unit.x;

        while(col.contains(unit.x + unit.width + unit.width / 8, unit.y + (unit.height / 3))
                  || col.contains(unit.x + unit.width + unit.width / 8, unit.y + (unit.height / 3 * 2) )) {
            unit.setVx(0);
            unit.x--;

            if(unit.x < initial - unit.getWidth()) break;
        }
    }
}
