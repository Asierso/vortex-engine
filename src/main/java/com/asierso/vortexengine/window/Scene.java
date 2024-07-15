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
    void start();

    /**
     * Executes every window render cycle
     *
     * @param context Takes window to draw. Window is taken as draw context
     * @param events Takes window events
     */
    void update(Window context, Iterable<Event> events);

    /**
     * Executes at window is going to close
     */
    void close();
}
