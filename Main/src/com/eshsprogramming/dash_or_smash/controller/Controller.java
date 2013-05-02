package com.eshsprogramming.dash_or_smash.controller;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;

/**
 * Base class for a controller
 *
 * @author Benjamin Landers
 */
public abstract class Controller
{
    /**
     * A reference to the actual Game instance
     */
    private DashOrSmash game = null;
    /**
     * The position of the user's touch.
     */
    private Vector2 touchPosition = null;

    /**
     * Creates a new controller.
     *
     * @param game The game instance.
     */
    public Controller(DashOrSmash game)
    {
        this.game = game;
    }

    /**
     * Called each frame. Should be used to update things on screen.
     *
     * @param delta The time in milliseconds between frames.
     */
    public abstract void update(float delta);

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

    /**
     * Returns the main game instance
     *
     * @return The main game instance
     */
    public DashOrSmash getGame()
    {
        return game;
    }
}
