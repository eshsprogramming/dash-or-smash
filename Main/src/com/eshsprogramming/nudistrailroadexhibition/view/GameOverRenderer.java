package com.eshsprogramming.nudistrailroadexhibition.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eshsprogramming.nudistrailroadexhibition.model.Block;
import com.eshsprogramming.nudistrailroadexhibition.model.GameOver;

/**
 * @author Benjamin Landers
 */
public class GameOverRenderer
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
     * The GameOver instance.
     */
    private GameOver gameOver;

    /**
     * The sprite batch. Used for rendering sprites.
     */
    private SpriteBatch spriteBatch;

    /**
     * The texture for the blocks.
     */
    private Texture blockTexture;

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
     * Creates a new GameOverRenderer
     */
    public GameOverRenderer(GameOver gameOver)
    {
        this.gameOver = gameOver;
        this.spriteBatch = new SpriteBatch();
        loadTextures();
    }

    /**
     * Renders the contents of main menu renderer.
     *
     * @param delta The time in milliseconds between frames.
     */
    public void render(float delta)
    {
        spriteBatch.begin();
        drawBlocks();
        drawText();
        spriteBatch.end();
    }

    /**
     * Draws blocks on the screen.
     */
    private void drawBlocks()
    {
        for(Block block : gameOver.getBlocks())
        {
            spriteBatch.draw(blockTexture, block.getPosition().x * ppuX, block.getPosition().y * ppuY,
                    Block.SIZE * ppuX, Block.SIZE * ppuY);
        }
    }

    /**
     * Draws the text onto the screen.
     */
    private void drawText()
    {
        gameOver.getMessageText().render(spriteBatch, ppuX, ppuY);
        gameOver.getScoreText().render(spriteBatch, ppuX, ppuY);
    }

    /**
     * Sets the size of the window in pixels.
     *
     * @param width  The width of the window in pixels.
     * @param height The height of the window in pixels.
     */
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        ppuX = (float) width / CAMERA_WIDTH;
        ppuY = (float) height / CAMERA_HEIGHT;
    }

    /**
     * Loads the textures from files.
     */
    private void loadTextures()
    {
        blockTexture = new Texture(Gdx.files.internal("images/block.png"));
    }
}
