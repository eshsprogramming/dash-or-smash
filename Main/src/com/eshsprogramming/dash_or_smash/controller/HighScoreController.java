package com.eshsprogramming.dash_or_smash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.model.world.HighScoreWorld;
import com.eshsprogramming.dash_or_smash.screens.HighScoreScreen;

/**
 * Controller for the high score screen.
 *
 * @author Zachary Latta
 */
public class HighScoreController extends Controller
{
    /**
     * The high score's world.
     */
    private HighScoreWorld highScoreWorld = null;
    /**
     * The high score screen instance.
     */
    private HighScoreScreen highScoreScreen = null;

    /**
     * Played when the user selects something.
     */
    private Sound selectSound = null;

    /**
     * Creates a new high score controller
     *
     * @param game The instance of DashOrSmash
     */
    public HighScoreController(DashOrSmash game)
    {
        super(game);
        this.highScoreScreen = game.highScoreScreen;
        this.highScoreWorld = game.highScoreScreen.getHighScoreWorld();
        this.selectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/effects/select.wav"));
        // Sets touch position to (0, 0) to avoid NullPointerException
        this.setTouchPosition(new Vector2(0, 0));
    }

    /**
     * The main update method. Called each frame.
     *
     * @param delta Time in milliseconds between frames.
     */
    @Override
    public void update(float delta)
    {
        if(highScoreWorld.getBackToMainMenuText().touches(getTouchPosition(), highScoreScreen.getWidth(),
                highScoreScreen.getHeight()))
        {
            selectSound.play();
            getGame().setScreen(getGame().mainMenuScreen);
        }
    }
}
