package com.nebsting.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Catnap extends Game {
	SpriteBatch batch;
    BitmapFont font;
    Texture img;

    Music themesong;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        font = new BitmapFont();
		img = new Texture(Gdx.files.internal("title.png"));

        Music themesong = Gdx.audio.newMusic(Gdx.files.internal("mus/lightplaneleft.mp3"));

        themesong.setVolume(0.5f);
        themesong.setLooping(true);

        this.setScreen(new LevelOne(this));
	}

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
