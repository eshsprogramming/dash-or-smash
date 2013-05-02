package com.eshsprogramming.dash_or_smash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eshsprogramming.dash_or_smash.model.entity.block.BlockEntity;
import com.eshsprogramming.dash_or_smash.model.world.GameOverWorld;

/**
 * The renderer for the game over screen.
 *
 * @author Benjamin Landers
 */
public class GameOverRenderer extends Renderer
{
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
        for(BlockEntity blockEntity : gameOverWorld.getBlockEntities())
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
