package ru.kev.runnerman.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public abstract class State {

    OrthographicCamera camera;

    GameStateManager gsm;

    State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
    }


    protected abstract void handleInput();//запрашивает пользовательский ввод,были ли нажаты определённые клавиши
    public abstract void update(float dt);//обновление картинки
    public abstract void render(SpriteBatch sb);//предоставляет текстуру и кординаты для рисования текстур
    public abstract void dispose();//уничтожает экземпляр класса, когда тот становится ненужным

}
