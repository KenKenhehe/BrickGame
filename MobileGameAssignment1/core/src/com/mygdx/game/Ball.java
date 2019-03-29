package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import java.util.Random;
/**
 * Created by Administrator on 3/15/2019.
 */

public class Ball {
    Vector2 position;
    Texture ballTexture;
    public boolean isLaunched;
    public boolean canLaunch = false;
    Random r1;
    Paddle paddleToAttach;
    float ranNum;
    Rectangle collider;
    DelayedRemovalArray<BallHitFX> ballHitFXes;
    float xSpeed ;
    float ySpeed;
    boolean hasCollide;

    public Ball(Vector2 position, Paddle paddleToAttach){
        ballHitFXes = new DelayedRemovalArray<BallHitFX>();
        this.position = position;
        ballTexture = new Texture("Ball.png");

        r1 = new Random();
        ranNum = MathUtils.random(-1f, 1f);
        this.paddleToAttach = paddleToAttach;
        //initialize the collider's position to be at the center of the ball position
        collider = new Rectangle(position.x, position.y, ballTexture.getWidth(), ballTexture.getHeight());
        xSpeed = ranNum * Constants.BALL_X_SPEED;
        ySpeed = Constants.BALL_Y_SPEED;
    }

    public void render(SpriteBatch batch){
        if(ballTexture != null){
            batch.draw(ballTexture, position.x, position.y);
        }
        for (BallHitFX fx : ballHitFXes){
            fx.render(batch);
            if(fx.ballHitAni.getFrameNum() == 5){
                ballHitFXes.removeValue(fx, true);
            }
        }
    }

    public void update(float deltaTime){
        collider.setPosition(position);
        //when the user didn't press the launch, ball should attach to the paddle
        if (isLaunched == false){
            position.x =
                    paddleToAttach.position.x +
                            paddleToAttach.getTexture().getWidth() / 2 - getBallTexture().getWidth() / 2;
        }
        if(isLaunched == false && Gdx.input.justTouched()){
            isLaunched = true;
        }
        //when launched, ball no longer attached to the paddle and respond to collision
        else if (isLaunched == true){
            position.y += ySpeed * deltaTime;
            position.x += xSpeed * deltaTime;
            if(position.x >= MyGdxGame.screenWidth - ballTexture.getWidth() ||
                    position.x <= 0 + ballTexture.getWidth()){
                xSpeed *= -1;
                ballHitFXes.add(new BallHitFX(position));
                System.out.print(position);
            }

            //the ball only collide once instead of checking collision every frame
            if((position.y > MyGdxGame.screenHeight ||
                    (position.y <=
                            paddleToAttach.position.y + paddleToAttach.getTexture().getHeight()
                            && collider.overlaps(paddleToAttach.collider))) && hasCollide == false)
            {
                hasCollide = true;
                ySpeed *= -1;
                ballHitFXes.add(new BallHitFX(position));
                System.out.print(position);
            }
            else{
                hasCollide = false;
            }
        }

        for (BallHitFX fx : ballHitFXes){
            fx.update(deltaTime);
        }

    }

    public Texture getBallTexture(){
        return ballTexture;
    }

    public void dispose(){
        ballTexture.dispose();
    }
}
