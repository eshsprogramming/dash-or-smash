package com.eshsprogramming.nudistrailroadexhibition.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.NudistEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.TrainEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.gui.Score;
import com.eshsprogramming.nudistrailroadexhibition.model.world.GameWorld;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

/**
 * The controller for the gameWorld. Manages sprite properties.
 *
 * @author Zachary Latta, Benjamin Landers
 */
public class WorldController extends Controller
{
    /**
     * Used to count how long since the player hit a train
     */
    private float respawnCounter = 0;
    /**
     * Timer to keep track of total delta time.
     */
    private float timer = 0;
    /**
     * Used to tell if player selected a nudist
     */
    private boolean selected = false;
    /**
     * The gameWorld.
     */
    private GameWorld gameWorld = null;
    /**
     * The nudists in the gameWorld.
     */
    private Array<NudistEntity> nudists = null;
    /**
     * The trains in the gameWorld.
     */
    private Array<TrainEntity> trains = null;
    /**
     * The hurt sound. Played when a nudist dies.
     */
    private Sound hurtSound = null;
    /**
     * The Score object
     */
    private Score score = null;

    /**
     * Creates a new gameWorld controller.
     *
     * @param gameWorld The gameWorld to be used in the gameWorld controller.
     * @param game      a reference to the game (did you win)
     */
    public WorldController(GameWorld gameWorld, NudistRailroadExhibition game)
    {
        super(game);
        this.gameWorld = gameWorld;
        this.nudists = gameWorld.getNudists();
        this.trains = gameWorld.getTrains();
        this.score = gameWorld.getScore();
        setTouchPosition(gameWorld.getNudists().get(0).getPosition());
        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("sounds/effects/hurt.wav"));
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
        for(NudistEntity nudistEntity : nudists)
        {
            nudistEntity.update(delta);
        }

        // Calls update methods of trains
        for(TrainEntity trainEntity : trains)
        {
            trainEntity.update(delta);
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
    {
        try
        {
            float temp = (getTouchPosition().x - nudists.get(0).getPosition().x) * 1f;
            temp = (selected) ? temp : 0;
            temp = (-1f < temp && temp < 1f) ? temp : 0;
            temp = (getTouchPosition().x > 0) ? temp : 0;
            temp = (getTouchPosition().x < WorldRenderer.CAMERA_WIDTH - NudistEntity.SIZEX) ?
                    temp : WorldRenderer.CAMERA_WIDTH - NudistEntity.SIZEX - nudists.get(0).getPosition().x;
            nudists.get(0).getPosition().x += temp;
        } catch(Exception e)
        {
            getGame().gameOverScreen.setScore(this.gameWorld.getScore().getScore());
            getGame().setScreen(getGame().gameOverScreen);
        }
    }

    /**
     * Checks if trains are allowed to be on the gameWorld. Removes them if they aren't and creates a new one.
     */
    private void checkTrainValidity()
    {
        for(TrainEntity trainEntity : trains)
        {
            if(trainEntity.getPosition().y + TrainEntity.SIZEY < 0)
            {
                trains.removeValue(trainEntity, true);
                score.increment(nudists.size);
            }
        }

        TrainEntity temp = null;

        while(trains.size < TrainEntity.NUMBER_OF_TRAINS)
        {
            temp = new TrainEntity(new Vector2(MathUtils.random(0f, WorldRenderer.CAMERA_WIDTH - TrainEntity.SIZEX),
                    MathUtils.random(5f, 10f)), MathUtils.random(-2.2f) * timer * .01f - .8f);

            while(checkTrain(temp))
            {
                temp.getPosition().x = MathUtils.random(0f, WorldRenderer.CAMERA_WIDTH - TrainEntity.SIZEX);
            }

            trains.add(temp);
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
                    {
                        selected = false;
                    }
                }
            }
        }
    }

    /**
     * Checks for collision between a trainEntity and a nudistEntity.
     *
     * @param trainEntity  The trainEntity used for the collision check.
     * @param nudistEntity The nudistEntity used for the collision check.
     * @return Whether or not there is a collision between the trainEntity and nudistEntity.
     */
    private boolean checkCollision(TrainEntity trainEntity, NudistEntity nudistEntity)
    {
        return Intersector.intersectRectangles(trainEntity.getBounds(), nudistEntity.getBounds());
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
     * Spawns a new nudist into the gameWorld.
     */
    private void spawnNudist()
    {
        NudistEntity temp = new NudistEntity(new Vector2(nudists.first().getPosition().x +
                NudistEntity.SIZEX, nudists.first().getPosition().y));

        if(temp.getPosition().x < 0 || temp.getPosition().x > WorldRenderer.CAMERA_WIDTH - NudistEntity.SIZEX)
        {
            temp.getPosition().x = (temp.getPosition().x > 0) ? temp.getPosition().x : 0;
            temp.getPosition().x = (temp.getPosition().x < WorldRenderer.CAMERA_WIDTH - NudistEntity.SIZEX)
                    ? temp.getPosition().x : WorldRenderer.CAMERA_WIDTH - NudistEntity.SIZEX;
        }

        nudists.add(temp);
    }

    /**
     * Checks whether teh train is on the same track as another
     *
     * @param train the train in question
     * @return Whether or not the train fails the check or not
     */
    private boolean checkTrain(TrainEntity train)
    {
        for(TrainEntity temp : trains)
        {
            if(train != temp)
            {
                if(train.getPosition().x > temp.getPosition().x - TrainEntity.SIZEX && train.getPosition().x < temp.getPosition().x + TrainEntity.SIZEX)
                {
                    return true;
                }
            }
        }

        return false;
    }
}
