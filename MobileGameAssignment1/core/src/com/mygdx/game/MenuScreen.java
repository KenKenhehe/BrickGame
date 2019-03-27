package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class MenuScreen implements Screen {
    MyGdxGame game;

    SpriteBatch batch;
    Skin skin;
    Stage stage;
    Button startButton;
    Button quitButton;

    public MenuScreen(MyGdxGame game){
        this.game = game;

    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        startButton = new TextButton("Start!", skin, "default");
        quitButton = new TextButton("Quit", skin, "default");
        startButton.setHeight(64);
        startButton.setWidth(256);
        quitButton.setHeight(64);
        quitButton.setWidth(256);
        startButton.setPosition(MyGdxGame.screenWidth /2 - startButton.getWidth() / 2 ,
                MyGdxGame.screenHeight/2  );

        quitButton.setPosition(startButton.getX(), startButton.getY() - 128);
        stage.addActor(startButton);
        stage.addActor(quitButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.4f, 0.4f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        if(startButton.isPressed()){
            game.setScreen(MyGdxGame.gameScreen);
        }
        else if(quitButton.isPressed()){
            Gdx.app.exit();
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
        stage.dispose();
        skin.dispose();
    }
}
