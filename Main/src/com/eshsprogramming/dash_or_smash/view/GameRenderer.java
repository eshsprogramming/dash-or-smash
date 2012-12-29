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
	 * An animation for Idle red shirts
	 */
	private SpriteAnimation pedestrianIdleRedshirtAnim = null;
	/**
	 * An animation for Idle purple shirts
	 */
	private SpriteAnimation pedestrianIdlePurpleshirtAnim = null;
	/**
	 * An animation for Idle yellow shirts
	 */
	private SpriteAnimation pedestrianIdleYellowshirtAnim = null;
	/**
	 * An animation for Dying red shirts
	 */
	private SpriteAnimation pedestrianDyingRedshirtAnim = null;
	/**
	 * An animation for Dying purple shirts
	 */
	private SpriteAnimation pedestrianDyingPurpleshirtAnim = null;
	/**
	 * An animation for Dying yellow shirts
	 */
	private SpriteAnimation pedestrianDyingYellowshirtAnim = null;
	/**
	 * An animation for Red vehicles
	 */
	private SpriteAnimation vehicleRedAnim = null;
	/**
	 * An animation for Green Vehicles
	 */
	private SpriteAnimation vehicleGreenAnim = null;
	/**
	 * An animation for Blue vehicles
	 */
	private SpriteAnimation vehicleBlueAnim = null;
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
		pedestrianIdleRedshirtAnim = new SpriteAnimation(2, 2, .5f,
				new Texture(Gdx.files.internal("images/pedestrian_idle_redshirt_sheet.png")));
		pedestrianIdlePurpleshirtAnim = new SpriteAnimation(2, 2, .5f,
				new Texture(Gdx.files.internal("images/pedestrian_idle_purpleshirt_sheet.png")));
		pedestrianIdleYellowshirtAnim = new SpriteAnimation(2, 2, .5f,
				new Texture(Gdx.files.internal("images/pedestrian_idle_yellowshirt_sheet.png")));
		pedestrianDyingRedshirtAnim = new SpriteAnimation(2, 2, .25f,
				new Texture(Gdx.files.internal("images/pedestrian_dying_redshirt_sheet.png")));
		pedestrianDyingPurpleshirtAnim = new SpriteAnimation(2, 2, .25f,
				new Texture(Gdx.files.internal("images/pedestrian_dying_purpleshirt_sheet.png")));
		pedestrianDyingYellowshirtAnim = new SpriteAnimation(2, 2, .25f,
				new Texture(Gdx.files.internal("images/pedestrian_dying_yellowshirt_sheet.png")));
		vehicleRedAnim = new SpriteAnimation(2, 2, .25f, new Texture(Gdx.files.internal("images/vehicle_red_sheet.png")));
		vehicleGreenAnim = new SpriteAnimation(2, 2, .25f, new Texture(Gdx.files.internal("images/vehicle_green_sheet.png")));
		vehicleBlueAnim = new SpriteAnimation(2, 2, .25f, new Texture(Gdx.files.internal("images/vehicle_blue_sheet.png")));

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
		pedestrianIdleRedshirtAnim.updateCurrentFrame(stateTime, true);
		pedestrianIdlePurpleshirtAnim.updateCurrentFrame(stateTime, true);
		pedestrianIdleYellowshirtAnim.updateCurrentFrame(stateTime, true);

		pedestrianDyingRedshirtAnim.updateCurrentFrame(stateTime, true);
		pedestrianDyingPurpleshirtAnim.updateCurrentFrame(stateTime, true);
		pedestrianDyingYellowshirtAnim.updateCurrentFrame(stateTime, true);

		vehicleRedAnim.updateCurrentFrame(stateTime, true);
		vehicleGreenAnim.updateCurrentFrame(stateTime, true);
		vehicleBlueAnim.updateCurrentFrame(stateTime, true);
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
					spriteBatch.draw(vehicleRedAnim.getCurrentFrame(), vehicleEntity.getPosition().x * getPPuX(),
							vehicleEntity.getPosition().y * getPPuY(), VehicleEntity.SIZEX * getPPuX(),
							VehicleEntity.SIZEY * getPPuY());
					break;

				case 1:
					spriteBatch.draw(vehicleGreenAnim.getCurrentFrame(), vehicleEntity.getPosition().x * getPPuX(),
							vehicleEntity.getPosition().y * getPPuY(), VehicleEntity.SIZEX * getPPuX(),
							VehicleEntity.SIZEY * getPPuY());
					break;

				case 2:
					spriteBatch.draw(vehicleBlueAnim.getCurrentFrame(), vehicleEntity.getPosition().x * getPPuX(),
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
						spriteBatch.draw(pedestrianIdleRedshirtAnim.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					else if(pedestrianEntity.getState().equals(PedestrianEntity.State.DYING))
					{
						spriteBatch.draw(pedestrianDyingRedshirtAnim.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					break;

				case 1:
					if(pedestrianEntity.getState().equals(PedestrianEntity.State.IDLE))
					{
						spriteBatch.draw(pedestrianIdlePurpleshirtAnim.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					else if(pedestrianEntity.getState().equals(PedestrianEntity.State.DYING))
					{
						spriteBatch.draw(pedestrianDyingPurpleshirtAnim.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					break;

				case 2:
					if(pedestrianEntity.getState().equals(PedestrianEntity.State.IDLE))
					{
						spriteBatch.draw(pedestrianIdleYellowshirtAnim.getCurrentFrame(),
								pedestrianEntity.getPosition().x * getPPuX(),
								pedestrianEntity.getPosition().y * getPPuY(),
								PedestrianEntity.SIZEX * getPPuX(),
								PedestrianEntity.SIZEY * getPPuY());
					}
					else if(pedestrianEntity.getState().equals(PedestrianEntity.State.DYING))
					{
						spriteBatch.draw(pedestrianDyingYellowshirtAnim.getCurrentFrame(),
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
