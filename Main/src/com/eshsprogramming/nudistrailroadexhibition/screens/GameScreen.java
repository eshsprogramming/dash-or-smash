package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.eshsprogramming.nudistrailroadexhibition.model.World;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

/**
 * @author Zachary Latta
 */
public class GameScreen implements Screen
{
	private World world;
	private WorldRenderer renderer;

	private int width, height;

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderer.render();
	}

	@Override
	public void resize(int width, int height)
	{
		renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void show()
	{
		world = new World();
		renderer = new WorldRenderer(world, true);
	}

	@Override
	public void hide()
	{
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
	}
}
