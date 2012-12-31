package com.eshsprogramming.dash_or_smash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.controller.HighScoreController;
import com.eshsprogramming.dash_or_smash.model.world.HighScoreWorld;
import com.eshsprogramming.dash_or_smash.view.GameRenderer;
import com.eshsprogramming.dash_or_smash.view.HighScoreRenderer;

/**
 * The high score screen. Display high scores to the user.
 *
 * @author Zachary Latta
 */
public class HighScoreScreen extends BaseScreen
{
	/**
	 * The high score's world.
	 */
	private HighScoreWorld highScoreWorld;

	/**
	 * The background music for this screen.
	 */
	private Music music;

	/**
	 * Creates a new high score screen.
	 *
	 * @param game The game that the screen is in.
	 */
	public HighScoreScreen(DashOrSmash game)
	{
		super(game);
	}

	/**
	 * Called when this screen becomes the current screen.
	 */
	@Override
	public void show()
	{
		super.show();

		highScoreWorld = new HighScoreWorld(this);
		renderer = new HighScoreRenderer(highScoreWorld);
		controller = new HighScoreController(getGame());

		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/soundtrack/high_score.mp3"));

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

	/**
	 * Returns the high score world.
	 *
	 * @return The high score world.
	 */
	public HighScoreWorld getHighScoreWorld()
	{
		return highScoreWorld;
	}
}
