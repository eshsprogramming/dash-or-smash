package com.eshsprogramming.nudistrailroadexhibition.model.gui;

/**
 * @author Zachary Latta
 */
// todo-ben Make score fit nicely into the new organizational structure
public class Score
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
     * A getter for score
     *
     * @return score the score the player currently has
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Increments the score by the points for each train
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
