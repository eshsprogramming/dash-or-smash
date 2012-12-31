package com.eshsprogramming.dash_or_smash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.view.NewHighScoreRenderer;

/**
 * @author Zachary Latta
 */
public class NewHighScoreController extends Controller
{
	/**
	 * Played when the user selects something.
	 */
	private Sound selectSound = null;

	/**
	 * Creates a new NewHighScoreController.
	 *
	 * @param game A reference to the game's starting class
	 */
	public NewHighScoreController(DashOrSmash game)
	{
		super(game);

		this.selectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/effects/select.wav"));

		setTouchPosition(new Vector2(0, 0));	// To avoid a null pointer exception
	}

	/**
	 * The main update method. Called each frame.
	 *
	 * @param delta Time in milliseconds between frames.
	 */
	public void update(float delta)
	{
		if(((NewHighScoreRenderer)getGame().newHighScoreScreen.getRenderer()).getWorld().getMainMenuText()
				.touches(getTouchPosition(), getGame().newHighScoreScreen.getWidth(),
						getGame().newHighScoreScreen.getHeight()))
		{
			selectSound.play();

			getGame().setScreen(getGame().mainMenuScreen);
		}

		if(((NewHighScoreRenderer)getGame().newHighScoreScreen.getRenderer()).getWorld().getPlayAgainText()
				.touches(getTouchPosition(), getGame().newHighScoreScreen.getWidth(),
						getGame().newHighScoreScreen.getHeight()))
		{
			selectSound.play();

			getGame().setScreen(getGame().gameScreen);
		}
	}
}
