package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Administrator on 3/24/2019.
 */

public class FinishedScreen implements Screen {
    SpriteBatch batch;
    MyGdxGame game;
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

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(Gdx.input.justTouched()){
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
