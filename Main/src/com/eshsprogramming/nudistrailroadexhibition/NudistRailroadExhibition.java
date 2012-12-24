package com.eshsprogramming.nudistrailroadexhibition;

import com.badlogic.gdx.Game;
import com.eshsprogramming.nudistrailroadexhibition.screens.GameScreen;

/**
 * @author Zachary Latta
 */
public class NudistRailroadExhibition extends Game
{
	@Override
	public void create()
	{
		setScreen(new GameScreen());
	}
}
