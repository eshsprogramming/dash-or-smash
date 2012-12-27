package com.eshsprogramming.nudistrailroadexhibition.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.BlockEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.world.GameOverWorld;

/**
 * @author Benjamin Landers
 */
public class GameOverRenderer extends Renderer
{
    /**
     * The width of the world in relative units.
     */
    public static final float CAMERA_WIDTH = 8f;
    /**
     * The height of the world in relative units.
     */
    public static final float CAMERA_HEIGHT = 5f;

    /**
     * The GameOverWorld instance.
     */
    private GameOverWorld gameOverWorld;

    /**
     * The sprite batch. Used for rendering sprites.
     */
    private SpriteBatch spriteBatch;

    /**
     * The texture for the blocks.
     */
    private Texture blockTexture;

    /**
     * Creates a new GameOverRenderer
     */
    public GameOverRenderer(GameOverWorld gameOverWorld)
    {
        this.gameOverWorld = gameOverWorld;
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
     * Draws blocks on the world.
     */
    private void drawBlocks()
    {
        for(BlockEntity blockEntity : gameOverWorld.getBlocks())
        {
            spriteBatch.draw(blockTexture, blockEntity.getPosition().x * getPPuX(), blockEntity.getPosition().y * getPPuY(),
                    BlockEntity.SIZEX * getPPuX(), BlockEntity.SIZEY * getPPuY());
        }
    }

    /**
     * Draws the text onto the world.
     */
    private void drawText()
    {
        gameOverWorld.getScoreText().render(spriteBatch, getPPuX(), getPPuY());
        gameOverWorld.getScoreText().render(spriteBatch, getPPuX(), getPPuY());
        gameOverWorld.getPlayAgainText().render(spriteBatch, getPPuX(), getPPuY());
        gameOverWorld.getMainMenuText().render(spriteBatch, getPPuX(), getPPuY());
    }

    /**
     * Loads the textures from files.
     */
    private void loadTextures()
    {
        blockTexture = new Texture(Gdx.files.internal("images/block.png"));
    }

    /**
     * Returns the world to render.
     *
     * @return The world to render.
     */
    public GameOverWorld getWorld()
    {
        return gameOverWorld;
    }
}
