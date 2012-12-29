package com.eshsprogramming.dash_or_smash;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 * @author Zachary Latta
 */
public class DesktopStarter
{
	public static void main(String[] args)
	{
		new LwjglApplication(new DashOrSmash(), "Dash Or Smash", 1280, 800, true);
	}
}
