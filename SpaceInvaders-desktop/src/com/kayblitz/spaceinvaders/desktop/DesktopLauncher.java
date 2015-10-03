package com.kayblitz.spaceinvaders.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kayblitz.spaceinvaders.SpaceInvaders;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SpaceInvaders.WIDTH;
		config.height = SpaceInvaders.HEIGHT;
		new LwjglApplication(new SpaceInvaders(), config);
	}
}
