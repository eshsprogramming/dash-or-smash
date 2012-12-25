package com.eshsprogramming.nudistrailroadexhibition;

import com.badlogic.gdx.Game;
import com.eshsprogramming.nudistrailroadexhibition.screens.GameScreen;
import com.eshsprogramming.nudistrailroadexhibition.screens.MainMenuScreen;

/**
 * The entry point of Nudist Railroad Exhibition.
 *
 * @author Zachary Latta
 */
public class NudistRailroadExhibition extends Game
{
	@Override
	public void create()
	{
		setScreen(new MainMenuScreen());
	}
}
