package com.nebsting.catnap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Catnap extends Game {
    OrthographicCamera camera;
	SpriteBatch batch;

    Map map;

    Player player;

    ShapeRenderer hitboxes;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 960);

        player = new Player();
        map = new Map("level/testlevel/");
        
        hitboxes = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.collideRectangleLayer(map.rectangleObjects);

        player.logic();

        map.renderer.setView(camera);
        map.renderer.render();

        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        batch.draw(player.sprite, player.x-player.spriteOffset, player.y);
		batch.end();

        hitboxes.setProjectionMatrix(camera.combined);
        hitboxes.begin(ShapeType.Line);
        hitboxes.rect(player.x, player.y, player.getWidth(), player.getHeight());
        hitboxes.end();

        camera.position.set(player.x + (player.getWidth() / 2), 450, 0);
        camera.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
