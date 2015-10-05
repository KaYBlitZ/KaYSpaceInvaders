package com.kayblitz.spaceinvaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bullet extends Entity {
	
	private static final float VELOCITY = 300f;
	private int direction;
	private Texture texture;
	
	public Bullet(float x, float y, Texture texture, int direction) {
		super(x, y, texture.getWidth(), texture.getHeight());
		this.texture = texture;
		this.direction = direction;
	}

	@Override
	public void update(float delta) {
		hitBox.y += VELOCITY * delta * direction;
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer renderer) {
		batch.draw(texture, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}
	
	public int getDirection() {
		return direction;
	}
}