package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.controller.GameOverController;
import com.eshsprogramming.nudistrailroadexhibition.model.world.GameOverWorld;
import com.eshsprogramming.nudistrailroadexhibition.view.GameOverRenderer;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

/**
 * The main game over screen
 *
 * @author Benjamin Landers, Zachary latta
 */
public class GameOverScreen extends BaseScreen
{
    /**
     * The game itself.
     */
    private NudistRailroadExhibition game;

    /**
     * The score as an integer.
     */
    private int score = 0;
    /**
     * The controller object.
     */
    private GameOverController controller = null;

    /**
     * The background music for the screen.
     */
    private Music music = null;

    /**
     * The publicly assessable constructor
     *
     * @param game a reference to the main game
     */
    public GameOverScreen(NudistRailroadExhibition game)
    {
        super(game);
        this.game = game;
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

    /**
     * Called each frame. Handles rendering of things for the game over screen.
     *
     * @param delta The time in milliseconds between frames.
     */
    public void render(float delta)
    {
        super.render(delta);

        controller.update(delta);
    }

    /**
     * Called when the window is resized.
     *
     * @param width  The new width of the screen.
     * @param height The new height of the screen.
     */
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
        controller = new GameOverController(game);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/soundtrack/game_over.mp3"));

        controller.setTouchPosition(new Vector2(0, 0));

        music.setLooping(true);
        music.setVolume(0.7f);
        music.play();
    }

    /**
     * Called when this screen is no longer the current screen for the game.
     */
    @Override
    public void hide()
    {
        music.setLooping(false);
        music.stop();

        super.hide();
    }

    /**
     * Sets the score to the value passed.
     *
     * @param score The new score.
     */
    public void setScore(int score)
    {
        this.score = score;
    }
}
