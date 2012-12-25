package com.eshsprogramming.nudistrailroadexhibition.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * A train class to exhibit all behavior of a train
 * @author Benjamin Landers
 */
public class Train
{
	/**
	 * The total number of trains.
	 */
	public static int NUMBER_OF_TRAINS =7;
	/**
	 * The velocity of the train on the Y axis.
	 */
    private float vY = 0f;
    /**
     * The train's width in units
     */
    public static final float SIZEX = 0.5f;
    /**
     * The trains's height in units
     */
    public static final float SIZEY = 1f;
    /**
     * The 2 d location of the train
     */
    private Vector2 position = new Vector2();
    /**
     * The bounds of the train
     */
    private Rectangle bounds = new Rectangle();

	/**
	 * Creates a new train.
	 *
	 * @param position The train's starting position
	 * @param vY The train's velocity on the Y axis
	 */
    public Train(Vector2 position, float vY)
    {
        this.position = position;
        this.bounds.height = SIZEY;
        this.bounds.width = SIZEX;
		this.bounds.setX(position.x);
        this.vY = vY;
    }

    /**
     * The update method for the train. Makes the train move down
     */
    public void update(float delta)
    {
        position.add(0,delta*vY);
        bounds.setY(position.y);
    }

    /**
     * Returns the bounds of the train.
     *
     * @return The bounds of the train.
     */
    public Rectangle getBounds()
    {
        return bounds;
    }

    /**
     * Returns the position of the train.
     *
     * @return The position of the train
     */
    public Vector2 getPosition()
    {
        return position;
    }
}
