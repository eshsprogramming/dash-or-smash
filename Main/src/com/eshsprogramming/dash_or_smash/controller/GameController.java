package com.eshsprogramming.dash_or_smash.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eshsprogramming.dash_or_smash.DashOrSmash;
import com.eshsprogramming.dash_or_smash.model.entity.PedestrianEntity;
import com.eshsprogramming.dash_or_smash.model.entity.VehicleEntity;
import com.eshsprogramming.dash_or_smash.model.gui.Score;
import com.eshsprogramming.dash_or_smash.model.world.GameWorld;
import com.eshsprogramming.dash_or_smash.view.GameRenderer;

/**
 * The controller for the gameWorld. Manages sprite properties.
 *
 * @author Zachary Latta, Benjamin Landers
 */
public class GameController extends Controller
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
	 * Used to tell if player selected a pedestrian
	 */
	private boolean selected = false;
	/**
	 * The gameWorld.
	 */
	private GameWorld gameWorld = null;
	/**
	 * The pedestrianEntities in the gameWorld.
	 */
	private Array<PedestrianEntity> pedestrianEntities = null;
	/**
	 * The vehicleEntities in the gameWorld.
	 */
	private Array<VehicleEntity> vehicleEntities = null;
	/**
	 * The hurt sound. Played when a pedestrian dies.
	 */
	private Sound hurtSound = null;
    /**
     * The powerup sound. Played when a pedestrian is spawned.
     */
    private Sound powerupSound = null;
	/**
	 * The Score object
	 */
	private Score score = null;

	/**
	 * Creates a new gameWorld controller.
	 *
	 * @param gameWorld The gameWorld to be used in the gameWorld controller.
	 * @param game      A reference to the game.
	 */
	public GameController(GameWorld gameWorld, DashOrSmash game)
	{
		super(game);
		this.gameWorld = gameWorld;
		this.pedestrianEntities = gameWorld.getPedestrianEntities();
		this.vehicleEntities = gameWorld.getVehicleEntities();
		this.score = gameWorld.getScore();
		this.hurtSound = Gdx.audio.newSound(Gdx.files.internal("sounds/effects/hurt.wav"));
        this.powerupSound = Gdx.audio.newSound(Gdx.files.internal("sounds/effects/powerup.wav"));

        setTouchPosition(gameWorld.getPedestrianEntities().first().getPosition());
	}

	/**
	 * The main update method.
	 */
	public void update(float delta)
	{
		processInput();
		respawnCounter += delta;
		timer += delta;

		// Calls update methods of pedestrianEntities
		for(PedestrianEntity pedestrianEntity : pedestrianEntities)
		{
			pedestrianEntity.update(delta);
		}

		// Calls update methods of vehicleEntities
		for(VehicleEntity vehicleEntity : vehicleEntities)
		{
			vehicleEntity.update(delta);
		}

		handleCollision();
		checkTrainValidity();

		if(respawnCounter > 5)
		{
            powerupSound.play();

			respawnCounter = 0;
			spawnPedestrian();
		}
	}

	/**
	 * Changes the pedestrianEntities' state and position based on input controls
	 */
	private void processInput()
	{
		try
		{
			float temp = (getTouchPosition().x - pedestrianEntities.get(0).getPosition().x) * 1f;

			temp = (selected) ? temp : 0;
			temp = (-1f < temp && temp < 1f) ? temp : 0;
			temp = (getTouchPosition().x > 0) ? temp : 0;
			temp = (getTouchPosition().x < GameRenderer.CAMERA_WIDTH - PedestrianEntity.SIZEX) ?
					temp : GameRenderer.CAMERA_WIDTH - PedestrianEntity.SIZEX -
					pedestrianEntities.get(0).getPosition().x;

			pedestrianEntities.get(0).getPosition().x += temp;
		}
		catch(Exception e)
		{
			getGame().gameOverScreen.setScore(this.gameWorld.getScore().getScore());
			getGame().setScreen(getGame().gameOverScreen);
		}
	}

	/**
	 * Checks if vehicleEntities are allowed to be on the gameWorld. Removes them if they aren't and creates a new one.
	 */
	private void checkTrainValidity()
	{
		for(VehicleEntity vehicleEntity : vehicleEntities)
		{
			if(vehicleEntity.getPosition().y + VehicleEntity.SIZEY < 0)
			{
				vehicleEntities.removeValue(vehicleEntity, true);
				score.increment(pedestrianEntities.size);
			}
		}

		VehicleEntity temp;

		while(vehicleEntities.size < VehicleEntity.NUMBER_OF_TRAINS)
		{
			temp = new VehicleEntity(new Vector2(MathUtils.random(0f, GameRenderer.CAMERA_WIDTH - VehicleEntity.SIZEX),
					MathUtils.random(5f, 10f)), MathUtils.random(-2.2f) * timer * .01f - .8f, MathUtils.random(1));

			while(checkTrain(temp))
			{
				temp.getPosition().x = MathUtils.random(0f, GameRenderer.CAMERA_WIDTH - VehicleEntity.SIZEX);
			}

			vehicleEntities.add(temp);
		}
	}

	/**
	 * Handles collision between vehicleEntities and pedestrianEntities.
	 */
	private void handleCollision()
	{
		for(int index1 = 0; index1 < vehicleEntities.size; index1++)
		{
			for(int index2 = 0; index2 < pedestrianEntities.size; index2++)
			{
				if(checkCollision(vehicleEntities.get(index1), pedestrianEntities.get(index2)))
				{
					hurtSound.play();
					pedestrianEntities.removeIndex(index2);
					respawnCounter = 0;
					score.pedestrianDeath();

					if(index2 == 0)
					{
						selected = false;
					}
				}
			}
		}
	}

	/**
	 * Checks for collision between a vehicleEntity and a pedestrianEntity.
	 *
	 * @param vehicleEntity    The vehicleEntity used for the collision check.
	 * @param pedestrianEntity The pedestrianEntity used for the collision check.
	 * @return Whether or not there is a collision between the vehicleEntity and pedestrianEntity.
	 */
	private boolean checkCollision(VehicleEntity vehicleEntity, PedestrianEntity pedestrianEntity)
	{
		return Intersector.intersectRectangles(vehicleEntity.getBounds(), pedestrianEntity.getBounds());
	}

	/**
	 * Sets whether or not the user has a selected pedestrian.
	 *
	 * @param isSelected Whether or not the user has selected a pedestrian.
	 */
	public void setSelected(boolean isSelected)
	{
		selected = isSelected;
	}

	/**
	 * Spawns a new pedestrian into the gameWorld.
	 */
	private void spawnPedestrian()
	{
		PedestrianEntity temp = new PedestrianEntity(new Vector2(pedestrianEntities.first().getPosition().x +
				PedestrianEntity.SIZEX, pedestrianEntities.first().getPosition().y));

		if(temp.getPosition().x < 0 || temp.getPosition().x > GameRenderer.CAMERA_WIDTH - PedestrianEntity.SIZEX)
		{
			temp.getPosition().x = (temp.getPosition().x > 0) ? temp.getPosition().x : 0;
			temp.getPosition().x = (temp.getPosition().x < GameRenderer.CAMERA_WIDTH - PedestrianEntity.SIZEX)
					? temp.getPosition().x : GameRenderer.CAMERA_WIDTH - PedestrianEntity.SIZEX;
		}

		pedestrianEntities.add(temp);
	}

	/**
	 * Checks whether teh train is on the same track as another
	 *
	 * @param vehicle the train in question
	 * @return Whether or not the train fails the check or not
	 */
	private boolean checkTrain(VehicleEntity vehicle)
	{
		for(VehicleEntity temp : vehicleEntities)
		{
			if(vehicle != temp)
			{
				if(vehicle.getPosition().x > temp.getPosition().x - VehicleEntity.SIZEX && vehicle.getPosition().x <
                        temp.getPosition().x + VehicleEntity.SIZEX)
				{
					return true;
				}
			}
		}

		return false;
	}
}
