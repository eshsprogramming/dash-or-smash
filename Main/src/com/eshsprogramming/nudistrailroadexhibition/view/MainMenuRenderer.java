package com.eshsprogramming.nudistrailroadexhibition.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.model.MainMenu;
import com.eshsprogramming.nudistrailroadexhibition.model.Text;

/**
 * Renders the main menu.
 *
 * @author Zachary Latta
 */
public class MainMenuRenderer
{
    /**
     * The width of the screen in relative units.
     */
    public static final float CAMERA_WIDTH = 8f;
    /**
     * The height of the screen in relative units.
     */
    public static final float CAMERA_HEIGHT = 5f;

    /**
     * The main menu instance.
     */
    private MainMenu mainMenu;

    /**
     * The sprite batch. Used for rendering sprites.
     */
    private SpriteBatch spriteBatch;

    /**
     * The width of the screen in pixels.
     */
    private int width;
    /**
     * The height of the screen in pixels.
     */
    private int height;
    /**
     * Pixels per unit on the X axis
     */
    private float ppuX;
    /**
     * Pixels per unit on the Y axis
     */
    private float ppuY;

    /**
     * Creates a new MainMenuRenderer
     */
    public MainMenuRenderer()
    {
        mainMenu = new MainMenu();
        spriteBatch = new SpriteBatch();
    }

    /**
     * Renders the contents of main menu renderer.
     *
     * @param delta The time in milliseconds between frames.
     */
    public void render(float delta)
    {
        spriteBatch.begin();
        drawText();
        spriteBatch.end();
    }

    /**
     * Draws the text onto the screen.
     */
    private void drawText()
    {
        mainMenu.getTitleText().render(spriteBatch, ppuX, ppuY);
        mainMenu.getPlayText().render(spriteBatch, ppuX, ppuY);
    }

    /**
     * Sets the size of the window in pixels.
     *
     * @param width The width of the window in pixels.
     * @param height The height of the window in pixels.
     */
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }
}
