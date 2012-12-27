package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.model.gui.Text;
import com.eshsprogramming.nudistrailroadexhibition.view.GameOverRenderer;
import com.eshsprogramming.nudistrailroadexhibition.view.MainMenuRenderer;

/**
 * controls the gameover screen
 *
 * @author Benjamin Landers
 */
public class GameOverController extends Controller
{
	/**
	 *
	 * @param game a ref to the main game class
	 */
    public GameOverController(NudistRailroadExhibition game)
    {
       super(game);
        setTouchPosition(new Vector2(0, 0)); // to avoid a null pointer exception
    }

    /**
     * @param delta time in milisecs
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
