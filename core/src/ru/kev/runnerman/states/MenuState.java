package ru.kev.runnerman.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.kev.runnerman.RunnerMan;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private Texture menuMan;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, RunnerMan.WIDTH / 2, RunnerMan.HEIGHT / 2);
        background = new Texture("interface.png");
        playBtn = new Texture("playbtn.png");
        menuMan = new Texture("menuMan.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y - playBtn.getHeight() /2);
        sb.draw(menuMan, 25, camera.position.y - menuMan.getHeight() /6 );
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        menuMan.dispose();
        System.out.println("MenuState Disposed");

    }
}