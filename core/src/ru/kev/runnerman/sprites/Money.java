package ru.kev.runnerman.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Random;

public class Money {

    public static final int FLUCTUATION = 150;
    public static final int  MONEY_GAP = 50;
    public static final int LOWEST_OPENING = 100;

    public static final int MONEY_WIDTH = 100;

    private Texture topMoney , botMoney;
    private Vector2 posTopMoney, posBotMoney;

    private Random rand;

    public Texture getTopMoney() {
        return topMoney;
    }

    public Texture getBotMoney() {
        return botMoney;
    }

    public Vector2 getPosTopMoney() {
        return posTopMoney;
    }

    public Vector2 getPosBotMoney() {
        return posBotMoney;
    }

    public Money(float x){
        topMoney = new Texture("coins.png");
        botMoney = new Texture("coins.png");
        rand = new Random();

        posTopMoney = new Vector2(x, rand.nextInt(FLUCTUATION) + MONEY_GAP+LOWEST_OPENING);
        posBotMoney = new Vector2(x, 120);


    }

    public void reposition(float x) {
        posTopMoney.set(x, rand.nextInt(FLUCTUATION) + MONEY_GAP+LOWEST_OPENING);
        posBotMoney.set(x, 120);
    }


    public void dispose() {
        botMoney.dispose();
        topMoney.dispose();
    }
}
