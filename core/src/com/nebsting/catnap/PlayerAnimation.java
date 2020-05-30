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

    float animationTimer;

    public PlayerAnimation() {
        standSheet = new Texture(Gdx.files.internal("obj/cfang/standsheet.png"));
        standAnimation = initStandAnimation();

        walkSheet = new Texture(Gdx.files.internal("obj/cfang/walksheet.png"));
        walkAnimation = initWalkAnimation();
    }

    public void setCurrentSprite(float timer) {
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

}

