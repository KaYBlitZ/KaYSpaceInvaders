package com.kayblitz.spaceinvaders.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Resources {
	
	private AssetManager manager;
	
	public Resources() {
		manager.load("font", BitmapFont.class);
	}
	
	public BitmapFont getFont(String fileName) {
		return manager.get(fileName, BitmapFont.class);
	}
}
