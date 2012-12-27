package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.model.gui.Text;
import com.eshsprogramming.nudistrailroadexhibition.view.GameOverRenderer;
import com.eshsprogramming.nudistrailroadexhibition.view.MainMenuRenderer;

/**
 * @author Benjamin Landers
 */
public class GameOverController
{
    /**
     * a ref to game
     */
    private NudistRailroadExhibition game = null;
    /**
     * The current position of the user's touch
     */
    private Vector2 touchPosition = null;

    public GameOverController(NudistRailroadExhibition game)
    {
        this.game = game;
        this.touchPosition = new Vector2(0, 0); // to avoid a null pointer exception
    }

    public void update(float delta, NudistRailroadExhibition game)
    {
        if(textTouched(touchPosition,
                ((GameOverRenderer) game.gameOverScreen.getRenderer()).getWorld().getMainMenuText()))
        {
            game.setScreen(game.mainMenuScreen);
        }

        if(textTouched(touchPosition,
                ((GameOverRenderer) game.gameOverScreen.getRenderer()).getWorld().getPlayAgainText()))
        {
            game.setScreen(game.gameScreen);
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
     * @param text          The text to test.
     * @return Whether or not the text is touched.
     */
    private boolean textTouched(Vector2 touchPosition, Text text)
    {
        // Makes width and height relative.
        float width = (text.getPixelWidth() / game.gameOverScreen.getWidth()) * MainMenuRenderer.CAMERA_WIDTH;
        float height = (text.getPixelHeight() / game.gameOverScreen.getHeight()) * MainMenuRenderer.CAMERA_HEIGHT;

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
