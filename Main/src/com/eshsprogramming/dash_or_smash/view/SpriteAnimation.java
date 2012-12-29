package com.eshsprogramming.dash_or_smash.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * An animation utilizing a sprite sheet.
 *
 * @author Benjamin Landers, Zachary Latta
 */
public class SpriteAnimation
{
	/**
	 * The number of columns in the sprite sheet.
	 */
	private int columns;
	/**
	 * The number of rows in the sprite sheet.
	 */
	private int rows;
	/**
	 * The current frame of the animation .
	 */
	private TextureRegion currentFrame = null;
	/**
	 * The frames used for the animation.
	 */
	private TextureRegion[] frames = null;
	/**
	 * The instance of animation from libGDX.
	 */
	private Animation animation = null;
	/**
	 * The sprite sheet used in the animation.
	 */
	private Texture spriteSheet = null;

	/**
	 * Creates a new sprite animation.
	 *
	 * @param columns       The number of columns in the sprite sheet.
	 * @param rows          The number of rows in the sprite sheet.
	 * @param frameDuration The time in seconds between frames.
	 * @param spriteSheet   The sprite sheet used in the animation.
	 */
	public SpriteAnimation(int columns, int rows, float frameDuration, Texture spriteSheet)
	{
		this.columns = columns;
		this.rows = rows;
		this.spriteSheet = spriteSheet;

		frames = new TextureRegion[columns * rows];
		loadFrames(frames, this.spriteSheet);
		animation = new Animation(frameDuration, frames);
	}

	/**
	 * Loads frames into a TextureRegion[]
	 *
	 * @param frames      The frames to load into from the sprite sheet
	 * @param spriteSheet The sprite sheet to load the frames from
	 */
	private void loadFrames(TextureRegion[] frames, Texture spriteSheet)
	{
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / columns,
				spriteSheet.getHeight() / rows);

		int index = 0;
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				frames[index++] = tmp[i][j];
			}
		}
	}

	/**
	 * Updates the current frame of the animation.
	 *
	 * @param stateTime The time spent in the state represented by this animation
	 * @param looping   Whether or not the animation should be looping
	 */
	public void updateCurrentFrame(float stateTime, boolean looping)
	{
		currentFrame = animation.getKeyFrame(stateTime, looping);
	}

	/**
	 * Returns the current frame of the animation.
	 *
	 * @return The current frame of the animation.
	 */
	public TextureRegion getCurrentFrame()
	{
		return currentFrame;
	}
}
