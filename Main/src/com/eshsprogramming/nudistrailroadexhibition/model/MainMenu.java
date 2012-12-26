package com.eshsprogramming.nudistrailroadexhibition.model;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.screens.MainMenuScreen;
import com.eshsprogramming.nudistrailroadexhibition.view.MainMenuRenderer;

/**
 * @author Zachary Latta
 */
public class MainMenu
{
    /**
     * The main menu screen. Used for relative sizing of text.
     */
    MainMenuScreen mainMenuScreen = null;
    /**
     * The title text.
     */
    Text titleText = null;
    /**
     * The play text.
     */
    Text playText = null;

    /**
     * Creates a new main menu
     */
    public MainMenu(MainMenuScreen mainMenuScreen)
    {
        this.mainMenuScreen = mainMenuScreen;

        titleText = new Text("fonts/arial/font.fnt", false, mainMenuScreen.getWidth() * 0.0035f, new Vector2(1, 4.5f), "Nudist Railroad Exhibition");
        playText = new Text("fonts/arial/font.fnt", false, mainMenuScreen.getWidth() * 0.003f, new Vector2(1, 3), "Play");
    }

    /**
     * Returns the title text object.
     *
     * @return The title text object.
     */
    public Text getTitleText()
    {
        return titleText;
    }

    /**
     * Returns the play text object.
     *
     * @return The play text object.
     */
    public Text getPlayText()
    {
        return playText;
    }
}
