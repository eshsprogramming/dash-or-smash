package com.eshsprogramming.nudistrailroadexhibition.model.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.eshsprogramming.nudistrailroadexhibition.view.WorldRenderer;

/**
 * Text.
 *
 * @author Zachary Latta
 */
// todo-zach Make text fit nicely into the new organizational structure
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
    private String text = null;

    /**
     * Creates a new Text object.
     *
     * @param ref  The location of the .fnt file to use.
     * @param flip Whether or not the text should be flipped.
     */
    public Text(String ref, boolean flip)
    {
        this.bitmapFont = new BitmapFont(Gdx.files.internal(ref), flip);
    }

    /**
     * Creates a new Text object.
     *
     * @param ref  The location of the .fnt file to use.
     * @param flip Whether or not the text should be flipped.
     * @param text The text to be displayed.
     */
    public Text(String ref, boolean flip, String text)
    {
        this.bitmapFont = new BitmapFont(Gdx.files.internal(ref), flip);
        this.setText(text);
    }

    /**
     * Creates a new Text object.
     *
     * @param ref      The location of the .fnt file to use.
     * @param position The position of the text.
     */
    public Text(String ref, boolean flip, Vector2 position)
    {
        this.bitmapFont = new BitmapFont(Gdx.files.internal(ref), flip);
        this.setPosition(position);
    }

    /**
     * Creates a new Text object.
     *
     * @param ref      The location of the .fnt file to use.
     * @param position The position of the text.
     * @param scale    the scaler facter
     */
    public Text(String ref, boolean flip, float scale, Vector2 position)
    {
        this.bitmapFont = new BitmapFont(Gdx.files.internal(ref), flip);
        this.setScale(scale);
        this.setPosition(position);
    }

    /**
     * Creates a new Text object.
     *
     * @param ref      The location of the .fnt file to use.
     * @param flip     Whether or not the text should be flipped.
     * @param position The position of the text.
     * @param text     The text to be displayed.
     */
    public Text(String ref, boolean flip, float scale, Vector2 position, String text)
    {
        bitmapFont = new BitmapFont(Gdx.files.internal(ref), flip);

        this.setScale(scale);
        this.setPosition(position);
        this.setText(text);
    }

    /**
     * Draws the text on the world.
     *
     * @param spriteBatch The spritebatch to use for drawing.
     * @param ppuX        Pixels per unit on the x axis.
     * @param ppuY        Pixels per unit on the y axis.
     */
    public void render(SpriteBatch spriteBatch, float ppuX, float ppuY)
    {
        this.bitmapFont.draw(spriteBatch, text, getPosition().x * ppuX, getPosition().y * ppuY);
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
     * Returns the x scale of the text
     *
     * @return the x scale of the text;
     */
    public float getScaleX()
    {
        return bitmapFont.getScaleX();
    }

    /**
     * Returns the y scale of the text
     *
     * @return the y scale of the text;
     */
    public float getScaleY()
    {
        return this.bitmapFont.getScaleY();
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
     * Returns the width of the text in pixels.
     *
     * @return The width of the text in pixels.
     */
    public float getPixelWidth()
    {
        return bitmapFont.getBounds(text).width;
    }

    /**
     * Returns the height of the text in pixels.
     *
     * @return The height of the text in pixels.
     */
    public float getPixelHeight()
    {
        return bitmapFont.getBounds(text).height;
    }

    /**
     * Returns the width of the text in pixels.
     *
     * @param screenWidth The width of the screen in pixels.
     * @return The width of the text in pixels.
     */
    public float getRelativeWidth(float screenWidth)
    {
        return (bitmapFont.getBounds(text).width / screenWidth) * WorldRenderer.CAMERA_WIDTH;
    }

    /**
     * Returns the height of the text in pixels.
     *
     * @param screenHeight The height of the screen in pixels.
     * @return The height of the text in pixels.
     */
    public float getRelativeHeight(float screenHeight)
    {
        return (bitmapFont.getBounds(text).height / screenHeight) * WorldRenderer.CAMERA_HEIGHT;
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
     * Sets the scale of the text.
     *
     * @param scale The new scale of the text.
     */
    public void setScale(float scale)
    {
        this.bitmapFont.setScale(scale);
    }

    /**
     * Sets the scale of the text.
     *
     * @param scaleX The new x scale of the text.
     * @param scaleY The new y scale of the text.
     */
    public void setScale(float scaleX, float scaleY)
    {
        this.bitmapFont.setScale(scaleX, scaleY);
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
