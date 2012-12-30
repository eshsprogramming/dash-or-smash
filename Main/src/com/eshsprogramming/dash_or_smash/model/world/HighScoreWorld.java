package com.eshsprogramming.dash_or_smash.model.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.dash_or_smash.model.entity.block.BlockEntity;
import com.eshsprogramming.dash_or_smash.model.gui.Text;
import com.eshsprogramming.dash_or_smash.screens.HighScoreScreen;

/**
 * The world for the high score screen
 *
 * @author Zachary Latta
 */
public class HighScoreWorld extends World
{
	/**
	 * The number of high scores to display.
	 */
	public static final int HIGH_SCORE_COUNT = 5;

	/**
	 * The high score screen. Used to retrieve values from related classes.
	 */
	private HighScoreScreen highScoreScreen;

	/**
	 * The text used to display the title of the screen.
	 */
	private Text titleText = null;
	/**
	 * The text used to display "Back to Main Menu"
	 */
	private Text backToMainMenuText = null;

	/**
	 * The text used to display the top 5 high scores
	 */
	private Array<Text> highScoreTexts = null;
	/**
	 * The values of the top high scores
	 */
	private Array<Integer> highScoreValues = null;

	/**
	 * An array of blocks which makes up the background.
	 */
	private Array<BlockEntity> blockEntities = null;

	/**
	 * Creates a new high score world.
	 */
	public HighScoreWorld(HighScoreScreen highScoreScreen)
	{
		this.highScoreScreen = highScoreScreen;

		this.titleText = new Text("fonts/arial-15.fnt", false, highScoreScreen.getWidth() * 0.007f,
				new Vector2(1, 4.5f), "High Scores");
		this.backToMainMenuText = new Text("fonts/arial-15.fnt", false, highScoreScreen.getWidth() * 0.0035f,
				new Vector2(4, 0.85f), "Back to Main Menu");

		this.highScoreTexts = new Array<Text>();
		this.highScoreValues = new Array<Integer>();

		this.blockEntities = new Array<BlockEntity>();

		fillBlockArray();
		loadValuesFromFile();
		setupHighScoreTexts();
	}

	/**
	 * Fills the blocks array with blockEntities.
	 */
	private void fillBlockArray()
	{
		for(int index1 = 0; index1 < 8; index1++)
		{
			for(int index2 = 0; index2 < 5; index2++)
			{
				blockEntities.add(new BlockEntity(new Vector2(index1, index2)));
			}
		}
	}

	/**
	 * Loads the values of the high scores from the file into highScoreValues.
	 */
	private void loadValuesFromFile()
	{
		// todo-zach Load the values from a file in this method
	}

	/**
	 * Creates Text instances in highScoreText.
	 */
	private void setupHighScoreTexts()
	{
		// todo-zach Make this method load proper values to display into the Text instances
		for(int index = 0; index < HIGH_SCORE_COUNT; index++)
		{
			highScoreTexts.insert(index, new Text("fonts/arial-15.fnt", false, highScoreScreen.getWidth() * 0.0035f,
					new Vector2(1, 3.25f - index * 0.6f), index + 1 + ": "));
		}
	}

	/**
	 * Returns the back to main menu text instance.
	 *
	 * @return The back to main menu text instance.
	 */
	public Text getBackToMainMenuText()
	{
		return backToMainMenuText;
	}

	/**
	 * Returns the array of blocks in the world.
	 *
	 * @return The array of blocks in the world.
	 */
	public Array<BlockEntity> getBlockEntities()
	{
		return blockEntities;
	}

	/**
	 * Returns the title text in the world.
	 *
	 * @return The title text in the world.
	 */
	public Text getTitleText()
	{
		return titleText;
	}

	/**
	 * Returns the array of high score texts in the world.
	 *
	 * @return The array of high score texts in the world.
	 */
	public Array<Text> getHighScoreTexts()
	{
		return highScoreTexts;
	}
}
