package com.kayblitz.spaceinvaders.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.kayblitz.spaceinvaders.SpaceInvaders;

public abstract class Entity {
	protected Rectangle hitBox;
	
	public Entity(float x, float y, float width, float height) {
		hitBox = new Rectangle(x, y, width * SpaceInvaders.RESOURCES_SCALE, height * SpaceInvaders.RESOURCES_SCALE);
	}
	
	public boolean checkCollision(Entity entity) {
		return hitBox.overlaps(entity.hitBox);
	}
	
	public abstract void update(float delta);
	public abstract void render(SpriteBatch batch, ShapeRenderer renderer);
	
	public float getX() {
		return hitBox.x;
	}
	
	public float getY() {
		return hitBox.y;
	}
	
	public float getWidth() {
		return hitBox.width;
	}
	
	public float getHeight() {
		return hitBox.height;
	}
}