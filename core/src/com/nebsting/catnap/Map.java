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

    public Map(Player player) {
        file = new TmxMapLoader().load("level/testlevel.tmx");
        renderer = new OrthogonalTiledMapRenderer(file);

        // Get collision objs from layers
        polygonObjects   = loadPolygonLayer(file.getLayers().get(1).getObjects());
        rectangleObjects = loadRectangleLayer(file.getLayers().get(2).getObjects());
    }

    public boolean checkRectangleCollision(Player player) {
        boolean landed = false;

        for(int i = 0; i<rectangleObjects.length; i++) {
            Rectangle col = rectangleObjects[i];
            // top
            if(checkCollisionFace(col, player, 1)) {
                player.collideTop(player.y);
                //Gdx.app.log("Collision", "Top: "+Float.toString(player.y));
            }
            // bottom
            if(checkCollisionFace(col, player, 4)) {
                player.collideBottom(player.y);
                landed = true;
                //Gdx.app.log("Collision", "Bottom: "+Float.toString(player.y));
            }
            // left
            if(checkCollisionFace(col, player, 2)) {
                player.collideLeft(player.x);
                //Gdx.app.log("Collision", "Left: "+Float.toString(player.x));
            }
            // right
            if(checkCollisionFace(col, player, 3)) {
                player.collideRight(player.x);
                //Gdx.app.log("Collision", "Right: "+Float.toString(player.x));
            }
        }
        return landed;
    }

    public boolean checkCollisionFace(Rectangle solid, Rectangle player, int face) {
        boolean collided = false;

        float x = player.x;
        float y = player.y;
        float w = player.width;
        float h = player.height;

        switch(face) {
            case 1: // top
                y += player.height;
                for(int i=0; i<Math.round(w); i++) {
                    if(solid.contains(x+i, y)) { 
                        Gdx.app.log("CollisionFace", "Vertically on iteration " + Integer.toString(i));
                        collided = true; 
                        i=Math.round(w);
                    }
                }
                break;

            // horizontal
            case 4: // bottom 
                y -= player.height / 2 ;
                for(int i=0; i<Math.round(w); i++) {
                    if(solid.contains(x+i, y)) { 
                        Gdx.app.log("CollisionFace", "Vertically on iteration " + Integer.toString(i));
                        collided = true; 
                        i=Math.round(w);
                    }
                }
                break;
            case 2: // left
                x -= player.width / 2 ;
                for(int i=0; i<Math.round(h); i++) {
                    if(solid.contains(x, y+i)) {
                        Gdx.app.log("CollisionFace", "Horizontally on iteration " + Integer.toString(i));
                        collided = true; 
                        i=Math.round(h);
                    }
                }
                break;
            case 3: // right 
                x += player.width / 4 + player.width;
                for(int i=0; i<Math.round(h); i++) {
                    if(solid.contains(x, y+i)) {
                        Gdx.app.log("CollisionFace", "Horizontally on iteration " + Integer.toString(i));
                        collided = true; 
                        i=Math.round(h);
                    }
                }
                break;

            default: Gdx.app.log("CollisionFace", "Invalid face"); break;
        }

        return collided;
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

