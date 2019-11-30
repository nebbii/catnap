package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class LevelOne extends Level {

    OrthographicCamera camera;

    Toriyasu toriyasu;

    public LevelOne(final Catnap game) {
        super(game);

        toriyasu = new Toriyasu();
        toriyasu.x = 800 / 2;
        toriyasu.y = 0;
        toriyasu.width = 64;
        toriyasu.height = 64;
    }

    public void render(float delta) {
        super.render(delta);

        toriyasu.controller.checkPresses(delta);

        // Gravity
        toriyasu.gravity(delta);

        game.batch.begin();
        game.batch.draw(toriyasu.sprite, toriyasu.x, toriyasu.y);
        game.batch.end();
    }
}

