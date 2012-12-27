package com.eshsprogramming.nudistrailroadexhibition.model.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * A block in the world.
 *
 * @author Zachary Latta
 */
// todo-ben Make block extend from entity
public class BlockEntity
{
    /**
     * The block's size
     */
    public static final float SIZE = 1f;

    /**
     * The block's position in the world.
     */
    private Vector2 position = new Vector2();
    /**
     * The bounds of the block
     */
    private Rectangle bounds = new Rectangle();

    /**
     * Creates a new block.
     *
     * @param position The block's position in the world.
     */
    public BlockEntity(Vector2 position)
    {
        this.position = position;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    /**
     * Returns the bounds of the block
     *
     * @return The bounds of the block
     */
    public Rectangle getBounds()
    {
        return bounds;
    }

    /**
     * Returns the position of the block
     *
     * @return The position of the block
     */
    public Vector2 getPosition()
    {
        return position;
    }
}
