package com.eshsprogramming.dash_or_smash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.model.world.MainMenuWorld;
import com.eshsprogramming.dash_or_smash.screens.MainMenuScreen;

/**
 * The controller for the main menu. Handles input.
 *
 * @author Zachary Latta
 */
public class MainMenuController extends Controller
{
	/**
	 * The main menu.
	 */
	private MainMenuWorld mainMenuWorld = null;
	/**
	 * The main menu screen.
	 */
	private MainMenuScreen mainMenuScreen = null;

    /**
     * Played when the user selects something.
     */
    private Sound selectSound = null;

	/**
	 * Creates a new main menu controller.
	 *
	 * @param mainMenuWorld The main menu to use with the main menu controller
	 */
	public MainMenuController(MainMenuWorld mainMenuWorld, MainMenuScreen mainMenuScreen, DashOrSmash game)
	{
		super(game);

		this.mainMenuWorld = mainMenuWorld;
		this.mainMenuScreen = mainMenuScreen;
        this.selectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/effects/select.wav"));

		// Sets touch position to (0, 0) to avoid NullPointerException
		this.setTouchPosition(new Vector2(0, 0));
	}

	/**
	 * The main update method. Called each frame.
	 *
	 * @param delta Time in milliseconds between frames.
	 */
	public void update(float delta)
	{
		if(mainMenuWorld.getPlayText().touches(getTouchPosition(), mainMenuScreen.getWidth(),
				mainMenuScreen.getHeight()))
		{
            selectSound.play();

			getGame().setScreen(getGame().gameScreen);
		}
	}
}