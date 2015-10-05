package com.kayblitz.spaceinvaders.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.kayblitz.spaceinvaders.SpaceInvaders;

public class MainMenuScreen implements InputProcessor, UpdateableScreen {
	
	private SpaceInvaders game;
	private SpriteBatch batch;
	private Rectangle rStart;
	private BitmapFont font;
	private GlyphLayout layout;
	private OrthographicCamera camera;
	
	public MainMenuScreen(SpaceInvaders game) {
		this.game = game;
		batch = game.getSpriteBatch();
		font = game.getResources().getFont("arial_32.fnt");
		camera = game.getOrthographicCamera();
		
		layout = new GlyphLayout();
		layout.setText(font, "Start");
		
		// specified x y is bottom left
		rStart = new Rectangle(
				SpaceInvaders.WIDTH / 2 - layout.width / 2, 
				200f,
				layout.width,
				layout.height);
	}

	@Override
	public void show() {
		font.setColor(Color.WHITE);
		
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void update(float delta) {}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		// specified x, y is top left
		layout.setText(font, "Start");
		font.draw(batch, layout, rStart.x, rStart.y + rStart.height);
		
		String text = "KaY Space Invaders\nCreated by: Kenneth Liang";
		layout.setText(font, text, 0, text.length(), font.getColor(), 0, Align.center, false, null);
		font.draw(batch, layout, SpaceInvaders.WIDTH / 2, 700f);
		
		text = "Powered by libGDX";
		layout.setText(font, text, 0, text.length(), font.getColor(), 0, Align.center, false, null);
		font.draw(batch, layout, SpaceInvaders.WIDTH / 2, 500f);
		
		text = "Assets by Kenney @ www.kenney.nl";
		layout.setText(font, text, 0, text.length(), font.getColor(), 0, Align.center, false, null);
		font.draw(batch, layout, SpaceInvaders.WIDTH / 2, 400f);
		
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 touchPos = new Vector3(screenX, screenY, 0f);
		camera.unproject(touchPos);
		
		//System.out.println(String.format("(%.3f, %.3f", touchPos.x, touchPos.y));
		
		if (rStart.contains(touchPos.x, touchPos.y)) {
			game.setScreen(new GameScreen(game));
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