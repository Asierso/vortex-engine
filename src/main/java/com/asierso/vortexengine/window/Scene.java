package com.asierso.vortexengine.window;

import org.jsfml.window.event.Event;

/**
 * The scene contains the main execution methods of the window where the
 * different GameObjects can be instantiated, handled and rendered
 *
 * @author Asierso
 */
public interface Scene {

    /**
     * Executes where window is showed
     */
    public void start();

    /**
     * Executes every window render cycle
     *
     * @param window Takes window to draw
     * @param events Takes window events
     */
    public void update(Window window, Iterable<Event> events);

    /**
     * Executes at window is going to close
     */
    public void close();
}
