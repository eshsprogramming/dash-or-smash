package com.eshsprogramming.dash_or_smash.model.entity.pedestrian;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.model.entity.Entity;

/**
 * A pedestrian. Pedestrians can either be idle or dying. Their position should be where the user moves them.
 *
 * @author Zachary Latta
 */
public class PedestrianEntity extends Entity
{
    /**
     * The different states the pedestrian can be in.
     */
    public enum State
    {
        IDLE, DYING
    }

    /**
     * The pedestrian's width in units
     */
    public static final float SIZEX = 0.4f;
    /**
     * The pedestrian's height in units
     */
    public static final float SIZEY = 0.6f;

    /**
     * The pedestrian's current state
     */
    private State state = State.IDLE;

    /**
     * Creates a new pedestrian.
     *
     * @param position The pedestrian's starting position.
     */
    public PedestrianEntity(Vector2 position)
    {
        super(position, SIZEX, SIZEY);
    }

    /**
     * Creates a new pedestrian.
     *
     * @param position  The pedestrian's starting position.
     * @param imageType The type of image to use while rendering
     */
    public PedestrianEntity(Vector2 position, int imageType)
    {
        super(position, SIZEX, SIZEY, imageType);
    }

    /**
     * Returns the state of the pedestrian.
     *
     * @return The current state of the pedestrian.
     */
    public State getState()
    {
        return state;
    }

    /**
     * Sets the state of the pedestrian.
     *
     * @param state The new state of the pedestrian.
     */
    public void setState(State state)
    {
        this.state = state;
    }
}
