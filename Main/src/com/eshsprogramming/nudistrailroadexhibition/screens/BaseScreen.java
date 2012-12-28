package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.eshsprogramming.nudistrailroadexhibition.DashOrSmash;
import com.eshsprogramming.nudistrailroadexhibition.controller.Controller;
import com.eshsprogramming.nudistrailroadexhibition.model.world.World;
import com.eshsprogramming.nudistrailroadexhibition.view.Renderer;

/**
 * The base class for screens in the game. Examples of screens
 * include the main menu, a pause menu, and a game over screen.
 *
 * @author Benjamin Landers
 */
public class BaseScreen implements Screen, InputProcessor
{
	/**
	 * A reference to the game
	 */
	private DashOrSmash game = null;

	/**
	 * Handles the rendering for the screen.
	 */
	protected Renderer renderer = null;
	/**
	 * Handles updating for the screen.
	 */
	protected Controller controller = null;
	/**
	 * The world for the controller and renderer to utilize.
	 */
	protected World world = null;

	/**
	 * The width of the world in pixels.
	 */
	private float width;
	/**
	 * The height of the world in pixels.
	 */
	private float height;

	/**
	 * Creates a new game screen.
	 *
	 * @param game The game that the screen is in.
	 */
	public BaseScreen(DashOrSmash game)
	{
		this.game = game;
	}

	/**
	 * Called each method to render and update the screen.
	 *
	 * @param delta The time in milliseconds between frames.
	 */
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderer.render(delta);
		controller.update(delta);
	}

	/**
	 * Called when the window is resized.
	 *
	 * @param width  The new width of the game.
	 * @param height The new width of the game
	 */
	public void resize(int width, int height)
	{
		this.width = width;
		this.height = height;

		renderer.setSize(width, height);
	}

	/**
	 * Called when this world becomes the current world for the game.
	 */
	@Override
	public void show()
	{
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();

		Gdx.input.setInputProcessor(this);
	}

	/**
	 * Called when this world is no longer the current world for the game.
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
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

	/**
	 * Returns the width in pixels of the world.
	 *
	 * @return The width in pixels of the world.
	 */
	public float getWidth()
	{
		return width;
	}

	/**
	 * Returns the height in pixels of the world.
	 *
	 * @return The height in pixels of the world.
	 */
	public float getHeight()
	{
		return height;
	}

	/**
	 * Returns the game itself.
	 *
	 * @return The game instance itself.
	 */
	public DashOrSmash getGame()
	{
		return game;
	}

	/**
	 * Returns the renderer for the screen.
	 *
	 * @return The renderer for the screen.
	 */
	public Renderer getRenderer()
	{
		return renderer;
	}

	/**
	 * Returns the controller of the screen.
	 *
	 * @return The controller of the screen.
	 */
	public Controller getController()
	{
		return controller;
	}

	/**
	 * Returns the world of the screen.
	 *
	 * @return The world of the screen.
	 */
	public World getWorld()
	{
		return world;
	}
}
