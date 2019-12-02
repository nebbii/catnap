package com.nebsting.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Toriyasu extends Player {

    Texture standSheet;
    Animation<TextureRegion> standAnimation;
    float standTimer;

    public Toriyasu() {
        super();

        // Rect
        x = 800 / 2;
        y = 0;
        width = 128;
        height = 128;

        // Unit
        health     = 100;
        weight     = 50;

        // Animations
        standSheet = new Texture(Gdx.files.internal("obj/cfang/standloop.png"));
        standAnimation = initStandAnimation();
        standTimer = 0f;
    }

    public void logic(float delta) {
        super.logic(delta);

        standTimer += delta;
        this.sprite = standAnimation.getKeyFrame(standTimer, true); 
    }

    public Animation<TextureRegion> initStandAnimation() {
        TextureRegion[][] tmp = TextureRegion.split(standSheet, 
                this.standSheet.getWidth() / 5, this.standSheet.getHeight() / 1);

        // Do frames
        TextureRegion[] standFrames = new TextureRegion[] {
            tmp[0][0], tmp[0][0], tmp[0][0], tmp[0][0], tmp[0][1], tmp[0][2], tmp[0][3], tmp[0][4], tmp[0][3], tmp[0][2], tmp[0][1]
        };

        return new Animation<TextureRegion>(0.150f,standFrames);
    }

}
