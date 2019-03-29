package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;

/**
 * Created by Administrator on 3/29/2019.
 */

public class BallHitFX {
    Sprite sprite;
    SpriteBatch batch;
    Texture BallHit;
    TextureRegion ballHitAniRegion;
    BrickAnimationFX ballHitAni;
    Vector2 position;

    public BallHitFX(Vector2 position){
        this.position = position;
        BallHit = new Texture("BallHit.png");
        ballHitAniRegion = new TextureRegion(BallHit);
        ballHitAni = new BrickAnimationFX(ballHitAniRegion, 6, .4f);
        sprite = new Sprite(ballHitAni.getFrame());
        sprite.setPosition(position.x - 32, position.y - 16);
    }

    public void render(SpriteBatch batch){
        sprite.setScale(1.4f);
        //sprite.setPosition(position.x - 32, position.y - 10);
        sprite.draw(batch);
    }

    public void update(float deltaTime){
        ballHitAni.update(deltaTime);
        sprite.setRegion(ballHitAni.getFrame());
    }
}
