package com.eshsprogramming.dash_or_smash.model.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * A train class to exhibit all behavior of a train
 *
 * @author Benjamin Landers, Zachary Latta
 */
public class VehicleEntity extends Entity
{
	/**
	 * The total number of trains.
	 */
	public static int NUMBER_OF_TRAINS = 5;
	/**
	 * The velocity of the train on the Y axis.
	 */
	private float vY = 0f;
	/**
	 * The vehicle's width in units
	 */
	public static final float SIZEX = 0.7f;
	/**
	 * The vehicle's height in units
	 */
	public static final float SIZEY = 1.4f;

	/**
	 * Creates a new vehicle.
	 *
	 * @param position The vehicle's starting position
	 * @param vY       The vehicle's velocity on the Y axis
	 * @param imageType The type of image to use for the vehicle object's rendering
	 */
	public VehicleEntity(Vector2 position, float vY, int imageType)
	{
		super(position, SIZEX, SIZEY, imageType);

		this.vY = vY;
	}

	/**
	 * The update method for the vehicle. Makes the vehicle move down
	 */
	public void update(float delta)
	{
		super.update(delta);

		getPosition().add(0, delta * vY);
		getBounds().setY(getPosition().y);
	}
}
