package com.eshsprogramming.nudistrailroadexhibition;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 * @author Zachary Latta
 */
public class DesktopStarter
{
	public static void main(String[] args)
	{
		new LwjglApplication(new DashOrSmash(), "Dash Or Smash", 640, 400, true);
	}
}
