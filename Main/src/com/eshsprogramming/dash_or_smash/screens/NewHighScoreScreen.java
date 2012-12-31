package com.eshsprogramming.dash_or_smash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.controller.NewHighScoreController;
import com.eshsprogramming.dash_or_smash.model.world.HighScoreWorld;
import com.eshsprogramming.dash_or_smash.model.world.NewHighScoreWorld;
import com.eshsprogramming.dash_or_smash.view.GameRenderer;
import com.eshsprogramming.dash_or_smash.view.NewHighScoreRenderer;

/**
 * The new high score screen. Displayed when the user gets a new high score.
 *
 * @author Zachary Latta
 */
public class NewHighScoreScreen extends BaseScreen
{
	/**
	 * The score as an integer.
	 */
	private int score = 0;

	/**
	 * The background music for this screen.
	 */
	private Music music = null;

	/**
	 * Creates a new new high score screen.
	 *
	 * @param game The game that the screen is in.
	 */
	public NewHighScoreScreen(DashOrSmash game)
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

		renderer = new NewHighScoreRenderer(new NewHighScoreWorld(this, score));
		controller = new NewHighScoreController(game);

		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/soundtrack/new_high_score.mp3"));

		music.setLooping(true);
		music.setVolume(0.7f);
		music.play();
	}

	/**
	 * Called when this screen is no longer the current screen.
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
	 * Sets the value of the score to the value provided.
	 *
	 * @param score The new score value.
	 */
	public void setScore(int score)
	{
		this.score = score;
	}
}
