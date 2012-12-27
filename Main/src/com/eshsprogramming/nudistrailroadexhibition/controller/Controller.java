package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;

/**
 * Base class for the controllers
 * @author Benjamin Landers
 */
public class Controller
{
	/**
	 * A ref to the main game
	 */
	private NudistRailroadExhibition game = null;
	/**
	 * The position of the user's touch.
	 */
	private Vector2 touchPosition = null;
	/**
	 * default constructor
	 */
	public Controller(NudistRailroadExhibition game)
	{
		 this.game = game;
	}
	/**
	 * getter for touch position
	 * @return the touch position
	 */
	public Vector2 getTouchPosition()
	{
		return touchPosition;
	}

	/**
	 *  setter for touch position
	 * @param newTouch the new touch position
	 */
	public void setTouchPosition(Vector2 newTouch)
	{
		touchPosition = newTouch;
	}
	public NudistRailroadExhibition getGame()
	{
		return game;
	}
}
