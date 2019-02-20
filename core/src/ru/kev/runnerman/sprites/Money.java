package ru.kev.runnerman.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

//import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Random;

public class Money {

    private static final int FLUCTUATION = 150;// диапазон отклонений на котором создаются монеты
    private static final int  MONEY_GAP = 50;// разница между одной и вторй монетой
    private static final int LOWEST_OPENING = 100;// нижняя граница просвета

    public static final int MONEY_WIDTH = 150;
    private static Vector2 posTopMoney;


    private Texture topMoney , botMoney;
    private Vector2 posBotMoney;

    private Random rand;
    private Rectangle topCoins, botCoins;

    public Texture getTopMoney() {
        return topMoney;
    }

    public Texture getBotMoney() {
        return botMoney;
    }

    public static Vector2 getPosTopMoney() {
        return posTopMoney;
    }

    public Vector2 getPosBotMoney() {
        return posBotMoney;
    }

    public Money(float x){
        topMoney = new Texture("coins.png");
        botMoney = new Texture("coins.png");
        rand = new Random();

        posTopMoney = new Vector2(x, rand.nextInt(FLUCTUATION) + MONEY_GAP + LOWEST_OPENING);//просвет между монетами одинаковый, но находяится на разной высоте
        posBotMoney = new Vector2(x, posTopMoney.y - MONEY_GAP - botMoney.getHeight());


        topCoins = new Rectangle(posTopMoney.x , posTopMoney.y , topMoney.getWidth(), topMoney.getHeight());
        botCoins = new Rectangle(posBotMoney.x , posBotMoney.y , botMoney.getWidth() , botMoney.getHeight());
    }

    //движение монет
    public void reposition(float x) {
        posTopMoney.set(x, rand.nextInt(FLUCTUATION) + MONEY_GAP+LOWEST_OPENING);
        posBotMoney.set(x, posTopMoney.y - MONEY_GAP - botMoney.getHeight());
        topCoins.setPosition(posTopMoney.x , posTopMoney.y);
        botCoins.setPosition(posBotMoney.x , posBotMoney.y);

    }

    //обнаружение сталкновения с камнями
    public boolean collides(Rectangle player){
        return player.overlaps(topCoins)|| player.overlaps(botCoins);
    }


    public void dispose() {
        botMoney.dispose();
        topMoney.dispose();
    }

//    public Rectangle getRectangleCoinBot () {
//        return botCoins;
//    }
//    public Rectangle getRectangleCoinTop () {
//        return topCoins;
//    }
}
