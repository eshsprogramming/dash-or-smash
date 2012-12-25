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
     * The train's width in units
     */
    public static final float SIZEX = 0.5f;
    /**
     * The trains's height in units
     */
    public static final float SIZEY = 0.5f;
    /**
     * The 2 d location of the train
     */
    private Vector2 position = new Vector2();
    /**
     * The bounds of the train
     */
    private Rectangle bounds = new Rectangle();

    public Train(Vector2 position)
    {
        this.position = position;
        this.bounds.height = SIZEY;
        this.bounds.width = SIZEX;
    }

    /**
     * The update method for the train. Doesn't do anything.
     */
    public void update(float delta)
    {
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
