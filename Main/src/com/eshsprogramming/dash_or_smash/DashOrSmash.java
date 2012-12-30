package com.eshsprogramming.dash_or_smash;

import com.badlogic.gdx.Game;
import com.eshsprogramming.dash_or_smash.screens.GameOverScreen;
import com.eshsprogramming.dash_or_smash.screens.GameScreen;
import com.eshsprogramming.dash_or_smash.screens.HighScoreScreen;
import com.eshsprogramming.dash_or_smash.screens.MainMenuScreen;

/**
 * The entry point of Dash Or Smash.
 *
 * @author Zachary Latta
 */
public class DashOrSmash extends Game
{
	/**
	 * The main menu screen.
	 */
	public MainMenuScreen mainMenuScreen = null;
	/**
	 * The high score screen.
	 */
	public HighScoreScreen highScoreScreen = null;
	/**
	 * The game screen.
	 */
	public GameScreen gameScreen = null;
	/**
	 * The game over screen.
	 */
	public GameOverScreen gameOverScreen = null;

	@Override
	public void create()
	{
		mainMenuScreen = new MainMenuScreen(this);
		highScoreScreen = new HighScoreScreen(this);
		gameScreen = new GameScreen(this);
		gameOverScreen = new GameOverScreen(this);

		setScreen(mainMenuScreen);
	}
}
