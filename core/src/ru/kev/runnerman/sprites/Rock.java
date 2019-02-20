package ru.kev.runnerman.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Класс камней
 */
public class Rock {
    private static final int FLUCTUATION = 100;// диапазон отклонений на котором создаются камни
    private static final int ROCK_GAP = 80;// разница между одним и вторым камнем
    private static final int LOWEST_OPENING = 100;// нижняя граница просвета
    public static final int ROCK_WIDTH = 100;
    private static Vector2 posTopRock;

    private Texture topRock, botRock;
    private Vector2 posBotRock;
    private Random rand;
    private Rectangle topStone, botStone;

    public Texture getTopRock() {
        return topRock;
    }

    public Texture getBotRock() {
        return botRock;
    }

    public static Vector2 getPosTopRock() {
        return posTopRock;
    }

    public Vector2 getPosBotRock() {
        return posBotRock;
    }

    public Rock(float x){

        topRock = new Texture("rock.png");
        botRock = new Texture("rock.png");
        rand = new Random();

        posTopRock = new Vector2(x, rand.nextInt(FLUCTUATION) + ROCK_GAP +LOWEST_OPENING);//просвет между монетами одинаковый, но находяится на разной высоте
        posBotRock = new Vector2(x, posTopRock.y - ROCK_GAP - botRock.getHeight());

        topStone = new Rectangle(posTopRock.x , posTopRock.y , topRock.getWidth(), topRock.getHeight());
        botStone = new Rectangle(posBotRock.x , posBotRock.y , botRock.getWidth() , botRock.getHeight());
        topStone.width = 40;
        topStone.height = 40;
        botStone.height = 40;
        botStone.width = 40;
    }

    //движение камней
    public void reposition(float x) {
        posTopRock.set(x, rand.nextInt(FLUCTUATION) + ROCK_GAP +LOWEST_OPENING);
        posBotRock.set(x, posTopRock.y - ROCK_GAP - botRock.getHeight());
        topStone.setPosition(posTopRock.x , posTopRock.y);
        botStone.setPosition(posBotRock.x , posBotRock.y);

    }
//обнаружение сталкновения с камнями

    public boolean collides(Rectangle player){
        return player.overlaps(topStone)|| player.overlaps(botStone);
    }

    public void dispose() {
        botRock.dispose();
        topRock.dispose();
    }

}
