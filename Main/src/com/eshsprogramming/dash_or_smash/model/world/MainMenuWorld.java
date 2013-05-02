package com.eshsprogramming.dash_or_smash.model.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.dash_or_smash.model.entity.block.BlockEntity;
import com.eshsprogramming.dash_or_smash.model.gui.Text;
import com.eshsprogramming.dash_or_smash.screens.MainMenuScreen;

/**
 * The world for the main menu. Contains the things displayed and handled by the main menu.
 *
 * @author Zachary Latta
 */
public class MainMenuWorld extends World
{
    /**
     * The main menu screen. Used to retrieve values from related classes.
     */
    private MainMenuScreen mainMenuScreen = null;
    /**
     * The title text.
     */
    private Text titleText = null;
    /**
     * The play text.
     */
    private Text playText = null;
    /**
     * The high score text.
     */
    private Text highScoreText = null;

    /**
     * An array of the blocks which make up the background.
     */
    private Array<BlockEntity> blockEntities = null;

    /**
     * Creates a new main menu
     */
    public MainMenuWorld(MainMenuScreen mainMenuScreen)
    {
        this.mainMenuScreen = mainMenuScreen;
        this.titleText = new Text("fonts/arial-15.fnt", false, mainMenuScreen.getWidth() * 0.007f,
                                  new Vector2(1, 4.5f), "Dash Or Smash");
        this.playText = new Text("fonts/arial-15.fnt", false, mainMenuScreen.getWidth() * 0.0035f,
                                 new Vector2(1, 3), "Play");
        this.highScoreText = new Text("fonts/arial-15.fnt", false, mainMenuScreen.getWidth() * 0.0035f,
                                      new Vector2(1, 2), "High Scores");
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
     * Returns the play text object.
     *
     * @return The play text object.
     */
    public Text getPlayText()
    {
        return playText;
    }

    /**
     * Returns the high score text object.
     *
     * @return The high score text object.
     */
    public Text getHighScoreText()
    {
        return highScoreText;
    }
}
