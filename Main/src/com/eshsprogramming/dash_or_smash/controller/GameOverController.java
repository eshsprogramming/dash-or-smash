package com.eshsprogramming.dash_or_smash.controller;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.view.GameOverRenderer;

/**
 * Controller for the game over screen. Handles input and potentially other changes that occur.
 *
 * @author Benjamin Landers, Zachary Latta
 */
public class GameOverController extends Controller
{
	/**
	 * Creates a new GameOverController.
	 *
	 * @param game A reference to the actual Game instance
	 */
	public GameOverController(DashOrSmash game)
	{
		super(game);

		setTouchPosition(new Vector2(0, 0)); // to avoid a null pointer exception
	}

	/**
	 * The main update method. Called each frame.
	 *
	 * @param delta time in milliseconds between frames.
	 */
	public void update(float delta)
	{
		if(((GameOverRenderer)getGame().gameOverScreen.getRenderer()).getWorld().getMainMenuText()
				.touches(getTouchPosition(), getGame().gameOverScreen.getWidth(), getGame().gameOverScreen.getHeight()))
		{
			getGame().setScreen(getGame().mainMenuScreen);
		}

		if(((GameOverRenderer)getGame().gameOverScreen.getRenderer()).getWorld().getPlayAgainText()
				.touches(getTouchPosition(), getGame().gameOverScreen.getWidth(), getGame().gameOverScreen.getHeight()))
		{
			getGame().setScreen(getGame().gameScreen);
		}
	}
}
