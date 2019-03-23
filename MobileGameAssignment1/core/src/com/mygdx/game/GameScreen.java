package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;

/**
 * Created by Administrator on 3/24/2019.
 */

public class GameScreen implements Screen{
    MyGdxGame game;
    SpriteBatch batch;
    public boolean gameOver = false;
    public static final int screenWidth = 480;
    public static final int screenHeight = 640;
    Paddle paddle;
    Ball ball;
    DelayedRemovalArray<Brick> bricks;


    int brickAmount = 20;

    public GameScreen(MyGdxGame game){
        this.game = game;
    }

    public void init(){
        bricks = new DelayedRemovalArray<Brick>();
        //paddle = new Paddle(new Vector2(screenWidth / 2 - 16, screenHeight));
        paddle = new Paddle(new Vector2(0, 0));

        paddle.position = new Vector2(screenWidth / 2 - paddle.getTexture().getWidth() /2,32);

        ball = new Ball(new Vector2(0, 0), paddle);
        ball.position = new Vector2(
                paddle.position.x + paddle.getTexture().getWidth() / 2 - ball.getBallTexture().getWidth() / 2,
                paddle.position.y + paddle.getTexture().getHeight() + 16);

        batch = new SpriteBatch();
        System.out.print(paddle.position);
        for(int i = 0; i < brickAmount; i++){
            bricks.add(new Brick(new Vector2(0, 0)));
            bricks.get(i).position = new Vector2(
                    (bricks.get(i).brickTexture.getWidth()/2 + 10) * i,
                    screenHeight - 32
            );
            bricks.get(i).collider.setPosition(bricks.get(i).position);
        }

    }


    public void update(float deltaTime){
        paddle.update(deltaTime);
        ball.update(deltaTime);
        if(ball.position.y < -10){
            game.setScreen(MyGdxGame.menuScreen);
        }
    }

    @Override
    public void show() {
        init();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        paddle.render(batch);
        ball.render(batch);

        update(Gdx.graphics.getDeltaTime());

        for (Brick brick: bricks) {
            brick.render(batch);
            if(ball.collider.overlaps(brick.collider)) {
                bricks.removeValue(brick, false);
                System.out.print("Collide!!!");
            }
        }


        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose () {
        batch.dispose();
        paddle.dispose();
    }
}
