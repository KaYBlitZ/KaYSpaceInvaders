package com.kayblitz.spaceinvaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kayblitz.spaceinvaders.SpaceInvaders;
import com.kayblitz.spaceinvaders.resources.Resources;

public class LoadingScreen implements UpdateableScreen {
	
	private SpaceInvaders game;
	private Resources resources;
	private SpriteBatch batch;
	
	public LoadingScreen(SpaceInvaders game) {
		this.game = game;
		this.resources = game.getResources();
		this.batch = game.getSpriteBatch();
	}
	
	@Override
	public void update(float delta) {
		if (resources.getManager().update()) {
			game.setScreen(new MainMenuScreen(game));
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (resources.getManager().isLoaded("badlogic.jpg", Texture.class)) {
			Texture logo = resources.getTexture("badlogic.jpg");
			
			batch.begin();
			batch.draw(logo, SpaceInvaders.WIDTH / 2 - logo.getWidth() / 2, SpaceInvaders.HEIGHT / 2 - logo.getHeight() / 2);
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}
}
