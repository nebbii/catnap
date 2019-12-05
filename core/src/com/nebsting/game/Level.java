package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level implements Screen {

    final Catnap game;

    Viewport viewport;
    Camera camera;
    TmxMapLoader loader;

    public Level(final Catnap game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(960, 720, camera);

        loader = new TmxMapLoader();
        camera.position.set(viewport.getScreenWidth() / 2, viewport.getScreenHeight() / 2, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int w, int h) {
        viewport.update(w, h);
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
    }
}

