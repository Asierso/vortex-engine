/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.asierso.vortexengine.window;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.event.Event;

/**
 * The scene contains the main execution methods of the window where the
 * diferent GameObjects can be instantiated, handled and rendered
 *
 * @author Asierso
 */
public class BaseScene {

    /**
     * Executes where window is showed
     */
    public void start() {
    }

    /**
     * Executes every window render cycle
     *
     * @param window Takes window to draw
     * @param events Takes window events
     */
    public void update(Window window, Iterable<Event> events) {
    }

    /**
     * Executes at window is going to close
     */
    public void close() {
    }
}
