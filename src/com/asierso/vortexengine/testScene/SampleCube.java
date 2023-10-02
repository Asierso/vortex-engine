/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.testScene;

import com.asierso.vortexengine.sceneObjects.GameObject;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;

/**
 *
 * @author asier
 */
public class SampleCube extends GameObject {

    @Override
    public void render(Window win) {
        var rect = new RectangleShape();
        rect.setPosition(this.getPosition());
        rect.setSize(new Vector2f(this.getBoxSize().x, this.getBoxSize().y));
        rect.setFillColor(this.getColor());
        win.getRender().draw(rect);
    }
}
