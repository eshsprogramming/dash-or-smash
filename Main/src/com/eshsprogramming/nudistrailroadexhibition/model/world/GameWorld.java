package com.eshsprogramming.nudistrailroadexhibition.model.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.model.Score;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.BlockEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.NudistEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.TrainEntity;

/**
 * The game world.
 *
 * @author Zachary Latta, Benjamin Landers
 */
public class GameWorld
{
    /**
     * An array of the blocks which make up the world.
     */
    private Array<BlockEntity> blocks = new Array<BlockEntity>();
    /**
     * An array of the nudists in the world.
     */
    private Array<NudistEntity> nudists = new Array<NudistEntity>();
    /**
     * An array of all the trains
     */
    private Array<TrainEntity> trains = new Array<TrainEntity>();
    // todo-ben Instead of using the score class for score, just use a Text instance. It makes things simpler when playing with multiple classes.
    /**
     * The player's score.
     */
    private Score score = new Score();

    /**
     * Creates a new world.
     */
    public GameWorld()
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
            nudists.add(new NudistEntity(new Vector2((int) (Math.random() * 6), 0)));
        }

        // Adds blocks to the world
        for(int index1 = 0; index1 < 8; index1++)
        {
            for(int index2 = 0; index2 < 5; index2++)
            {
                blocks.add(new BlockEntity(new Vector2(index1, index2)));
            }
        }
    }

    /**
     * Returns the array of blocks in the world.
     *
     * @return The array of blocks in the world.
     */
    public Array<BlockEntity> getBlocks()
    {
        return blocks;
    }

    /**
     * Returns the array of nudists in the world.
     *
     * @return The array of nudists in the world.
     */
    public Array<NudistEntity> getNudists()
    {
        return nudists;
    }

    /**
     * Returns the array of trains in the world.
     *
     * @return The array of trains in the world.
     */
    public Array<TrainEntity> getTrains()
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
