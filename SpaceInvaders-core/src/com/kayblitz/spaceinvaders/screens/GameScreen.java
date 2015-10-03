package com.kayblitz.spaceinvaders.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.kayblitz.spaceinvaders.Action;
import com.kayblitz.spaceinvaders.SpaceInvaders;
import com.kayblitz.spaceinvaders.entities.Bullet;
import com.kayblitz.spaceinvaders.entities.Controller;
import com.kayblitz.spaceinvaders.entities.Enemy;
import com.kayblitz.spaceinvaders.entities.Entity;

public class GameScreen implements UpdateableScreen {
	
	private SpaceInvaders game;
	private SpriteBatch batch;
	private ShapeRenderer renderer;
	
	private Controller controller;
	private Array<Enemy> enemies;
	private Array<Bullet> bullets;
	private int enemiesDefeated;
	private float time;
	private boolean isGameOver;
	private int lives;
	
	public GameScreen(SpaceInvaders game) {
		this.game = game;
		batch = game.getSpriteBatch();
		renderer = game.getShapeRenderer();
		enemies = new Array<Enemy>(false, 50);
		bullets = new Array<Bullet>(false, 50);
		
		enemiesDefeated = 0;
		time = 0f;
		lives = 3;
		isGameOver = false;
		Enemy.velocity = 10f;
	}
	
	@Override
	public void show() {}
	
	@Override
	public void update(float delta) {
		if (!isGameOver) {
			time += delta;
			
			if (time > 30f) {
				Enemy.velocity = 20f;
			} else if (time > 15f) {
				Enemy.velocity = 15f;
			}
			
			for (Entity enemy : enemies) {
				enemy.update(delta);
			}
			for (Bullet bullet : bullets) {
				bullet.update(delta);
			}
			
			if (Enemy.changeDirection) {
				Enemy.direction *= -1;
				Enemy.changeDirection = false;
				for (Enemy enemy : enemies) {
					enemy.moveDown();
				}
			}
			
			controller.update(delta, enemies, bullets);
			
			checkCollision();
		}
	}

	@Override
	public void render(float delta) {
		if (!isGameOver) {
			for (Enemy enemy : enemies) {
				enemy.render(batch, renderer);
			}
			for (Bullet bullet : bullets) {
				bullet.render(batch, renderer);
			}
		} else {
			
		}
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
	
	public void checkCollision() {
		for (int i = 0; i < enemies.size; i++) {
			Entity enemy = enemies.get(i);
			
			for (int j = 0; j < bullets.size; j++) {
				Bullet bullet = bullets.get(j);
				
				if (enemy.checkCollision(bullet)) {
					enemiesDefeated++;
					enemies.removeIndex(i);
					i--; // array gets compressed after removal
					// don't skip over replacement entity at this index
					bullets.removeIndex(j);
					j--; // don't skip over replacement
				} else if (controller.checkCollision(bullet)) {
					bullets.removeIndex(j);
					j--;
					if (--lives == 0) {
						isGameOver = true;
					}
				}
			}
		}
	}
	
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	public void createBullet(float x, float y, int direction) {
		bullets.add(new Bullet(x, y, direction));
	}
}