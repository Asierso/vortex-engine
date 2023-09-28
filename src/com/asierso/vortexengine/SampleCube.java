/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine;

import com.asierso.vortexengine.gameComponents.GameObject;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 *
 * @author asier
 */
public class SampleCube extends GameObject {

    private Color color = Color.WHITE;

    @Override
    public void render(RenderWindow render) {
        var rect = new RectangleShape();
        rect.setPosition(this.getPosition());
        rect.setSize(new Vector2f(this.getBoxSize().x, this.getBoxSize().y));
        rect.setFillColor(color);
        render.draw(rect);
    }

    public void setCubeColor(Color color) {
        this.color = color;
    }

    public Color getCubeColor() {
        return color;
    }
}
