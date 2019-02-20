package ru.kev.runnerman.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Класс мальчика, задаются текстуры...
 */

public class Man {

    private static final int MOVEMENT = 100;

    private static final int GRAVITY = -20;

    private Vector3 position;//позиция
    private Vector3 velocity; //скорость
    private Texture man;
    private Rectangle boundsMan;

    public Man(int x, int y) {
        position = new Vector3(x,y,0); //создание
        velocity = new Vector3(0,0,0);// направление
        man = new Texture("RunMan.png");//текстура
        boundsMan = new Rectangle(x,y, man.getWidth(), man.getHeight());
        boundsMan.width = 60;
        boundsMan.height = 60;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getMan() {
        return man;
    }
    /**
     * реализация силы тяжести
     * @param dt - время в секундах
     */
    public void update(float dt) {
        if (position.y > 0)
            velocity.add(0 , GRAVITY , 0);
            velocity.scl(dt);
            position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 110 )
            position.y = 110;
        if (position.y > 480-60) {
            position.y =480 - 60;
        }
        velocity.scl(1 / dt);
        boundsMan.setPosition(position.x , position.y);
    }
    /**
     * Скорость прыжка
     */
    public void jump() {
        velocity.y = 300;
    }

        public void dispose() {
        man.dispose();
    }

    public Rectangle getBoundsMan() {
        return boundsMan;
    }
}
