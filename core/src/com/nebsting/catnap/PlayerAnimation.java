package com.nebsting.catnap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimation {

    Texture standSheet;
    Animation<TextureRegion> standAnimation;

    Texture walkSheet;
    Animation<TextureRegion> walkAnimation;

    float timer;

    Player player;

    public PlayerAnimation(Player player) {
        standSheet = new Texture(Gdx.files.internal("obj/toriyasu/stand192.png"));
        standAnimation = initStandAnimation();

        walkSheet = new Texture(Gdx.files.internal("obj/cfang/walk192px.png"));
        walkAnimation = initWalkAnimation();

        timer = 0f;

        this.player = player;
    }

    public TextureRegion setCurrentSprite() {
        TextureRegion frame = new TextureRegion();

        // Idle
        if(player.getVx() == 0) {
            frame = standAnimation.getKeyFrame(timer, true);

            if( (player.lastDirection != 'l') && frame.isFlipX() ) frame.flip(true,false);
            if( (player.lastDirection != 'r') && !frame.isFlipX() ) frame.flip(true,false);

            player.spriteOffset = 4;
        }

        // Walk left
        if(player.getVx() < 0) {
            frame = walkAnimation.getKeyFrame(timer, true);

            // Flip logic
            if(!frame.isFlipX()) frame.flip(true,false);
            if(player.lastDirection != 'l') player.lastDirection = 'l';

            player.spriteOffset = 32;
        }

        // Walk right
        if(player.getVx() > 0) {
            frame = walkAnimation.getKeyFrame(timer, true);
            
            // Flip logic
            if(frame.isFlipX()) frame.flip(true,false);
            if(player.lastDirection != 'r') player.lastDirection = 'r';

            player.spriteOffset = 32;
        }

        return frame;
    }

    public Animation<TextureRegion> initStandAnimation() {
        TextureRegion[][] tmp = TextureRegion.split(standSheet, 
                this.standSheet.getWidth() / 2, this.standSheet.getHeight() / 2);

        // Do frames
        TextureRegion[] standFrames = new TextureRegion[] {
            tmp[0][0], tmp[0][1], tmp[1][0], tmp[1][1], tmp[1][0], tmp[0][1] 
        };

        return new Animation<TextureRegion>(0.150f,standFrames);
    }

    public Animation<TextureRegion> initWalkAnimation() {
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, 
                this.walkSheet.getWidth() / 4, this.walkSheet.getHeight() / 2);

        // Do frames
        TextureRegion[] frames = new TextureRegion[8];
        int index = 0;
        for(int i=0;i<2;i++) {
            for(int j=0;j<4;j++) {
                frames[index++] = tmp[i][j];
            }
        }
        return new Animation<TextureRegion>(0.075f,frames);
    }

}

