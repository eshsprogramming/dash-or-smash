package com.eshsprogramming.dash_or_smash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.controller.GameController;
import com.eshsprogramming.dash_or_smash.model.entity.PedestrianEntity;
import com.eshsprogramming.dash_or_smash.model.world.GameWorld;
import com.eshsprogramming.dash_or_smash.view.GameRenderer;

/**
 * The screen that contains the gameplay.
 *
 * @author Zachary Latta, Benjamin Landers
 */
public class GameScreen extends BaseScreen
{
	/**
	 * The game's world.
	 */
	private GameWorld gameWorld;

	/**
	 * The background music.
	 */
	private Music music;

	/**
	 * Creates a new game screen
	 *
	 * @param game The instance of Game.
	 */
	public GameScreen(DashOrSmash game)
	{
		super(game);
	}

	/**
	 * Called each frame to render and update the game.
	 *
	 * @param delta The time in milliseconds between frames.
	 */
	@Override
	public void render(float delta)
	{
		super.render(delta);
	}

	/**
	 * Called when this screen becomes the current screen for the game.
	 */
	@Override
	public void show()
	{
		super.show();
		gameWorld = new GameWorld(getGame());
		renderer = new GameRenderer(gameWorld, false);
		controller = new GameController(gameWorld, getGame());
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/soundtrack/game.mp3"));

		music.setLooping(true);
		music.setVolume(0.7f);
		music.play();
	}

	/**
	 * Called when this screen is no longer the current screen for the game.
	 */
	@Override
	public void hide()
	{
		music.setLooping(false);
		music.stop();

		super.hide();
	}

	@Override
	public void dispose()
	{
		music.setLooping(false);
		music.stop();

		super.dispose();
	}

	////////////////////////////////
	//   InputProcessor Methods   //
	////////////////////////////////
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		float touchX = screenX;
		float touchY = getHeight() - screenY;

		float x = (touchX / getWidth()) * GameRenderer.CAMERA_WIDTH;
		float y = (touchY / getHeight()) * GameRenderer.CAMERA_HEIGHT;

		for(int index = 0; index < gameWorld.getPedestrianEntities().size; index++)
		{
			PedestrianEntity pedestrianEntity = gameWorld.getPedestrianEntities().get(index);

			// If the touch is on a pedestrianEntity
			if(x > pedestrianEntity.getPosition().x && x < pedestrianEntity.getPosition().x + pedestrianEntity.getBounds().width &&
					y > pedestrianEntity.getPosition().y && y < pedestrianEntity.getPosition().y + pedestrianEntity.getBounds().height)
			{
				if(index != 0)
				{
					// Swap the pedestrianEntity into index 0 of the array
					gameWorld.getPedestrianEntities().swap(0, index);
				}

				((GameController)controller).setSelected(true);
				break;
			}

		}

		controller.setTouchPosition(new Vector2(x - PedestrianEntity.SIZEX / 2, y));

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
		float touchY = getHeight() - screenY;

		float x = (touchX / getWidth()) * GameRenderer.CAMERA_WIDTH;
		float y = (touchY / getHeight()) * GameRenderer.CAMERA_HEIGHT;

		controller.setTouchPosition(new Vector2(x - PedestrianEntity.SIZEX / 2, y));

		return true;
	}
}
