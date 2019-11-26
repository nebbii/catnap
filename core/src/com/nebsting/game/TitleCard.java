package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class TitleCard implements Screen {

    final Catnap game;

    OrthographicCamera camera;

    public TitleCard(final Catnap game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        game.batch.begin();
        game.batch.draw(game.img, 0, 0, camera.viewportWidth, camera.viewportHeight);
        game.font.draw(game.batch, "PRESS START", 800 / 2, 480 / 2); 
        game.batch.end();

        if(Gdx.input.isTouched()) {
            Gdx.app.log("Bing", "Going to level 1!");
            game.setScreen(new LevelOne(game));
        }
    }

    @Override
    public void dispose() {}
    @Override
    public void hide() {}
    @Override
    public void pause() {}
    @Override
    public void resize(int w, int h) {}
    @Override
    public void resume() {}
    @Override
    public void show() {}

}

