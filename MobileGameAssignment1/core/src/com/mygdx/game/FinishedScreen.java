package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Administrator on 3/24/2019.
 */

public class FinishedScreen implements Screen {
    SpriteBatch batch;
    MyGdxGame game;
    Button restartButton;
    Stage stage;
    Skin skin;

    public boolean isWin = false;
    String stateText;
    public FinishedScreen(MyGdxGame game, boolean isWin){
        this.isWin = isWin;
        this.game = game;
        batch = new SpriteBatch();
        if(isWin == false){
            stateText = "GAME OVER!";
        }
        else{
            stateText = "YOU WIN!";
        }
        System.out.print(stateText);
    }
    @Override
    public void show() {
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        restartButton = new TextButton("Restart", skin, "default");
        restartButton.setHeight(64);
        restartButton.setWidth(256);
        restartButton.setPosition(MyGdxGame.screenWidth / 2 - restartButton.getWidth() / 2, MyGdxGame.screenHeight / 2);
        stage.addActor(restartButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        if(restartButton.isPressed()){
            game.setScreen(MyGdxGame.menuScreen);
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
    public void dispose() {
        batch.dispose();
    }
}
