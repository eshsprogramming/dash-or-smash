package com.eshsprogramming.nudistrailroadexhibition.model.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * The base class for both NudistEntity and TrainEntity
 *
 * @author Benjamin Landers
 */
public class Entity
{
    /**
     * The entity's width in units
     */
    public static float SIZEX = 0.4f;
    /**
     * The entity's height in units
     */
    public static float SIZEY = 0.6f;

    /**
     * The entity's position on the x and y plane.
     */
    private Vector2 position = null;
    /**
     * The bounds of the entity
     */
    private Rectangle bounds = new Rectangle();

    /**
     * @param position the current position
     */
    public Entity(Vector2 position)
    {
        this.position = position;
        this.bounds.height = SIZEY;
        this.bounds.width = SIZEX;
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