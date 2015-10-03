package com.kayblitz.spaceinvaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.kayblitz.spaceinvaders.Action;
import com.kayblitz.spaceinvaders.screens.GameScreen;

public class Controller extends Entity {
	private static final float VELOCITY = 20f;
	
	private GameScreen screen;
	private Texture texture;
	
	public Controller(float x, float y, Texture texture, GameScreen screen) {
		super(x, y, texture.getWidth(), texture.getHeight());
		this.screen = screen;
		this.texture = texture;
	}
	
	// different search algorithms should be utilized here
	public Action getAction() {
		return Action.SHOOT;
	}

	@Override
	public void update(float delta) {}
	
	public void update(float delta, Array<Enemy> enemies, Array<Bullet> bullets) {
		Action action = getAction();
		
		switch (action) {
		case LEFT:
			hitBox.x -= VELOCITY * delta;
			break;
		case RIGHT:
			hitBox.x += VELOCITY * delta;
			break;
		case SHOOT:
			screen.createBullet(hitBox.x + texture.getWidth() / 2, hitBox.y + texture.getHeight(), 1);
			break;
		}
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer renderer) {
		batch.draw(texture, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}
}