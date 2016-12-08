package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen4 implements Screen{
	
	 final Box game;
	 long startTime = 0;
	 int HP = 1;
	 private int point =60;
	 private BitmapFont font;
	 public SpriteBatch batch;
	 
	public GameScreen4(final Box gam) {
		 	startTime = TimeUtils.nanoTime();
	        this.game = gam;
	        font = new BitmapFont();
	        font.setColor(Color.WHITE);
	        batch = new SpriteBatch();
	        
	 
	 }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		 Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	     if (TimeUtils.timeSinceNanos(startTime) > 1000000000) { 
	    	 point  -= 1;  	     	 
	    	 MainMenuScreen.UT += 1;
	    	 startTime = TimeUtils.nanoTime();
	     }
	     batch.begin();
    	 font.draw(batch, "TIME-LIMIT : "+point, 550, 700);
	     batch.end();
	     
	     if(point <= 0) {
	    	 game.setScreen(new Lost(game));
	     } else {
	    	 MainMenuScreen.Point[3] = false;
	     } 
		
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
