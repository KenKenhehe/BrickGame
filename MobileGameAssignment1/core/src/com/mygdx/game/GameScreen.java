package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    DelayedRemovalArray<BrickExplodeFX> brickExplodeFXes;
    boolean canLaunch = false;
    boolean isPause = false;
    int brickAmount = 5;
    int brickColumn = 5;
    int brickRow = 4;
    Texture brickExplode;
    TextureRegion brickExplodeAni;

    public GameScreen(MyGdxGame game){
        this.game = game;
    }



    public void init(){
        bricks = new DelayedRemovalArray<Brick>();
        brickExplodeFXes = new DelayedRemovalArray<BrickExplodeFX>();
        paddle = new Paddle(new Vector2(0, 0));
        paddle.position = new Vector2(screenWidth / 2 - paddle.getTexture().getWidth() /2,32);

        ball = new Ball(new Vector2(0, 0), paddle);
        ball.position = new Vector2(
                paddle.position.x + paddle.getTexture().getWidth() / 2 - ball.getBallTexture().getWidth() / 2,
                paddle.position.y + paddle.getTexture().getHeight() + 16);

        batch = new SpriteBatch();

        for(int i = 0; i < 5; i++){

            bricks.add(new Brick(new Vector2(0, 0)));
            bricks.get(i).position = new Vector2(
                    (bricks.get(i).sprite.getWidth()) * i,
                    MyGdxGame.screenHeight - 32
            );
            bricks.get(i).collider.setPosition(bricks.get(i).position);

        }

        for(int i = 0; i < 5; i++){

            bricks.add(new Brick(new Vector2(0, 0)));
            bricks.get(i + 5).position = new Vector2(
                    (bricks.get(i + 5).sprite.getWidth()) * i,
                    MyGdxGame.screenHeight - 48
            );
            bricks.get(i + 5).collider.setPosition(bricks.get(i + 5).position);

        }

        for(int i = 0; i < 5; i++){

            bricks.add(new Brick(new Vector2(0, 0)));
            bricks.get(i + 10).position = new Vector2(
                    (bricks.get(i + 10).sprite.getWidth()) * i,
                    MyGdxGame.screenHeight - 64
            );
            bricks.get(i + 10).collider.setPosition(bricks.get(i + 10).position);

        }

        for(int i = 0; i < 5; i++){

            bricks.add(new Brick(new Vector2(0, 0)));
            bricks.get(i + 15).position = new Vector2(
                    (bricks.get(i + 15).sprite.getWidth()) * i,
                    MyGdxGame.screenHeight - 80
            );
            bricks.get(i + 15).collider.setPosition(bricks.get(i + 15).position);

        }
        /*for (Brick brick:
             bricks) {
            for(int i = 0; i < brickColumn; i++){
                System.out.println(brick.position);
                for (int j = 0; j <brickRow; j++ ){
                    brick.position = new Vector2(brick.sprite.getWidth() * i, MyGdxGame.screenHeight - brick.sprite.getHeight() * j);
                }
            }
            brick.collider.setPosition(brick.position);
        }*/
    }


    public void update(float deltaTime){
        paddle.update(deltaTime);
        ball.update(deltaTime);
        if(ball.position.y < -10){
            MyGdxGame.finishedScreen = new FinishedScreen(game, false);
            game.setScreen(MyGdxGame.finishedScreen);
        }
        else if(bricks.size <= 0){
            MyGdxGame.finishedScreen = new FinishedScreen(game, true);
            game.setScreen(MyGdxGame.finishedScreen);
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


        if(isPause == false) {
            update(Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            isPause = !(isPause);
        }
        for (Brick brick: bricks) {
            brick.render(batch);
            //if collide, destroy the corresponding brick
            if(ball.collider.overlaps(brick.collider)) {
                brickExplodeFXes.add(new BrickExplodeFX(brick.position));
                bricks.removeValue(brick, true);
                ball.ySpeed *= -1;
                ball.hasCollide = true;
            }
        }

        for (BrickExplodeFX fx : brickExplodeFXes){
            fx.render(batch);
            fx.update(delta);
            if(fx.brickAni.getFrameNum() == 2){
                brickExplodeFXes.removeValue(fx, true);
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
        ball.dispose();
        batch.dispose();
        paddle.dispose();
        for(Brick brick : bricks){
            brick.dispose();
        }
    }
}
