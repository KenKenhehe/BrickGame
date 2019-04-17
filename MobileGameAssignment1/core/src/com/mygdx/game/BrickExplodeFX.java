package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Administrator on 3/29/2019.
 */

public class BrickExplodeFX {
    Sprite sprite;
    SpriteBatch batch;
    Texture brickExplode;
    TextureRegion brickExplodeAni;

    BrickAnimationFX brickAni;
    Vector2 position;

    public BrickExplodeFX(Vector2 position){
        this.position = position;
        brickExplode = new Texture("BrickExplode.png");
        brickExplodeAni = new TextureRegion(brickExplode);
        brickAni = new BrickAnimationFX(brickExplodeAni, 3, .3f);
        sprite = new Sprite(brickAni.getFrame());
    }

    public void render(SpriteBatch batch){
        sprite.setScale(.5f);
        sprite.setPosition(position.x - 20, position.y - sprite.getTexture().getHeight() / 2);
        sprite.draw(batch);
    }

    public void update(float deltaTime){
        brickAni.update(deltaTime);
        sprite = new Sprite(brickAni.getFrame());
    }
}
