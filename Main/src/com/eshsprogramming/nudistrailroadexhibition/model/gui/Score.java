package com.eshsprogramming.nudistrailroadexhibition.model.gui;

import com.badlogic.gdx.math.Vector2;

/**
 * The score of the user.
 *
 * @author Zachary Latta
 */
public class Score extends Text
{
    /**
     * The score coefficient. The amount used to increment the score.
     */
    public static final int SCORE_COEFF = 10;
    /**
     * The death coefficient. The amount subtracted on nudist death.
     */
    public static final int DEATH_COEFF = -100;
    /**
     * The current score.
     */
    private int score = 0;

    /**
     * A constructor to provide the super construct the necessary information.
     *
     * @param font     The location of the font file
     * @param flipped  Whether or not the text is flipped
     * @param scale    The scale of the score
     * @param position The 2d position of the score
     */
    public Score(String font, boolean flipped, float scale, Vector2 position)
    {
        super(font, flipped, scale, position);
    }

    /**
     * A getter for score
     *
     * @return The score the player currently has.
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Increments the score by the points for each train
     *
     * @param nudistCount The current number of nudists.
     */
    public void increment(int nudistCount)
    {
        score += Score.SCORE_COEFF + 7 * (nudistCount - 1);
    }

    /**
     * Should be called when a nudist dies. Adds the death coefficient to the score.
     */
    public void nudistDeath()
    {
        score += (Score.DEATH_COEFF);
    }
}
