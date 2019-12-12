package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Level implements Screen {

    final Catnap game;

    Viewport viewport;
    OrthographicCamera camera;

    Toriyasu toriyasu;

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

        Gdx.app.log("Obj", mapCollisionObjects.toString());

        Gdx.app.log("Obj", "Makin objects");
        for(int i=0; i<mapCollisionObjects.getCount(); i++) {
            if(mapCollisionObjects.get(i) instanceof PolylineMapObject) {
                PolylineMapObject test = (PolylineMapObject) mapCollisionObjects.get(i);
                Polyline polytest = test.getPolyline();
                Gdx.app.log("Map Loop", polytest.toString());
            }
        }

        //for (RectangleMapObject rectangleObject : mapCollisionObjects.getByType(RectangleMapObject.class)) {
        //    Rectangle i = rectangleObject.getRectangle();
        //    Gdx.app.log("Obj", i.toString());
        //    Gdx.app.log("Obj", "Bing");
        //}

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

