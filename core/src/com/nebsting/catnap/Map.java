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

    public Map() {
        file = new TmxMapLoader().load("level/testlevel.tmx");
        renderer = new OrthogonalTiledMapRenderer(file);

        // Get collision objs from layers
        polygonObjects   = loadPolygonLayer(file.getLayers().get(1).getObjects());
        rectangleObjects = loadRectangleLayer(file.getLayers().get(2).getObjects());
    }

    public boolean checkRectangleCollision(Player player) {
        boolean landed = false;
        for(int i = 0; i<rectangleObjects.length; i++) {
            if(rectangleObjects[i] instanceof Rectangle) {
                // top
                if(rectangleObjects[i].contains(player.x, player.y)) {
                    player.collideTop(player.y);
                    Gdx.app.log("Collision", "Top: "+Float.toString(player.y));
                }

                // bottom
                if(rectangleObjects[i].contains(player.x, player.y-player.height)) {
                    player.collideBottom(player.y);
                    landed = true;
                    Gdx.app.log("Collision", "Bottom: "+Float.toString(player.y));
                }
                
                // left
                if(rectangleObjects[i].contains(player.x, player.y)) {
                    player.collideLeft(player.x);
                    Gdx.app.log("Collision", "Left: "+Float.toString(player.x));
                }
                
                // right
                if(rectangleObjects[i].contains(player.x+player.width, player.y)) {
                    player.collideRight(player.x);
                    Gdx.app.log("Collision", "Right: "+Float.toString(player.x));
                }
            }

        }
        return landed;
    }

    // Returns polygons from a Tiled object layer
    public Polygon[] loadPolygonLayer(MapObjects layer) {
        Polygon[] result = new Polygon[layer.getCount()];

        for(int i=0; i<result.length; i++) {
            if(layer.get(i) instanceof PolygonMapObject) {
                PolygonMapObject cast = (PolygonMapObject) layer.get(i);
                Polygon objects = cast.getPolygon();

                result[i] = objects;
                //Gdx.app.log("ObjLayers", objects.toString());
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

