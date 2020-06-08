package com.nebsting.catnap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Aris extends Unit {

    Texture sprite;

    public Aris(float x, float y, Map map) {
        width = 96;
        height = 96;

        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;

        walkAcceleration = 30;
        walkMaxSpeed = 600;

        jumpSpeed = 1400;
        fallSpeed = 2;

        onGround = false;

        this.map = map;

        sprite = new Texture(Gdx.files.internal("obj/misc/aris.png"));
    }

    public void logic() {
        collideLayers();
        gravity();
    }
}

