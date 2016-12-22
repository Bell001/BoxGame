package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
	 int num =0;
	 private int point =60;
	 private BitmapFont font;
	 public SpriteBatch batch;
	 Texture[] led = new Texture[3];
	 Texture red_on;
	 Texture green_on;
	 Texture red_off;
	 Texture green_off;
	 public Boolean[] ansq = {false,false,false,false,false,false};
	 public Boolean[][] quiz = {{true,false,true},{false,true,false},{true,false,false},{false,false,true},{false,false,false},{true,true,false}}; //ให้ปิดไฟตามนี้แล้วเป็นรูปด้วย เล่นรูป 3 ก่อน
	 public Boolean[][] status = {{true,true,true},{true,true,true},{true,true,true},{true,true,true},{true,true,true},{true,true,true}}; //เช็คว่าถูกช้อยส์ไหม
	 private TestSwitch Test;
	 
	public GameScreen4(final Box gam) {
			Test = new TestSwitch();
			Thread thr = new Thread(Test);
			thr.setDaemon(true);
			thr.start();
		    led[0] = new Texture("redoff.png");
		    led[1] = new Texture("greenoff.png");
		    led[2] = new Texture("redoff.png");
		 	startTime = TimeUtils.nanoTime();
	        this.game = gam;
	        font = new BitmapFont();
	        font.setColor(Color.WHITE);
	        batch = new SpriteBatch();
	        red_on = new Texture("redon.png");
	        green_on = new Texture("greenon.png");
	        red_off = new Texture("redoff.png");
	        green_off = new Texture("greenoff.png");	        	 
	 }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		 checkquiz();
		 setledquiz();
		 Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	     if (TimeUtils.timeSinceNanos(startTime) > 1000000000) { 
	    	 point  -= 1;  	     	 
	    	 MainMenuScreen.UT += 1;
	    	 startTime = TimeUtils.nanoTime();
	     }
	     batch.begin();
    	 font.draw(batch, "TIME-LIMIT : "+point, 550, 700);	 	
    	 batch.draw(led[0],300,300);
    	 batch.draw(led[1],600,300);
    	 batch.draw(led[2],900,300);
	     batch.end();
	     checkans();
	     
	     if(point <= 0) {
	    	 game.setScreen(new Lost(game));
	     } else {
	    	 MainMenuScreen.Point[3] = false;
	     } 
		
	}
	
	public void checkans() {
		int Check =0;
		status[num][0] = Test.getred1();
		status[num][1] = Test.getgreen();
		status[num][2] = Test.getred2();
		for(int i=0;i<3 ;i++) {
	       if(status[num][i] == quiz[num][i]) {
	    	   Check++;
	       }
		}
		if(Check == 3) {
			ansq[num] = true;
			num++;
		}
	}
	
	public void checkquiz() {
		int count =0;
		for(int i=0;i<6;i++) {
			if(ansq[i] == true) {
				count++;
			} else {
				num =i;
			}
		}
		if(count == 6) {
			MainMenuScreen.Point[3] = false;
			game.setScreen(new MainMenuScreen(game));
		}
	}
	
	public void setledquiz() {
		for(int i=0;i<3;i++) {
			if(quiz[num][i] == true && i != 1) {
			  led[i] = red_on;
			} else if(i != 1){
			   led[i] = red_off;
			}
			
			if(quiz[num][i] == true && i == 1) {
				led[i] = green_on;
			} else if(i == 1){
				led[i] = green_off;
			}
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
