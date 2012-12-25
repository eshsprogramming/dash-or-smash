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
	 * The nudist's size in units
	 */
	public static final float SIZE = 0.5f;

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
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
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
	 * Sets the state of the nudist.
	 *
	 * @param state The new state of the nudist.
	 */
	public void setState(State state)
	{
		this.state = state;
	}
}
