package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.controller.MainMenuController;
import com.eshsprogramming.nudistrailroadexhibition.model.MainMenu;
import com.eshsprogramming.nudistrailroadexhibition.view.MainMenuRenderer;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

/**
 * The main menu of the game.
 *
 * @author Zachary Latta
 */
public class MainMenuScreen implements Screen, InputProcessor
{
    /**
     * The game.
     */
    private NudistRailroadExhibition game;

    /**
     * The main menu.
     */
    private MainMenu mainMenu;

    /**
     * The main menu renderer.
     */
    private MainMenuRenderer renderer;
    /**
     * The main menu controller.
     */
    private MainMenuController controller;

    /**
     * The width of the world in pixels.
     */
    private float width;
    /**
     * The height of the world in pixels.
     */
    private float height;

    public MainMenuScreen(NudistRailroadExhibition game)
    {
        this.game = game;
    }

    @Override
    public void render(float delta)
    {
        renderer.render(delta);
        controller.update(delta, game);
    }

    @Override
    // todo-ben Fix resizing issue with main menu screen
    public void resize(int width, int height)
    {
        renderer.setSize(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void show()
    {
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        mainMenu = new MainMenu(this);
        renderer = new MainMenuRenderer(mainMenu);
        controller = new MainMenuController(mainMenu, this);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide()
    {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
        Gdx.input.setInputProcessor(null);
    }

    ////////////////////////////////
    //   InputProcessor Methods   //
    ////////////////////////////////

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        float touchX = screenX;
        float touchY = height - screenY;

        float x = (touchX / width) * WorldRenderer.CAMERA_WIDTH;
        float y = (touchY / height) * WorldRenderer.CAMERA_HEIGHT;

        controller.setTouchPosition(new Vector2(x, y));

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }

    /**
     * Returns the width in pixels of the screen.
     *
     * @return The width in pixels of the screen.
     */
    public float getWidth()
    {
        return width;
    }

    /**
     * Returns the height in pixels of the screen.
     *
     * @return The height in pixels of the screen.
     */
    public float getHeight()
    {
        return height;
    }
}
