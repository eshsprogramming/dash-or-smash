package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.model.gui.Text;
import com.eshsprogramming.nudistrailroadexhibition.view.GameOverRenderer;
import com.eshsprogramming.nudistrailroadexhibition.view.MainMenuRenderer;

/**
 * Controller for the game over screen. Handles input and potentially other changes that occur.
 *
 * @author Benjamin Landers, Zachary Latta
 */
public class GameOverController extends Controller
{
    /**
     * Creates a new GameOverController.
     *
     * @param game A reference to the actual Game instance
     */
    public GameOverController(NudistRailroadExhibition game)
    {
        super(game);
        setTouchPosition(new Vector2(0, 0)); // to avoid a null pointer exception
    }

    /**
     * The main update method. Called each frame.
     *
     * @param delta time in milliseconds between frames.
     */
    public void update(float delta)
    {
        if(textTouched(getTouchPosition(),
                ((GameOverRenderer) getGame().gameOverScreen.getRenderer()).getWorld().getMainMenuText()))
        {
            getGame().setScreen(getGame().mainMenuScreen);
        }

        if(textTouched(getTouchPosition(),
                ((GameOverRenderer) getGame().gameOverScreen.getRenderer()).getWorld().getPlayAgainText()))
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
        float width = (text.getPixelWidth() / getGame().gameOverScreen.getWidth()) * MainMenuRenderer.CAMERA_WIDTH;
        float height = (text.getPixelHeight() / getGame().gameOverScreen.getHeight()) * MainMenuRenderer.CAMERA_HEIGHT;

        return touchPosition.x > text.getPixelWidth() && touchPosition.x < text.getPosition().x + width &&
                touchPosition.y > text.getPosition().y && touchPosition.y < text.getPosition().y + height;
    }
}
