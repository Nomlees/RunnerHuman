package ru.kev.runnerman.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.kev.runnerman.RunnerMan;

/**
 * Окно которое открывается после проигрыша
 */
public class GameOver extends State {

    private Texture background;
    private Texture over;


    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, RunnerMan.WIDTH, RunnerMan.HEIGHT);
        background = new Texture("interface.png");
        over = new Texture("playbtn.png");
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

        sb.begin();
        sb.draw(background, 0, 0);//рисуется фон
        sb.draw(over, camera.position.x - over.getWidth() / 2, camera.position.y - over.getHeight() /2);

        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        over.dispose();
        System.out.println("GameOver Disposed");

    }
}