package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Toriyasu extends Player {

    public Toriyasu() {
        super();

        // Rect
        x = 800 / 2;
        y = 0;
        width = 128;
        height = 128;

        // Unit
        health     = 100;
        weight     = 50;

        this.sprite = new Texture(Gdx.files.internal("obj/cfang/stand.gif"));
    }

}
