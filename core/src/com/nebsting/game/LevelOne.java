package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class LevelOne extends Level {

    Toriyasu toriyasu;

    ShapeRenderer shapeRenderer;

    // Level
    OrthogonalTiledMapRenderer renderer;
    TiledMap testLevel2;

    public LevelOne(final Catnap game) {
        super(game);

        toriyasu = new Toriyasu();
        shapeRenderer = new ShapeRenderer();

        testLevel2 = loader.load("level/testLevel2.tmx");
        renderer = new OrthogonalTiledMapRenderer(testLevel2);
    }

    public void render(float delta) {
        super.render(delta);

        toriyasu.logic(delta);

        camera.update();

        renderer.render();

        game.batch.begin();
        game.batch.draw(toriyasu.sprite, toriyasu.x, toriyasu.y);
        game.batch.end();
    }
}

