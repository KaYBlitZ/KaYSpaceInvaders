package com.kayblitz.spaceinvaders.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kayblitz.spaceinvaders.SpaceInvaders;
import com.kayblitz.spaceinvaders.screens.GameScreen;

public class Enemy extends Entity {	
	public static final float MOVE_DOWN_AMOUNT = 20f;
	public static final float THRESHOLD = 50f;
	
	public static boolean changeDirection = false; // whether to change direction after updating all enemies
	public static int direction = 1;
	public static float velocity = 10f;
	public static Random rand = new Random();
	
	private Texture texture;
	private GameScreen screen;

	public Enemy(float x, float y, Texture texture, GameScreen screen) {
		super(x, y, texture.getWidth(), texture.getHeight());
	}

	@Override
	public void update(float delta) {
		hitBox.x += velocity * delta * direction;
		if (hitBox.x + hitBox.width > SpaceInvaders.WIDTH || hitBox.x < 0f) {
			changeDirection = true;
		}
		if (rand.nextInt(10) < 2) {
			shoot();
		}
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer renderer) {
		batch.draw(texture, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}
	
	public void moveDown() {
		hitBox.y -= MOVE_DOWN_AMOUNT;
		if (hitBox.y < THRESHOLD) {
			screen.setGameOver(true);
		}
	}
	
	public void shoot() {
		screen.createBullet(hitBox.x, hitBox.y, -1);
	}
}
