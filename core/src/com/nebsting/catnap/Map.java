package com.nebsting.catnap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Map {

    TiledMap file;

    Polygon[] polygonObjects;
    Rectangle[] rectangleObjects;

    MapRenderer renderer;

    Player player;

    public Map(Player player) {
        file = new TmxMapLoader().load("level/testlevel2.tmx");
        renderer = new OrthogonalTiledMapRenderer(file);

        // Get collision objs from layers
        polygonObjects   = loadPolygonLayer(file.getLayers().get(1).getObjects());
        rectangleObjects = loadRectangleLayer(file.getLayers().get(2).getObjects());

        // attach player
        this.player = player;
    }

    public void checkRectangleCollision() {
        this.player.setOnGround(false);

        for(int i = 0; i<rectangleObjects.length; i++) {
            Rectangle col = rectangleObjects[i];
            // top
            if(col.contains(player.x + (player.width / 2), player.y + player.height)) { 
                player.collideTop(col);
                //Gdx.app.log("Collision", "Top: "+Float.toString(player.y));
            }
            // bottom
            for(int j=0; j<player.width-1; j++) {
                if(col.contains(player.x + j, player.y - player.height / 8)) { 
                    player.collideBottom(col);
                    //Gdx.app.log("Collision", "Bottom: "+Float.toString(player.y));
                }
            }
            // left
            if(col.contains(player.x - player.width / 8, player.y + (player.height / 3))) {
                player.collideLeft(col);
                //Gdx.app.log("Collision", "Left: "+Float.toString(player.y));
            }
            // right
            if(col.contains(player.x + player.width, player.y + (player.height / 3))) {
                player.collideRight(col);
                //Gdx.app.log("Collision", "Right: "+Float.toString(player.y));
            }
        }
    }

    // Returns polygons from a Tiled object layer
    public Polygon[] loadPolygonLayer(MapObjects layer) {
        Polygon[] result = new Polygon[layer.getCount()];

        for(int i=0; i<result.length; i++) {
            if(layer.get(i) instanceof PolygonMapObject) {
                PolygonMapObject cast = (PolygonMapObject) layer.get(i);
                Polygon objects = cast.getPolygon();

                result[i] = objects;
            }
        }

        return result;
    }

    // Returns rectangles from a Tiled object layer
    public Rectangle[] loadRectangleLayer(MapObjects layer) {
        Rectangle[] result = new Rectangle[layer.getCount()];

        for(int i=0; i<result.length; i++) {
            if(layer.get(i) instanceof RectangleMapObject) {
                RectangleMapObject cast = (RectangleMapObject) layer.get(i);
                Rectangle objects = cast.getRectangle();

                result[i] = objects;
                //Gdx.app.log("ObjLayers", objects.toString());
            }
        }

        return result;
    }
}

