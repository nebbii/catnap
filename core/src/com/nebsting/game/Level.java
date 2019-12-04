package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level implements Screen {

    final Catnap game;

    Viewport viewport;
    Camera camera;
    TmxMapLoader maploader;
    OrthogonalTiledMapRenderer renderer;
    TiledMap map;

    public Level(final Catnap game) {
        this.game = game;
        camera = new PerspectiveCamera();
        viewport = new FitViewport(960, 720, camera);

        //maploader = new TmxMapLoader();
        //map = maploader.load("level/test.tmx");
        //renderer = new OrthogonalTiledMapRenderer(map);

        //camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renderer.render();
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

