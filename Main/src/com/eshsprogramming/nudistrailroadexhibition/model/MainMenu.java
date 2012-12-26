package com.eshsprogramming.nudistrailroadexhibition.model;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Zachary Latta
 */
public class MainMenu
{
    /**
     * The title text.
     */
    Text titleText;
    /**
     * The play text.
     */
    Text playText;

    /**
     * Creates a new main menu
     */
    public MainMenu()
    {
        // todo-zach Make size of text relative
        titleText = new Text("fonts/arial/font.fnt", false, 5f, new Vector2(1, 4.5f), "Nudist Railroad Exhibition");
        playText = new Text("fonts/arial/font.fnt", false, 3f, new Vector2(1, 3), "Play");
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
