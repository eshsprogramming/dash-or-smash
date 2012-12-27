package com.eshsprogramming.nudistrailroadexhibition;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * @author Zachary Latta
 */
public class DesktopStarter
{
	public static void main(String[] args)
	{
        // todo-zach Make world size relative.
		new LwjglApplication(new NudistRailroadExhibition(), "NudistEntity Railroad Exhibition", 640, 400 , true);
	}
}
