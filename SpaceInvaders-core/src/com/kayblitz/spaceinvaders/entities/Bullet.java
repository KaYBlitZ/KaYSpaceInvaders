package com.kayblitz.spaceinvaders.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bullet extends Entity {
	
	private static final float VELOCITY = 10f;
	private static final int WIDTH = 10;
	private static final int HEIGHT = 20;
	private int direction;
	
	public Bullet(float x, float y, int direction) {
		super(x, y, WIDTH, HEIGHT);
		this.direction = direction;
	}

	@Override
	public void update(float delta) {
		hitBox.y += VELOCITY * delta * direction;
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer renderer) {
		renderer.rect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}
}
