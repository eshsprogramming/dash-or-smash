package com.eshsprogramming.nudistrailroadexhibition.model;

/**
 * @author Zachary Latta
 */
public class Score
{
	public static final int SCORE_COEFF = 10;
	private int score=0;

	/**
	 * A getter for score
	 * @return  scrore the score the player currently has
	 */
	public int getScore()
	{
		  return score;
	}

	/**
	 * Increments the score by the points for each train
	 */
	public void increment()
	{
		  score += Score.SCORE_COEFF;
	}

}
