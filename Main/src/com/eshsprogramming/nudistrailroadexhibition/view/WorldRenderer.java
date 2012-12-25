package com.eshsprogramming.nudistrailroadexhibition.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.eshsprogramming.nudistrailroadexhibition.model.Block;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.Train;
import com.eshsprogramming.nudistrailroadexhibition.model.World;

import java.io.File;

/**
 * Renderer for the world. Manages rendering of objects.
 *
 * @author Zachary Latta
 */
public class WorldRenderer
{
	/**
	 * The width of the screen in relative units.
	 */
	public static final float CAMERA_WIDTH = 8f;
	/**
	 * The height of the screen in relative units.
	 */
	public static final float CAMERA_HEIGHT = 5f;

	private static final int FRAME_COLUMNS = 2;
	private static final int FRAME_ROWS = 2;

	/**
	 * The world itself.
	 */
	private World world;
	/**                     
	 * The camera used in rendering.
	 */
	private OrthographicCamera camera;

	/**
	 * For debug rendering
	 */
	ShapeRenderer debugRenderer = new ShapeRenderer();

	/**
	 * The idle animation of the nudists.
	 */
	private Animation nudistIdleAnimation;
	/**
	 * The dying animation of the nudists.
	 */
	private Animation nudistDyingAnimation;
	/**
	 * The animation for the trains.
	 */
	private Animation trainAnimation;

	/**
	 * The texture for the nudist idle animation.
	 */
	private Texture nudistIdleSheet;
	/**
	 * The texture for the nudist dying animation.
	 */
	private Texture nudistDyingSheet;
	/**
	 * The texture for the train animation.
	 */
	private Texture trainSheet;

	/**
	 * The frames of the nudist idle animation.
	 */
	private TextureRegion[] nudistIdleFrames;
	/**
	 * The frames of the nudist dying animation.
	 */
	private TextureRegion[] nudistDyingFrames;
	/**
	 * The current frame of animation for the nudists.
	 */
	private TextureRegion nudistCurrentFrame;
	/**
	 * The frames of the train animation.
	 */
	private TextureRegion[] trainFrames;
	/**
	 * The current frame of the train animation.
	 */
	private TextureRegion trainCurrentFrame;

	/**
	 * A summation of the time between frames during the animation.
	 */
	float stateTime;

	/**
	 * The texture for the blocks.
	 */
	private Texture blockTexture;

	/**
	 * Used for rendering sprites.
	 */
	private SpriteBatch spriteBatch;
	/**
	 * Whether or not debug information should be rendered.
	 */
	private boolean debug = false;

	/**
	 * The width of the screen in pixels.
	 */
	private int width;
	/**
	 * The height of the screen in pixels.
	 */
	private int height;
	/**
	 * Pixels per unit on the X axis
	 */
	private float ppuX;
	/**
	 * Pixels per unit on the Y axis
	 */
	private float ppuY;
	/**
	 * Basic font
	 */
	private BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/arial/font.fnt"), false);
	/**
	 * Creates a new world renderer.
	 *
	 * @param world The world to be rendered.
	 * @param debug Whether or not debug information should be rendered.
	 */
	public WorldRenderer(World world, boolean debug)
	{
		this.world = world;
		this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.camera.update();
		this.debug = debug;
		
		spriteBatch = new SpriteBatch();
		
		loadTextures();
		initTextureRegions();
		initAnimations();

		font.setScale(4f);

		stateTime = 0;
	}

	/**
	 * Loads the textures from files.
	 */
	private void loadTextures()
	{
		nudistIdleSheet = new Texture(Gdx.files.internal("images/nudist_idle_sheet.png"));
		nudistDyingSheet = new Texture(Gdx.files.internal("images/nudist_dying_sheet.png"));
		trainSheet = new Texture(Gdx.files.internal("images/train_sheet.png"));
		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
	}

	/**
	 * Sets up and populates the texture regions.
	 */
	private void initTextureRegions()
	{
		nudistIdleFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];
		nudistDyingFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];

		trainFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];

		{
			TextureRegion[][] tmp = TextureRegion.split(nudistIdleSheet, nudistIdleSheet.getWidth() / FRAME_COLUMNS,
					nudistIdleSheet.getHeight() / FRAME_ROWS);

			int index = 0;
			for(int i = 0; i < FRAME_ROWS; i++)
			{
				for(int j = 0; j < FRAME_COLUMNS; j++)
				{
					nudistIdleFrames[index++] = tmp[i][j];
				}
			}
		}

		{
			TextureRegion[][] tmp = TextureRegion.split(nudistDyingSheet, nudistDyingSheet.getWidth() / FRAME_COLUMNS,
					nudistDyingSheet.getHeight() / FRAME_ROWS);

			int index = 0;
			for(int i = 0; i < FRAME_ROWS; i++)
			{
				for(int j = 0; j < FRAME_COLUMNS; j++)
				{
					nudistDyingFrames[index++] = tmp[i][j];
				}
			}
		}

		{
			TextureRegion[][] tmp = TextureRegion.split(trainSheet, trainSheet.getWidth() / FRAME_COLUMNS,
					trainSheet.getHeight() / FRAME_ROWS);

			int index = 0;
			for(int i = 0; i < FRAME_ROWS; i++)
			{
				for(int j = 0; j < FRAME_COLUMNS; j++)
				{
					trainFrames[index++] = tmp[i][j];
				}
			}
		}
	}

	/**
	 * Sets up animations
	 */
	private void initAnimations()
	{
		nudistIdleAnimation = new Animation(0.25f, nudistIdleFrames);
		nudistDyingAnimation = new Animation(0.25f, nudistDyingFrames);

		trainAnimation = new Animation(0.25f, trainFrames);
	}

	/**
	 * Renders things to be rendered on the screen.
	 */
	public void render(float delta)
	{
		stateTime += delta;

		setCurrentFrames();

		spriteBatch.begin();
		drawBlocks();
		drawTrains();
		drawNudists();
		drawScore();
		spriteBatch.end();
		if(debug)
		{
			drawDebug();
		}
	}

	/**
	 * Sets the current frames for nudists and trains
	 */
	private void setCurrentFrames()
	{
		for(Nudist nudist : world.getNudists())
		{
			if(nudist.getState() == Nudist.State.IDLE)
			{
				nudistCurrentFrame = nudistIdleAnimation.getKeyFrame(stateTime, true);
			}
			else if(nudist.getState() == Nudist.State.DYING)
			{
				nudistCurrentFrame = nudistDyingAnimation.getKeyFrame(stateTime, true);
			}
		}

		for(Train train : world.getTrains())
		{
			trainCurrentFrame = trainAnimation.getKeyFrame(stateTime, true);
		}
	}

	/**
	 * Draws blocks on the screen.
	 */
	private void drawBlocks()
	{
		for(Block block : world.getBlocks())
		{
			spriteBatch.draw(blockTexture, block.getPosition().x * ppuX, block.getPosition().y * ppuY,
					Block.SIZE * ppuX, Block.SIZE * ppuY);
		}
	}

	/**
	 * Draws nudists on the screen.
	 */
	private void drawNudists()
	{
		for(Nudist nudist : world.getNudists())
		{
			spriteBatch.draw(nudistCurrentFrame, nudist.getPosition().x * ppuX, nudist.getPosition().y * ppuY,
					Nudist.SIZE * ppuX, Nudist.SIZE * ppuY);
		}
	}

	/**
	 * Draws trains on the screen.
	 */
	private void drawTrains()
	{
		for(Train train : world.getTrains())
		{
			spriteBatch.draw(trainCurrentFrame, train.getPosition().x * ppuX, train.getPosition().y * ppuY,
					Train.SIZEX * ppuX, Train.SIZEY * ppuY);
		}
	}

	/**
	 * Draws debug information on the screen.
	 */
	private void drawDebug()
	{
		debugRenderer.setProjectionMatrix(camera.combined);
		debugRenderer.begin(ShapeRenderer.ShapeType.Rectangle);

		for(Block block : world.getBlocks())
		{
			Rectangle rect = block.getBounds();

			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;

			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}

		for(Train train : world.getTrains())
		{
			Rectangle rect = train.getBounds();

			float x1 = rect.x;
			float y1 = rect.y;

			debugRenderer.setColor(new Color(0, 1, 1, 0));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}

		for(Nudist nudist : world.getNudists())
		{
			Rectangle rect = nudist.getBounds();

			float x1 = rect.x;
			float y1 = rect.y;

			debugRenderer.setColor(new Color(0, 1, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}

		debugRenderer.end();
	}

	/**
	 * Sets the size of the window in pixels.
	 *
	 * @param width The width of the window in pixels.
	 * @param height The height of the window in pixels.
	 */
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}
	
	private void drawScore()
	{
		// todo Make score display correctly on Android devices.
		font.draw(spriteBatch, "" + world.getScore().getScore(), 6.25f * ppuX, 4.75f * ppuY);
	}
}
