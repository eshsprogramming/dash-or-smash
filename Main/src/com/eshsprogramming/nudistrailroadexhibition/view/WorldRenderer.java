package com.eshsprogramming.nudistrailroadexhibition.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.eshsprogramming.nudistrailroadexhibition.model.Block;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.Train;
import com.eshsprogramming.nudistrailroadexhibition.model.World;

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
	 * The texture for the nudists.
	 */
	private Texture nudistTexture;
	/**
	 * The texture for the trains.
	 */
	private Texture trainTexture;
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
	private BitmapFont font = new BitmapFont();
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
		font.setScale(4f);
		loadTextures();
	}

	/**
	 * Loads the textures from files.
	 */
	private void loadTextures()
	{
		nudistTexture = new Texture(Gdx.files.internal("images/nudist_01.png"));
		trainTexture = new Texture(Gdx.files.internal("images/train_01.png"));
		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
	}

	/**
	 * Renders things to be rendered on the screen.
	 */
	public void render()
	{
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
			spriteBatch.draw(nudistTexture, nudist.getPosition().x * ppuX, nudist.getPosition().y * ppuY,
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
			spriteBatch.draw(trainTexture, train.getPosition().x * ppuX, train.getPosition().y * ppuY,
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
		  font.draw(spriteBatch,""+world.getScore().getScore(),1000,800);
	}
}
