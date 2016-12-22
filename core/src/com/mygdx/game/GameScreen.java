package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
    final Box game;

    Texture TMImage;
    Texture FMImage;
    Texture BombImage;
    Texture BImage;
    Sound dropSound;
    int Fnum =0;
    int Bombnum =0;
    Music backMusic;
    OrthographicCamera camera;
    Rectangle Box;
    Array<Rectangle> T_Mush;
    Array<Rectangle> F_Mush;
    Array<Rectangle> Bomb;
    int[] dropsvalue;
    long lastDropTime;
    int Score;
	private long startTime;
	private int point = 60;
	private SpriteBatch batch;
	private BitmapFont font;
	private TestSwitch Test;

    public GameScreen(final Box gam) {
        this.game = gam;
        Test = new TestSwitch();
	    Thread thr = new Thread(Test);
    	thr.setDaemon(true);
    	thr.start();
        startTime = TimeUtils.nanoTime();
        
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        batch = new SpriteBatch();
                        // โหลดไฟล์รูปถังน้ำและเม็ดฝน ขนาด 64x64 
        TMImage = new Texture(Gdx.files.internal("truemushroom.png"));
        FMImage = new Texture(Gdx.files.internal("falsemushroom.png"));
        BombImage = new Texture(Gdx.files.internal("bomb.png"));
        BImage = new Texture(Gdx.files.internal("mariobox.png"));

                        // โหลดเสียงเม็ดฝน และ effect
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        backMusic = Gdx.audio.newMusic(Gdx.files.internal("Super Mario.mp3"));
        backMusic.setLooping(true);

                        // สร้าง camera และ SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

                        // สร้างออปเจ็ค bucket จากคลาส Rectangle
        Box = new Rectangle();
        Box.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        Box.y = 20; 
        Box.width = 64;
        Box.height = 64;

                        // สร้าง array เม็ดฝน และเริ่มโปรยเม็ดฝนเม็ดแรก
        T_Mush = new Array<Rectangle>();
        spawnT_Mush();
        F_Mush = new Array<Rectangle>();
        spawnF_Mush();
        Bomb = new Array<Rectangle>();
        spawnBomb();
        

    }

    private void spawnT_Mush() {
        Rectangle t_Mush = new Rectangle();
        t_Mush.x = MathUtils.random(0, 800 - 64);
        t_Mush.y = 480;
        t_Mush.width = 64;
        t_Mush.height = 64;
        T_Mush.add(t_Mush);
        lastDropTime = TimeUtils.nanoTime();
    }
    
    private void spawnF_Mush() {
        Rectangle f_Mush = new Rectangle();
        f_Mush.x = MathUtils.random(0, 800 - 64);
        f_Mush.y = 480;
        f_Mush.width = 64;
        f_Mush.height = 64;
        F_Mush.add(f_Mush);
       
    }
    
    private void spawnBomb() {
        Rectangle bomb = new Rectangle();
        bomb.x = MathUtils.random(0, 800 - 64);
        bomb.y = 480;
        bomb.width = 64;
        bomb.height = 64;
        Bomb.add(bomb);
        
    }

    @Override
    public void render(float delta) {
                // เคลียร์หน้าจอ ด้วยสี Dark Blue 
                // โดย argument ของ glClearColor คือ red, green, blue, alpha
                // ค่าอยู่ระหว่าง [0, 1] (float)
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

                        // camera อัพเดท matrix
        camera.update();

                        // ให้ SpriteBatch ทำการเรนเดอร์พิกัดทั้งหมดของระบบ
        game.batch.setProjectionMatrix(camera.combined);

                        // เริ่มวาด เรนเดอร์ ตะกร้าและเม็ดฝน รวมถึงนับจำนวนเม็ดฝนที่เก็บได้
        game.batch.begin();
        game.font.draw(game.batch, "Score:   " + Score, 0, 480);
        game.batch.draw(BImage, Box.x, Box.y);
        for (Rectangle t_Mush : T_Mush) {
            game.batch.draw(TMImage, t_Mush.x, t_Mush.y);
        }
        for (Rectangle f_Mush : F_Mush) {
            game.batch.draw(FMImage, f_Mush.x, f_Mush.y);
        }
        for (Rectangle bomb : Bomb) {
            game.batch.draw(BombImage, bomb.x, bomb.y);
        }
        game.batch.end();

                        // เช็คว่า มีการคลิกเมาท์หรือแตะหน้าจอหรือไม่
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            Box.x = touchPos.x - 64 / 2;
        }

                // เช็คว่า มีการกดคีย์บอร์ดปุ่มลูกศรซ้าย/ขวา หรือไม่
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            Box.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            Box.x += 200 * Gdx.graphics.getDeltaTime();
        if (Test.getsw3())
            Box.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Test.getsw4())
            Box.x += 200 * Gdx.graphics.getDeltaTime();

                // เช็คไม่ให้ถังน้ำมันล้นหน้าจอ
        if (Box.x < 0)
            Box.x = 0;
        if (Box.x > 800 - 64)
            Box.x = 800 - 64;

                        // เช็คว่าถึงเวลาที่จะโปรยเม็ดฝนเม็ดถัดไปหรือยัง
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) {
        	spawnT_Mush();
        	++Fnum;
        	++Bombnum;
        }
        if(Fnum == 2) {
        	spawnF_Mush();
        	Fnum =0;
        }
        if(Bombnum == 3) {
        	spawnBomb();
        	Bombnum =0;
        } 
            
        
                // ทำให้เม็ดฝนขยับ โดยลบเม็ดฝนทุกครั้งที่ตกลงพ้นขอบจอหรือว่าไปชนกับถังน้ำ 
                // รวมถึงให้มันเล่นเสียงเวลาโดนถังน้ำ
        Iterator<Rectangle> iter = T_Mush.iterator();
        while (iter.hasNext()) {
            Rectangle t_Mush = iter.next();
            t_Mush.y -= 200 * Gdx.graphics.getDeltaTime();
            if (t_Mush.y + 64 < 0)
                iter.remove();
            if (t_Mush.overlaps(Box)) {
                Score++;
                dropSound.play();
                iter.remove();
            }
        }
        Iterator<Rectangle> iter1 = F_Mush.iterator();
        while (iter1.hasNext()) {
            Rectangle f_Mush = iter1.next();
            f_Mush.y -= 200 * Gdx.graphics.getDeltaTime();
            if (f_Mush.y + 64 < 0)
                iter1.remove();
            if (f_Mush.overlaps(Box)) {
                Score--;
                dropSound.play();
                iter1.remove();
            }
        }
        Iterator<Rectangle> iter2 = Bomb.iterator();
        while (iter2.hasNext()) {
            Rectangle bomb = iter2.next();
            bomb.y -= 200 * Gdx.graphics.getDeltaTime();
            if (bomb.y + 64 < 0)
                iter2.remove();
            if (bomb.overlaps(Box)) {
                Score -= 3;
                dropSound.play();
                iter2.remove();
            }
        }
        
        if(Score == 20) {
        	backMusic.stop();
        	MainMenuScreen.Status[0] = "OFF";
        	MainMenuScreen.Point[0] = false;
        	game.setScreen(new MainMenuScreen(game));
            
        } 
        if (TimeUtils.timeSinceNanos(startTime) > 1000000000) { 
     	    	 point  -= 1;  	
     	    	 MainMenuScreen.UT += 1;
     	    	 startTime = TimeUtils.nanoTime();
     	}
     	batch.begin();
        font.draw(batch, "TIME-LIMIT : "+point, 550, 700);
     	batch.end();
     	     
     	if(point <= 0) {
     		     MainMenuScreen.Status[0] = "OFF";
     		     backMusic.stop();
    	    	 Lost.bomb();
     	    	 game.setScreen(new Lost(game));
     	    	 
     	}
     	
     	if(Score <= -10) {
     		MainMenuScreen.Status[0] = "OFF";
     		game.setScreen(new Lost(game));
     		backMusic.stop();
        	Lost.bomb();
     	}
;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
                // เริ่มเล่นเสียงเพลง (เสียงฝนตก) เมื่อหน้าจอนี้แสดง
        backMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        TMImage.dispose();
        BImage.dispose();
        dropSound.dispose();
        backMusic.dispose();
    }
}
