package com.eshsprogramming.dash_or_smash.model.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * The base class for both PedestrianEntity and VehicleEntity
 *
 * @author Benjamin Landers
 */
public abstract class Entity
{
	/**
	 * The type of image that the entity should use. This is used in a Renderer instance to decide which image to
	 * render for the entity.
	 */
	private int imageType;
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
	 * @param position The current position of the entity.
	 * @param sizeX The size of the entity on the x axis in relative units.
	 * @param sizeY The size of the entity on the y axis in relative units.
	 */
	public Entity(Vector2 position, float sizeX, float sizeY)
	{
		this.position = position;
		this.bounds.height = sizeY;
		this.bounds.width = sizeX;

		imageType = 0;
	}

	/**
	 * Creates a new entity
	 *
	 * @param position The current position of the entity.
	 * @param sizeX The size of the entity on the x axis in relative units.
	 * @param sizeY The size of the entity on the y axis in relative units.
	 * @param imageType The type of image the entity should use.
	 */
	public Entity(Vector2 position, float sizeX, float sizeY, int imageType)
	{
		this(position, sizeX, sizeY);

		this.imageType = imageType;
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

	/**
	 * Returns the image type.
	 *
	 * @return The image type.
	 */
	public int getImageType()
	{
		return imageType;
	}
}
