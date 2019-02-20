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
import ru.kev.runnerman.sprites.Rock;

public class PlayState extends State {

    private static final int MONEY_SPACING = 40;//Расстояние между появляющимися монетами
    private static final int MONEY_COUNT = 200;// Количество монет
    private static final int ROCK_SPACING = 100;//Расстояние между появляющимися камнями
    private static final int ROCK_COUNT = 100;// Количество камней

    //private GameStateManager gsm;
   // protected Object money;

    private Man man;
    private Texture bg;
    private BitmapFont font;
    private Array<Money> monies;
    private Array<Rock> rocks;
    private int countStone;
    private int countCoins;




    PlayState(GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        countStone = 10;
        man = new Man(50, 50);
        camera.setToOrtho(false, RunnerMan.WIDTH, RunnerMan.HEIGHT);//область обзора
        bg = new Texture("interface.png");
        font = new BitmapFont();
        monies = new Array<Money>();
        rocks = new Array<Rock>();
        //создание монет
        for (int i = 0; i < MONEY_COUNT; i++) {
            monies.add(new Money(i * (MONEY_SPACING + Money.MONEY_WIDTH)));
        }
        for (int i = 0; i < ROCK_COUNT; i++) {
            rocks.add(new Rock(i * (ROCK_SPACING + Rock.ROCK_WIDTH)));
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
        camera.position.x = man.getPosition().x + 80;//позция и текстура из класса man

        for (Money money : monies) {
            if (camera.position.x - (camera.viewportWidth / 2) > Money.getPosTopMoney().x + money.getTopMoney().getWidth()) {
                money.reposition(Money.getPosTopMoney().x + ((Money.MONEY_WIDTH + MONEY_SPACING) * MONEY_COUNT));
            }

        }
        for (Rock rock : rocks) {
            if (camera.position.x - (camera.viewportWidth / 2) > Rock.getPosTopRock().x + rock.getTopRock().getWidth()) {
                rock.reposition(Rock.getPosTopRock().x + ((Rock.ROCK_WIDTH + ROCK_SPACING) * ROCK_COUNT));
            }
        }

        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);//матрица проекций для камеры
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(man.getMan(), man.getPosition().x, man.getPosition().y);
        font.draw(sb, "SCORE: " + countCoins, camera.position.x, 90);
        font.draw(sb, "LIVE: " + countStone, camera.position.x, 70);

        for (Money money : monies) {
            sb.draw(money.getTopMoney(), Money.getPosTopMoney().x, Money.getPosTopMoney().y);
            sb.draw(money.getBotMoney(), money.getPosBotMoney().x, money.getPosBotMoney().y);
        }
        for (Rock rock : rocks) {
            sb.draw(rock.getTopRock(), Rock.getPosTopRock().x, Rock.getPosTopRock().y);
            sb.draw(rock.getBotRock(), rock.getPosBotRock().x, rock.getPosBotRock().y);
        }
        sb.end();
        conditions();
  }

    /**
     * Условия столкновения мальчика с камнями и монетами
     */
    private void conditions(){
        Iterator<Rock> iteratorRock=rocks.iterator();
            while(iteratorRock.hasNext()){
                Rock stone=iteratorRock.next();
                    if(stone.collides(man.getBoundsMan())){
                        countStone--;
                        iteratorRock.remove();
//                        if(countStone<1){
////                        gsm.set(new GameOver(gsm));
//                        }
                    }
            }
        Iterator<Money> iterator=monies.iterator();
            while(iterator.hasNext()){
                Money coin=iterator.next();
                    if(coin.collides(man.getBoundsMan())){
                        countCoins++;
                        iterator.remove();
                        }
            }
        }

    @Override
    public void dispose() {
        bg.dispose();
        man.dispose();
        for (Rock rock:rocks) {
            rock.dispose();
        }
        for (Money money:monies) {
            money.dispose();
        }
    }
}
