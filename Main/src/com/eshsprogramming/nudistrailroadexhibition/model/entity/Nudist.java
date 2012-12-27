package com.eshsprogramming.nudistrailroadexhibition.model.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * A nudist. Nudists can either be dancing or dying. Their position should be where the user moves them.
 *
 * @author Zachary Latta
 */
public class Nudist extends Entity
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
    public static final float SIZEX = 0.4f;
    /**
     * The nudist's height in units
     */
    public static final float SIZEY = 0.6f;
    ;
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
        super(position);
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
