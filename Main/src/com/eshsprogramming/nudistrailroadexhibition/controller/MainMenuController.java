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
     * The main menu world.
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
        if(textTouched(getTouchPosition(), mainMenuWorld.getPlayText()))
        {
            getGame().setScreen(getGame().gameScreen);
        }
    }

    /**
     * Returns whether or not the text is touched.
     *
     * @param touchPosition The current touch position.
     * @param text          The text to test.
     * @return Whether or not the text is touched.
     */
    private boolean textTouched(Vector2 touchPosition, Text text)
    {
        // Makes width and height relative.
        float width = (text.getPixelWidth() / mainMenuScreen.getWidth()) * MainMenuRenderer.CAMERA_WIDTH;
        float height = (text.getPixelHeight() / mainMenuScreen.getHeight()) * MainMenuRenderer.CAMERA_HEIGHT;

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

        return touchPosition.x > text.getPixelWidth() && touchPosition.x < text.getPosition().x + width &&
                touchPosition.y > text.getPosition().y && touchPosition.y < text.getPosition().y + height;
    }
}
