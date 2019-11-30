package com.nebsting.game;

public class Player extends Unit {

    float runFrames;

    public Player() {
        super();
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

    }

    public static Player getInstance() {
        return PlayerHolder.INSTANCE;
    }

    private static class PlayerHolder {
        private static final Player INSTANCE = new Player();
    }

}

