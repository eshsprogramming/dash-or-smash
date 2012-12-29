package com.eshsprogramming.dash_or_smash.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	 * The width of the gameWorld in relative units.
	 */
	public static final float CAMERA_WIDTH = 8f;
	/**
	 * The height of the gameWorld in relative units.
	 */
	public static final float CAMERA_HEIGHT = 5f;

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
	 * The idle animation of the pedestrians.
	 */
	private Animation pedestrianIdleAnimation = null;
	/**
	 * The dying animation of the pedestrians.
	 */
	private Animation pedestrianDyingAnimation = null;
	/**
	 * The animation for the red vehicles.
	 */
	private Animation vehicleRedAnimation = null;
    /**
     * The animation for the green vehicles.
     */
    private Animation vehicleGreenAnimation = null;
	/**
	 * The animation for the blue vehicles.
	 */
	private Animation vehicleBlueAnimation = null;

	/**
	 * The texture for the pedestrians idle animation.
	 */
	private Texture pedestrianIdleSheet = null;
	/**
	 * The texture for the pedestrians dying animation.
	 */
	private Texture pedestrianDyingSheet = null;
	/**
	 * The texture for the red vehicle animation.
	 */
	private Texture vehicleRedSheet = null;
    /**
     * The texture for the green vehicle animation.
     */
    private Texture vehicleGreenSheet = null;
	/**
	 * The texture for the blue vehicle animation.
	 */
	private Texture vehicleBlueSheet = null;

	/**
	 * The frames of the nudist idle animation.
	 */
	private TextureRegion[] pedestrianIdleFrames = null;
	/**
	 * The frames of the nudist dying animation.
	 */
	private TextureRegion[] pedestrianDyingFrames = null;
	/**
	 * The frames of the red vehicle animation.
	 */
	private TextureRegion[] vehicleRedFrames = null;
	/**
	 * The frames of the green vehicle animation.
	 */
	private TextureRegion[] vehicleGreenFrames = null;
	/**
	 * The frames of the blue vehicle animation.
	 */
	private TextureRegion[] vehicleBlueFrames = null;

	/**
	 * The current frame of animation for the pedestrians.
	 */
	private TextureRegion pedestrianCurrentFrame = null;
	/**
	 * The current frame of the red vehicle animation.
	 */
	private TextureRegion vehicleRedCurrentFrame = null;
    /**
     * The current frame of the green vehicle animation.
     */
    private TextureRegion vehicleGreenCurrentFrame = null;
	/**
	 * The current frame of the blue vehicle animation.
	 */
	private TextureRegion vehicleBlueCurrentFrame = null;

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
	 * A summation of the time between frames during the animation.
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

		loadTextures();
		initTextureRegions();
		initAnimations();

		stateTime = 0;
	}

	/**
	 * Loads the textures from files.
	 */
	private void loadTextures()
	{
		pedestrianIdleSheet = new Texture(Gdx.files.internal("images/pedestrian_idle_sheet.png"));
		pedestrianDyingSheet = new Texture(Gdx.files.internal("images/pedestrian_dying_sheet.png"));
		vehicleRedSheet = new Texture(Gdx.files.internal("images/vehicle_red_sheet.png"));
        vehicleGreenSheet = new Texture(Gdx.files.internal("images/vehicle_green_sheet.png"));
		vehicleBlueSheet = new Texture(Gdx.files.internal("images/vehicle_blue_sheet.png"));
		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
	}

	/**
	 * Sets up and populates the texture regions.
	 */
	private void initTextureRegions()
	{
		pedestrianIdleFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];
		pedestrianDyingFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];

		vehicleRedFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];
        vehicleGreenFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];
		vehicleBlueFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];

		loadFrames(pedestrianIdleFrames, pedestrianIdleSheet);
        loadFrames(pedestrianDyingFrames, pedestrianDyingSheet);
        loadFrames(vehicleRedFrames, vehicleRedSheet);
        loadFrames(vehicleGreenFrames, vehicleGreenSheet);
		loadFrames(vehicleBlueFrames, vehicleBlueSheet);
	}

    /**
     * Loads frames from a spritesheet into a TextureRegion[]
     */
    private void loadFrames(TextureRegion[] frames, Texture sheet)
    {
        TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth() / FRAME_COLUMNS,
                sheet.getHeight() / FRAME_ROWS);

        int index = 0;
        for(int i = 0; i < FRAME_ROWS; i++)
        {
            for(int j = 0; j < FRAME_COLUMNS; j++)
            {
                frames[index++] = tmp[i][j];
            }
        }
    }


	/**
	 * Sets up animations
	 */
	private void initAnimations()
	{
		pedestrianIdleAnimation = new Animation(0.5f, pedestrianIdleFrames);
		pedestrianDyingAnimation = new Animation(0.25f, pedestrianDyingFrames);

		vehicleRedAnimation = new Animation(0.25f, vehicleRedFrames);
        vehicleGreenAnimation = new Animation(0.25f, vehicleGreenFrames);
		vehicleBlueAnimation = new Animation(0.25f, vehicleBlueFrames);
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
		for(PedestrianEntity pedestrianEntity : gameWorld.getPedestrianEntities())
		{
			if(pedestrianEntity.getState() == PedestrianEntity.State.IDLE)
			{
				pedestrianCurrentFrame = pedestrianIdleAnimation.getKeyFrame(stateTime, true);
			}
			else if(pedestrianEntity.getState() == PedestrianEntity.State.DYING)
			{
				pedestrianCurrentFrame = pedestrianDyingAnimation.getKeyFrame(stateTime, true);
			}
		}

		vehicleRedCurrentFrame = vehicleRedAnimation.getKeyFrame(stateTime, true);
        vehicleGreenCurrentFrame = vehicleGreenAnimation.getKeyFrame(stateTime, true);
		vehicleBlueCurrentFrame = vehicleBlueAnimation.getKeyFrame(stateTime, true);
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
					spriteBatch.draw(vehicleRedCurrentFrame, vehicleEntity.getPosition().x * getPPuX(),
							vehicleEntity.getPosition().y * getPPuY(), VehicleEntity.SIZEX * getPPuX(),
							VehicleEntity.SIZEY * getPPuY());
					break;

				case 1:
					spriteBatch.draw(vehicleGreenCurrentFrame, vehicleEntity.getPosition().x * getPPuX(),
							vehicleEntity.getPosition().y * getPPuY(), VehicleEntity.SIZEX * getPPuX(),
							VehicleEntity.SIZEY * getPPuY());
					break;

				case 2:
					spriteBatch.draw(vehicleBlueCurrentFrame, vehicleEntity.getPosition().x * getPPuX(),
							vehicleEntity.getPosition().y * getPPuY(), VehicleEntity.SIZEX * getPPuX(),
							VehicleEntity.SIZEY * getPPuY());
					break;

				default:
					System.out.println("Image type specified does not exist.");
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
			spriteBatch.draw(pedestrianCurrentFrame, pedestrianEntity.getPosition().x * getPPuX(), pedestrianEntity.getPosition().y * getPPuY(),
					PedestrianEntity.SIZEX * getPPuX(), PedestrianEntity.SIZEY * getPPuY());
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
