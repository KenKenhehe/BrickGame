package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.*;

/**
 * Created by Administrator on 3/16/2019.
 */

public class Brick {

    Vector2 position;
    Texture[] brickTexturesToChoose = new Texture[4];
    Texture brickTexture;
    int score;
    Rectangle collider;

    public Brick(Vector2 position){
        for (int i  = 0; i < brickTexturesToChoose.length; i++){
            brickTexturesToChoose[i] = new Texture("Brick" + (i + 1) + ".png");
        }
        this.position = position;
        brickTexture = brickTexturesToChoose[MathUtils.random(3)];
        collider = new Rectangle(position.x, position.y, brickTexture.getWidth(), brickTexture.getHeight());
    }

    public void render(SpriteBatch batch){
        batch.draw(brickTexture, position.x, position.y);
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
