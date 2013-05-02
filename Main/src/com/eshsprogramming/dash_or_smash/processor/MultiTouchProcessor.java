package com.eshsprogramming.dash_or_smash.processor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;

/**
 * A class to unify multi touch or single touch in the game
 *
 * @author Benjamin Landers
 */
public class MultiTouchProcessor
{
    private DashOrSmash game = null;
    private Vector2[] positions = null;

    /**
     * Creates a new MultiTouchProcessor instance. Also sets the max touches that it can deal with.
     *
     * @param maxTouches the max number of touches
     */
    public MultiTouchProcessor(DashOrSmash game, int maxTouches)
    {
        this.game = game;
        this.positions = new Vector2[maxTouches];

        for(int i = 0; i < maxTouches; i++)
        {
            positions[i] = new Vector2(-5, -5);
        }
    }

    /**
     * Updates all the positions of touches
     */
    public void updatePositions()
    {
        // Sets both to negative five to reset positions.
        positions[0].x = -5;
        positions[0].y = -5;
        positions[0].x = (Gdx.input.isTouched()) ? game.gameScreen.getRelativeX(Gdx.input.getX()) : -5;
        positions[0].y = (Gdx.input.isTouched()) ? game.gameScreen.getRelativeY(Gdx.input.getY()) : -5;

        for(int i = 1; i < positions.length; i++)
        {
            positions[i].x = -5;
            positions[i].y = -5;
            positions[i].x = (Gdx.input.isTouched(i)) ? game.gameScreen.getRelativeX(Gdx.input.getX(i)) : -5;
            positions[i].y = (Gdx.input.isTouched(i)) ? game.gameScreen.getRelativeY(Gdx.input.getY(i)) : -5;
        }

        //System.out.println( "x:" + game.gameScreen.getRelativeX(Gdx.input.getX(1))+ " y:" + game.gameScreen.getRelativeY(Gdx.input.getY()));
        //System.out.println( "x pos:" + positions[1]+ " y pos:" + positions[1]);
    }

    /**
     * Getter for the position array
     *
     * @return The positions of all the touches
     */
    public Vector2[] getPositions()
    {
        return positions;
    }

    public DashOrSmash getGame()
    {
        return game;
    }

}
