package com.eshsprogramming.nudistrailroadexhibition.model.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * A train class to exhibit all behavior of a train
 *
 * @author Benjamin Landers
 */
public class TrainEntity extends Entity
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
     * The train's width in units
     */
    public static final float SIZEX = 0.7f;
    /**
     * The trains's height in units
     */
    public static final float SIZEY = 1.4f;

    /**
     * Creates a new train.
     *
     * @param position The train's starting position
     * @param vY       The train's velocity on the Y axis
     */
    public TrainEntity(Vector2 position, float vY)
    {
        super(position, SIZEX, SIZEY);
        this.vY = vY;
    }

    /**
     * The update method for the train. Makes the train move down
     */
    public void update(float delta)
    {
        super.update(delta);
        getPosition().add(0, delta * vY);
        getBounds().setY(getPosition().y);
    }
}
