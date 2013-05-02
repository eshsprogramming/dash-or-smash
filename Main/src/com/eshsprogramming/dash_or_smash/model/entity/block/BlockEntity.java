package com.eshsprogramming.dash_or_smash.model.entity.block;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.model.entity.Entity;

/**
 * A block in the world.
 *
 * @author Zachary Latta
 */
public class BlockEntity extends Entity
{
    /**
     * The block's horizontal size in relative units
     */
    public static final float SIZEX = 1f;
    /**
     * The block's vertical size in relative units
     */
    public static final float SIZEY = 1f;

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
        super(position, SIZEX, SIZEY);
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
}
