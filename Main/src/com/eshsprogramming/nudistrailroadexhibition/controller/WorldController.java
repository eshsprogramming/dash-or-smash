package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.Score;
import com.eshsprogramming.nudistrailroadexhibition.model.Train;
import com.eshsprogramming.nudistrailroadexhibition.model.World;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;

/**
 * The controller for the world. Manages sprite properties.
 *
 * @author Zachary Latta, Benjamin Landers
 */
public class WorldController
{
	/**
	 * the game
	 */
	private NudistRailroadExhibition game = null;
    /**
     * Used to count how long since the player hit a train
     */
    private float respawnCounter = 0;
    /**
     * timer
     */
    private float timer = 0;
    /**
     * Used to tell if player selected a nudist
     */
    private boolean selected = false;
    /**
     * The world.
     */
    private World world = null;
    /**
     * The nudists in the world.
     */
    private Array<Nudist> nudists = null;
    /**
     * The trains in the world.
     */
    private Array<Train> trains = null;
    /**
     * The position of the user's touch.
     */
    private Vector2 touchPosition = null;
    /**
     * The hurt sound. Played when a nudist dies.
     */
    private Sound hurtSound = null;
    /**
     * The Score object
     */
    private Score score = null;

    /**
     * Creates a new world controller.
     *
     * @param world The world to be used in the world controller.
	 * @param game a reference to the game (did you win)
     */
    public WorldController(World world, NudistRailroadExhibition game)
    {
        this.world = world;
		this.game = game;
        this.nudists = world.getNudists();
        this.trains = world.getTrains();
        this.score = world.getScore();
        this.touchPosition = world.getNudists().get(0).getPosition();
        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hurt.wav"));
    }

    /**
     * The main update method.
     */
    public void update(float delta)
    {
        processInput();
        respawnCounter += delta;
        timer += delta;

        // Calls update methods of nudists
        for(Nudist nudist : nudists)
        {
            nudist.update(delta);
        }

        // Calls update methods of trains
        for(Train train : trains)
        {
            train.update(delta);
        }

        handleCollision();
        checkTrainValidity();

        if(respawnCounter > 5)
        {
            respawnCounter = 0;
            spawnNudist();
        }
    }

    /**
     * Changes the nudists' state and position based on input controls
     */
    private void processInput()
    {   try{
        float temp = (touchPosition.x - nudists.get(0).getPosition().x) * 1f;
        temp = (selected) ? temp : 0;
        temp = (-1f < temp && temp < 1f) ? temp : 0;
		temp = (touchPosition.x > 0)? temp : 0;
		temp = (touchPosition.x < WorldRenderer.CAMERA_WIDTH - Nudist.SIZEX)?
				temp:WorldRenderer.CAMERA_WIDTH - Nudist.SIZEX- nudists.get(0).getPosition().x;
        nudists.get(0).getPosition().x += temp;
		}catch(Exception e){
		game.setScreen(game.gameOverScreen);
		}
    }

    /**
     * Checks if trains are allowed to be on the screen. Removes them if they aren't and creates a new one.
     */
    private void checkTrainValidity()
    {
        for(Train train : trains)
        {
            if(train.getPosition().y + Train.SIZEY < 0)
            {
                trains.removeValue(train, true);
                score.increment(nudists.size);
            }
        }

        while(trains.size < Train.NUMBER_OF_TRAINS)
        {
            trains.add(new Train(new Vector2(MathUtils.random(0f, WorldRenderer.CAMERA_WIDTH - Train.SIZEX),
                    MathUtils.random(5f, 10f)), MathUtils.random(-2.2f) * timer * .01f - .8f));
        }
    }

    /**
     * Handles collision between trains and nudists.
     */
    private void handleCollision()
    {
        for(int index1 = 0; index1 < trains.size; index1++)
        {
            for(int index2 = 0; index2 < nudists.size; index2++)
            {
                if(checkCollision(trains.get(index1), nudists.get(index2)))
                {
                    hurtSound.play();
                    nudists.removeIndex(index2);
                    respawnCounter = 0;
                    score.nudistDeath();
                    if(index2 == 0)
                        selected = false;
                }
            }
        }
    }

    /**
     * Checks for collision between a train and a nudist.
     *
     * @param train  The train used for the collision check.
     * @param nudist The nudist used for the collision check.
     * @return Whether or not there is a collision between the train and nudist.
     */
    private boolean checkCollision(Train train, Nudist nudist)
    {
        return Intersector.intersectRectangles(train.getBounds(), nudist.getBounds());
    }

    /**
     * Sets the touch position to the position given
     *
     * @param touchPosition The position of the user's touch.
     */
    public void setTouchPosition(Vector2 touchPosition)
    {
        this.touchPosition = touchPosition;
    }

    /**
     * Sets whether or not the user has a selected nudist.
     *
     * @param isSelected Whether or not the user has selected a nudist.
     */
    public void setSelected(boolean isSelected)
    {
        selected = isSelected;
    }

    /**
     * Spawns a new nudist into the world.
     */
    private void spawnNudist()
    {
        nudists.add(new Nudist(new Vector2(nudists.first().getPosition().x +
                Nudist.SIZEX, nudists.first().getPosition().y)));
    }
}
