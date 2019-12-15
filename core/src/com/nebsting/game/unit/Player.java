package com.nebsting.game;

import com.badlogic.gdx.Gdx;

public class Player extends Unit {

    float runFrames;

    Controller controller;

    public Player() {
        super();

        this.controller = new Controller(this);
    }

    public void jump(float delta, boolean justPressed) {
        // Start jump timer if just pressed
        if(justPressed && this.getOnFloor()) {
            this.setOnFloor(false);
            this.jumpFrames += 40;
            if(y==0) this.increaseY(2);
        }

        if(!this.getOnFloor()) {
            if(this.getJumpFrames() > 30) {
                this.decreaseVspeed(100 * delta);
            } 
            else if(jumpFrames > 0) {
                this.decreaseVspeed(50 * delta);
            }
        }
    }

    public void run(float delta, Boolean[] input, Boolean[] justInput) {
        // Start initial run timer on justpress
        if(justInput[0] || justInput[1]) {
            this.setRunFrames(40);
        }

        // Accelerating run
        if(!(input[0] && input[1])
                && this.getHspeed() < 400
                && this.getHspeed() > -400) {
            this.runAccel(delta, input);
        } 

        // Decelerating
        if( !input[0] && ( this.getHspeed() < 0 )
           || !input[1] && ( this.getHspeed() > 0 ) ) { 
            this.runDecel(delta, input);
        }

        this.increaseX(getHspeed() * delta);
    }

    public void runAccel(float delta, Boolean[] input) {
        if(this.getRunFrames() > 35) { 
            if(input[0]) this.decreaseHspeed(500 * delta);
            if(input[1]) this.increaseHspeed(500 * delta);
        }
        else { 
            if(input[0]) this.decreaseHspeed(700 * delta);
            if(input[1]) this.increaseHspeed(700 * delta);
        }
    }

    public void runDecel(float delta, Boolean[] input) {
        float decelFormula = 1500 * delta;
        float nextSpeedL = this.getHspeed() - decelFormula;
        float nextSpeedR = this.getHspeed() + decelFormula;

        if(!input[0] && (this.getHspeed() < 0)) {
            if(nextSpeedR < 0) {
                this.increaseHspeed(decelFormula); 
            }
            else {
                this.setHspeed(0);
            } 
        }

        if(!input[1] && (this.getHspeed() > 0)) {
            if(nextSpeedL > 0) {
                this.decreaseHspeed(decelFormula); 
            }
            else {
                this.setHspeed(0);
            }
        }
    }

    public static Player getInstance() {
        return PlayerHolder.INSTANCE;
    }

    private static class PlayerHolder {
        private static final Player INSTANCE = new Player();
    }

    public void setRunFrames(float runFrames) {
        this.runFrames = runFrames;
    }

    public float getRunFrames() {
        return runFrames;
    }

}


