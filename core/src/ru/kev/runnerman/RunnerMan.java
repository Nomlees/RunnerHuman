package ru.kev.runnerman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.kev.runnerman.states.GameStateManager;
import ru.kev.runnerman.states.MenuState;


/**
 *Класс в передставления игры
 */

public class RunnerMan extends ApplicationAdapter {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	public static final String TITLE = "Run Man";
	private SpriteBatch batch;
	private Music music;
	private GameStateManager gsm;

/**
	В методе вызывается меню, задаётся цвет фона, музыка
 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		batch = new SpriteBatch();
	}


	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());//время между прошедшим и текущем кадром в секундах
		gsm.render(batch);//отрисовывает верхний экран
	}

	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}

//	public static void set(GameStateManager gsm){
//		gsm.set(new GameOver(gsm));
//	}
}
