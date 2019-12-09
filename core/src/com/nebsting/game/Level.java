package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level implements Screen {

    final Catnap game;

    Viewport viewport;
    OrthographicCamera camera;

    Toriyasu toriyasu;

    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;

    public Level(final Catnap game, String map, float playerX, float playerY) {
        this.game = game;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        toriyasu = new Toriyasu();
        toriyasu.x = 300;
        toriyasu.y = 200;

        tiledMap = new TmxMapLoader().load("level/" + map + ".tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        toriyasu.logic(delta);

        camera.position.set(toriyasu.x + (toriyasu.getWidth() / 2), 350, 0);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(toriyasu.sprite, toriyasu.x, toriyasu.y);
        game.batch.end();
    }

    @Override
    public void dispose() {
        game.batch.dispose();
        tiledMap.dispose();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int w, int h) {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
    }
}

