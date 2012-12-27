package com.eshsprogramming.nudistrailroadexhibition.view;

/**
 * @author Benjamin Landers
 */
public class Renderer
{
	/**
	 * The width of the world in relative units.
	 */
	public static float CAMERA_WIDTH = 8f;
	/**
	 * The height of the world in relative units.
	 */
	public static float CAMERA_HEIGHT = 5f;
	/**
 	 * The width of the world in pixels.
 	 */
	private int width;
	/**
	 * The height of the world in pixels.
	 */
	private int height;
	/**
	 * Pixels per unit on the X axis
	 */
	private float ppuX;
	/**
	 * Pixels per unit on the Y axis
	 */
	private float ppuY;

	public void render(float delta)
	{
	}
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
		ppuX = (float) width / CAMERA_WIDTH;
		ppuY = (float) height / CAMERA_HEIGHT;
	}
	public float getPPuY()
	{
		return ppuY;
	}
	public float getPPuX()
	{
	   return ppuX;
	}

}
