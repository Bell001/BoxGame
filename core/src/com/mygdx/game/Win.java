package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Win implements Screen {
	
	static Sound BombSound;
	Sprite back;
    Texture Background;
    private BitmapFont font;
    public SpriteBatch batch;
	
	public Win(final Box gam) {
		BombSound = Gdx.audio.newSound(Gdx.files.internal("bomb.mp3"));
		font = new BitmapFont();
	    font.setColor(Color.RED);
	    batch = new SpriteBatch();
        Background = new Texture("Background.png");
        back = new Sprite(Background);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		 Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 batch.begin();
	     back.draw(batch);
	     font.draw(batch, "You Win", 550, 700);
	     batch.end();	
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
