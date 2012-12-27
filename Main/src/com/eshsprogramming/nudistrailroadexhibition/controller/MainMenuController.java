package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.model.gui.Text;
import com.eshsprogramming.nudistrailroadexhibition.model.world.MainMenuWorld;
import com.eshsprogramming.nudistrailroadexhibition.screens.MainMenuScreen;
import com.eshsprogramming.nudistrailroadexhibition.view.MainMenuRenderer;

/**
 * The controller for the main menu. Handles input.
 *
 * @author Zachary Latta
 */
public class MainMenuController extends Controller
{
    /**
     * The main menu.
     */
    private MainMenuWorld mainMenuWorld = null;
    /**
     * The main menu screen.
     */
    private MainMenuScreen mainMenuScreen = null;

    /**
     * Creates a new main menu controller.
     *
     * @param mainMenuWorld The main menu to use with the main menu controller
     */
    public MainMenuController(MainMenuWorld mainMenuWorld, MainMenuScreen mainMenuScreen, NudistRailroadExhibition game)
    {
        super(game);
        this.mainMenuWorld = mainMenuWorld;
        this.mainMenuScreen = mainMenuScreen;

        // Sets touch position to (0, 0) to avoid NullPointerException
        this.setTouchPosition(new Vector2(0, 0));
    }

    /**
     * The main update method. Called each frame.
     *
     * @param delta Time in milliseconds between frames.
     */
    public void update(float delta)
    {
        if(mainMenuWorld.getPlayText().touches(getTouchPosition(), mainMenuScreen.getWidth(),
                mainMenuScreen.getHeight()))
        {
            getGame().setScreen(getGame().gameScreen);
        }
    }
}
