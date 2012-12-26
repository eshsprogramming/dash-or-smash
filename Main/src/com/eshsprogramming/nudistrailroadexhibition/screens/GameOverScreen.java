package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.model.GameOver;
import com.eshsprogramming.nudistrailroadexhibition.view.GameOverRenderer;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;


/**
 * @author Benjamin Landers
 */
public class GameOverScreen implements Screen, InputProcessor
{
	/**
	 * a reference to the game   (btw this is a pun)
	 */
	private NudistRailroadExhibition game = null;
	/**
	 * The width of the world in pixels.
	 */
	private float width;
	/**
	 * The height of the world in pixels.
	 */
	private float height;
	/**
	 * the renderer
	 */
	private GameOverRenderer renderer = null;
	/**
	 * the score as an integer
	 */
	private int score = 0;
	/**
	 *  the publicly accesable constructor
	 * @param game a reference to the main game
	 */
	public GameOverScreen(NudistRailroadExhibition game)
	{
		this.game = game;
	}
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderer.render(delta);
	//	controller.update(delta);
	}
	public void resize(int width, int height)
	{
		renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	/**
	 * Called when this screen becomes the current screen for the game.
	 */
	@Override
	public void show()
	{
		renderer = new GameOverRenderer(new GameOver(this,score));


		Gdx.input.setInputProcessor(this);
	}

	/**
	 * Called when this screen is no longer the current screen for the game.
	 */
	@Override
	public void hide()
	{
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void dispose()
	{
		Gdx.input.setInputProcessor(null);
	}

	////////////////////////////////
	//   InputProcessor Methods   //
	////////////////////////////////

	@Override
	public boolean keyDown(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		float touchX = screenX;
		float touchY = height - screenY;

		float x = (touchX / width) * WorldRenderer.CAMERA_WIDTH;
		float y = (touchY / height) * WorldRenderer.CAMERA_HEIGHT;

		//controller.setTouchPosition(new Vector2(x - Nudist.SIZEX / 2, y));

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
	/**
	 * Returns the width in pixels of the screen.
	 *
	 * @return The width in pixels of the screen.
	 */
	public float getWidth()
	{
		return width;
	}

	/**
	 * Returns the height in pixels of the screen.
	 * @return The height in pixels of the screen.
	 */
	public float getHeight()
	{
		return height;
	}
}
