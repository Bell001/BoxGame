package com.mygdx.game; //เกมตอบให้ถูก ขึ้นภาพทายปริศนา 2 วิแล้ว ใส่เลขตามสวิสต์

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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

import practicum.TestPeri;

public class GameScreen2 implements Screen{
	
	 final Box game;
	 private Stage stage;
	 private Skin skin;
	 long startTime = 0;
	 int GMath =0;
	 int HP = 1;
	 int i=0;
	 int Answers =-1;
	 boolean Checkclick = false;
	 public static int[] numbomb = {0,0,0,0,0};
	 public static boolean[] clearbomb = {false,false,false,false,false};
	 private int point =60;
	 private BitmapFont font;
	 public SpriteBatch batch;
	 private TestSwitch Test;
	 Texture back;
	 
	 public GameScreen2(final Box gam) {
		    Test = new TestSwitch();
		    Thread thr = new Thread(Test);
	    	thr.setDaemon(true);
	    	thr.start();
		 
		    startTime = TimeUtils.nanoTime();
		    for(int i=0;i<5;i++) {
      		     GMath = MathUtils.random(4);
      		     numbomb[i] = GMath;
		    }
	        this.game = gam;
	        font = new BitmapFont();
	        font.setColor(Color.WHITE);
	        batch = new SpriteBatch();
//	        back = new Texture(Gdx.files.internal("Background2.png"));
	        
	    
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
//	     batch.draw(back,0,0);
    	 font.draw(batch, "TIME-LIMIT : "+point, 550, 700);
    	 font.draw(batch, "Clear    "+i, 1000, 700);
	     batch.end();
//	     System.out.println(Test.getValue2());
	    
	     if(point <= 0) {
	    	 game.setScreen(new Lost(game));
	     } else if(point >= 58) {
	    	 batch.begin();
	    	 font.draw(batch, "LOOK FOR TIPS!!! ", 550, 400);
	    	 font.draw(batch, "Tips: "+(numbomb[0]+1)+(numbomb[1]+1)+(1+numbomb[2])+(numbomb[3]+1)+(numbomb[4]+1), 550, 100);
		     batch.end();
	     } else {
	    	 MainMenuScreen.Point[1] = false;
	     } 
	     

	     if(checksw()){
	    	 check();
	     }
	 
	     Answers =-1;
		
	}
	
	public boolean checksw() {
		if(Test.getsw1()) {
	    	Answers = 0;
	    }
	    if(Test.getsw2()) {
	    	Answers = 1;
	    }
	    if(Test.getsw3()) {
	    	Answers = 2;
	    }
	    if(Test.getsw4()) {
	    	Answers = 3;
	    }
	    if(Test.getsw5()) {
	    	Answers = 4;
	    }
	    if(Answers == -1) {
	    	return false;
	    }
	    return true;
	}
	
	public void check() {
        if(Answers != -1 && Answers == numbomb[i]) {
      			  clearbomb[i] = true;
      			  i += 1;
      			 
        }	
//      	} else if(Answers != numbomb[i] && Answers != -1) {
//      			  HP -= 1;
//      			  resetclearbomb();
//      	} 
      	if(i >= 5) {
      		MainMenuScreen.Point[1] = false;
      		MainMenuScreen.Status[1] = "OFF";
		 	game.setScreen(new MainMenuScreen(game));	  	
      	}		 
		
		
//		if(HP < 0){
//			MainMenuScreen.Status[1] = "OFF";
//		 	game.setScreen(new Lost(game));
//		}
	}
	
	
	public void resetclearbomb() {
		for(int i=0;i<5;i++) {
			clearbomb[i] = false;
		}
		i =0;
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
