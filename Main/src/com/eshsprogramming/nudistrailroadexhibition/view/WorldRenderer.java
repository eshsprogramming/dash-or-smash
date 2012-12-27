package com.eshsprogramming.nudistrailroadexhibition.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.model.gui.Text;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.BlockEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.NudistEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.TrainEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.world.GameWorld;

/**
 * Renderer for the gameWorld. Manages rendering of objects.
 *
 * @author Zachary Latta
 */
public class WorldRenderer
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
     * The width of the gameWorld in pixels.
     */
    private int width;
    /**
     * The height of the gameWorld in pixels.
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
     * Text used for displaying score.
     */
    private Text scoreText;

    /**
     * Creates a new gameWorld renderer.
     *
     * @param gameWorld The gameWorld to be rendered.
     * @param debug     Whether or not debug information should be rendered.
     */
    public WorldRenderer(GameWorld gameWorld, boolean debug)
    {
        this.gameWorld = gameWorld;
        this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        this.camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        this.camera.update();
        this.debug = debug;
        this.scoreText = gameWorld.getScore();

        spriteBatch = new SpriteBatch();

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
        nudistIdleAnimation = new Animation(0.5f, nudistIdleFrames);
        nudistDyingAnimation = new Animation(0.25f, nudistDyingFrames);

        trainAnimation = new Animation(0.25f, trainFrames);
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
        for(NudistEntity nudistEntity : gameWorld.getNudists())
        {
            if(nudistEntity.getState() == NudistEntity.State.IDLE)
            {
                nudistCurrentFrame = nudistIdleAnimation.getKeyFrame(stateTime, true);
            }
            else if(nudistEntity.getState() == NudistEntity.State.DYING)
            {
                nudistCurrentFrame = nudistDyingAnimation.getKeyFrame(stateTime, true);
            }
        }

        trainCurrentFrame = trainAnimation.getKeyFrame(stateTime, true);
    }

    /**
     * Draws blocks on the gameWorld.
     */
    private void drawBlocks()
    {
        for(BlockEntity blockEntity : gameWorld.getBlocks())
        {
            spriteBatch.draw(blockTexture, blockEntity.getPosition().x * ppuX, blockEntity.getPosition().y * ppuY,
                    BlockEntity.SIZE * ppuX, BlockEntity.SIZE * ppuY);
        }
    }

    /**
     * Draws trains on the gameWorld.
     */
    private void drawTrains()
    {
        for(TrainEntity trainEntity : gameWorld.getTrains())
        {
            spriteBatch.draw(trainCurrentFrame, trainEntity.getPosition().x * ppuX, trainEntity.getPosition().y * ppuY,
                    TrainEntity.SIZEX * ppuX, TrainEntity.SIZEY * ppuY);
        }
    }

    /**
     * Draws nudists on the gameWorld.
     */
    private void drawNudists()
    {
        for(NudistEntity nudistEntity : gameWorld.getNudists())
        {
            spriteBatch.draw(nudistCurrentFrame, nudistEntity.getPosition().x * ppuX, nudistEntity.getPosition().y * ppuY,
                    NudistEntity.SIZEX * ppuX, NudistEntity.SIZEY * ppuY);
        }
    }

    /**
     * Draws the score on the gameWorld.
     */
    private void drawScore()
    {
        scoreText.setText(Integer.toString(gameWorld.getScore().getScore()));
        scoreText.render(spriteBatch, ppuX, ppuY);
    }

    /**
     * Draws debug information on the gameWorld.
     */
    private void drawDebug()
    {
        debugRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Rectangle);

        for(BlockEntity blockEntity : gameWorld.getBlocks())
        {
            Rectangle rect = blockEntity.getBounds();

            float x1 = blockEntity.getPosition().x + rect.x;
            float y1 = blockEntity.getPosition().y + rect.y;

            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
        }

        for(TrainEntity trainEntity : gameWorld.getTrains())
        {
            Rectangle rect = trainEntity.getBounds();

            float x1 = rect.x;
            float y1 = rect.y;

            debugRenderer.setColor(new Color(0, 1, 1, 0));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
        }

        for(NudistEntity nudistEntity : gameWorld.getNudists())
        {
            Rectangle rect = nudistEntity.getBounds();

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
     * @param width  The width of the window in pixels.
     * @param height The height of the window in pixels.
     */
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        ppuX = (float) width / CAMERA_WIDTH;
        ppuY = (float) height / CAMERA_HEIGHT;
    }
}
