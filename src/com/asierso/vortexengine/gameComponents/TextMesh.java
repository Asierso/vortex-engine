/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.gameComponents;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.graphics.Text;

/**
 *
 * @author asier
 */
public class TextMesh extends GameObject {

    private String textString = "";
    private Font font = new Font();
    private Color color = Color.WHITE;
    private int fontSize = 10;
    private Text t = new Text();

    public TextMesh() {

    }

    public TextMesh(Vector2f position, String text) {
        this.setPosition(position);
        textString = text;
    }

    public TextMesh(float x, float y, String text) {
        this.setPosition(x, y);
        textString = text;
    }

    public final Font getFont() {
        return font;
    }

    public final void setColor(Color color) {
        this.color = color;
    }

    public final void getColor(Color color) {
        this.color = color;
    }

    public final void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public final int getFontSize() {
        return fontSize;
    }

    public final void setText(String text) {
        textString = text;
    }

    public final String getText() {
        return textString;
    }

    @Override
    public void render(RenderWindow render) {
        t.setCharacterSize(fontSize);
        t.setString(textString);
        t.setFont(font);
        render.draw(t);
    }
}
