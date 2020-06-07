package com.nebsting.catnap;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {

    Player player;

    public Camera(Player player) {
        this.setToOrtho(false, 1280, 720);
        this.player = player;
    }

    public void logic() {
        this.position.set(player.x + (player.getWidth() / 2), 350, 0);
        this.update();
    }

}

