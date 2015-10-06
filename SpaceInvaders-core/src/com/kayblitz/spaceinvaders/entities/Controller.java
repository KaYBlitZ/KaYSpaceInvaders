package com.kayblitz.spaceinvaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kayblitz.spaceinvaders.SpaceInvaders;
import com.kayblitz.spaceinvaders.resources.Resources;
import com.kayblitz.spaceinvaders.screens.GameScreen;

public class Controller extends Entity {
	private static final float VELOCITY = 200f;
	private static final long SHOOT_DELAY = 1250L; // 1.25 sec delay
	
	private GameScreen screen;
	private Texture texture;
	private Texture damagedTextures[];
	private boolean left, right, shoot, canShoot;
	// canShoot prevents holding down space & shooting
	private long lastShotTime;
	
	public Controller(float x, float y, Resources resources, GameScreen screen) {
		super(x, y, 
				resources.getTexture("playerShip1_orange.png").getWidth(), 
				resources.getTexture("playerShip1_orange.png").getHeight());
		this.screen = screen;
		this.texture = resources.getTexture("playerShip1_orange.png");
		
		damagedTextures = new Texture[3];
		damagedTextures[0] = resources.getTexture("playerShip1_damage1.png");
		damagedTextures[1] = resources.getTexture("playerShip1_damage2.png");
		damagedTextures[2] = resources.getTexture("playerShip1_damage3.png");
		
		canShoot = true;
	}

	@Override
	public void update(float delta) {
		if (left) {
			hitBox.x -= VELOCITY * delta;
			if (hitBox.x < 0f) {
				hitBox.x = 0f;
			}
		}
		if (right) {
			hitBox.x += VELOCITY * delta;
			if (hitBox.x + hitBox.width > SpaceInvaders.WIDTH) {
				hitBox.x = SpaceInvaders.WIDTH - hitBox.width;
			}
		}
		if (shoot && canShoot && System.currentTimeMillis() - lastShotTime > SHOOT_DELAY) {
			screen.createBullet(hitBox.x + hitBox.width / 2, hitBox.y, 1);
			canShoot = false;
			lastShotTime = System.currentTimeMillis();
		}
	}
	
	@Override
	public void render(SpriteBatch batch, ShapeRenderer renderer) {}
	
	public void render(SpriteBatch batch, ShapeRenderer renderer, int lives) {
		batch.draw(texture, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		
		if (lives == 2) {
			batch.draw(damagedTextures[0], hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		} else if (lives == 1) {
			batch.draw(damagedTextures[1], hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		} else if (lives == 0) {
			batch.draw(damagedTextures[2], hitBox.x, hitBox.y, hitBox.width, hitBox.height);
		}
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}
	
	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}
}