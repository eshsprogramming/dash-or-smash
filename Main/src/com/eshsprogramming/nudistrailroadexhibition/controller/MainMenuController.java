package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.model.MainMenu;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.model.Text;
import com.eshsprogramming.nudistrailroadexhibition.screens.MainMenuScreen;
import com.eshsprogramming.nudistrailroadexhibition.view.MainMenuRenderer;

/**
 * The controller for the main menu. Handles input.
 *
 * @author Zachary Latta
 */
public class MainMenuController
{
    /**
     * The main menu.
     */
    private MainMenu mainMenu = null;
    /**
     * The main menu screen.
     */
    private MainMenuScreen mainMenuScreen = null;

    /**
     * The current position of the user's touch
     */
    private Vector2 touchPosition = null;

    /**
     * Creates a new main menu controller.
     *
     * @param mainMenu The main menu to use with the main menu controller
     */
    public MainMenuController(MainMenu mainMenu, MainMenuScreen mainMenuScreen)
    {
        this.mainMenu = mainMenu;
        this.mainMenuScreen = mainMenuScreen;

        // Sets touch position to (0, 0) to avoid NullPointerException
        this.setTouchPosition(new Vector2(0, 0));
    }

    /**
     * The main update method. Called each frame.
     *
     * @param delta Time in milliseconds between frames.
     */
    public void update(float delta, NudistRailroadExhibition game)
    {
        if(textTouched(touchPosition, mainMenu.getPlayText()))
        {
            game.setScreen(game.gameScreen) ;
        }
    }

    /**
     * Sets the controller's touch position.
     *
     * @param touchPosition The new touch position.
     */
    public void setTouchPosition(Vector2 touchPosition)
    {
        this.touchPosition = touchPosition;
    }

    /**
     * Returns whether or not the text is touched.
     *
     * @param touchPosition The current touch position.
     * @param text The text to test.
     * @return Whether or not the text is touched.
     */
    private boolean textTouched(Vector2 touchPosition, Text text)
    {
        // Makes width and height relative.
        float width = (text.getWidth()/mainMenuScreen.getWidth()) * MainMenuRenderer.CAMERA_WIDTH;
        float height = (text.getHeight()/mainMenuScreen.getHeight()) * MainMenuRenderer.CAMERA_HEIGHT;

        if(touchPosition.x > text.getPosition().x)
        {
            if(touchPosition.x < text.getPosition().x + width)
            {
                if(touchPosition.y > text.getPosition().y - height)
                {
                    if(touchPosition.y < text.getPosition().y)
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            return false;
        }

        return touchPosition.x > text.getWidth() && touchPosition.x < text.getPosition().x + width &&
                touchPosition.y > text.getPosition().y && touchPosition.y < text.getPosition().y + height;
    }
}
