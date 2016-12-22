package com.mygdx.game;

import java.io.*;
import java.net.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import gnu.io.SerialPortEvent;

public class MainMenuScreen implements Screen {

	    final Box game;
	    static int UT = 0;
	    OrthographicCamera camera;
	    private Stage stage;
	    private Skin skin;
	    public SpriteBatch batch;
	    
	    public boolean AA = true;

	    public static  boolean[] Point = {true,true,true,true};
	    public static  String[] Status = {"OFF","OFF","OFF","OFF"};
	
	    Texture Background;
	    private BitmapFont font;
	    private Controller ctrl;
	    private SerialTest serialtest;
		private Sprite back;
	 
	    public MainMenuScreen(final Box gam) {
	    	
	    	ctrl = new Controller();
	    	Thread thr = new Thread(ctrl);
	    	thr.setDaemon(true);
	    	thr.start();
	    
	    	
	    	game = gam;
	        font = new BitmapFont();
	        font.setColor(Color.RED);
	        batch = new SpriteBatch();
	        Background = new Texture(Gdx.files.internal("Background.png"));
	        back = new Sprite(Background);
	    	
	        camera = new OrthographicCamera();
	        camera.setToOrtho(false, 800, 480);
	        
	        stage = new Stage(new StretchViewport(800, 480));
	        Gdx.input.setInputProcessor(stage);

	        skin = new Skin(Gdx.files.internal("uiskin.json"));
            	TextButton buttonpuzzle1 = new TextButton("puzzle1", skin);
            	buttonpuzzle1.setWidth(200);
            	buttonpuzzle1.setHeight(50);
            	buttonpuzzle1.setPosition(800 / 2 - 200 / 2, 340);
            	if(Point[0]){
            		stage.addActor(buttonpuzzle1);
            			
            		buttonpuzzle1.addListener(new ClickListener() {
            			@Override
            			public void clicked(InputEvent event, float x, float y) {
            				super.clicked(event, x, y);
            				Status[0] = "ON";
            				game.setScreen(new GameScreen(game));
            			}
            		});
            	}
	        

            	TextButton buttonpuzzle2 = new TextButton("puzzle2", skin);
            	buttonpuzzle2.setWidth(200);
            	buttonpuzzle2.setHeight(50);
            	buttonpuzzle2.setPosition(800 / 2 - 200 / 2, 260);
            	if(Point[1]){
            		stage.addActor(buttonpuzzle2);
            		
            		buttonpuzzle2.addListener(new ClickListener() {
            			@Override
            			public void clicked(InputEvent event, float x, float y) {
            				super.clicked(event, x, y);
	            			Status[1] = "ON";
		        			game.setScreen(new GameScreen2(game));
	        				}
	        		});
            	
	        }
	        

	        TextButton buttonpuzzle3 = new TextButton("puzzle3", skin);
	        buttonpuzzle3.setWidth(200);
	        buttonpuzzle3.setHeight(50);
	        buttonpuzzle3.setPosition(800 / 2 - 200 / 2, 180);
            if(Point[2]){
            	stage.addActor(buttonpuzzle3);
            	
            	buttonpuzzle3.addListener(new ClickListener() {
            		@Override
	            	public void clicked(InputEvent event, float x, float y) {
	            		super.clicked(event, x, y);
	            		Status[2] = "ON";
	                	game.setScreen(new GameScreen3(game));
	            	}
            	});
            }
            
            TextButton buttonpuzzle4 = new TextButton("puzzle4", skin);
        	buttonpuzzle4.setWidth(200);
        	buttonpuzzle4.setHeight(50);
        	buttonpuzzle4.setPosition(800 / 2 - 200 / 2, 100);
        	if(Point[3]){
        		stage.addActor(buttonpuzzle4);
        			
        		buttonpuzzle4.addListener(new ClickListener() {
        			@Override
        			public void clicked(InputEvent event, float x, float y) {
        				super.clicked(event, x, y);
        				Status[3] = "ON";
        				game.setScreen(new GameScreen4(game));
        			}
        		});
        	}
        	

	    }
	    
	    String a;
	    
	    @Override
	    public void render(float delta) {
	        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        batch.begin();
	        back.draw(batch);
//	        batch.draw(Background,0,0);
	        font.draw(batch, "Secret Box", 550, 700);

	        batch.end();	
	        stage.act(Gdx.graphics.getDeltaTime());
	        stage.draw();
	        
	        if(AA) {
	        	try {
	        		GameScreen3.setQ =  ctrl.getValue();
	        		System.out.println(ctrl.getValue());
	        		if(ctrl.getword() == null){ AA = false; }
	        	} catch (Exception e) {
	        		// TODO Auto-generated catch block
	        		e.printStackTrace();
	        	}
	        }
	       
	        check();
	        
	    }
	    

	    public void check() {
	    	int c =0;
	    	
	    	for(int i=0;i<4;i++) {
	    		if(Point[i] == false) {
	    			c+=1;
	    		}
	    	}
	    	
	    	if(c == 4){
	    		game.setScreen(new Win(game));
	    	}
	    }
	    
	    public static void resetPoint() {
	    	for(int i=0;i<4;i++) {
	    		if(Point[i] == false) {
	    			Point[i] = true;
	    		}
	    	}
	    }

		@Override
		public void show() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resize(int width, int height) {
			  stage.getViewport().update(width, height, true);
			
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
			 stage.dispose();
		     skin.dispose();
		     batch.dispose();
		}


}
