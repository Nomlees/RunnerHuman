package ru.kev.runnerman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


import ru.kev.runnerman.states.GameStateManager;
import ru.kev.runnerman.states.MenuState;
import ru.kev.runnerman.states.PlayState;


public class RunnerMan extends ApplicationAdapter {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	public static final String TITLE = "Run Man";
	SpriteBatch batch;
	private Music music;
	private GameStateManager gsm;


	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		batch = new SpriteBatch();
		gsm.push(new PlayState(gsm));

	}


	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());

		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}
}
