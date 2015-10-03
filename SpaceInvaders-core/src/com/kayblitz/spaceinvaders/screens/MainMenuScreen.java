package com.kayblitz.spaceinvaders.screens;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.kayblitz.spaceinvaders.SpaceInvaders;

public class MainMenuScreen implements UpdateableScreen {
	
	private SpaceInvaders game;
	private SpriteBatch batch;
	private ShapeRenderer renderer;
	private Rectangle rStart;
	
	public MainMenuScreen(SpaceInvaders game) {
		this.game = game;
		batch = game.getSpriteBatch();
		renderer = game.getShapeRenderer();
	}

	@Override
	public void show() {}
	
	@Override
	public void update(float delta) {}

	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}
}