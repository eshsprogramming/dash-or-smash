package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;

/**
 * Base class for a controller
 *
 * @author Benjamin Landers
 */
public class Controller
{
    /**
     * A reference to the actual Game instance
     */
    private NudistRailroadExhibition game = null;
    /**
     * The position of the user's touch.
     */
    private Vector2 touchPosition = null;

    /**
     * Default constructor
     */
    public Controller(NudistRailroadExhibition game)
    {
        this.game = game;
    }

    /**
     * Returns the touch position.
     *
     * @return The touch position.
     */
    public Vector2 getTouchPosition()
    {
        return touchPosition;
    }

    /**
     * Sets the touch position to the new touch position passed.
     *
     * @param touchPosition the new touch position
     */
    public void setTouchPosition(Vector2 touchPosition)
    {
        this.touchPosition = touchPosition;
    }

    public NudistRailroadExhibition getGame()
    {
        return game;
    }
}
