package com.mygdx.game; //เกมตอบให้ถูก ขึ้นภาพทายปริศนา 2 วิแล้ว ใส่เลขตามสวิสต์

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	        
	        stage = new Stage(new StretchViewport(800, 480));
	        Gdx.input.setInputProcessor(stage);

	        skin = new Skin(Gdx.files.internal("uiskin.json"));

	        TextButton buttonpuzzle0 = new TextButton("0", skin);
	        buttonpuzzle0.setWidth(200);
	        buttonpuzzle0.setHeight(50);
	        buttonpuzzle0.setPosition(300 , 350);

	        stage.addActor(buttonpuzzle0);

	        buttonpuzzle0.addListener(new ClickListener() {
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                super.clicked(event, x, y);
	                Checkclick = true;
	                Answers =0;
	            }
	        });

	        TextButton buttonpuzzle1 = new TextButton("1", skin);
	        buttonpuzzle1.setWidth(200);
	        buttonpuzzle1.setHeight(50);
	        buttonpuzzle1.setPosition(200 , 250);

	        stage.addActor(buttonpuzzle1);

	        buttonpuzzle1.addListener(new ClickListener() {
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                super.clicked(event, x, y);
	                Checkclick = true;
	                Answers =1;
	            }
	        });
	        
	        TextButton buttonpuzzle2 = new TextButton("2", skin);
	        buttonpuzzle2.setWidth(200);
	        buttonpuzzle2.setHeight(50);
	        buttonpuzzle2.setPosition(400 , 250);

	        stage.addActor(buttonpuzzle2);

	        buttonpuzzle2.addListener(new ClickListener() {
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                super.clicked(event, x, y);
	                Checkclick = true;
	                Answers =2;
	            }
	        });
	        
	        TextButton buttonpuzzle3 = new TextButton("3", skin);
	        buttonpuzzle3.setWidth(200);
	        buttonpuzzle3.setHeight(50);
	        buttonpuzzle3.setPosition(200 , 150);

	        stage.addActor(buttonpuzzle3);

	        buttonpuzzle3.addListener(new ClickListener() {
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                super.clicked(event, x, y);
	                Checkclick = true;
	                Answers =3;
	            }
	        });
	        
	        TextButton buttonpuzzle4 = new TextButton("4", skin);
	        buttonpuzzle4.setWidth(200);
	        buttonpuzzle4.setHeight(50);
	        buttonpuzzle4.setPosition(400, 150);

	        stage.addActor(buttonpuzzle4);

	        buttonpuzzle4.addListener(new ClickListener() {
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                super.clicked(event, x, y);
	                Checkclick = true;
	                Answers =4;
	            }
	        });
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
	     System.out.println(Test.getValue2());
	    
	     if(point <= 0) {
	    	 game.setScreen(new Lost(game));
	     } else if(point >= 58) {
	    	 batch.begin();
	    	 font.draw(batch, "Tips: "+numbomb[0]+numbomb[1]+numbomb[2]+numbomb[3]+numbomb[4], 550, 100);
		     batch.end();
	     } else {
	    	 MainMenuScreen.Point[1] = false;
	     } 
	     
	     stage.act(Gdx.graphics.getDeltaTime());
	     if(Checkclick == true) {
    	      check();
	     }
	     stage.draw();
	     
	     Answers =-1;
		
	}
	
	public void check() {
		
        if(Answers != -1 && Answers == numbomb[i]) {
      			  clearbomb[i] = true;
      			  Checkclick  = false;
      			  i += 1;
      			 
      			
      	} else if(Answers != numbomb[i] && Answers != -1) {
      			  HP -= 1;
      			  resetclearbomb();
      	} 
      	if(i >= 5) {
      		MainMenuScreen.Point[1] = false;
      		MainMenuScreen.Status[1] = "OFF";
		 	game.setScreen(new MainMenuScreen(game));	  	
      	}		 
		Checkclick  = false;
		
		if(HP < 0){
			MainMenuScreen.Status[1] = "OFF";
		 	game.setScreen(new Lost(game));
		}
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
