package com.eshsprogramming.dash_or_smash.model.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.dash_or_smash.model.entity.block.BlockEntity;
import com.eshsprogramming.dash_or_smash.model.gui.Text;
import com.eshsprogramming.dash_or_smash.screens.NewHighScoreScreen;

/**
 * The world for the new high score screen.
 *
 * @author Zachary Latta
 */
public class NewHighScoreWorld extends World
{
    /**
     * The main menu screen. Used to retrieve values from related classes.
     */
    private NewHighScoreScreen newHighScoreScreen = null;
    /**
     * The title text.
     */
    private Text titleText = null;
    /**
     * The text to display score
     */
    private Text scoreText = null;
    /**
     * The play again text.
     */
    private Text playAgainText = null;
    /**
     * The text to return to main menu.
     */
    private Text mainMenuText = null;

    /**
     * An array of the blocks which make up the background.
     */
    private Array<BlockEntity> blockEntities = null;

    /**
     * Creates a new new high score world
     */
    public NewHighScoreWorld(NewHighScoreScreen newHighScoreScreen, int score)
    {
        this.newHighScoreScreen = newHighScoreScreen;
        this.titleText = new Text("fonts/arial-15.fnt", false, newHighScoreScreen.getWidth() * 0.006f,
                                  new Vector2(1f, 4.5f), "New High Score!");
        this.scoreText = new Text("fonts/arial-15.fnt", false, newHighScoreScreen.getWidth() * 0.004f,
                                  new Vector2(1f, 3.5f), "Score: " + score);
        this.playAgainText = new Text("fonts/arial-15.fnt", false, newHighScoreScreen.getWidth() * 0.0035f,
                                      new Vector2(1f, 2.25f), "Play Again");
        this.mainMenuText = new Text("fonts/arial-15.fnt", false, newHighScoreScreen.getWidth() * 0.0035f,
                                     new Vector2(1f, 1.5f), "Main Menu");
        this.blockEntities = new Array<BlockEntity>();
        fillBlockArray();
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
     * Returns the array of blockEntities which make up the background.
     *
     * @return The array of blockEntities which make up the background.
     */
    public Array<BlockEntity> getBlockEntities()
    {
        return blockEntities;
    }

    /**
     * Returns the title text object.
     *
     * @return The title text object.
     */
    public Text getTitleText()
    {
        return titleText;
    }

    /**
     * Returns the score text object.
     *
     * @return The score text object.
     */
    public Text getScoreText()
    {
        return scoreText;
    }

    /**
     * Returns the play again text object.
     *
     * @return The play again text object.
     */
    public Text getPlayAgainText()
    {
        return playAgainText;
    }

    /**
     * Returns the main menu text object.
     *
     * @return The main menu text object.
     */
    public Text getMainMenuText()
    {
        return mainMenuText;
    }
}
