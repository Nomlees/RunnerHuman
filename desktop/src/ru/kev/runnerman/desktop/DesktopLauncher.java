package ru.kev.runnerman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.kev.runnerman.RunnerMan;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = RunnerMan.WIDTH;
		config.height = RunnerMan.HEIGHT;
		config.title = RunnerMan.TITLE;
		new LwjglApplication(new RunnerMan(), config);
	}
}
