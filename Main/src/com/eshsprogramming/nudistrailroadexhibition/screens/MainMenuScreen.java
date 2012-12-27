package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.controller.MainMenuController;
import com.eshsprogramming.nudistrailroadexhibition.model.world.MainMenuWorld;
import com.eshsprogramming.nudistrailroadexhibition.view.MainMenuRenderer;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

/**
 * The main menu of the game.
 *
 * @author Zachary Latta, Benjamin Landers
 */
public class MainMenuScreen extends BaseScreen
{
    /**
     * The main menu.
     */
    private MainMenuWorld mainMenuWorld;
    /**
     * The main menu controller.
     */
    private MainMenuController controller;

    /**
     * The background music for this screen.
     */
    private Music music;

    /**
     * Creates a new main menu screen.
     *
     * @param game The instance of Game
     */
    public MainMenuScreen(NudistRailroadExhibition game)
    {
        super(game);
    }

    /**
     * Called each frame. Calls necessary methods to render and update the screen.
     *
     * @param delta The time in milliseconds between frames.
     */
    @Override
    public void render(float delta)
    {
        super.render(delta);
        controller.update(delta);
    }

    /**
     * Called when this screen becomes the current screen.
     */
    @Override
    public void show()
    {
        super.show();

        mainMenuWorld = new MainMenuWorld(this);
        renderer = new MainMenuRenderer(mainMenuWorld);
        controller = new MainMenuController(mainMenuWorld, this, getGame());

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/soundtrack/main_menu.mp3"));

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

    ////////////////////////////////
    //   InputProcessor Methods   //
    ////////////////////////////////

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        float touchX = screenX;
        float touchY = getHeight() - screenY;

        float x = (touchX / getWidth()) * WorldRenderer.CAMERA_WIDTH;
        float y = (touchY / getHeight()) * WorldRenderer.CAMERA_HEIGHT;

        controller.setTouchPosition(new Vector2(x, y));

        return true;
    }
}
