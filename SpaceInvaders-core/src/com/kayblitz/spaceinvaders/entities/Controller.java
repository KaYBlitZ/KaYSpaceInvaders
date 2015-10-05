package com.kayblitz.spaceinvaders.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.kayblitz.spaceinvaders.Action;
import com.kayblitz.spaceinvaders.resources.Resources;
import com.kayblitz.spaceinvaders.screens.GameScreen;

public class Controller extends Entity {
	private static final float VELOCITY = 20f;
	
	private GameScreen screen;
	private Texture texture;
	private Texture damagedTextures[];
	
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
			//screen.createBullet(hitBox.x + texture.getWidth() / 2, hitBox.y + texture.getHeight(), 1);
			break;
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
}