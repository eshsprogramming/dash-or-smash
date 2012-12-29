package com.eshsprogramming.dash_or_smash.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A class to organize rendering code
 * @author benjamin Landers
 */
public class SpriteAnimation
{
	/**
	 * the number of columns
	 */
	private int columns;
	/**
	 * the number of rows
	 */
	private int rows;
	/**
	 * The current frame of the animation
	 */
	private TextureRegion currentFrame = null;
	/**
	 * The frames of the animation.
	 */
	private TextureRegion[] frames = null;
	/**
	 * The animation for class
	 */
	private Animation animation = null;
	/**
	 * The texture sheet for the animation
	 */
	private Texture textureSheet = null;

	/**
	 * makes the animation
	 * @param col the number of columns
	 * @param row the number of rows
	 * @param sheet the sheet to base the animation off of
	 */
	public SpriteAnimation(int col, int row, float frameDuration, Texture sheet)
	{
		columns = col;
		this.rows = row;
		frames = new TextureRegion[col*row];
		textureSheet = sheet;
		loadFrames(frames,textureSheet);
		animation = new Animation(frameDuration,frames);
	}
	private void loadFrames(TextureRegion[] frames, Texture sheet)
	{
		TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth() / columns,
				sheet.getHeight() / rows);

		int index = 0;
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				frames[index++] = tmp[i][j];
			}
		}
	}
	public void updateKeyFrame(float stateTime, boolean looping)
	{
		 currentFrame = animation.getKeyFrame(stateTime,looping);
	}
	public TextureRegion getCurrentFrame()
	{
		return currentFrame;
	}
}
