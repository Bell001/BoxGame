package com.mygdx.game; //จอ led

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

public class GameScreen3 implements Screen{

	final Box game;
	int num =0;
	int HP = 1;
	 private Stage stage;
	 private Skin skin;
	 long startTime = 0;
	 int GMath =0;
	 int i=0;
	 public SpriteBatch batch;
	 private int point =60;
	 int Answers =-1;
	 boolean Checkclick = false;
	 private BitmapFont font;
	 public static boolean[] clearQ = {false,false,false,false,false};
	 int[] ansnum = {0,4,2,3,1};
	 String[] Word = {"A: FISH  B: PICTURE  C: CAT  D: FLYFISH  E: SLEEP  ","A: FISH  B: PICTURE  C: CAT  D: FLYFISH  E: SLEEP  ","A: NUM B: HAHA C: D: E:","A: B: C: D: E:","A: B: C: D: E:"};

	 public GameScreen3(final Box gam) {
	        
	        startTime = TimeUtils.nanoTime();		  
	        this.game = gam;
	        font = new BitmapFont();
	        font.setColor(Color.WHITE);
	        batch = new SpriteBatch();
	        
	        stage = new Stage(new StretchViewport(800, 480));
	        Gdx.input.setInputProcessor(stage);

	        skin = new Skin(Gdx.files.internal("uiskin.json"));
            
	        TextButton buttonpuzzle0 = new TextButton("A", skin);
	        buttonpuzzle0.setWidth(100);
	        buttonpuzzle0.setHeight(50);
	        buttonpuzzle0.setPosition(150 , 150);

	        stage.addActor(buttonpuzzle0);

	        buttonpuzzle0.addListener(new ClickListener() {
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                super.clicked(event, x, y);
	                Checkclick = true;
	                Answers =0;
	            }
	        });

	        TextButton buttonpuzzle1 = new TextButton("B", skin);
	        buttonpuzzle1.setWidth(100);
	        buttonpuzzle1.setHeight(50);
	        buttonpuzzle1.setPosition(250 , 150);

	        stage.addActor(buttonpuzzle1);

	        buttonpuzzle1.addListener(new ClickListener() {
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                super.clicked(event, x, y);
	                Checkclick = true;
	                Answers =1;
	            }
	        });
	        
	        TextButton buttonpuzzle2 = new TextButton("C", skin);
	        buttonpuzzle2.setWidth(100);
	        buttonpuzzle2.setHeight(50);
	        buttonpuzzle2.setPosition(350 , 150);

	        stage.addActor(buttonpuzzle2);

	        buttonpuzzle2.addListener(new ClickListener() {
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                super.clicked(event, x, y);
	                Checkclick = true;
	                Answers =2;
	            }
	        });
	        
	        TextButton buttonpuzzle3 = new TextButton("D", skin);
	        buttonpuzzle3.setWidth(100);
	        buttonpuzzle3.setHeight(50);
	        buttonpuzzle3.setPosition(450 , 150);

	        stage.addActor(buttonpuzzle3);

	        buttonpuzzle3.addListener(new ClickListener() {
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	                super.clicked(event, x, y);
	                Checkclick = true;
	                Answers =3;
	            }
	        });
	        
	        TextButton buttonpuzzle4 = new TextButton("E", skin);
	        buttonpuzzle4.setWidth(100);
	        buttonpuzzle4.setHeight(50);
	        buttonpuzzle4.setPosition(550, 150);

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
	    	 startTime = TimeUtils.nanoTime();
	     }
	     batch.begin();
   	     font.draw(batch, "TIME-LIMIT : "+point, 550, 700);
   	     font.draw(batch, "What Picture "+num+" Mean?", 540, 600);
   	     font.draw(batch, "CHOICE "+Word[num], 400, 500);
	     batch.end();
	     
	     if(point <= 0) {
	    	 game.setScreen(new Lost(game));
	     } 
	     
	     stage.act(Gdx.graphics.getDeltaTime());
	     stage.draw();
	     
	     if(Checkclick == true) {
    	      check();
	     }
	     Answers =-1;
		
	}
	
	public void check() {
		
        if(Answers == ansnum[num]) {
      			  clearQ[num] = true;
      			  Checkclick  = false;
      			  num += 1;
      			
      	} else if(Answers != ansnum[num]){
      			  HP -= 1;
      	} 
      	if(num >= 5) {
      		MainMenuScreen.Point[2] = false;
      		MainMenuScreen.Status[2] = "OFF";
		 	game.setScreen(new MainMenuScreen(game));	  	
      	}		 
		Checkclick  = false;
		
		if(HP < 0){
			MainMenuScreen.Status[2] = "OFF";
		 	game.setScreen(new Lost(game));
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
