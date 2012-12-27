package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.model.world.GameOverWorld;
import com.eshsprogramming.nudistrailroadexhibition.view.GameOverRenderer;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;


/**
 * @author Benjamin Landers
 */
public class GameOverScreen extends BaseScreen
{
    /**
     * the score as an integer
     */
    private int score = 0;

    /**
     * the publicly accesable constructor
     *
     * @param game a reference to the main game
     */
    public GameOverScreen(NudistRailroadExhibition game)
    {
        super(game);
    }

    public void render(float delta)
    {
        super.render(delta);
        //	controller.update(delta);
    }

    public void resize(int width, int height)
    {
       super.resize(width, height);
    }

    /**
     * Called when this world becomes the current world for the game.
     */
    @Override
    public void show()
    {
		super.show();
        renderer = new GameOverRenderer(new GameOverWorld(this, score));
    }
	public void setScore(int x)
	{
		this.score = x;
	}

}
