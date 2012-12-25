package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Screen;
import com.eshsprogramming.nudistrailroadexhibition.model.MainMenu;
import com.eshsprogramming.nudistrailroadexhibition.view.MainMenuRenderer;

/**
 * The main menu of the game.
 *
 * @author Zachary Latta
 */
public class MainMenuScreen implements Screen
{
    /**
     * The main menu.
     */
    private MainMenu mainMenu;
    /**
     * The main menu renderer.
     */
    private MainMenuRenderer renderer;

    /**
     * The width of the world in pixels.
     */
    private float width;
    /**
     * The height of the world in pixels.
     */
    private float height;

    @Override
    public void render(float delta)
    {
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height)
    {
        renderer.setSize(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void show()
    {
        mainMenu = new MainMenu();
        renderer = new MainMenuRenderer(mainMenu);
    }

    @Override
    public void hide()
    {
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
    }
}
