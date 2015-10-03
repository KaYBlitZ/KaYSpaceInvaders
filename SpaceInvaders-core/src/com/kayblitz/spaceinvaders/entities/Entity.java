package com.kayblitz.spaceinvaders.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
	protected Rectangle hitBox;
	
	public Entity(float x, float y, float width, float height) {
		hitBox = new Rectangle(x, y, width, height);
	}
	
	public boolean checkCollision(Entity entity) {
		return hitBox.overlaps(entity.hitBox);
	}
	
	public abstract void update(float delta);
	public abstract void render(SpriteBatch batch, ShapeRenderer renderer);
}