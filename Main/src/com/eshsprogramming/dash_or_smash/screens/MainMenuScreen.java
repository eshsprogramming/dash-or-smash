package com.eshsprogramming.dash_or_smash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.controller.MainMenuController;
import com.eshsprogramming.dash_or_smash.model.world.MainMenuWorld;
import com.eshsprogramming.dash_or_smash.view.GameRenderer;
import com.eshsprogramming.dash_or_smash.view.MainMenuRenderer;

/**
 * The main menu of the game.
 *
 * @author Zachary Latta, Benjamin Landers
 */
public class MainMenuScreen extends BaseScreen
{
	/**
	 * The main menu's world.
	 */
	private MainMenuWorld mainMenuWorld;

	/**
	 * The background music for this screen.
	 */
	private Music music;

	/**
	 * Creates a new main menu screen.
	 *
	 * @param game The instance of Game
	 */
	public MainMenuScreen(DashOrSmash game)
	{
		super(game);
	}

	/**
	 * Called each frame. Calls necessary methods to render and update the screen.
	 *
	 * @param delta The time in milliseconds between frames.
	 */
	@Override
	public void render(float delta)
	{
		super.render(delta);
	}

	/**
	 * Called when this screen becomes the current screen.
	 */
	@Override
	public void show()
	{
		super.show();

		mainMenuWorld = new MainMenuWorld(this);
		renderer = new MainMenuRenderer(mainMenuWorld);
		controller = new MainMenuController(mainMenuWorld, this, getGame());

		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/soundtrack/main_menu.mp3"));

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

		controller.setTouchPosition(new Vector2(x, y));

		return true;
	}
}
