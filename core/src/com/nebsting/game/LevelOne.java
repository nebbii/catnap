package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class LevelOne extends Level {

    Toriyasu toriyasu;

    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;

    public LevelOne(final Catnap game) {
        super(game);

        toriyasu = new Toriyasu();
        toriyasu.x = 300;
        toriyasu.y = 200;

        tiledMap = new TmxMapLoader().load("level/testLevel2.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void render(float delta) {
        super.render(delta);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        toriyasu.logic(delta);

        camera.position.set(toriyasu.x, 350, 0);

        game.batch.begin();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.draw(toriyasu.sprite, toriyasu.x, toriyasu.y);
        game.batch.end();
    }
}

