package com.eshsprogramming.nudistrailroadexhibition.model.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.BlockEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.gui.Text;
import com.eshsprogramming.nudistrailroadexhibition.screens.MainMenuScreen;

/**
 * The world for the main menu. Contains the things displayed and handled by the main menu.
 *
 * @author Zachary Latta
 */
public class MainMenuWorld
{
    /**
     * The main menu world. Used for relative sizing of text.
     */
    MainMenuScreen mainMenuScreen = null;
    /**
     * The title text.
     */
    Text titleText = null;
    /**
     * The play text.
     */
    Text playText = null;
    /**
     * An array of the blocks which make up the background.
     */
    private Array<BlockEntity> blocks = new Array<BlockEntity>();

    /**
     * Creates a new main menu
     */
    public MainMenuWorld(MainMenuScreen mainMenuScreen)
    {
        this.mainMenuScreen = mainMenuScreen;

        titleText = new Text("fonts/8BitWonder/font.fnt", false, mainMenuScreen.getWidth() * 0.0035f*.15f,
                new Vector2(.25f, 4.5f), "Nudist Railroad Exhibition");
        playText = new Text("fonts/8BitWonder/font.fnt", false, mainMenuScreen.getWidth() * 0.003f*.25f,
                new Vector2(3, 3), "Play");

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
}
