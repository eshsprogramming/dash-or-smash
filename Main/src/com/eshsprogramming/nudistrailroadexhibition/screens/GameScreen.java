package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.controller.WorldController;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.World;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

/**
 * @author Zachary Latta
 */
public class GameScreen implements Screen, InputProcessor
{
	private World world;
	private WorldRenderer renderer;
	private WorldController controller;

	private Music backgroundMusic;

	private float width, height;

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		renderer.render();
		controller.update(delta);
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
		controller = new WorldController(world);
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundMusic.mp3"));

		backgroundMusic.setLooping(true);
		backgroundMusic.play();

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide()
	{
		backgroundMusic.setLooping(false);
		backgroundMusic.stop();

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

		float x = (touchX/width) * WorldRenderer.CAMERA_WIDTH;
		float y = (touchY/height) * WorldRenderer.CAMERA_HEIGHT;

		for(int index = 0; index < world.getNudists().size; index++)
		{
			Nudist nudist = world.getNudists().get(index);

			// If the touch is on a nudist
			if(x > nudist.getPosition().x && x < nudist.getPosition().x + nudist.getBounds().width &&
					y > nudist.getPosition().y && y < nudist.getPosition().y + nudist.getBounds().height)
			{
				if(index != 0)
				{
					// Swap the nudist into index 0 of the array
					world.getNudists().swap(0, index);
				}

				break;
			}
		}

		controller.setTouchPosition(new Vector2(x - Nudist.SIZE/2, y));

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
		float touchX = screenX;
		float touchY = height - screenY;

		float x = (touchX/width) * WorldRenderer.CAMERA_WIDTH;
		float y = (touchY/height) * WorldRenderer.CAMERA_HEIGHT;

		controller.setTouchPosition(new Vector2(x - Nudist.SIZE/2, y));

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
}
