package com.eshsprogramming.nudistrailroadexhibition.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.eshsprogramming.nudistrailroadexhibition.model.Block;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.World;

/**
 * @author Zachary Latta
 */
public class WorldRenderer
{
	public static final float CAMERA_WIDTH = 8f;
	public static final float CAMERA_HEIGHT = 5f;

	private World world;
	private OrthographicCamera camera;

	/**
	 * For debug rendering
	 */
	ShapeRenderer debugRenderer = new ShapeRenderer();

	private Texture nudistTexture;
	private Texture blockTexture;

	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	/**
	 * Pixels per unit on the X axis
	 */
	private float ppuX;
	/**
	 * Pixels per unit on the Y axis
	 */
	private float ppuY;

	public WorldRenderer(World world, boolean debug)
	{
		this.world = world;
		this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.camera.update();
		this.debug = debug;
		spriteBatch = new SpriteBatch();

		loadTextures();
	}

	private void loadTextures()
	{
		nudistTexture = new Texture(Gdx.files.internal("images/nudist_01.png"));
		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
	}

	public void render()
	{
		spriteBatch.begin();
		drawBlocks();
		drawNudists();
		spriteBatch.end();

		if(debug)
		{
			drawDebug();
		}
	}

	private void drawBlocks()
	{
		for(Block block : world.getBlocks())
		{
			spriteBatch.draw(blockTexture, block.getPosition().x * ppuX, block.getPosition().y * ppuY,
					Block.SIZE * ppuX, Block.SIZE * ppuY);
		}
	}

	private void drawNudists()
	{
		for(Nudist nudist : world.getNudists())
		{
			spriteBatch.draw(nudistTexture, nudist.getPosition().x * ppuX, nudist.getPosition().y * ppuY,
					Nudist.SIZE * ppuX, Nudist.SIZE * ppuY);
		}
	}

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

	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}
}
