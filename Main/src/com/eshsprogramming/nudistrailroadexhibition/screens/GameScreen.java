package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.controller.WorldController;
import com.eshsprogramming.nudistrailroadexhibition.model.entity.NudistEntity;
import com.eshsprogramming.nudistrailroadexhibition.model.world.GameWorld;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

/**
 * The screen that contains the gameplay.
 *
 * @author Zachary Latta, Benjamin Landers
 */
public class GameScreen extends BaseScreen
{
    /**
     * The game's world.
     */
    private GameWorld gameWorld;
    /**
     * The game's controller.
     */
    private WorldController controller;

    /**
     * The background music.
     */
    private Music music;

    /**
     * Creates a new game screen
     *
     * @param game The instance of Game.
     */
    public GameScreen(NudistRailroadExhibition game)
    {
        super(game);
    }

    /**
     * Called each frame to render and update the game.
     *
     * @param delta The time in milliseconds between frames.
     */
    @Override
    public void render(float delta)
    {
        super.render(delta);
        controller.update(delta);
    }

    /**
     * Called when this screen becomes the current screen for the game.
     */
    @Override
    public void show()
    {
        super.show();
        gameWorld = new GameWorld(getGame());
        renderer = new WorldRenderer(gameWorld, false);
        controller = new WorldController(gameWorld, getGame());
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/soundtrack/game.mp3"));

        music.setLooping(true);
        music.setVolume(0.7f);
        music.play();
    }

    /**
     * Called when this screen is no longer the current screen for the game.
     */
    @Override
    public void hide()
    {
        music.setLooping(false);
        music.stop();

        super.hide();
    }

    @Override
    public void dispose()
    {
        music.setLooping(false);
        music.stop();

        super.dispose();
    }

    ////////////////////////////////
    //   InputProcessor Methods   //
    ////////////////////////////////
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        float touchX = screenX;
        float touchY = getHeight() - screenY;

        float x = (touchX / getWidth()) * WorldRenderer.CAMERA_WIDTH;
        float y = (touchY / getHeight()) * WorldRenderer.CAMERA_HEIGHT;

        for(int index = 0; index < gameWorld.getNudists().size; index++)
        {
            NudistEntity nudistEntity = gameWorld.getNudists().get(index);

            // If the touch is on a nudistEntity
            if(x > nudistEntity.getPosition().x && x < nudistEntity.getPosition().x + nudistEntity.getBounds().width &&
                    y > nudistEntity.getPosition().y && y < nudistEntity.getPosition().y + nudistEntity.getBounds().height)
            {
                if(index != 0)
                {
                    // Swap the nudistEntity into index 0 of the array
                    gameWorld.getNudists().swap(0, index);
                }

                controller.setSelected(true);
                break;
            }

        }

        controller.setTouchPosition(new Vector2(x - NudistEntity.SIZEX / 2, y));

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
        float touchY = getHeight() - screenY;

        float x = (touchX / getWidth()) * WorldRenderer.CAMERA_WIDTH;
        float y = (touchY / getHeight()) * WorldRenderer.CAMERA_HEIGHT;

        controller.setTouchPosition(new Vector2(x - NudistEntity.SIZEX / 2, y));

        return true;
    }
}
