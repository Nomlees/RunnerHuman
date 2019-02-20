package ru.kev.runnerman.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Управление окнами или состояниями игры
 */
public class GameStateManager {

    private Stack<State> states;

 //конструктор который создаёт пустой стек
    public GameStateManager(){
        states = new Stack<State>();
    }
//помещает элемент в вершину стека (игровые состояния)
    public void push(State state){
        states.push(state);
    }
////извлекает верхний элемент, удаляя его из стека
//    public void pop(){
//        states.pop().dispose();
//    }
// удаляет из стека верхний экран и помещает следующий экран в вершину стека
     void set(State state) {
    states.pop().dispose();
    states.push(state);
    }

// обнавляет игру, обнавляя при этом только состояние которое находяится на вершине
    public void update(float dt){
        states.peek().update(dt);
    }
//принимает состояния из вершины стека, оставляет его и прорисовывает
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }



}
