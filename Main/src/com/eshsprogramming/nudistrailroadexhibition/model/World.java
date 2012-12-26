package com.eshsprogramming.nudistrailroadexhibition.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * The game world.
 *
 * @author Zachary Latta, Benjamin Landers
 */
public class World
{
    /**
     * An array of the blocks which make up the world.
     */
    private Array<Block> blocks = new Array<Block>();
    /**
     * An array of the nudists in the world.
     */
    private Array<Nudist> nudists = new Array<Nudist>();
    /**
     * An array of all the trains
     */
    private Array<Train> trains = new Array<Train>();
    // todo-ben Instead of using the score class for score, just use a Text instance. It makes things simpler when playing with multiple classes.
    /**
     * The player's score.
     */
    private Score score = new Score();

    /**
     * Creates a new world.
     */
    public World()
    {
        createBlankWorld();
    }

    /**
     * Fills the world with blocks.
     */
    private void createBlankWorld()
    {
        // Adds nudists to the array of them
        for(int index = 0; index < 1; index++)
        {
            nudists.add(new Nudist(new Vector2((int) (Math.random() * 6), 0)));
        }

        // Adds blocks to the world
        for(int index1 = 0; index1 < 8; index1++)
        {
            for(int index2 = 0; index2 < 5; index2++)
            {
                blocks.add(new Block(new Vector2(index1, index2)));
            }
        }
    }

    /**
     * Returns the array of blocks in the world.
     *
     * @return The array of blocks in the world.
     */
    public Array<Block> getBlocks()
    {
        return blocks;
    }

    /**
     * Returns the array of nudists in the world.
     *
     * @return The array of nudists in the world.
     */
    public Array<Nudist> getNudists()
    {
        return nudists;
    }

    /**
     * Returns the array of trains in the world.
     *
     * @return The array of trains in the world.
     */
    public Array<Train> getTrains()
    {
        return trains;
    }

    /**
     * Returns the current score.
     *
     * @return The current score.
     */
    public Score getScore()
    {
        return score;
    }
}
