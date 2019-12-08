package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class LevelOne extends Level {

    Toriyasu toriyasu;

    OrthographicCamera camera;

    // Map
    TmxMapLoader tmxLoader;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;

    public LevelOne(final Catnap game) {
        super(game);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        toriyasu = new Toriyasu();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();

        // Load map
        tmxLoader = new TmxMapLoader();
        tiledMap = tmxLoader.load("level/testLevel2.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void render(float delta) {
        super.render(delta);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        toriyasu.logic(delta);

        game.batch.begin();
        game.batch.draw(toriyasu.sprite, toriyasu.x, toriyasu.y);
        game.batch.end();
    }
}

