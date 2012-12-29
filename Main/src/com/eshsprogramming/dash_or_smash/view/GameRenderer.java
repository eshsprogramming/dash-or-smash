package com.eshsprogramming.dash_or_smash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.eshsprogramming.dash_or_smash.model.entity.BlockEntity;
import com.eshsprogramming.dash_or_smash.model.entity.PedestrianEntity;
import com.eshsprogramming.dash_or_smash.model.entity.VehicleEntity;
import com.eshsprogramming.dash_or_smash.model.gui.Text;
import com.eshsprogramming.dash_or_smash.model.world.GameWorld;

/**
 * Renderer for the gameWorld. Manages rendering of objects.
 *
 * @author Zachary Latta
 */
public class GameRenderer extends Renderer
{
	/**
	 * The columns in the spritesheets
	 */
	private static final int FRAME_COLUMNS = 2;
	/**
	 * The rows in the spritesheets.
	 */
	private static final int FRAME_ROWS = 2;

	/**
	 * The gameWorld itself.
	 */
	private GameWorld gameWorld;
	/**
	 * The camera used in rendering.
	 */
	private OrthographicCamera camera;

	/**
	 * For debug rendering
	 */
	private ShapeRenderer debugRenderer = new ShapeRenderer();
	/**
	 * An animation for idle red shirt pedestrians
	 */
	private SpriteAnimation pedestrianIdleRedshirtAnimation = null;
	/**
	 * An animation for idle purple shirt pedestrians
	 */
	private SpriteAnimation pedestrianIdlePurpleshirtAnimation = null;
	/**
	 * An animation for idle yellow shirt pedestrians
	 */
	private SpriteAnimation pedestrianIdleYellowshirtAnimation = null;
	/**
	 * An animation for dying red shirt pedestrians
	 */
	private SpriteAnimation pedestrianDyingRedshirtAnimation = null;
	/**
	 * An animation for dying purple shirt pedestrians
	 */
	private SpriteAnimation pedestrianDyingPurpleshirtAnimation = null;
	/**
	 * An animation for dying yellow shirt pedestrians
	 */
	private SpriteAnimation pedestrianDyingYellowshirtAnimation = null;
	/**
	 * An animation for red vehicles
	 */
	private SpriteAnimation vehicleRedAnimation = null;
	/**
	 * An animation for green Vehicles
	 */
	private SpriteAnimation vehicleGreenAnimation = null;
	/**
	 * An animation for blue vehicles
	 */
	private SpriteAnimation vehicleBlueAnimation = null;
	/**
	 * The texture for the blocks.
	 */
	private Texture blockTexture = null;

	/**
	 * Used for rendering sprites.
	 */
	private SpriteBatch spriteBatch = null;
	/**
	 * Whether or not debug information should be rendered.
	 */
	private boolean debug = false;
	/**
	 * Text used for displaying score.
	 */
	private Text scoreText = null;

	/**
	 * The time spent in the state represented by this animation
	 */
	float stateTime;

	/**
	 * Creates a new gameWorld renderer.
	 *
	 * @param gameWorld The gameWorld to be rendered.
	 * @param debug     Whether or not debug information should be rendered.
	 */
	public GameRenderer(GameWorld gameWorld, boolean debug)
	{
		this.gameWorld = gameWorld;
		this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.camera.update();
		this.debug = debug;
		this.scoreText = gameWorld.getScore();

		this.spriteBatch = new SpriteBatch();

		loadSpriteAnimations();

		stateTime = 0;
	}

	private void loadSpriteAnimations()
	{
		pedestrianIdleRedshirtAnimation = new SpriteAnimation(2, 2, .5f,
				new Texture(Gdx.files.internal("images/pedestrian_idle_redshirt_sheet.png")));
		pedestrianIdlePurpleshirtAnimation = new SpriteAnimation(2, 2, .5f,
				new Texture(Gdx.files.internal("images/pedestrian_idle_purpleshirt_sheet.png")));
		pedestrianIdleYellowshirtAnimation = new SpriteAnimation(2, 2, .5f,
				new Texture(Gdx.files.internal("images/pedestrian_idle_yellowshirt_sheet.png")));

		pedestrianDyingRedshirtAnimation = new SpriteAnimation(2, 2, .25f,
				new Texture(Gdx.files.internal("images/pedestrian_dying_redshirt_sheet.png")));
		pedestrianDyingPurpleshirtAnimation = new SpriteAnimation(2, 2, .25f,
				new Texture(Gdx.files.internal("images/pedestrian_dying_purpleshirt_sheet.png")));
		pedestrianDyingYellowshirtAnimation = new SpriteAnimation(2, 2, .25f,
				new Texture(Gdx.files.internal("images/pedestrian_dying_yellowshirt_sheet.png")));

		vehicleRedAnimation = new SpriteAnimation(2, 2, .25f,
				new Texture(Gdx.files.internal("images/vehicle_red_sheet.png")));
		vehicleGreenAnimation = new SpriteAnimation(2, 2, .25f,
				new Texture(Gdx.files.internal("images/vehicle_green_sheet.png")));
		vehicleBlueAnimation = new SpriteAnimation(2, 2, .25f,
				new Texture(Gdx.files.internal("images/vehicle_blue_sheet.png")));

		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
	}

	/**
	 * Renders things to be rendered on the gameWorld.
	 */
	public void render(float delta)
	{
		stateTime += delta;

		setCurrentFrames();

		spriteBatch.begin();
		drawBlocks();
		drawVehicles();
		drawPedestrians();
		drawScore();
		spriteBatch.end();

		if(debug)
		{
			drawDebug();
		}
	}

	/**
	 * Sets the current frames for pedestrian and vehicles
	 */
	private void setCurrentFrames()
	{
		pedestrianIdleRedshirtAnimation.updateCurrentFrame(stateTime, true);
		pedestrianIdlePurpleshirtAnimation.updateCurrentFrame(stateTime, true);
		pedestrianIdleYellowshirtAnimation.updateCurrentFrame(stateTime, true);

		pedestrianDyingRedshirtAnimation.updateCurrentFrame(stateTime, true);
		pedestrianDyingPurpleshirtAnimation.updateCurrentFrame(stateTime, true);
		pedestrianDyingYellowshirtAnimation.updateCurrentFrame(stateTime, true);

		vehicleRedAnimation.updateCurrentFrame(stateTime, true);
		vehicleGreenAnimation.updateCurrentFrame(stateTime, true);
		vehicleBlueAnimation.updateCurrentFrame(stateTime, true);
	}

	/**
	 * Draws blocks on the gameWorld.
	 */
	private void drawBlocks()
	{
		for(BlockEntity blockEntity : gameWorld.getBlockEntities())
		{
			spriteBatch.draw(blockTexture, blockEntity.getPosition().x * getPPuX(), blockEntity.getPosition().y * getPPuY(),
					BlockEntity.SIZEX * getPPuX(), BlockEntity.SIZEY * getPPuY());
		}
	}

	/**
	 * Draws vehicles on the gameWorld.
	 */
	private void drawVehicles()
	{
		for(VehicleEntity vehicleEntity : gameWorld.getVehicleEntities())
		{
			switch(vehicleEntity.getImageType())
			{
				case 0:
					spriteBatch.draw(vehicleRedAnimation.getCurrentFrame(), vehicleEntity.getPosition().x * getPPuX(),
							vehicleEntity.getPosition().y * getPPuY(), VehicleEntity.SIZEX * getPPuX(),
							VehicleEntity.SIZEY * getPPuY());
					break;

				case 1:
					spriteBatch.draw(vehicleGreenAnimation.getCurrentFrame(), vehicleEntity.getPosition().x * getPPuX(),
							vehicleEntity.getPosition().y * getPPuY(), VehicleEntity.SIZEX * getPPuX(),
							VehicleEntity.SIZEY * getPPuY());
					break;

				case 2:
					spriteBatch.draw(vehicleBlueAnimation.getCurrentFrame(), vehicleEntity.getPosition().x * getPPuX(),
							vehicleEntity.getPosition().y * getPPuY(), VehicleEntity.SIZEX * getPPuX(),
							VehicleEntity.SIZEY * getPPuY());
					break;

				default:
					System.out.println("Vehicle image type specified does not exist.");
					break;
			}
		}
	}

	/**
	 * Draws pedestrians on the gameWorld.
	 */
	private void drawPedestrians()
	{
		for(PedestrianEntity pedestrianEntity : gameWorld.getPedestrianEntities())
		{
			switch(pedestrianEntity.getImageType())
			{
				case 0:
					if(pedestrianEntity.getState().equals(PedestrianEntity.State.IDLE))
					{
						spriteBatch.draw(pedestrianIdleRedshirtAnimation.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					else if(pedestrianEntity.getState().equals(PedestrianEntity.State.DYING))
					{
						spriteBatch.draw(pedestrianDyingRedshirtAnimation.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					break;

				case 1:
					if(pedestrianEntity.getState().equals(PedestrianEntity.State.IDLE))
					{
						spriteBatch.draw(pedestrianIdlePurpleshirtAnimation.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					else if(pedestrianEntity.getState().equals(PedestrianEntity.State.DYING))
					{
						spriteBatch.draw(pedestrianDyingPurpleshirtAnimation.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					break;

				case 2:
					if(pedestrianEntity.getState().equals(PedestrianEntity.State.IDLE))
					{
						spriteBatch.draw(pedestrianIdleYellowshirtAnimation.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					else if(pedestrianEntity.getState().equals(PedestrianEntity.State.DYING))
					{
						spriteBatch.draw(pedestrianDyingYellowshirtAnimation.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					break;

				default:
					System.out.println("Pedestrian image type specified does not exist.");
			}
		}
	}

	/**
	 * Draws the score on the gameWorld.
	 */
	private void drawScore()
	{
		scoreText.setText(Integer.toString(gameWorld.getScore().getScore()));
		scoreText.render(spriteBatch, getPPuX(), getPPuY());
	}

	/**
	 * Draws debug information on the gameWorld.
	 */
	private void drawDebug()
	{
		debugRenderer.setProjectionMatrix(camera.combined);
		debugRenderer.begin(ShapeRenderer.ShapeType.Rectangle);

		for(BlockEntity blockEntity : gameWorld.getBlockEntities())
		{
			Rectangle rect = blockEntity.getBounds();

			float x1 = blockEntity.getPosition().x + rect.x;
			float y1 = blockEntity.getPosition().y + rect.y;

			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}

		for(VehicleEntity vehicleEntity : gameWorld.getVehicleEntities())
		{
			Rectangle rect = vehicleEntity.getBounds();

			float x1 = rect.x;
			float y1 = rect.y;

			debugRenderer.setColor(new Color(0, 1, 1, 0));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}

		for(PedestrianEntity pedestrianEntity : gameWorld.getPedestrianEntities())
		{
			Rectangle rect = pedestrianEntity.getBounds();

			float x1 = rect.x;
			float y1 = rect.y;

			debugRenderer.setColor(new Color(0, 1, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}

		debugRenderer.end();
	}
}
