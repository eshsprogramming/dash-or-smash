package com.eshsprogramming.dash_or_smash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eshsprogramming.dash_or_smash.model.entity.block.BlockEntity;
import com.eshsprogramming.dash_or_smash.model.world.NewHighScoreWorld;

/**
 * The renderer for the new high score screen.
 *
 * @author Zachary Latta
 */
public class NewHighScoreRenderer extends Renderer
{
    /**
     * The NewHighScoreWorld instance.
     */
    private NewHighScoreWorld newHighScoreWorld;

    /**
     * The sprite batch. Used for rendering sprites.
     */
    private SpriteBatch spriteBatch;

    /**
     * The texture for the blocks.
     */
    private Texture blockTexture;

    /**
     * Creates a new NewHighScoreRenderer
     */
    public NewHighScoreRenderer(NewHighScoreWorld newHighScoreWorld)
    {
        this.newHighScoreWorld = newHighScoreWorld;
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
        for(BlockEntity blockEntity : newHighScoreWorld.getBlockEntities())
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
        newHighScoreWorld.getTitleText().render(spriteBatch, getPPuX(), getPPuY());
        newHighScoreWorld.getScoreText().render(spriteBatch, getPPuX(), getPPuY());
        newHighScoreWorld.getPlayAgainText().render(spriteBatch, getPPuX(), getPPuY());
        newHighScoreWorld.getMainMenuText().render(spriteBatch, getPPuX(), getPPuY());
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
    public NewHighScoreWorld getWorld()
    {
        return newHighScoreWorld;
    }
}
