package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.*;

/**
 * Created by Administrator on 3/16/2019.
 */

public class Brick {

    Sprite sprite;
    Vector2 position;
    Texture[] brickTexturesToChoose = new Texture[4];
    Texture brickTexture;
    int score;
    Rectangle collider;

    public Brick(Vector2 position){

        for (int i  = 0; i < brickTexturesToChoose.length; i++){
            brickTexturesToChoose[i] = new Texture("Brick" + (i + 1) + ".png");
        }
        brickTexture = brickTexturesToChoose[MathUtils.random(3)];
        sprite = new Sprite(brickTexture);
        sprite.setSize(32, 16);
        this.position = position;
        //choose the brick randomly

        collider = new Rectangle(position.x, position.y, sprite.getWidth(), sprite.getHeight());

    }

    public void render(SpriteBatch batch){
        //batch.draw(brickTexture, position.x, position.y);
        sprite.draw(batch);
        sprite.setPosition(position.x, position.y);
    }

    public void update(float deltaTime){

    }
    public boolean CollideWith(Rectangle other){
        return other.overlaps(collider);
    }

    public void dispose(){
        brickTexture.dispose();
    }
}
