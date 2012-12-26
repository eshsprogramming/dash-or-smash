package com.eshsprogramming.nudistrailroadexhibition.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.screens.GameOverScreen;


/**
 * @author Benjamin Landers
 */
public class GameOver
{
    /**
     * The game over screen. Used for relative sizing of text.
     */
    private GameOverScreen gameOverScreen = null;
    /**
     * The title text.
     */
    Text messageText = null;
    /**
     * The play text.
     */
    Text scoreText = null;
    /**
     * An array of the blocks which make up the background.
     */
    private Array<Block> blocks = new Array<Block>();

    /**
     * Creates a new main menu
     */
    public GameOver(GameOverScreen gameOverScreen, int score)
    {
        this.gameOverScreen = gameOverScreen;

        messageText = new Text("fonts/arial/font.fnt", false, gameOverScreen.getWidth() * 0.0035f,
                new Vector2(1f, 4.5f), "Your score was ...");
        scoreText = new Text("fonts/arial/font.fnt", false, gameOverScreen.getWidth() * 0.003f, new Vector2(1, 3),
                "" + score);

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
                blocks.add(new Block(new Vector2(index1, index2)));
            }
        }
    }

    /**
     * Returns the array of blocks which make up the background.
     *
     * @return The array of blocks which make up the background.
     */
    public Array<Block> getBlocks()
    {
        return blocks;
    }

    /**
     * Returns the title text object.
     *
     * @return The title text object.
     */
    public Text getMessageText()
    {
        return messageText;
    }

    /**
     * Returns the play text object.
     *
     * @return The play text object.
     */
    public Text getScoreText()
    {
        return scoreText;
    }
}
