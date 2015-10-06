package com.kayblitz.spaceinvaders.screens;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.kayblitz.spaceinvaders.SpaceInvaders;
import com.kayblitz.spaceinvaders.entities.Bullet;
import com.kayblitz.spaceinvaders.entities.Controller;
import com.kayblitz.spaceinvaders.entities.Enemy;
import com.kayblitz.spaceinvaders.entities.Entity;
import com.kayblitz.spaceinvaders.resources.Resources;

public class GameScreen implements InputProcessor, UpdateableScreen {
	
	private static final int ENEMY_LENGTH = 7;
	private static final int ENEMY_HEIGHT = 5;
	private static final int ENEMY_DISTANCE_X = 70;
	private static final int ENEMY_DISTANCE_Y = 50;
	private static final int ENEMY_START_Y = SpaceInvaders.HEIGHT - 100;
	
	private SpaceInvaders game;
	private SpriteBatch batch;
	private ShapeRenderer renderer;
	private Resources resources;
	
	private Controller controller;
	private Array<Enemy> enemies;
	private Array<Bullet> bullets;
	private BitmapFont font;
	private GlyphLayout layout;
	private int enemiesDefeated;
	private float time;
	private boolean isGameOver;
	private int lives;
	
	public GameScreen(SpaceInvaders game) {
		this.game = game;
		batch = game.getSpriteBatch();
		renderer = game.getShapeRenderer();
		resources = game.getResources();
		font = resources.getFont("arial_32.fnt");
		layout = new GlyphLayout();
		
		Random random = new Random();
		enemies = new Array<Enemy>(false, 50);
		
		for (int i = 0; i < ENEMY_LENGTH; i++) {
			for (int j = 0; j < ENEMY_HEIGHT; j++) {
				Texture texture = null;
				switch(random.nextInt(4)) {
				case 0:
					texture = resources.getTexture("enemyBlack2.png");
					break;
				case 1:
					texture = resources.getTexture("enemyBlue2.png");
					break;
				case 2:
					texture = resources.getTexture("enemyGreen2.png");
					break;
				case 3:
					texture = resources.getTexture("enemyRed2.png");
					break;
				}
				enemies.add(new Enemy(i * ENEMY_DISTANCE_X, ENEMY_START_Y - j * ENEMY_DISTANCE_Y, texture, this));
			}
		}
		
		bullets = new Array<Bullet>(false, 50);
		
		controller = new Controller(SpaceInvaders.WIDTH / 2, 0, resources, this);
		
		enemiesDefeated = 0;
		time = 0f;
		lives = 3;
		isGameOver = false;
		Enemy.velocity = Enemy.INITIAL_VELOCITY;
	}
	
	@Override
	public void show() {
		font.setColor(Color.WHITE);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void update(float delta) {
		if (!isGameOver) {
			time += delta;
			
			if (time > 35f) {
				Enemy.velocity = 150f;
			} else if (time > 20f) {
				Enemy.velocity = 100f;
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
			
			controller.update(delta);
			
			checkCollision();
			checkBullets();
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		for (Bullet bullet : bullets) {
			bullet.render(batch, renderer);
		}
		for (Enemy enemy : enemies) {
			enemy.render(batch, renderer);
		}
		controller.render(batch, renderer, lives);
		
		layout.setText(font, String.format("Lives: %d", lives));
		font.draw(batch, layout, 0f, SpaceInvaders.HEIGHT);
		float livesHeight = layout.height;
		layout.setText(font, String.format("Enemes Defeated: %d", enemiesDefeated));
		font.draw(batch, layout, 0f, SpaceInvaders.HEIGHT - livesHeight);
		layout.setText(font, String.format("Time: %.3f", time));
		font.draw(batch, layout, SpaceInvaders.WIDTH - layout.width, SpaceInvaders.HEIGHT);
		
		if (isGameOver) {
			layout.setText(font, "Game Over");
			font.draw(batch, layout, SpaceInvaders.WIDTH / 2 - layout.width / 2, SpaceInvaders.HEIGHT / 2 + layout.height / 2);
		}
		batch.end();
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
		Iterator<Bullet> bulletIterator = bullets.iterator();
		while (bulletIterator.hasNext()) {
			Bullet bullet = bulletIterator.next();
			
			Iterator<Enemy> enemyIterator = enemies.iterator();
			while (enemyIterator.hasNext()) {
				Enemy enemy = enemyIterator.next();
				
				if (bullet.getDirection() == 1 && enemy.checkCollision(bullet)) {
					enemyIterator.remove();
					bulletIterator.remove();
					if (++enemiesDefeated == ENEMY_LENGTH * ENEMY_HEIGHT) {
						isGameOver = true;
					}
					break;
				} else if (bullet.getDirection() == -1 && controller.checkCollision(bullet)) {
					bulletIterator.remove();
					if (--lives == 0) {
						isGameOver = true;
					}
					break;
				}
			}
		}
	}
	
	// check if a bullet is off the map and remove it
	public void checkBullets() {
		Iterator<Bullet> bulletIterator = bullets.iterator();
		while (bulletIterator.hasNext()) {
			Bullet bullet = bulletIterator.next();
			float y = bullet.getY() + bullet.getHeight();
			if (y < 0 || y > SpaceInvaders.HEIGHT) {
				bulletIterator.remove();
			}
		}
	}
	
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	public void createBullet(float x, float y, int direction) {
		if (direction == 1) { // player bullet
			bullets.add(new Bullet(x, y, resources.getTexture("laserRed07.png"), direction));
		} else { // enemy bullet
			bullets.add(new Bullet(x, y, resources.getTexture("laserBlue07.png"), direction));
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT) {
			controller.setLeft(true);
		} else if (keycode == Keys.RIGHT) {
			controller.setRight(true);
		} else if (keycode == Keys.SPACE) {
			controller.setShoot(true);
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT) {
			controller.setLeft(false);
		} else if (keycode == Keys.RIGHT) {
			controller.setRight(false);
		} else if (keycode == Keys.SPACE) {
			controller.setShoot(false);
			controller.setCanShoot(true);
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (isGameOver) {
			game.setScreen(new MainMenuScreen(game));
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}