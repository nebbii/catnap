package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Toriyasu extends Player {

    public Toriyasu() {
        super();

        this.health     = 100;
        this.weight     = 16;

        this.sprite = new Texture(Gdx.files.internal("bucket.png"));
    }

}
