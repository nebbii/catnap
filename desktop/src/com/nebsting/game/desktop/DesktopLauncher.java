package com.nebsting.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nebsting.game.Catnap;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Catnapped!";
        config.width = 960;
        config.height = 720;
		new LwjglApplication(new Catnap(), config);
	}
}
