package com.eshsprogramming.nudistrailroadexhibition.model.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.BlockEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.gui.Text;
import com.eshsprogramming.nudistrailroadexhibition.screens.GameOverScreen;


/**
 * @author Benjamin Landers
 */
public class GameOverWorld
{
    /**
     * The game over world. Used for relative sizing of text.
     */
    private GameOverScreen gameOverScreen = null;
    /**
     * Text to display score
     */
    Text scoreText = null;
    /**
     * Text to play again
     */
    Text playAgainText = null;
    /**
     * Text to return to main menu.
     */
    Text mainMenuText = null;
    /**
     * An array of the blocks which make up the background.
     */
    private Array<BlockEntity> blocks = new Array<BlockEntity>();

    /**
     * Creates a new main menu
     */
    public GameOverWorld(GameOverScreen gameOverScreen, int score)
    {
        this.gameOverScreen = gameOverScreen;

        scoreText = new Text("fonts/arial/font.fnt", false, gameOverScreen.getWidth() * 0.005f,
                new Vector2(1f, 4.5f), "Score: " + score);
        playAgainText = new Text("fonts/arial/font.fnt", false, gameOverScreen.getWidth() * 0.0035f,
                new Vector2(1f, 3f), "Play Again");
        mainMenuText = new Text("fonts/arial/font.fnt", false, gameOverScreen.getWidth() * 0.0035f,
                new Vector2(1f, 2f), "Main Menu");

        fillBlockArray();
    }

    /**
     * Fills the blocks array with blocks.
     */
    private void fillBlockArray()
    {
        for(int index1 = 0; index1 < 8; index1++)
        {
            for(int index2 = 0; index2 < 5; index2++)
            {
                blocks.add(new BlockEntity(new Vector2(index1, index2)));
            }
        }
    }

    /**
     * Returns the array of blocks which make up the background.
     *
     * @return The array of blocks which make up the background.
     */
    public Array<BlockEntity> getBlocks()
    {
        return blocks;
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
