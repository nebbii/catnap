package com.nebsting.game;

public class Player extends Unit {

    float runFrames;

    Controller controller;

    public Player() {
        super();

        this.controller = new Controller(this);
    }

    public void jump(float delta, boolean justPressed) {
        // Start jump timer if just pressed
        if(justPressed && this.onFloor()) {
            this.jumpFrames += 40;
            if(y==0) this.increaseY(1);
        }

        if(!this.onFloor()) {
            if(this.getJumpFrames() > 30) {
                this.decreaseVspeed(100 * delta);
            } 
            else if(jumpFrames > 0) {
                this.decreaseVspeed(50 * delta);
            }
        }
    }

    public void run(float delta, boolean justPressed) {
        if(justPressed /* && this.onFloor() */) {
            this.runFrames += 40;
        }

    }

    public static Player getInstance() {
        return PlayerHolder.INSTANCE;
    }

    private static class PlayerHolder {
        private static final Player INSTANCE = new Player();
    }

}

