package com.kayblitz.spaceinvaders.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Resources {
	
	private AssetManager manager;
	
	public Resources() {
		manager = new AssetManager();
		
		manager.load("badlogic.jpg", Texture.class);
		manager.load("enemyBlack2.png", Texture.class);
		manager.load("enemyBlue2.png", Texture.class);
		manager.load("enemyGreen2.png", Texture.class);
		manager.load("enemyRed2.png", Texture.class);
		manager.load("laserBlue07.png", Texture.class);
		manager.load("laserRed07.png", Texture.class);
		manager.load("playerShip1_damage1.png", Texture.class);
		manager.load("playerShip1_damage2.png", Texture.class);
		manager.load("playerShip1_damage3.png", Texture.class);
		manager.load("playerShip1_orange.png", Texture.class);
		manager.load("arial_32.fnt", BitmapFont.class);
	}
	
	public void dispose() {
		manager.dispose();
	}
	
	public AssetManager getManager() {
		return manager;
	}
	
	public Texture getTexture(String fileName) {
		Texture texture = manager.get(fileName, Texture.class);
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return texture;
	}
	
	public BitmapFont getFont(String fileName) {
		BitmapFont font = manager.get(fileName, BitmapFont.class);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return font;
	}
}
