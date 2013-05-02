package com.eshsprogramming.dash_or_smash.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.controller.GameOverController;
import com.eshsprogramming.dash_or_smash.model.world.GameOverWorld;
import com.eshsprogramming.dash_or_smash.view.GameOverRenderer;
import com.eshsprogramming.dash_or_smash.view.GameRenderer;

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
    private DashOrSmash game;

    /**
     * The score as an integer.
     */
    private int score = 0;

    /**
     * The background music for the screen.
     */
    private Music music = null;

    /**
     * The publicly assessable constructor
     *
     * @param game a reference to the main game
     */
    public GameOverScreen(DashOrSmash game)
    {
        super(game);
        this.game = game;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        float touchX = screenX;
        float touchY = getHeight() - screenY;
        float x = (touchX / getWidth()) * GameRenderer.CAMERA_WIDTH;
        float y = (touchY / getHeight()) * GameRenderer.CAMERA_HEIGHT;
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
