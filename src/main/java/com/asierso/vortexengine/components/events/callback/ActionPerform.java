package com.asierso.vortexengine.components.events.callback;

import com.asierso.vortexengine.objects.GameObject;

/**
 * Run action if condition is true
 * @author Asierso
 */
public interface ActionPerform {
    /**
     * Run action method when action defined is true
     * @param handle Handled GameObject
     */
    public void run(GameObject handle);
}
