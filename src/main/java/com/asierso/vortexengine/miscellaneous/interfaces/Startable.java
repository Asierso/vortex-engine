package com.asierso.vortexengine.miscellaneous.interfaces;

/**
 * Default interface for objects that have a controllable running status
 * @author Asierso
 */
public interface Startable {
    /**
     * Begins with execution
     */
    void start();
    /**
     * Stops execution
     */
    void stop();
}
