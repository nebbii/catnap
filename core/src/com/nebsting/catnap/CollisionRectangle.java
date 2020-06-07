package com.nebsting.catnap;

import com.badlogic.gdx.math.Rectangle;

public class CollisionRectangle {

    Unit unit;

    public CollisionRectangle(Unit unit) {
        this.unit = unit;
    }

    public void top(Rectangle col) {
        while(col.contains(unit.x + (unit.width / 2), unit.y + unit.height)) { 
            unit.setVy(0);
            unit.y--;
        }
    }

    public boolean bottom(Rectangle col) {
        boolean landed = false;

        for(int i=Math.round(unit.width/6); i < Math.round(unit.width/6*5); i++) {
            // check for landing
            if(col.contains(unit.x + i, unit.y - unit.height / 16)) { 
                landed = true;
            }

            // push out of floor
            while(col.contains(unit.x + i, unit.y +1 - unit.height / 24)) { 
                unit.setVy(0);
                unit.y++;
            }
        }

        return landed;
    }

    public void left(Rectangle col) {
        // check every pixel for collision
        while((col.contains(unit.x - unit.width / 8, unit.y + (unit.height / 3)))
           || (col.contains(unit.x - unit.width / 8, unit.y + (unit.height / 3 * 2)) )
        ) {
            unit.setVx(0);
            unit.x++;
        }
    }

    public void right(Rectangle col) {
        while(col.contains(unit.x + unit.width + unit.width / 8, unit.y + (unit.height / 3))
          || (col.contains(unit.x + unit.width + unit.width / 8, unit.y + (unit.height / 3 * 2)) )
        ) {
            unit.setVx(0);
            unit.x--;
        }
    }
}

