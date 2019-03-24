package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Administrator on 3/15/2019.
 */

public class Paddle {
    Vector2 position;
    Texture paddleTexture;
    Rectangle collider;

    public Paddle(Vector2 position){
        this.position = position;
        paddleTexture = new Texture("Paddle.png");
        collider = new Rectangle(position.x, position.y, paddleTexture.getWidth(), paddleTexture.getHeight());
    }

    public void update(float deltaTime){
        //collider's position correspond to paddle's position
        collider.setPosition(position.x, position.y);

        if(Gdx.input.isTouched()){
            position.x = Gdx.input.getX();
        }
        //does not allow paddle to go out of bound
        if(position.x > MyGdxGame.screenWidth - paddleTexture.getWidth()){
            position.x = MyGdxGame.screenWidth - paddleTexture.getWidth();
        }
        else if(position.x < 0 ){
            position.x = 0;
        }
    }

    public void render(SpriteBatch batch){
        if(paddleTexture != null){
            batch.draw(paddleTexture, position.x, position.y);
        }
    }

    public void dispose(){
        paddleTexture.dispose();
    }

    public Texture getTexture(){
        return paddleTexture;
    }
}
