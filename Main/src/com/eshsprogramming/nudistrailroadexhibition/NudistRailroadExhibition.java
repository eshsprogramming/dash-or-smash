package com.eshsprogramming.nudistrailroadexhibition;

import com.badlogic.gdx.Game;
import com.eshsprogramming.nudistrailroadexhibition.screens.GameOverScreen;
import com.eshsprogramming.nudistrailroadexhibition.screens.GameScreen;
import com.eshsprogramming.nudistrailroadexhibition.screens.MainMenuScreen;

/**
 * The entry point of Nudist Railroad Exhibition.
 *
 * @author Zachary Latta
 */
public class NudistRailroadExhibition extends Game
{
    /**
     * The main menu world.
     */
    public MainMenuScreen mainMenuScreen = null;
    /**
     * The game world.
     */
    public GameScreen gameScreen = null;

    /**
     * the game over world
     */
    public GameOverScreen gameOverScreen = null;

    @Override
    public void create()
    {
        mainMenuScreen = new MainMenuScreen(this);
        gameScreen = new GameScreen(this);
        gameOverScreen = new GameOverScreen(this);

        setScreen(mainMenuScreen);
    }
}
