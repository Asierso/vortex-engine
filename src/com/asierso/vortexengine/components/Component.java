package com.asierso.vortexengine.components;

import com.asierso.vortexengine.objects.GameObject;

/**
 * Simple component definition. This interface allow to define "GameObject" modification
 * Components are renderer when GameObject is instantiated
 * @author sobblaas
 */
public interface Component {
    void run(GameObject target);
}
