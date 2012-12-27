package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.controller.GameOverController;
import com.eshsprogramming.nudistrailroadexhibition.model.world.GameOverWorld;
import com.eshsprogramming.nudistrailroadexhibition.view.GameOverRenderer;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;


/**
 * The main gameover screen
 *
 * @author Benjamin Landers
 */
public class GameOverScreen extends BaseScreen
{
    /**
     * the score as an integer
     */
    private int score = 0;
    /**
     * the controller object
     */
    GameOverController controller = null;

    /**
     * the publicly accesable constructor
     *
     * @param game a reference to the main game
     */
    public GameOverScreen(NudistRailroadExhibition game)
    {
        super(game);
        controller = new GameOverController(game);
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        float touchX = screenX;
        float touchY = getHeight() - screenY;

        float x = (touchX / getWidth()) * WorldRenderer.CAMERA_WIDTH;
        float y = (touchY / getHeight()) * WorldRenderer.CAMERA_HEIGHT;

        controller.setTouchPosition(new Vector2(x, y));

        return true;
    }

    public void render(float delta)
    {
        super.render(delta);
        controller.update(delta);
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
        controller.setTouchPosition(new Vector2(0, 0));
    }

    public void setScore(int x)
    {
        this.score = x;
    }

}
