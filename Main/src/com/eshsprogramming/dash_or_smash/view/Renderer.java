package com.eshsprogramming.dash_or_smash.view;

/**
 * The base render class. Render classes handles displaying of things for screens.
 *
 * @author Benjamin Landers, Zachary Latta
 */
public abstract class Renderer
{
    /**
     * The width of the world in relative units.
     */
    public static final float CAMERA_WIDTH = 8f;
    /**
     * The height of the world in relative units.
     */
    public static final float CAMERA_HEIGHT = 5f;
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

    /**
     * Creates a new Renderer instance
     */
    public Renderer()
    {
    }

    /**
     * Called each frame. Should be used to render things on screen.
     *
     * @param delta The time in milliseconds between frames.
     */
    public abstract void render(float delta);

    /**
     * Changes the width and height of the world
     *
     * @param width  The width of the window.
     * @param height The height of the window.
     */
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
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
