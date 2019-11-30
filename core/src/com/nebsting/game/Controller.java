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
        moveCheck(delta);
    }

    public void jumpCheck(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.jump(delta, Gdx.input.isKeyJustPressed(Input.Keys.SPACE));
        }
    }

    public void moveCheck(float delta) {
        Boolean[] input = new Boolean[2];
        Boolean[] justInput = new Boolean[2];

        input[0] = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        input[1] = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        justInput[0] = Gdx.input.isKeyJustPressed(Input.Keys.LEFT);
        justInput[1] = Gdx.input.isKeyJustPressed(Input.Keys.RIGHT);

        player.run(delta, input, justInput);
        
    }

}


