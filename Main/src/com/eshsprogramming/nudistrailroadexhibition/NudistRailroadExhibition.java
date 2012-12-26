package com.eshsprogramming.nudistrailroadexhibition;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.screens.GameScreen;
import com.eshsprogramming.nudistrailroadexhibition.screens.MainMenuScreen;

import java.util.Map;

/**
 * The entry point of Nudist Railroad Exhibition.
 *
 * @author Zachary Latta
 */
public class NudistRailroadExhibition extends Game
{
    /**
     * The main menu screen.
     */
    public MainMenuScreen mainMenuScreen = null;
    /**
     * The game screen.
     */
    public GameScreen gameScreen = null;

	@Override
	public void create()
	{
        mainMenuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);

		setScreen(mainMenuScreen);
	}
}
