package com.eshsprogramming.dash_or_smash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eshsprogramming.dash_or_smash.model.entity.block.BlockEntity;
import com.eshsprogramming.dash_or_smash.model.gui.Text;
import com.eshsprogramming.dash_or_smash.model.world.HighScoreWorld;

/**
 * @author Zachary Latta
 */
public class HighScoreRenderer extends Renderer
{
	/**
	 * The high score's world.
	 */
	private HighScoreWorld highScoreWorld;

	/**
	 * The sprite batch. Used for rendering sprites.
	 */
	private SpriteBatch spriteBatch;

	/**
	 * The texture for the blocks.
	 */
	private Texture blockTexture;

	/**
	 * Creates a new MainMenuRenderer
	 */
	public HighScoreRenderer(HighScoreWorld highScoreWorld)
	{
		this.highScoreWorld = highScoreWorld;
		this.spriteBatch = new SpriteBatch();

		this.loadTextures();
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
		for(BlockEntity blockEntity : highScoreWorld.getBlockEntities())
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
		highScoreWorld.getTitleText().render(spriteBatch, getPPuX(), getPPuY());
		highScoreWorld.getBackToMainMenuText().render(spriteBatch, getPPuX(), getPPuY());

		// Renders the high score text array (highScoreTexts)
		for(Text highScore : highScoreWorld.getHighScoreTexts())
		{
			highScore.render(spriteBatch, getPPuX(), getPPuY());
		}
	}

	/**
	 * Loads the textures from files.
	 */
	private void loadTextures()
	{
		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
	}
}
