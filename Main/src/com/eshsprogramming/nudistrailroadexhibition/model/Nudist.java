package com.eshsprogramming.nudistrailroadexhibition.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * A nudist. Nudists can either be dancing or dying. Their position should be where the user moves them.
 *
 * @author Zachary Latta
 */
public class Nudist
{
    /**
     * The different states the nudist can be in.
     */
    public enum State
    {
        IDLE, DYING
    }

    /**
     * The nudist's width in units
     */
    public static final float SIZEX = 0.33f;
    /**
     * The nudist's height in units
     */
    public static final float SIZEY = 0.5f;

    /**
     * The nudist's position on the x and y plane.
     */
    private Vector2 position = new Vector2();
    /**
     * The bounds of the nudist
     */
    private Rectangle bounds = new Rectangle();
    /**
     * The nudist's current state
     */
    private State state = State.IDLE;

    /**
     * Creates a new nudist.
     *
     * @param position The nudist's starting position.
     */
    public Nudist(Vector2 position)
    {
        this.position = position;
        this.bounds.height = SIZEY;
        this.bounds.width = SIZEX;
    }

    /**
     * The update method for the nudist. Doesn't do anything.
     */
    public void update(float delta)
    {
        bounds.setY(position.y);
        bounds.setX(position.x);
    }

    /**
     * Returns the bounds of the nudist.
     *
     * @return The bounds of the nudist.
     */
    public Rectangle getBounds()
    {
        return bounds;
    }

    /**
     * Returns the position of the nudist.
     *
     * @return The position of the nudist
     */
    public Vector2 getPosition()
    {
        return position;
    }

    /**
     * Returns the state of the nudist.
     *
     * @return The current state of the nudist.
     */
    public State getState()
    {
        return state;
    }

    /**
     * Sets the state of the nudist.
     *
     * @param state The new state of the nudist.
     */
    public void setState(State state)
    {
        this.state = state;
    }
}
