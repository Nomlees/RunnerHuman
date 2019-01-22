package ru.kev.runnerman.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<PlayState> states;

    public GameStateManager(){
        states = new Stack<PlayState>();
    }

    public void push(PlayState state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(PlayState state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
