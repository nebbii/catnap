package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LevelOne extends Level {

    Toriyasu toriyasu;

    ShapeRenderer shapeRenderer;

    public LevelOne(final Catnap game) {
        super(game);

        toriyasu = new Toriyasu();
        shapeRenderer = new ShapeRenderer();
    }

    public void render(float delta) {
        super.render(delta);

        toriyasu.logic(delta);

        game.batch.begin();
        game.batch.draw(toriyasu.sprite, toriyasu.x, toriyasu.y);
        game.batch.end();
    }
}

