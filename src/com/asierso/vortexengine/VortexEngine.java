/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.asierso.vortexengine;

import com.asierso.vortexengine.window.RenderRuntime;
import com.asierso.vortexengine.window.Window;
import org.jsfml.graphics.*;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

/**
 *
 * @author asier
 */
public class VortexEngine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Window w = new Window(300, 400);
        w.setTitle("Hola Mundo");
        w.setRenderRuntime(new RenderRuntime() {
            @Override
            public void start() {

            }

            @Override
            public void update(RenderWindow rw, Iterable<Event> events) {
                var rect = new RectangleShape();
                rect.setPosition(0,0);
                rect.setSize(new Vector2f(50,50));
                rect.setFillColor(Color.BLUE);
                rw.draw(rect);
            }
        });
        w.instantiate();
    }

}
