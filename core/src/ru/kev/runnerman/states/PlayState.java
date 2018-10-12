package ru.kev.runnerman.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import ru.kev.runnerman.RunnerMan;
import ru.kev.runnerman.sprites.Man;
import ru.kev.runnerman.sprites.Money;

public class PlayState extends State {

    public static final int MONEY_SPACING = 40;
    public static final int MONEY_COUNT = 10;

    protected GameStateManager gsm;
    protected Object money;

    private Man man;
    private Texture bg;
    private BitmapFont font;
    private Array<Money> monies;
    private int countCoins;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        this.money = money;
        man = new Man(50,50);
        camera.setToOrtho(false, RunnerMan.WIDTH /2, RunnerMan.HEIGHT/2);
        bg = new Texture("interface.png");
        font = new BitmapFont();

        monies = new Array<Money>();

        for (int i  = 1; i <MONEY_COUNT; i++){
            monies.add(new Money(i * (MONEY_SPACING + Money.MONEY_WIDTH )));
        }

    }

    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched())
            man.jump();

    }

    @Override
    public void update(float dt) {
        handleInput();
        man.update(dt);
        camera.position.x = man.getPosition().x + 80;
        for (Money money : monies) {
            if (camera.position.x - (camera.viewportWidth / 2) > money.getPosTopMoney().x + money.getTopMoney().getWidth()){
                money.reposition(money.getPosTopMoney().x + ((Money.MONEY_WIDTH + MONEY_SPACING) * MONEY_COUNT));
            }
        }
        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(gsm ,"Монет собрано: " + countCoins , 0);
        sb.draw(bg , camera.position.x - (camera.viewportWidth/2) , 0);
        sb.draw(man.getMan(), man.getPosition().x , man.getPosition().y);
        for (Money money : monies) {
            sb.draw(money.getTopMoney(), money.getPosTopMoney().x, money.getPosTopMoney().y);
            sb.draw(money.getBotMoney(), money.getPosBotMoney().x, money.getPosBotMoney().y);
        }
        sb.end();




    }

    @Override
    public void dispose() {
        bg.dispose();


    }
}
