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
		new LwjglApplication(new NudistRailroadExhibition(), "Nudist Railroad Exhibition", 1280, 800, true);
	}
}
