package com.asierso.vortexengine.objects;

import com.asierso.vortexengine.miscellaneous.interfaces.Transform;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Font;
import org.jsfml.system.Vector2f;
import org.jsfml.graphics.Text;

/**
 * Used to create a Text like a GameObject
 * @author Asierso
 */
public class TextMesh extends GameObject implements Transform {

    //Text content
    private String textString = "";
    
    //Text modifiers
    private final Font font = new Font();
    private int fontSize = 10;
    
    //Void SFML text
    private final Text t = new Text();

    /**
     * Initialize TextMesh
     */
    public TextMesh() {

    }

    /**
     * Initialize the TextMesh in specified position
     *
     * @param position Represents the position Vector of the object
     * @param text Content of the TextMesh
     */
    public TextMesh(Vector2f position, String text) {
        this.setPosition(position);
        textString = text;
    }

    /**
     * Initialize the TextMesh in specified position (without define a Vector
     * class)
     *
     * @param x Represents the x position of the object
     * @param y Represents the y position of the object
     * @param text Content of the TextMesh
     */
    public TextMesh(float x, float y, String text) {
        this.setPosition(x, y);
        textString = text;
    }

    /**
     * Get the SFML font object (modify reasons)
     *
     * @return SFML font
     */
    public final Font getFont() {
        return font;
    }

    /**
     * Set the TextMesh font size
     *
     * @param fontSize Number of font size
     */
    public final void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Get the TextMesh font size
     *
     * @return Number of font size
     */
    public final int getFontSize() {
        return fontSize;
    }

    /**
     * Set the text string
     *
     * @param text String with the content to show in TextMesh
     */
    public final void setText(String text) {
        textString = text;
    }

    /**
     * Get the text string
     *
     * @return String with the text content
     */
    public final String getText() {
        return textString;
    }

    /**
     * Render the TextMesh in the specified Window
     *
     * @param win The Window where render the text
     */
    @Override
    protected void render(Window win) {
        //Update text properties
        t.setCharacterSize(fontSize);
        t.setString(textString);
        t.setFont(font);
        t.setPosition(this.getPosition());
        t.setRotation(this.getRotation());
        t.setColor(this.getColor());

        //Render text
        win.getRender().draw(t);
    }
}
