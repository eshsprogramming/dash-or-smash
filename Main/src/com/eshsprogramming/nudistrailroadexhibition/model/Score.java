package com.eshsprogramming.nudistrailroadexhibition.model;

/**
 * @author Zachary Latta
 */
public class Score
{
    /**
     * The score coefficient. The amount used to increment the score.
     */
    public static final int SCORE_COEFF = 10;
	public static final int DEATH_COEFF = -30;
    /**
     * The current score.
     */
    private int score = 0;

    /**
     * A getter for score
     *
     * @return scrore the score the player currently has
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Increments the score by the points for each train
     */
    public void increment(int number)
    {
        score += Score.SCORE_COEFF*number;
    }
	public void nudistDeath()
	{
		score += Score.DEATH_COEFF;
	}

}
