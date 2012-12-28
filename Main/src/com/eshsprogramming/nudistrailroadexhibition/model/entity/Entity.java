package com.eshsprogramming.nudistrailroadexhibition.model.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * The base class for both PedestrianEntity and VehicleEntity
 *
 * @author Benjamin Landers
 */
public class Entity
{
	/**
	 * The entity's position on the x and y plane.
	 */
	private Vector2 position = null;
	/**
	 * The bounds of the entity
	 */
	private Rectangle bounds = new Rectangle();

	/**
	 * Creates a new entity
	 *
	 * @param position the current position
	 */
	public Entity(Vector2 position, float sizeX, float sizeY)
	{
		this.position = position;
		this.bounds.height = sizeY;
		this.bounds.width = sizeX;
	}

	/**
	 * The update method for the entity. Doesn't do anything important other than update collision.
	 */
	public void update(float delta)
	{
		bounds.setY(position.y);
		bounds.setX(position.x);
	}

	/**
	 * Returns the bounds of the entity.
	 *
	 * @return The bounds of the entity.
	 */
	public Rectangle getBounds()
	{
		return bounds;
	}

	/**
	 * Returns the position of the entity.
	 *
	 * @return The position of the entity
	 */
	public Vector2 getPosition()
	{
		return position;
	}
}
