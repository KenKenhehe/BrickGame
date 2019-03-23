package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;
/**
 * Created by Administrator on 3/15/2019.
 */

public class Ball {
    Vector2 position;
    Texture ballTexture;
    public boolean isLaunched;
    Random r1;
    Paddle paddleToAttach;
    float ranNum;
    Rectangle collider;

    float xSpeed ;
    float ySpeed;
    boolean hasCollide;

    public Ball(Vector2 position, Paddle paddleToAttach){
        this.position = position;
        ballTexture = new Texture("Ball.png");
        r1 = new Random();
        ranNum = MathUtils.random(-1f, 1f);
        this.paddleToAttach = paddleToAttach;
        collider = new Rectangle(position.x, position.y, ballTexture.getWidth(), ballTexture.getHeight());
        xSpeed = ranNum * Constants.BALL_X_SPEED;
        ySpeed = Constants.BALL_Y_SPEED;
    }

    public void render(SpriteBatch batch){
        if(ballTexture != null){
            batch.draw(ballTexture, position.x, position.y);
        }
    }

    public void update(float deltaTime){
        collider.setPosition(position);
        if (isLaunched == false){
            position.x =
                    paddleToAttach.position.x +
                            paddleToAttach.getTexture().getWidth() / 2 - getBallTexture().getWidth() / 2;
        }
        if(isLaunched == false && Gdx.input.isKeyPressed(Input.Keys.SPACE)){

            isLaunched = true;
        }
        else if (isLaunched == true){
            position.y += ySpeed * deltaTime;
            position.x += xSpeed * deltaTime;
            if(position.x >= MyGdxGame.screenWidth - ballTexture.getWidth() ||
                    position.x <= 0 + ballTexture.getWidth()){
                xSpeed *= -1;
            }
            if((position.y > MyGdxGame.screenHeight ||
                    (position.y <=
                            paddleToAttach.position.y + paddleToAttach.getTexture().getHeight()
                            && collider.overlaps(paddleToAttach.collider))) && hasCollide == false)
            {
                hasCollide = true;
                ySpeed *= -1;
            }
            else{
                hasCollide = false;
            }
        }


    }

    public Texture getBallTexture(){
        return ballTexture;
    }

    public void dispose(){
        ballTexture.dispose();
    }
}
