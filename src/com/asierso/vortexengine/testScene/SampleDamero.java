/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.testScene;

import com.asierso.vortexengine.sceneObjects.GameObject;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

/**
 *
 * @author asier
 */
public class SampleDamero extends GameObject {

    @Override
    public void render(Window win) {
        int k = 0;
        for (int i = Math.round(this.getPosition().x); i < Math.round(this.getPosition().x + this.getBoxSize().x) / 20; i++) {
            for (int j = Math.round(this.getPosition().y); j < Math.round(this.getPosition().y + this.getBoxSize().y) / 20; j++) {
                var rect = new RectangleShape();
                rect.setPosition(i * 20, j * 20);
                rect.setSize(new Vector2f(20, 20));
                if(k%2==0)
                    rect.setFillColor(this.getColor());
                else
                    rect.setFillColor(Color.BLACK);
                win.getRender().draw(rect);
                k++;

            }
            k++;
        }
    }
}
