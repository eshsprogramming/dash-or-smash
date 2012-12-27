package com.eshsprogramming.nudistrailroadexhibition.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.NudistRailroadExhibition;
import com.eshsprogramming.nudistrailroadexhibition.controller.MainMenuController;
import com.eshsprogramming.nudistrailroadexhibition.model.world.MainMenuWorld;
import com.eshsprogramming.nudistrailroadexhibition.view.MainMenuRenderer;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

/**
 * The main menu of the game.
 *
 * @author Zachary Latta
 */
public class MainMenuScreen extends BaseScreen
{
    /**
     * The main menu.
     */
    private MainMenuWorld mainMenuWorld;
    /**
     * The main menu controller.
     */
    private MainMenuController controller;

    public MainMenuScreen(NudistRailroadExhibition game)
    {
        super(game);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        controller.update(delta,getGame());
    }

    @Override
    public void show()
    {
        super.show();

        mainMenuWorld = new MainMenuWorld(this);
        renderer = new MainMenuRenderer(mainMenuWorld);
        controller = new MainMenuController(mainMenuWorld, this);
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

        controller.setTouchPosition(new Vector2(x, y));

        return true;
    }
}
