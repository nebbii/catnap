package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class LevelOne implements Screen {

    final Catnap game;

    OrthographicCamera camera;

    Toriyasu toriyasu;

    public LevelOne(final Catnap game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        toriyasu = new Toriyasu();
        toriyasu.x = 800 / 2;
        toriyasu.y = 0;
        toriyasu.width = 64;
        toriyasu.height = 64;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Controls
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            toriyasu.jump(delta, Gdx.input.isKeyJustPressed(Input.Keys.SPACE));
        }
        toriyasu.gravity(delta);

        camera.update();

        game.batch.begin();
        game.batch.draw(toriyasu.sprite, toriyasu.x, toriyasu.y);
        game.batch.end();
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
    public void resize(int i1, int i2) {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
    }
}

