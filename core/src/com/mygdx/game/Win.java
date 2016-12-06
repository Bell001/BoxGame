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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Win implements Screen {
	
	final Box game;
	static Sound SurpiseSound;
	Sprite back;
    Texture Background;
    private BitmapFont font;
    public SpriteBatch batch;
    private Stage stage;
    private Skin skin;
	
	public Win(final Box gam) {
		game =gam;
		SurpiseSound = Gdx.audio.newSound(Gdx.files.internal("bomb.mp3"));
		font = new BitmapFont();
	    font.setColor(Color.GREEN);
	    batch = new SpriteBatch();
        Background = new Texture("Background.png");
        back = new Sprite(Background);
        
        stage = new Stage(new StretchViewport(800, 480));
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton buttonpuzzle1 = new TextButton("puzzle", skin);
        buttonpuzzle1.setWidth(200);
        buttonpuzzle1.setHeight(50);
        buttonpuzzle1.setPosition(800 / 2 - 200 / 2, 300);

        stage.addActor(buttonpuzzle1);

        buttonpuzzle1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new MainMenuScreen(game));
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
		 batch.begin();
	     back.draw(batch);
	     font.draw(batch, "You Win", 550, 700);
	     batch.end();	
	     
	     stage.act(Gdx.graphics.getDeltaTime());
	     stage.draw();
		
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
