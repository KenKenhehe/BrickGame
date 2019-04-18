package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    Texture background;
    Label pauseText;
    Stage stage;
    Skin skin;
    Sound hitPaddleSound;
    public GameScreen(MyGdxGame game){
        this.game = game;
    }



    public void init(){
        hitPaddleSound = Gdx.audio.newSound(Gdx.files.internal("FS_LITTLE_TAP_01.wav"));
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage();
        pauseText = new Label("Paused\nPress ESCAPE to unpause", skin);
        pauseText.setPosition(Gdx.graphics.getWidth() / 2 - pauseText.getWidth() / 2, Gdx.graphics.getHeight()/2);
        pauseText.setWidth(200);
        pauseText.setHeight(32);
        stage.addActor(pauseText);
        background = new Texture("BackgroundGame.png");
        bricks = new DelayedRemovalArray<Brick>();
        brickExplodeFXes = new DelayedRemovalArray<BrickExplodeFX>();
        paddle = new Paddle(new Vector2(0, 0));
        paddle.position = new Vector2(screenWidth / 2 - paddle.getTexture().getWidth() /2,32);

        ball = new Ball(new Vector2(0, 0), paddle);
        ball.position = new Vector2(
                paddle.position.x + paddle.getTexture().getWidth() / 2 - ball.getBallTexture().getWidth() / 2,
                paddle.position.y + paddle.getTexture().getHeight() + 16);

        batch = new SpriteBatch();

        for(int i = 0; i < 20; i++){

            bricks.add(new Brick(new Vector2(0, 0)));
            bricks.get(i).position = new Vector2(
                    (bricks.get(i).sprite.getWidth()) * i,
                    MyGdxGame.screenHeight - 32
            );
            bricks.get(i).collider.setPosition(bricks.get(i).position);

        }

        for(int i = 0; i < 20; i++){

            bricks.add(new Brick(new Vector2(0, 0)));
            bricks.get(i + 20).position = new Vector2(
                    (bricks.get(i + 5).sprite.getWidth()) * i,
                    MyGdxGame.screenHeight - 48
            );
            bricks.get(i + 20).collider.setPosition(bricks.get(i + 20).position);

        }

        for(int i = 0; i < 20; i++){

            bricks.add(new Brick(new Vector2(0, 0)));
            bricks.get(i + 40).position = new Vector2(
                    (bricks.get(i + 40).sprite.getWidth()) * i,
                    MyGdxGame.screenHeight - 64
            );
            bricks.get(i + 40).collider.setPosition(bricks.get(i + 40).position);

        }

        for(int i = 0; i < 20; i++){

            bricks.add(new Brick(new Vector2(0, 0)));
            bricks.get(i + 60).position = new Vector2(
                    (bricks.get(i + 60).sprite.getWidth()) * i,
                    MyGdxGame.screenHeight - 80
            );
            bricks.get(i + 60).collider.setPosition(bricks.get(i + 60).position);

        }
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
        batch.draw(background, 0, 0);
        paddle.render(batch);
        ball.render(batch);


        if(isPause == false) {
            update(Gdx.graphics.getDeltaTime());
        }
        else{
            pauseText.draw(batch, 1);
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
