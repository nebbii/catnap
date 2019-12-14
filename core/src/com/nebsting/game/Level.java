package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Level implements Screen {

    final Catnap game;

    Viewport viewport;
    OrthographicCamera camera;

    Toriyasu toriyasu;

    Polygon[] objLayer;

    TiledMap map;
    TiledMapRenderer mapRenderer;
    MapLayer mapCollision;
    MapObjects mapCollisionObjects;

    public Level(final Catnap game, String mapFileName, int mapCollisionLayer, float playerX, float playerY) {
        this.game = game;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        // Character
        toriyasu = new Toriyasu();
        toriyasu.x = playerX;
        toriyasu.y = playerY;

        // Map
        map = new TmxMapLoader().load("level/" + mapFileName + ".tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        // Collision
        mapCollision = map.getLayers().get(mapCollisionLayer);
        mapCollisionObjects = mapCollision.getObjects();

        Polygon[] objLayer = new Polygon[255];
        for(int i=0; i<mapCollisionObjects.getCount(); i++) {
            if(mapCollisionObjects.get(i) instanceof PolygonMapObject) {
                PolygonMapObject obj = (PolygonMapObject) mapCollisionObjects.get(i);
                Polygon polytest = obj.getPolygon();

                objLayer[i] = polytest;
                Gdx.app.log("Obj", "Added polygon!");
                for(float j : objLayer[i].getVertices()) { 
                    Gdx.app.log("Obj", Float.toString(j));
                }
            } else {
                Gdx.app.log("Obj", "the fuck is this");
            }
        }

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.setView(camera);
        mapRenderer.render();

        toriyasu.logic(delta, objLayer);

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
        map.dispose();
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

