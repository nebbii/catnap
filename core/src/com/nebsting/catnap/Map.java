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

/**
 * Handles TiledMap files and layering
 */
public class Map {

    TiledMap file;

    Polygon[] polygonObjects;
    Rectangle[] rectangleObjects;

    MapRenderer renderer;

    public Map(String path) {
        file = new TmxMapLoader().load(path + "level.tmx");
        renderer = new OrthogonalTiledMapRenderer(file);

        // Get collision objs from layers
        polygonObjects   = loadPolygonLayer(file.getLayers().get(2).getObjects());
        rectangleObjects = loadRectangleLayer(file.getLayers().get(1).getObjects());
    }

    /**
     * Returns polygons from a Tiled object layer
     */
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

    /**
     * Returns rectangles from a Tiled object layer
     */
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

