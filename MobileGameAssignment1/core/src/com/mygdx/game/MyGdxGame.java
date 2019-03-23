package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public class MyGdxGame extends Game implements ApplicationListener {
	SpriteBatch batch;
	public boolean gameOver = false;
	public static final int screenWidth = 480;
	public static final int screenHeight = 640;
	Paddle paddle;
	Ball ball;
	DelayedRemovalArray<Brick> bricks;
	public static MenuScreen menuScreen;
	public static GameScreen gameScreen;

	int brickAmount = 20;
	@Override
	public void create () {
		//init();
		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		setScreen(menuScreen);
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

	@Override
	public void render () {
		/*Gdx.gl.glClearColor(.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		paddle.render(batch);
		ball.render(batch);

		update(Gdx.graphics.getDeltaTime());

		for (Brick brick: bricks) {
			brick.render(batch);
			if(ball.collider.overlaps(brick.collider)){
				bricks.removeValue(brick, false);
				System.out.print("Collide!!!");
			}
		}


		batch.end();*/

		super.render();
	}


	/*public void update(float deltaTime){
		paddle.update(deltaTime);
		ball.update(deltaTime);

	}*/

	@Override
	public void dispose () {
		batch.dispose();
		paddle.dispose();
	}
}
