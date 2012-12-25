package com.eshsprogramming.nudistrailroadexhibition.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Text.
 *
 * @author Zachary Latta
 */
public class Text
{
    /**
     * The internal font used.
     */
    private BitmapFont bitmapFont = null;
    /**
     * The position of the text in relative units.
     */
    private Vector2 position = null;
    /**
     * The text to display.
     */
    private String text;

    /**
     * Creates a new Text object.
     *
     * @param ref The location of the .fnt file to use.
     * @param flip Whether or not the text should be flipped.
     */
    public Text(String ref, boolean flip)
    {
        bitmapFont = new BitmapFont(Gdx.files.internal(ref), flip);
    }

    /**
     * Creates a new Text object.
     *
     * @param ref The location of the .fnt file to use.
     * @param flip Whether or not the text should be flipped.
     * @param text The text to be displayed.
     */
    public Text(String ref, boolean flip, String text)
    {
        bitmapFont = new BitmapFont(Gdx.files.internal(ref), flip);
        this.position = position;
        this.text = text;
    }

    /**
     * Creates a new Text object.
     *
     * @param ref The location of the .fnt file to use.
     * @param position The position of the text.
     */
    public Text(String ref, boolean flip, Vector2 position)
    {
        bitmapFont = new BitmapFont(Gdx.files.internal(ref), flip);
    }

    /**
     * Creates a new Text object.
     *
     * @param ref The location of the .fnt file to use.
     * @param flip Whether or not the text should be flipped.
     * @param position The position of the text.
     * @param text The text to be displayed.
     */
    public Text(String ref, boolean flip, Vector2 position, String text)
    {
        bitmapFont = new BitmapFont(Gdx.files.internal(ref), flip);
        this.position = position;
        this.text = text;
    }

    /**
     * Draws the text on the screen.
     *
     * @param spriteBatch The spritebatch to use for drawing.
     * @param ppuX Pixels per unit on the x axis.
     * @param ppuY Pixels per unit on the y axis.
     */
    public void render(SpriteBatch spriteBatch, float ppuX, float ppuY)
    {
        bitmapFont.draw(spriteBatch, text, getPosition().x * ppuX, getPosition().y * ppuY);
    }

    /**
     * Returns the position of the text.
     *
     * @return The position of the text.
     */
    public Vector2 getPosition()
    {
        return this.position;
    }

    /**
     * Returns the text string of the text object.
     *
     * @return The string of text in the object.
     */
    public String getText()
    {
        return this.text;
    }

    /**
     * Sets the position of the text.
     *
     * @param position The new position of the text
     */
    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    /**
     * Sets the text to be displayed.
     *
     * @param text The new text to be displayed.
     */
    public void setText(String text)
    {
        this.text = text;
    }
}
