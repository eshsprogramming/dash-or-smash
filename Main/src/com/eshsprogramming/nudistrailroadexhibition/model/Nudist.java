package com.eshsprogramming.nudistrailroadexhibition.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Zachary Latta
 */
public class Nudist
{
	public enum State
	{
		DANCING, DYING
	}

	public static final float SIZE = 0.5f;

	private Vector2 position = new Vector2();
	private Rectangle bounds = new Rectangle();
	private State state = State.DANCING;

	public Nudist(Vector2 position)
	{
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
	}

	public void update(Vector2 position)
	{
		position.set(position);
	}

	public Rectangle getBounds()
	{
		return bounds;
	}

	public Vector2 getPosition()
	{
		return position;
	}

	public void setState(State state)
	{
		this.state = state;
	}
}
