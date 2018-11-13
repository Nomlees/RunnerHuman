package ru.kev.runnerman.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Man {

    private static final int MOVEMENT = 100;

    private static final int GRAVITY = -20;

    private Vector3 position;
    private Vector3 velosity;
    private Texture man;
    private Rectangle boundsMan;

    public Man(int x, int y) {
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        man = new Texture("RunMan.png");
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

    public void update(float dt) {
        if (position.y > 0)
            velosity.add(0 , GRAVITY , 0);
            velosity.scl(dt);
            position.add(MOVEMENT * dt,velosity.y, 0);
        if (position.y < 110 )
            position.y = 110;
        if (position.y > 480-60) {
            position.y =480 - 60;
        }


        velosity.scl(1 / dt);
        boundsMan.setPosition(position.x , position.y);
    }


    public void jump() {
        velosity.y = 300;
    }

    public Rectangle getBoundsMan() {
        return boundsMan;
    }
}
