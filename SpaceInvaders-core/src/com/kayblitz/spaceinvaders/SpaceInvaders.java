package com.kayblitz.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kayblitz.spaceinvaders.screens.UpdateableScreen;

public class SpaceInvaders extends Game {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	private static final float FIXED_TIMESTEP = 1 / 60f;
	private static final int MAX_TIMESTEPS = 6;

	private SpriteBatch batch;
	private ShapeRenderer renderer;
	private float elapsedTime;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		renderer.setColor(Color.WHITE);
		
		
	}

	@Override
	public void render() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		
		// sets the time to a max of 6 timesteps
		// prevents updating too much if there is major lag
		if (elapsedTime > FIXED_TIMESTEP * MAX_TIMESTEPS) {
			elapsedTime = FIXED_TIMESTEP * MAX_TIMESTEPS;
		}
		
		while (elapsedTime >= FIXED_TIMESTEP) {
			elapsedTime -= FIXED_TIMESTEP;
			if (screen != null) {
				((UpdateableScreen) screen).update(FIXED_TIMESTEP);
			}
		}
		
		super.render();
	}
	
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	public ShapeRenderer getShapeRenderer() {
		return renderer;
	}
}
