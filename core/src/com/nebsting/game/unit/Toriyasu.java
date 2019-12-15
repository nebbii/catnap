package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;


public class Toriyasu extends Player {

    Texture standSheet;
    Animation<TextureRegion> standAnimation;
    Texture walkSheet;
    Animation<TextureRegion> walkAnimation;
    float animationTimer;

    public Toriyasu() {
        super();

        // Rect
        x = 800 / 2;
        y = 0;
        width = 100;
        height = 100;

        // Unit
        health = 100;
        weight = 50;

        // Animations
        animationTimer = 0f;

        standSheet = new Texture(Gdx.files.internal("obj/cfang/standsheet.png"));
        standAnimation = initStandAnimation();

        walkSheet = new Texture(Gdx.files.internal("obj/cfang/walksheet.png"));
        walkAnimation = initWalkAnimation();
    }

    public void logic(float delta, Polygon[] map) {
        super.logic(delta, map);

        this.controller.checkPresses(delta);

        animationTimer += delta;
        setCurrentSprite(animationTimer);
    }

    public void setCurrentSprite(float timer) {
        // Idle
        if(this.getHspeed() == 0) {
            TextureRegion frame = standAnimation.getKeyFrame(timer, true);

            if( (this.lastDirection != 'l') && frame.isFlipX() ) frame.flip(true,false);
            if( (this.lastDirection != 'r') && !frame.isFlipX() ) frame.flip(true,false);

            this.sprite = frame; 
        }

        // Walk left
        if(this.getHspeed() < 0) {
            TextureRegion frame = walkAnimation.getKeyFrame(timer, true);

            // Flip logic
            if(!frame.isFlipX()) frame.flip(true,false);
            if(this.lastDirection != 'l') this.lastDirection = 'l';

            this.sprite = frame; 
        }

        // Walk right
        if(this.getHspeed() > 0) {
            TextureRegion frame = walkAnimation.getKeyFrame(timer, true);
            
            // Flip logic
            if(frame.isFlipX()) frame.flip(true,false);
            if(this.lastDirection != 'r') this.lastDirection = 'r';

            this.sprite = walkAnimation.getKeyFrame(timer, true); 
        }
    }

    public Animation<TextureRegion> initStandAnimation() {
        TextureRegion[][] tmp = TextureRegion.split(standSheet, 
                this.standSheet.getWidth() / 5, this.standSheet.getHeight() / 1);

        // Do frames
        TextureRegion[] standFrames = new TextureRegion[] {
            tmp[0][1], tmp[0][1], tmp[0][1], tmp[0][1], tmp[0][1], tmp[0][2], tmp[0][3], tmp[0][4], tmp[0][3], tmp[0][2], tmp[0][1]
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

    public void dispose() {
        walkSheet.dispose();
        standSheet.dispose();
    }
}
