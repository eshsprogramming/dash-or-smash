package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.controller.WorldController;
import com.eshsprogramming.nudistrailroadexhibition.model.Nudist;
import com.eshsprogramming.nudistrailroadexhibition.model.World;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

/**
 * The screen that contains the gameplay.
 *
 * @author Zachary Latta
 */
public class GameScreen implements Screen, InputProcessor
{
    /**
     * The game instance. Used for switching screens.
     */
    private NudistRailroadExhibition game;

    /**
     * The game's world.
     */
    private World world;
    /**
     * The game's world renderer.
     */
    private WorldRenderer renderer;
    /**
     * The game's world controller.
     */
    private WorldController controller;

    /**
     * The background music.
     */
    private Music music;

    /**
     * The width of the world in pixels.
     */
    private float width;
    /**
     * The height of the world in pixels.
     */
    private float height;

    public GameScreen(NudistRailroadExhibition game)
    {
        this.game = game;
    }

    /**
     * Called each frame to render and update the game.
     *
     * @param delta The time in milliseconds between frames.
     */
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        renderer.render(delta);
        controller.update(delta);
    }

    /**
     * Called when the window is resized. Changes the width and size of the game.
     *
     * @param width  The new width of the screen in pixels.
     * @param height The new height of the screen in pixels.
     */
    @Override
    // todo-ben Fix resizing issues for the game screen
    public void resize(int width, int height)
    {
        renderer.setSize(width, height);
        this.width = width;
        this.height = height;
    }

    /**
     * Called when this screen becomes the current screen for the game.
     */
    @Override
    public void show()
    {
        world = new World();
        renderer = new WorldRenderer(world, false);
        controller = new WorldController(world, game);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));

        music.setLooping(true);
        music.setVolume(0.7f);
        music.play();

        Gdx.input.setInputProcessor(this);
    }

    /**
     * Called when this screen is no longer the current screen for the game.
     */
    @Override
    public void hide()
    {
        music.setLooping(false);
        music.stop();

        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
        music.setLooping(false);
        music.stop();

        Gdx.input.setInputProcessor(null);
    }

    ////////////////////////////////
    //   InputProcessor Methods   //
    ////////////////////////////////

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        float touchX = screenX;
        float touchY = height - screenY;

        float x = (touchX / width) * WorldRenderer.CAMERA_WIDTH;
        float y = (touchY / height) * WorldRenderer.CAMERA_HEIGHT;

        for(int index = 0; index < world.getNudists().size; index++)
        {
            Nudist nudist = world.getNudists().get(index);

            // If the touch is on a nudist
            if(x > nudist.getPosition().x && x < nudist.getPosition().x + nudist.getBounds().width &&
                    y > nudist.getPosition().y && y < nudist.getPosition().y + nudist.getBounds().height)
            {
                if(index != 0)
                {
                    // Swap the nudist into index 0 of the array
                    world.getNudists().swap(0, index);
                }

                controller.setSelected(true);
                break;
            }

        }

        controller.setTouchPosition(new Vector2(x - Nudist.SIZEX / 2, y));

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        float touchX = screenX;
        float touchY = height - screenY;

        float x = (touchX / width) * WorldRenderer.CAMERA_WIDTH;
        float y = (touchY / height) * WorldRenderer.CAMERA_HEIGHT;

        controller.setTouchPosition(new Vector2(x - Nudist.SIZEX / 2, y));

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
