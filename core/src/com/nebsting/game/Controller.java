package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controller {
    Player player;

    public Controller(Player player) {
        this.player = player;
    }

    public void checkPresses(float delta) {
        jumpCheck(delta);
    }

    public void jumpCheck(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.jump(delta, Gdx.input.isKeyJustPressed(Input.Keys.SPACE));
        }
    }

}


