package com.nebsting.catnap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import java.util.ArrayList;

public class Catnap extends Game {
	SpriteBatch batch;

    Map map;

    Player player;

    ArrayList<Aris> arisList;

    Aris aris;
    Camera camera;

    ShapeRenderer hitboxes;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

        map = new Map("level/testlevel/");

        player = new Player(2500,70,map);

        // Multiple arises
        arisList = new ArrayList<Aris>();
        aris = new Aris(2500,580,map);

        arisList.add(aris);

        camera = new Camera(player);
        
        hitboxes = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.logic();
        if(player.colRec.bottom(aris)) {
            player.setVy(50);
        }

        for(Aris obj: arisList) {
            obj.logic();
        }

        camera.logic();

        map.renderer.setView(camera);
        map.renderer.render();

        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        batch.draw(player.sprite, player.getSpritePosX(), player.getSpritePosY());

        for(Aris obj: arisList) {
            batch.draw(obj.sprite, obj.getX(), obj.getY());
        }

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
