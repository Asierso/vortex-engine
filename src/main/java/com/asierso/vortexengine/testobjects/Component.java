package com.asierso.vortexengine.testobjects;

import com.asierso.vortexengine.objects.GameObject;

/**
 * Simple component definition. This interface allow to define "GameObject" modification
 * Components are renderer when GameObject is instantiated
 * @author sobblaas
 */
public interface Component {
    /**
     * Component run method
     * @param target handled GameObject
     */
    void run(GameObject target);
}
