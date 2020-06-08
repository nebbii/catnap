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
	SpriteBatch batch;

    Map map;

    Player player;
    Aris aris;
    Aris aris2;
    Aris aris3;
    Aris aris4;
    Camera camera;

    ShapeRenderer hitboxes;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

        map = new Map("level/testlevel/");

        player = new Player(2500,70,map);
        aris = new Aris(2500,580,map);
        aris2 = new Aris(2500,580,map);
        aris3 = new Aris(2700,580,map);
        aris4 = new Aris(2300,580,map);
        camera = new Camera(player);
        
        hitboxes = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.logic();
        if(player.colRec.bottom(aris)) {
            player.setVy(20);
        }
        aris.logic();
        aris2.logic();
        aris3.logic();
        aris4.logic();
        camera.logic();

        map.renderer.setView(camera);
        map.renderer.render();

        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        batch.draw(player.sprite, player.getSpritePosX(), player.getSpritePosY());
        batch.draw(aris.sprite, aris.getX(), aris.getY());
        batch.draw(aris2.sprite, aris2.getX(), aris2.getY());
        batch.draw(aris3.sprite, aris3.getX(), aris3.getY());
        batch.draw(aris4.sprite, aris4.getX(), aris4.getY());
		batch.end();

        //hitboxes.setProjectionMatrix(camera.combined);
        //hitboxes.begin(ShapeType.Line);
        //hitboxes.rect(player.x, player.y, player.getWidth(), player.getHeight());
        //hitboxes.rect(aris.x, aris.y, aris.getWidth(), aris.getHeight());
        //hitboxes.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
